package com.example.ticketease.DataClasses.Person

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

enum class Cities (val city : String) {
    Voronezh("Воронеж"),
    Moscow("Москва"),
    SaintPetersburg("Санкт-Петербург");


}
@Serializable
data class City(
    @JsonProperty("city")
    val city: String
)