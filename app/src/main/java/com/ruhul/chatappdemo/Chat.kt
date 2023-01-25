package com.ruhul.chatappdemo


data class Chat(
    val action_status: String,
    val end_broadcast: String,
    val message: String,
    val request_to_logout: String,
    val status: String,
    val type: String,
    val name: String
){
    constructor(): this("", "", "", "", "", "", "")
}