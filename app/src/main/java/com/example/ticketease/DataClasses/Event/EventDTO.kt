package com.example.ticketease.DataClasses.Event

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    @JsonProperty("id")
    val id: Long? = null,
    @JsonProperty("placeTimeId")
    var placeTimeId: Long? = null,
    @JsonProperty("organizerId")
    var organizerId: Long? = null,
    @JsonProperty("name")
    val name: String="",
    @JsonProperty("genre")
    val genre: String ="",
    @JsonProperty("type")
    val type: String = "",
    @JsonProperty("status")
    val status: String = StatusEvent.CREATE.toString(),
    @JsonProperty("nameGroup")
    val nameGroup: String? = null,
    @JsonProperty("description")
    val description: String? = null)

@Serializable
data class EventId(
    @JsonProperty("id")
    val id : Long
)

