package com.example.database.repository


import com.example.database.forms.CustomersForm
import com.example.database.service.CustomerService
import com.example.utils.BaseResponse

class CustomersRepositoryImpl(
    private val userService: CustomerService
) : CustomersRepository {

    override suspend fun signUpCustomer(form: CustomersForm): BaseResponse<Any> {
        return if (isUserNameExist(form.userName)) {
            BaseResponse.ErrorResponse(message = "User already Sign-up")
        } else {
            val user = userService.signupCustomer(form)
            if (user != null) {
                BaseResponse.SuccessResponse(data = user)
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun signInCustomer(userName: String, pass: String): BaseResponse<Any> {
        TODO("Not yet implemented")
    }

    private suspend fun isUserNameExist(userName: String): Boolean {
        return userService.findUserByUserName(userName) != null
    }

}