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
    val customerID = varchar("CustomerID", 256)  // รหัสลูกค้า
    val userName = varchar("UserName", 256).uniqueIndex()  // ชื่อผู้ใช้
    val profileImage = text("ProfileImage")  // รูปโปรไฟล์
    val authKey = varchar("AuthKey", 256)  // คีย์การยืนยันตัวตน

    // กำหนดคอลัมน์ 'createAt' พร้อมค่าเริ่มต้น
    val createAt = varchar("CreateAccountDate", 256)

    val personID = varchar("PersonID", 256).references(PeopleTable.personID).uniqueIndex()  // รหัสบุคคล

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
        val success = createCustomer("ๅ", "test 1", "profile.jpg", "abc123", "2")

        // ตรวจสอบว่าการเพิ่มข้อมูลสำเร็จหรือไม่
        if (success) {
            println("Customer created successfully")
        } else {
            println("Failed to create customer")
        }
    }
}