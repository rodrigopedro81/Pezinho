package com.entities.autoComplete

data class AutoCompleteResponse(
    val features: List<Feature>,
    val query: Query,
    val type: String
)