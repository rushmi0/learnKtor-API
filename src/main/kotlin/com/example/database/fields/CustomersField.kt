package com.example.database.fields

data class CustomersField(
    val customerID: String,
    val userName: String,
    val profileImage: String,
    val authKey: String? = null,
    val createAt: String,
    val personID: String
)
