package com.example.database.form

data class AddressesForm(
    val addressID: String,
    val locationName: String,
    val city: String,
    val province: String,
    val postalNumber: String,
    val streetName: String
)

