package com.mes.inflight_mag.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.Issue
import com.mes.inflight_mag.ui.main.adapter.IssueAdapter
import com.mes.inflight_mag.ui.main.viewmodel.IssueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_issues.*
import kotlinx.android.synthetic.main.tool_bar.*


@AndroidEntryPoint
class Issues : AppCompatActivity() ,   SwipeRefreshLayout.OnRefreshListener  {
    private val issueVM: IssueViewModel by viewModels()
    private lateinit var adapter: IssueAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_issues)
        issueVM .magazine = intent.extras?.getParcelable("magazine")
        issueVM.fetchIssues()
        initToolbar()
        initToolbar()
        setupUI()
        setUpObservers()


    }

    override fun onRefresh() {
       issueVM.fetchIssues()
    }


    private fun initToolbar(){
        setSupportActionBar(tool_lyt as Toolbar)
        (tool_lyt as Toolbar).setNavigationIcon(R.drawable.ic_back)
        (tool_lyt as Toolbar).setNavigationOnClickListener {
            super.onBackPressed()
        }
        supportActionBar!!.title = issueVM.magazine?.title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true);
    }

    private fun setupUI() {
        issues_swipe_refresh.setOnRefreshListener(this)
        issues_swipe_refresh.setColorSchemeColors(
            ContextCompat.getColor( this,android.R.color.holo_green_dark),
            ContextCompat.getColor(this,android.R.color.holo_red_dark)  ,
            ContextCompat.getColor(this, android.R.color.holo_blue_dark)   ,
            ContextCompat.getColor(this, android.R.color.holo_orange_dark)   )

        issue_recycler_view.apply {
            layoutManager = GridLayoutManager(this@Issues, 2)
        }
        adapter = IssueAdapter(arrayListOf())
        issue_recycler_view.addItemDecoration(
            DividerItemDecoration(
                issue_recycler_view.context,
                (issue_recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        issue_recycler_view.adapter = adapter
    }

    private fun setUpObservers(){
        issueVM.getIssues().observe(
            this,{
                    issues ->run{
                if(issues.size > 0){
                    renderList(issues)
                    issues_preview.visibility = View.GONE
                    issues_content.visibility = View.VISIBLE
                }else{
                    issues_preview.visibility = View.VISIBLE
                    issues_content.visibility = View.GONE
                }

            }
            }
        )

        issueVM.loading.observe(
            this,{
                    loading ->run{
                issues_swipe_refresh.isRefreshing = loading
            }
            }
        )
    }

    private fun renderList(issues: ArrayList<Issue>) {
        if (issues.size == 1){
            issue_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@Issues)
            }
        }
        adapter.clear()
        adapter.addData(issues)
        adapter.notifyDataSetChanged()

        adapter.onItemClick = {
                issue ->
            val newIntent = Intent(
                this, IssueDetail::class.java
            )

            newIntent.putExtra("issue", issue)
            newIntent.putExtra("magazine", issueVM.magazine)
            startActivity(newIntent)
        }
    }
}