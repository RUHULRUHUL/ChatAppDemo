package com.ruhul.chatappdemo


data class Chat(
    val status: Boolean,
    val type: String,
    val action_status: String,
    val message: String,
    val request_to_logout: String,
    val end_broadcast: String

) {
    constructor() : this(false, "", "", "", "", "")
}