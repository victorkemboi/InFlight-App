package com.mes.inflight_mag.data.repository

import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.utils.net_adapter.NetworkResponse

interface IssueRepo {
    suspend fun fetchIssues(): NetworkResponse<List<Issue>, Error>
    suspend fun getIssueCount(magazineId:String): Int
    suspend  fun saveIssue(issue: Issue)
    suspend fun getIssue(issueId:String): Issue
    suspend fun checkIssueExists(issueId: String): Boolean
    suspend fun getMagazineIssues(magId:String): List<Issue>
    suspend fun getMagazineIssueCount(magId: String): Int
}