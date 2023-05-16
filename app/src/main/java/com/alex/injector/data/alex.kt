package com.alex.injector.data

import org.json.JSONArray

data class UpdateInfo(
    val version: String,
    val releaseDate: String,
    val downloadUrl: String,
    val changes: JSONArray,
    val serverstatus: String,
)
