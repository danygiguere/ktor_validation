package com.example.routes

import com.example.requests.CreatePostPayload
import com.example.requests.CreatePostRequest
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.demoRoutes() {

    get("/") {
        call.respondRedirect("demo")
    }

    get("/demo") {
        call.respond(mapOf("hello" to "world"))
    }

    post("/demo/validation") {
        val request = call.receive<CreatePostPayload>()
        call.respond(request)
    }

    post("/demo/validation/custom") {
        val validatedPost = CreatePostRequest.receive(call)
        call.respond(validatedPost)
    }

}