package com.example.database.fields

data class CustomersField(
    val CustomerID: String,
    val UserName: String,
    val ProfileImage: String,
    val AuthKey: String? = null,
    val CreateAt: String,
    val PersonID: String
)
