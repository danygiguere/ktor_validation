package com.example.requests

import com.example.plugins.UnprocessableEntityException
import com.example.services.I18n
import com.example.services.Validator
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

@Serializable
data class CreatePostPayload(
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null)

object CreatePostRequest {

    private fun authorize(call: ApplicationCall, locale: I18n.Locale) {
        // for instance: if user.id != to post.user_id, then throw 403, with localized message
    }

    private fun validate(payload:CreatePostPayload, locale: I18n.Locale): CreatePostPayload {

        val errors = mutableMapOf<String, List<String>>()

        val titleArray = payload.title?.let {
            Validator.check(
                arrayOf("en:title", "fr:titre"),
                it,
                arrayOf("minLength:2", "maxLength:4"),
                locale)
        }

        val bodyArray = payload.body?.let {
            Validator.check(
                arrayOf("en:body", "fr:contenu"),
                it,
                arrayOf("minLength:2", "maxLength:4"),
                locale)
        }

        if (titleArray != null && titleArray.isNotEmpty()) {
            errors["title"] = titleArray
        }
        if (bodyArray != null && bodyArray.isNotEmpty()) {
            errors["body"] = bodyArray
        }
        if (errors.isNotEmpty()) {
            throw UnprocessableEntityException(errors)
        }
        return payload
    }

    suspend fun receive(call: ApplicationCall): CreatePostPayload {
        val payload = call.receive<CreatePostPayload>()
        val locale = I18n.getLocale(call)
        this.authorize(call, locale)
        return this.validate(payload, locale)
    }

}