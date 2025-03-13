package com.example.recipefinder.model


data class ApiResponse(
    val results: List<User>
)

data class User(
    val name: Name,
    val email: String,
    val picture: Picture
) {
    fun fullName(): String = "${name.first} ${name.last}"
}

data class Name(
    val title: String,
    val first: String,
    val last: String
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)
