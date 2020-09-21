package com.mes.inflight_mag.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mes.inflight_mag.data.repository.IssueRepo

class IssueViewModel@ViewModelInject constructor (
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val issueRepo: IssueRepo,
    ) : ViewModel()  {
}