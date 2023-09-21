package com.example.database.tables

import org.jetbrains.exposed.sql.Table


object AddressesTable : Table("Address") {

    val addressID = varchar("AddressID", 256)
    val locationName = varchar("LocationName", 256)
    val city = varchar("City", 256)
    val province = varchar("Province", 256)
    val postalNumber = varchar("PostalNumber", 256)
    val streetName = varchar("StreetName", 256)

    override val primaryKey = PrimaryKey(addressID)
}