package com.example.final_project_android.Model

data class ApiPropertyItem(
    val _id: String,
    val address: String,
    val area: String,
    val baths: String,
    val city: String,
    val country: String,
    val imgUrl: String,
    val ownerID: String,
    val phoneNumber: PhoneNumber,
    val price: String,
    val rooms: String,
    val title: String
) {
    data class PhoneNumber(
        val mobile: String,
        val phone: String,
        val whatsapp: String,

        )
}