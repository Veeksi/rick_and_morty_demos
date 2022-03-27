package com.example.testapplication.data.dto

import com.example.testapplication.domain.model.PageInfo

data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
) {
    fun toPageInfo(): PageInfo {
        return PageInfo(
            count = count,
            next = next,
            pages = pages,
            prev = prev,
        )
    }
}