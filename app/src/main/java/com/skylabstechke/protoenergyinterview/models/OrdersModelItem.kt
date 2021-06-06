package com.skylabstechke.protoenergyinterview.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrdersModelItem(
    @Json(name = "batchNumber")
    val batchNumber: String?,
    @Json(name = "createdBy")
    val createdBy: String?,
    @Json(name = "creatorUserEmail")
    val creatorUserEmail: String?,
    @Json(name = "customerCode")
    val customerCode: String?,
    @Json(name = "customerName")
    val customerName: String?,
    @Json(name = "dateCreated")
    val dateCreated: String?,
    @Json(name = "dateModified")
    val dateModified: String?,
    @Json(name = "deliveryPointCode")
    val deliveryPointCode: String?,
    @Json(name = "deliveryPointName")
    val deliveryPointName: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "modifiedBy")
    val modifiedBy: String?,
    @Json(name = "modifierUserEmail")
    val modifierUserEmail: Any?,
    @Json(name = "orderTotal")
    val orderTotal: Int?,
    @Json(name = "remarks")
    val remarks: String?,
    @Json(name = "rowNumber")
    val rowNumber: Int?,
    @Json(name = "salesAreaCode")
    val salesAreaCode: String?,
    @Json(name = "salesAreaName")
    val salesAreaName: String?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "userPhoneNumber0")
    val userPhoneNumber0: String?
)