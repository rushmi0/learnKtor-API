package com.example.authentication

import com.example.database.forms.CustomersForm
import com.example.database.repository.CustomersRepository
import com.example.database.repository.CustomersRepositoryImpl
import com.example.database.service.CustomerService
import com.example.database.service.CustomerServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.authRoutes(repository: CustomersRepository) {

    routing {

//        route("api/v1") {
//            post ("/auth/signUp") {
//                val requestBody = call.receiveText()
//                println("\nReceived request body: $requestBody\n")
//                val params = call.receive<CustomersForm>()
//                println(params)
//                val result = repository.signUpCustomer(params)
//                call.respond(result.statusCode, result)
//            }
//        }

        route("api/v1") {
            post("/auth/signUp") {
                try {
                    val params = call.receive<CustomersForm>() // ใช้ call.receive แทน call.receiveOrNull
                    println("Received CustomersForm: $params")
                    val result = repository.signUpCustomer(params)
                    call.respond(result.statusCode, result)
                } catch (e: ContentTransformationException) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request body format")
                }
            }

        }

    }
}


fun Application.configureAuth() {
    val service: CustomerService = CustomerServiceImpl()
    val repository: CustomersRepository = CustomersRepositoryImpl(service)
    this.authRoutes(repository)
}