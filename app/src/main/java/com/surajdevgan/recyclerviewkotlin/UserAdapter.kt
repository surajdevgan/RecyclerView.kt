package com.surajdevgan.recyclerviewkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(
    private  val userModalArrayList: ArrayList<UserModel>,
    private val context: Context

    ) :
    RecyclerView.Adapter<UserAdapter.holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_rv_item, parent, false)
        return holder(view)
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        val  userModal   = userModalArrayList[position]

        holder.firstNameTV.text = userModal.first_name
        holder.lastNameTV.text = userModal.last_name
        holder.emailTV.text = userModal.email
        // on below line we are loading our image
        // from url in our image view using glide.
        Glide.with(context).load(userModal.avatar).into(holder.userIV)
    }

    override fun getItemCount(): Int {
        return userModalArrayList.size

    }

    class holder(itemView: View) : RecyclerView.ViewHolder (itemView){

        val  firstNameTV : TextView = itemView.findViewById(R.id.idTVFirstName)
        val  lastNameTV : TextView = itemView.findViewById(R.id.idTVLastName)
        val  emailTV : TextView = itemView.findViewById(R.id.idTVEmail)
        val  userIV : ImageView = itemView.findViewById(R.id.idIVUser)


    }



}