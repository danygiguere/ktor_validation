package com.example.routes

import com.example.requests.CreatePostRequest
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.demoRoutes() {

    get("/") {
        call.respondRedirect("/demo/validation")
    }

    get("/demo") {
        call.respond(mapOf("hello" to "world"))
    }

    post("/demo/validation") {
        val validatedPost = CreatePostRequest().receive(call)
        call.respond(validatedPost)
    }

}