package com.example

import com.example.plugins.configureExceptions
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.plugins.registerValidation
import io.ktor.server.application.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureExceptions()
    configureRouting()
    registerValidation()
}
