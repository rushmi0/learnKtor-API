package com.example.database.service

import com.example.database.fields.CustomersField
import com.example.database.forms.CustomersForm

interface CustomerService {

    suspend fun signupCustomer(form: CustomersForm): CustomersField?
    suspend fun findUserByUserName(userName: String): CustomersField?

}