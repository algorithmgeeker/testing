package com.example.matchmaking.features.matchmaking.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity
import io.reactivex.Completable

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserList(userList : List<UserEntity>) : Completable

    @Query("SELECT * from user_table WHERE status =:status")
    fun getFilteredUserLiveData(status : Int) : LiveData<List<UserEntity>>

    @Query("SELECT * from user_table")
    fun getAllUserLiveData() : LiveData<List<UserEntity>>

    @Query("UPDATE user_table SET status =:status WHERE id =:uuid")
    fun updateUserStatus(status : Int, uuid : String) : Completable
}