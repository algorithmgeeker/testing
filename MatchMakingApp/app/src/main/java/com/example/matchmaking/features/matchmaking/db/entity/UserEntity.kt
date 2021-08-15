package com.example.matchmaking.features.matchmaking.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.matchmaking.constants.DatabaseConstants.PENDING

/**
 * Entity Class for storing data in room DB. This class is also used to show UI Data
 */
@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id : String,
    val name : String,
    val age : Int,
    val address : String,
    val pictureUrl : String?,
    val status : Int = PENDING
    )