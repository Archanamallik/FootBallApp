package com.example.footballdetails.dataclass

data class Pagination(
    val count:Int,
    val per_page:Int,
    val current_page :Int,
    val next_page:String

    /* "pagination": {
        "count": 25,
        "per_page": 25,
        "current_page": 2,
        "next_page": "https://api.sportmonks.com/v3/core/countries?page=3",
        "has_more": true
    },*/
)