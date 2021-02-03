package com.example.mobilecomphw

import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime

data class Reminder (
    val title: String,
    var isChecked: Boolean = false,
    var dateTime: LocalDateTime
)