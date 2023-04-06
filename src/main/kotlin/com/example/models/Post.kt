package com.example.models
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int? = null,
    val title: String,
    val body: String)