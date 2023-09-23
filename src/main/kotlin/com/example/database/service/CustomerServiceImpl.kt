package com.example.database.service


import com.example.database.DatabaseFactory.dbQuery
import com.example.database.fields.CustomersField
import com.example.database.forms.CustomersForm
import com.example.database.tables.CustomersTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class CustomerServiceImpl : CustomerService {

    /**
     * [ INSERT DATA  ]
     * */
    override suspend fun signupCustomer(form: CustomersForm): CustomersField? {
        var statement: InsertStatement<Number>? = null
        // เริ่มกระบวนการทำงานแบบ Transaction ของฐานข้อมูล
        dbQuery {
            // สร้างและ execute INSERT statement เพื่อบันทึกข้อมูลลูกค้าใหม่ลงในฐานข้อมูล
            statement = CustomersTable.insert {
                it[customerID] = form.CustomerID
                it[userName] = form.UserName
                it[profileImage] = form.ProfileImage
                it[authKey] = form.AuthKey
                it[personID] = form.PersonID
            }
        }
        // คืนค่า CustomersField จากผลลัพธ์ของ INSERT statement
        return statement?.resultedValues?.get(0)?.let { rowCustomers(it) }
    }


    /**
     * [ SELECT DATA ]
     *  ฟังก์ชันสำหรับค้นหาข้อมูลลูกค้าจากชื่อผู้ใช้
     * */
    override suspend fun findUserByUserName(userName: String): CustomersField? {
        // ค้นหาข้อมูลลูกค้าจากชื่อผู้ใช้
        return dbQuery {
            // ค้นหาข้อมูลลูกค้าจากฐานข้อมูล
            CustomersTable.select { CustomersTable.userName.eq(userName) }
                .mapNotNull { rowCustomers(it) } // แปลงผลลัพธ์ให้เป็น `List` และกรองค่าที่ไม่ใช่ `null`
                .singleOrNull() // คืนผลลัพธ์เดียวจาก `List` หรือ `null` ถ้าไม่พบข้อมูล
        }
    }


    private fun rowCustomers(row: ResultRow?): CustomersField? {
        // ใช้ `let` จัดการผลลัพธ์ที่อาจเป็น `null` เพราะมันจะไม่ทำงานถ้า `row` เป็น `null`
        // เพื่อเข้าถึงข้อมูลใน ResultRow และสร้าง CustomersField object
        return row?.let {
            CustomersField(
                CustomerID = it[CustomersTable.customerID],
                UserName = it[CustomersTable.userName],
                ProfileImage = it[CustomersTable.profileImage],
                AuthKey = it[CustomersTable.authKey],
                CreateAt = it[CustomersTable.createAt],
                PersonID = it[CustomersTable.personID]
            )
        }
    }

}