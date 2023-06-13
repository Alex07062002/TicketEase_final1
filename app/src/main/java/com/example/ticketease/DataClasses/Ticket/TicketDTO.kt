package com.example.ticketease.DataClasses.Ticket

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class TicketDTO(
    @JsonProperty("id")
    val id: Long?=null,
    @JsonProperty("eventId")
    val eventId: Long,
    @JsonProperty("buyerId")
    val buyerId: Long? = null,
    @JsonProperty("row")
    val row: Int? = null,
    @JsonProperty("column")
    val column: Int? = null,
    @JsonProperty("status")
    val status: StatusTicket = StatusTicket.SALE,
    @JsonProperty("price")
    val price: String="",
    @JsonProperty("orderDate")
    val orderDate : Long? = null
)

@Serializable
data class TicketUpdate(
    @JsonProperty("buyerId")
    val buyerId : Long,
    @JsonProperty("eventId")
    val eventId: Long
)

@Serializable
data class TicketCountWithPrice(
    @JsonProperty("eventId")
    val eventId : Long,
    @JsonProperty("capacity")
    val capacity : Long,
    val row : Int? = null,
    val column : Int? = null,
    @JsonProperty("price")
    val price: Double)


