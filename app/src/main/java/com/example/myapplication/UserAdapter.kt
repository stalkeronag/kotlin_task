package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private var userList:MutableList<User>



    constructor(users:MutableList<User>):super(){
        userList = users
    }

    class UserViewHolder : RecyclerView.ViewHolder {
        private var nameUserTextView:TextView
            get() = field
            set(value) {
                field = value
            }

       private var idUserTextView:TextView
           get() = field
           set(value) {
               field = value
           }

        private var buttonShowMore: Button

        private var context: Context

        constructor(itemView:View) : super(itemView){
            context = itemView.context
            nameUserTextView = itemView.findViewById(R.id.tv_user_name_item)
            idUserTextView = itemView.findViewById(R.id.tv_user_id_item)
            buttonShowMore = itemView.findViewById(R.id.b_show_more_info)

        }

        fun bind(user:User, position: Int)
        {
            nameUserTextView?.setText(user.first_name)
            idUserTextView?.setText(user.id)

            buttonShowMore.setOnClickListener{
                var intent = Intent(context, SecondActivity::class.java)
                intent.putExtra("user",user)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context
        val layoutIdForListUser = R.layout.user_list_item

        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(layoutIdForListUser, parent, false)


        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.bind(user, position)
    }
}