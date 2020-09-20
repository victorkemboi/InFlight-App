package com.mes.inflight_mag.data.repository.data_src

import com.mes.inflight_mag.data.api.ApiService
import com.mes.inflight_mag.data.db.dao.IssueDao
import com.mes.inflight_mag.data.db.dao.MagazineDao
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.data.repository.IssueRepo
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

class IssueDataSrc(
    private val apiService: ApiService,
    private val issueDao: IssueDao
): IssueRepo {
    override suspend fun fetchIssues(): NetworkResponse<List<Issue>, Error> {
        return apiService.getIssues()
    }

    override suspend fun getIssueCount(magazineId: String): Int {
       return  issueDao.getIssueCount(magazineId)
    }

    override suspend fun saveIssue(issue: Issue) {
        issueDao.insert(issue)
    }

    override suspend fun getIssue(issueId: String): Issue {
        return issueDao.get(issueId)
    }

    override suspend fun checkIssueExists(issueId: String): Boolean {
        return when( issueDao.checkExists(issueId)){
            0 -> false
            else -> true
        }
    }
}