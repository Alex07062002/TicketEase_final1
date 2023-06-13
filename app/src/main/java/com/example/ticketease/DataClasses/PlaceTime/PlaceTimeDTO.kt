package com.example.ticketease.DataClasses.PlaceTime

import com.example.ticketease.DataClasses.DateSerializer
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class PlaceTimeDTO(
    @JsonProperty("id")
    var id: Long?=null,
    @JsonProperty("placeId")
    val placeId: Long? =null,
    @JsonProperty("date")
    var date: Long =0,
    @JsonProperty("status")
    var status: StatusPlaceTime? =null
    )

