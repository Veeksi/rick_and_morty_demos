package com.example.testapplication.domain.model

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)