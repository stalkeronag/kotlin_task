package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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

        private var emailUserTextView:TextView
            get() = field
            set(value) {
                field = value
            }

        private var viewHolderIndex:TextView
            get() = field
            set(value) {
                field = value
            }

        constructor(itemView:View) : super(itemView){

            nameUserTextView = itemView.findViewById(R.id.tv_user_name_item)
            emailUserTextView = itemView.findViewById(R.id.tv_user_email_item)
            viewHolderIndex = itemView.findViewById(R.id.tv_view_holder_user)
        }

        fun bind(user:User, position: Int)
        {
            nameUserTextView?.setText(user.name)
            emailUserTextView?.setText(user.email)
            viewHolderIndex?.setText(position.toString())
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