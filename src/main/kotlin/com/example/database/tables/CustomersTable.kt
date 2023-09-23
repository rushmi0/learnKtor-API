package com.example.database.tables

import com.example.database.DatabaseFactory
import com.example.database.DatabaseFactory.dbQuery
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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


suspend fun createCustomer(
    customerID: String,
    userName: String,
    profileImage: String,
    authKey: String,
    personID: String
): Boolean {
    return dbQuery {
        CustomersTable.insert {
            it[CustomersTable.customerID] = customerID
            it[CustomersTable.userName] = userName
            it[CustomersTable.profileImage] = profileImage
            it[CustomersTable.authKey] = authKey
            it[CustomersTable.personID] = personID
        }
        true
    }
}


suspend fun main() {
    DatabaseFactory.initialize()

    // ใช้ runBlocking หรือ coroutine scope เพื่อเรียกใช้ suspend function ใน coroutine
    runBlocking {
        val success = createCustomer("1150", "test 1", "profile.jpg", "abc123", "2")

        // ตรวจสอบว่าการเพิ่มข้อมูลสำเร็จหรือไม่
        if (success) {
            println("Customer created successfully")
        } else {
            println("Failed to create customer")
        }
    }
}