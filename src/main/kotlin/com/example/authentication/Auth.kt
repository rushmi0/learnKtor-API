package com.example.authentication

import com.example.database.forms.CustomersForm
import com.example.database.repository.CustomersRepository
import com.example.database.repository.CustomersRepositoryImpl
import com.example.database.service.CustomerService
import com.example.database.service.CustomerServiceImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: CustomersRepository) {

    routing {
        route("api/v1") {
            post ("/auth/signUp") {
                val params = call.receive<CustomersForm>()
                val result = repository.signUpCustomer(params)
                call.respond(result.statusCode, result)
            }
        }
    }
}


fun Application.configureAuth() {
    val service: CustomerService = CustomerServiceImpl()
    val repository: CustomersRepository = CustomersRepositoryImpl(service)
    authRoutes(repository)
}