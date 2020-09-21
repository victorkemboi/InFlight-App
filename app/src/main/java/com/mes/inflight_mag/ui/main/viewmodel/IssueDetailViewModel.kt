package com.mes.inflight_mag.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.data.db.model.Magazine

class IssueDetailViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    var issue : Issue? = null
    var magazine : Magazine? = null
}