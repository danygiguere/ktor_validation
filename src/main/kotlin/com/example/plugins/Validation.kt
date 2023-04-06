package com.example.plugins

import com.example.requests.CreatePostRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import com.example.services.Validator
import io.ktor.server.request.*

fun Application.registerValidation() {

    suspend fun getLocale(call: ApplicationCall): String = when (call.request.acceptLanguage()) {
        "fr" -> "fr"
        else -> "en"
    }

    fun authorize(call: ApplicationCall, locale: String) {
        // for instance: if user.id != to post.user_id, then throw 403, with localized message
    }

    install(RequestValidation) {

//      val locale = getLocale(call)
        val locale = "fr"

        val errors = mutableMapOf<String, List<String>>()

        validate<CreatePostRequest> { req ->

//            authorize(call, locale)

            val titleArray = Validator.check(
                arrayOf("en:title", "fr:titre"),
                req.title?.toString() ?: "",
                arrayOf("minLength:2", "maxLength:4"),
                locale)

            val bodyArray = Validator.check(
                arrayOf("en:body", "fr:contenu"),
                req.body?.toString() ?: "",
                arrayOf("minLength:2", "maxLength:4"),
                locale
            )
            if (titleArray.isNotEmpty()) {
                errors["title"] = titleArray
            }
            if (bodyArray.isNotEmpty()) {
                errors["body"] = bodyArray
            }
            if (errors.isNotEmpty()) {
                throw UnprocessableEntityException(errors)
            }
            ValidationResult.Valid
        }

    }

}