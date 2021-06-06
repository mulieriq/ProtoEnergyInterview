package com.skylabstechke.protoenergyinterview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.protoenergyinterview.databinding.ActivityRowLayoutBinding
import com.skylabstechke.protoenergyinterview.utils.OrdersDefaultUtil
import java.util.Collections.emptyList

class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.MyViewHolder>() {
    private var orders = emptyList<MockData>()

    class MyViewHolder(private val binding: ActivityRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orders: MockData) {
            binding.orderResult = orders
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ActivityRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderRecyclerViewAdapter.MyViewHolder {
        return MyViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: OrderRecyclerViewAdapter.MyViewHolder, position: Int) {
        val currentOrder = orders[position]
        holder.bind(currentOrder)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun setData(newData: List<MockData>) {
        val orderDifUtil = OrdersDefaultUtil(orders, newData)
        val diffUtilResults = DiffUtil.calculateDiff(orderDifUtil)
        diffUtilResults.dispatchUpdatesTo(this)
        orders = newData

    }
}