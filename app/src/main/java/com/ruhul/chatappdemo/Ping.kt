package com.ruhul.chatappdemo

data class Ping(
    val host_last_updated_at: Map<String, String>? = null,
    val device_id: String,
    val broadcast_id: String,
    val fcm_token: String,
    val status: String,

) {
    constructor() : this(null, "","", "", "")
}