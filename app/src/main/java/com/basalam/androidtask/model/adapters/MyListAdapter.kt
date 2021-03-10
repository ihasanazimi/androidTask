package com.basalam.androidtask.model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.basalam.androidtask.R
import com.basalam.androidtask.databinding.RowListBinding
import com.basalam.androidtask.model.MyPojo
import java.util.ArrayList

class MyListAdapter(var list: ArrayList<MyPojo>, val callBack: RecyclerViewEventListener) :
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
        item.rowItem = list[position]

        holder.itemView.setOnClickListener {
            callBack.onClickItem(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
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


    fun setList(newList: List<MyPojo>) {
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


    inner class VH(var binding: RowListBinding) : RecyclerView.ViewHolder(binding.root)


    interface RecyclerViewEventListener {
        fun onClickItem(pojo: MyPojo)
    }

}