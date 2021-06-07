package com.skylabstechke.protoenergyinterview.utils

import androidx.recyclerview.widget.DiffUtil
import com.skylabstechke.protoenergyinterview.models.OrdersModelItem

class OrdersDefaultUtil(
    private val oldOrders: List<OrdersModelItem>,
    private val newOrders: List<OrdersModelItem>,

    ) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldOrders.size
    }

    override fun getNewListSize(): Int {
        return newOrders.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrders[oldItemPosition] === newOrders[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOrders[oldItemPosition] == newOrders[newItemPosition]
    }
}