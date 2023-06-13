package com.example.ticketease.DataClasses.PlaceTime

import com.fasterxml.jackson.annotation.JsonProperty
import com.ticketEase.backend.DataClasses.Place.TypeOfPlace
import kotlinx.serialization.Serializable


@Serializable
data class PlaceDTO(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("name")
    val name: String="",
    @JsonProperty("capacity")
    var capacity: Long? =null,
    @JsonProperty("numRow")

    val numRow: Int?=null,
    @JsonProperty("numColumn")

    val numColumn: Int?=null,
    @JsonProperty("location")

    val location: String="",
    @JsonProperty("city")

    val city: String=""
)

@Serializable
data class PlaceId(
    @JsonProperty("id")

    val id: Long? =null
    )

data class PlaceType(
    @JsonProperty("type")

    val type: TypeOfPlace? =null,
    @JsonProperty("city")

    val city: String? =null
    )
