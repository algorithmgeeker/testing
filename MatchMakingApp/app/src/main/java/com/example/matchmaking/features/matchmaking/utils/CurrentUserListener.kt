package com.example.matchmaking.features.matchmaking.utils

/**
 * Used to get Click Event for Accept and Reject
 */
interface CurrentUserListener {
    fun accept(uuid : String)
    fun decline(uuid : String)
}