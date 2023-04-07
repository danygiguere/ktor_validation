package com.example.services

import io.ktor.server.application.*
import io.ktor.server.request.*

object I18n {

    enum class Locale(val shortName: String) {
        ENGLISH("en"),
        FRENCH("fr")
    }

    fun getLocale(call: ApplicationCall): Locale = when (call.request.acceptLanguage()) {
        "fr" -> Locale.FRENCH
        else -> Locale.ENGLISH
    }

}