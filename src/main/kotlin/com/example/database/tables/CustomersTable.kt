package com.example.database.tables


import org.jetbrains.exposed.sql.Table

object CustomersTable : Table("Customers") {

    // กำหนดคอลัมน์ของตาราง
    val customerID = text("CustomerID")  // รหัสลูกค้า
    val userName = text("UserName").uniqueIndex()  // ชื่อผู้ใช้
    val profileImage = text("ProfileImage")  // รูปโปรไฟล์
    val authKey = text("AuthKey")  // คีย์การยืนยันตัวตน
    val createAt = text("CreateAccountDate")

    val personID = text("PersonID").references(PeopleTable.personID).uniqueIndex()

    // กำหนด Primary Key
    override val primaryKey = PrimaryKey(customerID)
}