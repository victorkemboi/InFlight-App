package com.mes.inflight_mag.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mes.inflight_mag.R
import com.mes.inflight_mag.utils.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var settings: SharedPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startMain()
    }


    private fun startMain() = if(settings.user != ""){
        startActivity(Intent(
            this@SplashActivity, Home::class.java)
        )
        finish()
    }else{
        startActivity(Intent(this@SplashActivity, SignIn::class.java))

        finish()
    }
}