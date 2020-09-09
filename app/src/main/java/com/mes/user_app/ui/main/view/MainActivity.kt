package com.mes.user_app.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mes.user_app.R
import com.mes.user_app.data.model.core.UserB
import com.mes.user_app.ui.main.adapter.MainAdapter
import com.mes.user_app.ui.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),   SwipeRefreshLayout.OnRefreshListener  {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    override fun onRefresh() {
        setupObserver()
    }

    private fun setupUI() {
        users_swipe_refresh.setOnRefreshListener(this)
        users_swipe_refresh.setColorSchemeColors(
            ContextCompat.getColor( this,android.R.color.holo_green_dark),
            ContextCompat.getColor(this,android.R.color.holo_red_dark)  ,
            ContextCompat.getColor(this, android.R.color.holo_blue_dark)   ,
            ContextCompat.getColor(this, android.R.color.holo_orange_dark)   )

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        users_swipe_refresh.isRefreshing = true
        mainViewModel.getUsers().observe(this, {
            users -> run{
            when(users.size){
                0 ->{
                    recyclerView.visibility = View.GONE
                    users_preview.visibility = View.VISIBLE
                }
                else -> {
                    renderList(users)
                    recyclerView.visibility = View.VISIBLE
                    users_preview.visibility = View.GONE
                }
            }
            users_swipe_refresh.isRefreshing = false
        }
        })

    }

    private fun renderList(users: List<UserB>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}
