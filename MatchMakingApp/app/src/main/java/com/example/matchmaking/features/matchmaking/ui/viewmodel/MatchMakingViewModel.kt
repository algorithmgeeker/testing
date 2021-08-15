package com.example.matchmaking.features.matchmaking.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import com.example.matchmaking.R
import com.example.matchmaking.base.BaseViewModel
import com.example.matchmaking.constants.DatabaseConstants.ACCEPT
import com.example.matchmaking.constants.DatabaseConstants.ALL
import com.example.matchmaking.constants.DatabaseConstants.DECLINE
import com.example.matchmaking.constants.DatabaseConstants.PENDING
import com.example.matchmaking.constants.NavigationConstants
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity
import com.example.matchmaking.features.matchmaking.ui.repository.MatchMakingRepository
import com.example.matchmaking.features.matchmaking.ui.MatchingListAdapter
import com.example.matchmaking.features.matchmaking.utils.CurrentUserListener
import com.example.matchmaking.features.matchmaking.utils.LoadData
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MatchMakingViewModel @Inject constructor() : BaseViewModel() {
    @Inject lateinit var picasso: Picasso
    @Inject lateinit var repository: MatchMakingRepository

    val adapter = MatchingListAdapter()
    val selectedType = ObservableInt(ALL)

    val listener = object : CurrentUserListener {
        override fun accept(uuid: String) = repository.updateUserStatus(ACCEPT, uuid)

        override fun decline(uuid: String) = repository.updateUserStatus(DECLINE, uuid)
    }

    val loadMoreListener = object : LoadData {
        override fun onLoadMore() {
            if(selectedType.get() == PENDING || selectedType.get() == ALL)
                fetchUserData()
            else {
                message.what = NavigationConstants.SHOW_TOAST_MSG
                message.obj = "Please Load Data from All/Pending Page"
                singleLiveEvent.value = message
            }
        }
    }

    fun fetchUserData() = repository.getDataFromApi()

    fun getUserData() : LiveData<List<UserEntity>> {
        repository.filterUserData(selectedType.get())
        return repository.getUserData()
    }

    fun setUserData(userList: List<UserEntity>) = adapter.clearAndSetUsers(userList)

    fun onOptionClick(view : View) {
        when(view.id) {
            R.id.tv_all -> {
                selectedType.set(ALL)
                repository.filterUserData(ALL)
            }
            R.id.tv_pending -> {
                selectedType.set(PENDING)
                repository.filterUserData(PENDING)
            }
            R.id.tv_accepted -> {
                selectedType.set(ACCEPT)
                repository.filterUserData(ACCEPT)
            }
            R.id.tv_decline -> {
                selectedType.set(DECLINE)
                repository.filterUserData(DECLINE)
            }
        }
    }

    override fun onCleared() {
        repository.dispose()
        super.onCleared()
    }
}