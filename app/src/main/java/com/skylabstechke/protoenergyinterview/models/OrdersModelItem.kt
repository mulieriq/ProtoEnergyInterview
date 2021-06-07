package com.skylabstechke.protoenergyinterview.models


import com.google.gson.annotations.SerializedName

data class OrdersModelItem(
    @SerializedName("batchNumber")
    val batchNumber: String?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("creatorUserEmail")
    val creatorUserEmail: String?,
    @SerializedName("customerCode")
    val customerCode: String?,
    @SerializedName("customerName")
    val customerName: String?,
    @SerializedName("dateCreated")
    val dateCreated: String?,
    @SerializedName("dateModified")
    val dateModified: String?,
    @SerializedName("deliveryPointCode")
    val deliveryPointCode: String?,
    @SerializedName("deliveryPointName")
    val deliveryPointName: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("modifiedBy")
    val modifiedBy: String?,
    @SerializedName("modifierUserEmail")
    val modifierUserEmail: Any?,
    @SerializedName("orderTotal")
    val orderTotal: Int?,
    @SerializedName("remarks")
    val remarks: String?,
    @SerializedName("rowNumber")
    val rowNumber: Int?,
    @SerializedName("salesAreaCode")
    val salesAreaCode: String?,
    @SerializedName("salesAreaName")
    val salesAreaName: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("userPhoneNumber0")
    val userPhoneNumber0: String?
)