package com.mes.user_app.utils.livedata_adapter

import androidx.collection.ArrayMap
import retrofit2.Response
import java.io.IOException
import java.util.regex.Pattern

/**
 * Common class used by API responses.
 *
 * @param <T>
</T> */
class ApiResponse<T> {
    val body: T?
    val errorMessage: String?
    private val code: Int
    private val links: Map<String, String>

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
        links = emptyMap()
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    // Timber.e(ignored, "error while parsing response");
                }
            }
            if (message == null || message.trim { it <= ' ' }.length == 0) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
        val linkHeader = response.headers()["link"]
        if (linkHeader == null) {
            links = emptyMap()
        } else {
            links = ArrayMap()
            val matcher = LINK_PATTERN.matcher(linkHeader)
            while (matcher.find()) {
                val count = matcher.groupCount()
                if (count == 2) {
                    links.put(matcher.group(2), matcher.group(1))
                }
            }
        }
    }

    val isSuccessful: Boolean
        get() = code in 200..299

    val nextPage: Int?
        get() {
            val next = links[NEXT_LINK] ?: return null
            val matcher = PAGE_PATTERN.matcher(next)
            return if (!matcher.find() || matcher.groupCount() != 1) {
                null
            } else try {
                matcher.group(1)?.toInt()

            } catch (ex: NumberFormatException) {
                //Timber.w("cannot parse next page from %s", next);
                null
            }
        }

    companion object {
        private val LINK_PATTERN = Pattern
            .compile("<([^>]*)>[\\s]*;[\\s]*rel=\"([a-zA-Z0-9]+)\"")
        private val PAGE_PATTERN = Pattern.compile("page=(\\d)+")
        private const val NEXT_LINK = "next"
    }
}