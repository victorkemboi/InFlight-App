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
import com.mes.inflight_mag.data.db.model.Magazine
import com.mes.inflight_mag.ui.main.adapter.MagazineAdapter
import com.mes.inflight_mag.ui.main.viewmodel.MagazinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_magazines.*

@AndroidEntryPoint
class Magazines : AppCompatActivity(),   SwipeRefreshLayout.OnRefreshListener   {
    private val magazineVM: MagazinesViewModel by viewModels()
    private lateinit var adapter: MagazineAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magazines)
        magazineVM .airline = intent.extras?.getParcelable("airline")
        magazineVM.fetchMagazines()
        initToolbar()
        setupUI()
        setUpObservers()
    }

    override fun onRefresh() {
        magazineVM.fetchMagazines()
    }

    private fun initToolbar(){
        setSupportActionBar(tool_lyt as Toolbar)
        (tool_lyt as Toolbar).setNavigationIcon(R.drawable.ic_back)
        (tool_lyt as Toolbar).setNavigationOnClickListener {
            //super.onBackPressed()
            finish()
        }
        supportActionBar!!.title = magazineVM.airline?.name
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }
    private fun setupUI() {
        magazines_swipe_refresh.setOnRefreshListener(this)
        magazines_swipe_refresh.setColorSchemeColors(
            ContextCompat.getColor( this,android.R.color.holo_green_dark),
            ContextCompat.getColor(this,android.R.color.holo_red_dark)  ,
            ContextCompat.getColor(this, android.R.color.holo_blue_dark)   ,
            ContextCompat.getColor(this, android.R.color.holo_orange_dark)   )

        magazine_recycler_view.apply {
            layoutManager = GridLayoutManager(this@Magazines, 2)
        }
        adapter = MagazineAdapter(arrayListOf())
        magazine_recycler_view.addItemDecoration(
            DividerItemDecoration(
                magazine_recycler_view.context,
                (magazine_recycler_view.layoutManager as LinearLayoutManager).orientation
            )
        )
        magazine_recycler_view.adapter = adapter
    }

    private fun setUpObservers(){
        magazineVM.getMagazines().observe(
            this,{
                    magazines ->run{
                if(magazines.size > 0){
                    renderList(magazines)
                    magazines_preview.visibility = View.GONE
                    magazine_content.visibility = View.VISIBLE
                }else{
                    magazines_preview.visibility = View.VISIBLE
                    magazine_content.visibility = View.GONE
                }

            }
            }
        )

        magazineVM.loading.observe(
            this,{
                    loading ->run{
                magazines_swipe_refresh.isRefreshing = loading
            }
            }
        )
    }
    private fun renderList(magazines: ArrayList<Magazine>) {
        if (magazines.size == 1){
            magazine_recycler_view.apply {
                layoutManager = LinearLayoutManager(this@Magazines)
            }
        }
        adapter.clear()
        adapter.addData(magazines)
        adapter.notifyDataSetChanged()

        adapter.onItemClick = {
                magazine ->
            val newIntent = Intent(
                this, Issues::class.java
            )

            newIntent.putExtra("magazine", magazine)
            startActivity(newIntent)
        }
    }
}