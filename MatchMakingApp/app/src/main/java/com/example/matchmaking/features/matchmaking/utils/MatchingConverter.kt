package com.example.matchmaking.features.matchmaking.utils

import com.example.matchmaking.commons.Converter
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity
import com.example.matchmaking.features.matchmaking.model.ResultsItem
import javax.inject.Inject

/**
 * Converting Data from ResultsItem to UserEntity so that we can insert that in our DB
 */
class MatchingConverter @Inject constructor() : Converter<List<ResultsItem?>?, List<UserEntity>> {

    override fun apply(apiData: List<ResultsItem?>): List<UserEntity> {
        val userList = mutableListOf<UserEntity>()
            for (item in apiData) {
                val userEntity = UserEntity(
                    id = item?.login?.uuid ?: "NA",
                    pictureUrl = item?.picture?.large,
                    name = "${item?.name?.first} ${item?.name?.last}",
                    age = item?.dob?.age ?: 0,
                    address = "${item?.location?.city} ${item?.location?.state} ${item?.location?.country}"
                )
                userList.add(userEntity)
            }
        return userList
    }

}