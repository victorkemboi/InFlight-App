package com.mes.inflight_mag.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.mes.inflight_mag.R
import com.mes.inflight_mag.ui.main.viewmodel.IssueDetailViewModel
import kotlinx.android.synthetic.main.activity_issue_detail.*
import kotlinx.android.synthetic.main.magazine_item_layout.view.*

class IssueDetail : AppCompatActivity() {
    private val issueDetailVM: IssueDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issue_detail)
        issueDetailVM .magazine = intent.extras?.getParcelable("magazine")
        issueDetailVM .issue = intent.extras?.getParcelable("issue")
        initToolbar()
        setUpUi()
    }
    private fun initToolbar(){
        setSupportActionBar(tool_lyt as Toolbar)
        (tool_lyt as Toolbar).setNavigationIcon(R.drawable.ic_back)
        (tool_lyt as Toolbar).setNavigationOnClickListener {
            finish()
        }
        supportActionBar!!.title = issueDetailVM.magazine?.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    private fun setUpUi(){
        issue_period.text = issueDetailVM.issue?.issuePeriod
        issue_title.text = issueDetailVM.issue?.title
        publisher.text = "  ".plus(issueDetailVM.magazine?.publisher)

        if (issueDetailVM.issue?.coverImageLink != "" || issueDetailVM.issue?.coverImageLink != "n/a" ){
            try {
                Glide.with(issue_cover.context)
                    .load(issueDetailVM.issue?.coverImageLink)
                    .error(R.drawable.ic_magazine)
                    .placeholder(R.drawable.ic_magazine)
                    .into(issue_cover)

            }catch (e: GlideException){
            }

        }
    }
}