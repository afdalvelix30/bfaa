package com.dicoding.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.githubuserapp.model.DataUser
import com.dicoding.githubuserapp.databinding.ListItemUsersBinding

class ListDataUserAdapter() : RecyclerView.Adapter<ListDataUserAdapter.ListUserViewHolder>(){
    private val listUser = ArrayList<DataUser>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<DataUser>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListUserViewHolder {
        val binding = ListItemUsersBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(listViweHolder: ListUserViewHolder, position: Int) {
        listViweHolder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListUserViewHolder(private val binding: ListItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataUser: DataUser) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(dataUser)
            }
            with(binding){
                Glide.with(itemView.context)
                    .load(dataUser.avatar)
                    .apply(RequestOptions().override(55,55))
                    .into(imgAvatar)

                txtVUsername.text = dataUser.username
                tvId.text = "ID : ${dataUser.id}"

            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data : DataUser)
    }
}