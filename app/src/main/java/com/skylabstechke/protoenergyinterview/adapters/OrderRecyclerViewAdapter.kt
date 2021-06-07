package com.skylabstechke.protoenergyinterview.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.skylabstechke.protoenergyinterview.databinding.ActivityRowLayoutBinding
import com.skylabstechke.protoenergyinterview.models.OrdersModel
import com.skylabstechke.protoenergyinterview.utils.OrdersDefaultUtil
import java.text.SimpleDateFormat
import java.util.*

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


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            Locale.getDefault()
        )
        //dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val source = orders[position].dateCreated
        val vdate = dateFormat.parse(source!!)

        holder.binding.customerName.text = orders[position].customerName
        holder.binding.deliveryPoint.text = orders[position].deliveryPointName
        holder.binding.status.text = orders[position].status
        holder.binding.dateCreated.text = vdate.toString()

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