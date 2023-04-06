package com.example.requests

import kotlinx.serialization.Serializable

@Serializable
data class CreatePostRequest(
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null)