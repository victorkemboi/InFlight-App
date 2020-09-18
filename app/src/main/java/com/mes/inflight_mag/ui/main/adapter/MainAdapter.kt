package com.mes.inflight_mag.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mes.inflight_mag.R
import com.mes.inflight_mag.data.db.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
    fun clear(){
        users.clear()
    }
   fun getUsersNotDisplayed(list: ArrayList<User>): ArrayList<User>{
       val iterator =  list.listIterator()
       val usersNotDisplayed = arrayListOf<User>()
       while (iterator.hasNext()){
           val oldValue = iterator.next()
           if (!isUserInList(oldValue)){
               // iterator.remove()
               usersNotDisplayed.add(oldValue)
           }
       }
        return  usersNotDisplayed
    }
    private fun isUserInList(user: User): Boolean{
        val sortedDisplayedUsersList = users.sortedWith(compareBy { it.id })
        val index = sortedDisplayedUsersList.binarySearch { user.id }
        if (index>=0){
            return true
        }
        return false
    }

}