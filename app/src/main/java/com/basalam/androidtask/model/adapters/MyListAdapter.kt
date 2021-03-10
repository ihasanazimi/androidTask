package com.basalam.androidtask.model.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.basalam.androidtask.R
import com.basalam.androidtask.databinding.RowListBinding
import com.basalam.androidtask.model.MyPojo
import com.bumptech.glide.Glide
import java.util.*

class MyListAdapter(var list: ArrayList<MyPojo>, private val callBack: RecyclerViewEventListener) :
    RecyclerView.Adapter<MyListAdapter.VH>() {

    private lateinit var item: RowListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        item = DataBindingUtil.inflate<RowListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_list,
            parent,
            false
        )
        return VH(item)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        // binding to rows
        holder.bind(list[position])

        // item click listener
        holder.itemView.setOnClickListener {
            callBack.onClickItem(list[position] , position)
        }
    }

    override fun getItemCount(): Int {
        return list.size //return item count
    }


    fun addItem(pojo: MyPojo) {
        list.add(0, pojo)
        notifyItemInserted(0)
    }


    fun deleteItem(pojo: MyPojo) {
        for (i in 0 until list.size) {
            if (list[i].id == pojo.id) {
                list.remove(list[i])
                notifyItemRemoved(i)
                break
            }
        }
    }


    fun setNewList(newList: List<MyPojo>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }


    fun updateItem(pojo: MyPojo){
        for (i in 0 until list.size){
            if (list[i].id == pojo.id) {
                list.set(i , pojo)
                notifyItemChanged(i)
                break
            }
        }
    }


    inner class VH(binding: RowListBinding) : RecyclerView.ViewHolder(binding.root){

        private val titleTv : TextView = binding.title
        private val descriptionTv : TextView = binding.description
        private val imageView : ImageView = binding.imageView


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(pojo: MyPojo){
            titleTv.text = pojo.title
            descriptionTv.text = pojo.description
            Glide.with(imageView.context).load(pojo.imageUrl)
                .placeholder(imageView.context.resources.getDrawable(R.drawable.ic_picture,null)).into(imageView)
        }

    }


    interface RecyclerViewEventListener {
        fun onClickItem(pojo: MyPojo , position: Int)
    }

}