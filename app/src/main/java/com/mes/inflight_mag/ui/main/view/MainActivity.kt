package com.mes.inflight_mag.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.User
import com.mes.inflight_mag.ui.main.adapter.MainAdapter
import com.mes.inflight_mag.ui.main.viewmodel.MainViewModel
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
        airlines_swipe_refresh.setOnRefreshListener(this)
        airlines_swipe_refresh.setColorSchemeColors(
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
        airlines_swipe_refresh.isRefreshing = true
        if (adapter.itemCount > 0){
            adapter.clear()
        }
        mainViewModel.getUsers().observe(this, {
            users -> run{
            Log.d("Setup observer: ", users.size.toString())
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
            airlines_swipe_refresh.isRefreshing = false
        }
        })

    }

    private fun renderList(users: ArrayList<User>) {
        //val usersNotDisplayed = adapter.getUsersNotDisplayed(users)
        //Log.d("render list size: ", usersNotDisplayed.size.toString())
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

}
