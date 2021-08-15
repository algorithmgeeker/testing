package com.example.matchmaking.features.matchmaking.ui.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.matchmaking.base.BaseRepository
import com.example.matchmaking.constants.DatabaseConstants.ALL
import com.example.matchmaking.database.MatchMakingDatabase
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity
import com.example.matchmaking.features.matchmaking.utils.MatchingConverter
import com.example.matchmaking.network.MatchMakingApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MatchMakingRepository @Inject constructor(private val matchMakingApi : MatchMakingApi, private val database: MatchMakingDatabase, private val converter: MatchingConverter, private val application: Application): BaseRepository() {
    val userLiveData : LiveData<List<UserEntity>>
    val userRequestLiveData = MutableLiveData<Int>()


    init {
        // Transforming Live Data
        userLiveData = Transformations.switchMap(userRequestLiveData) {
            if(it == ALL)
                database.getUserDao().getAllUserLiveData()
            else
                database.getUserDao().getFilteredUserLiveData(it)
        }
    }

    // Getting Data from API
    fun getDataFromApi() {
        addDisposable(
            matchMakingApi.getDataFromApi(10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.results }
                .map(converter)
                .subscribe({
                    insertData(it)
                }, {
                    showToast("Something went wrong")
                })
        )
    }

    private fun insertData(userList: List<UserEntity>) {
        database.getUserDao().insertUserList(userList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    // Updating Status i.e. Accepted or Rejected
    fun updateUserStatus(status : Int, uuid : String) {
        database.getUserDao().updateUserStatus(status, uuid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getUserData() = userLiveData

    fun filterUserData(type : Int) {
        userRequestLiveData.value = type
    }

    fun showToast(msg : String) = Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
}