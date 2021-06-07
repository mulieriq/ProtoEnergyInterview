package com.skylabstechke.protoenergyinterview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.protoenergyinterview.databinding.ActivityRowLayoutBinding
import com.skylabstechke.protoenergyinterview.models.OrdersModel
import com.skylabstechke.protoenergyinterview.utils.OrdersDefaultUtil

class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.MyViewHolder>() {
    private var orders = OrdersModel()

    inner class MyViewHolder(val binding: ActivityRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ActivityRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.customerName.text = orders[position].customerName
        holder.binding.deliveryPoint.text = orders[position].deliveryPointName
        holder.binding.status.text = orders[position].status
        holder.binding.dateCreated.text = orders[position].dateCreated

    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun setData(newData: OrdersModel) {
        val orderDifUtil = OrdersDefaultUtil(orders, newData)
        val diffUtilResults = DiffUtil.calculateDiff(orderDifUtil)
        diffUtilResults.dispatchUpdatesTo(this)
        orders = newData

    }
}