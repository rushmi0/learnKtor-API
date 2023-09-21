package com.example.database.repository

import com.example.database.forms.CustomersForm
import com.example.utils.BaseResponse


interface CustomersRepository {

    suspend fun signUpCustomer(form: CustomersForm): BaseResponse<Any>
    suspend fun signInCustomer(userName: String, pass: String): BaseResponse<Any>

}