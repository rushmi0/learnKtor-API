package com.example.database.tables

import org.jetbrains.exposed.sql.Table

object PeopleTable :  Table("People") {

    val personID = varchar("PersonID", 256)
    val firstName = varchar("FirstName", 256)
    val lastName = varchar("LastName", 256)
    val email = varchar("Email", 256)
    val phoneNumber = varchar("PhoneNumber", 256)
    val addressID = varchar("AddressID", 256).references(AddressesTable.addressID).nullable()

    override val primaryKey = PrimaryKey(personID)
}
