package com.example.matchmaking.features.matchmaking.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmaking.features.matchmaking.ui.MatchingListAdapter
import com.squareup.picasso.Picasso


@BindingAdapter(value = ["matchingListRecyclerAdapter", "imageLib", "itemListener", "loadMoreListener"])
fun setMatchingListRecyclerAdapter(
    recyclerView: RecyclerView,
    recyclerAdapter: MatchingListAdapter,
    picasso: Picasso,
    listener: CurrentUserListener,
    loadDataListener: LoadData
) {
    val linearLayoutManager = LinearLayoutManager(
        recyclerView.context,
        LinearLayoutManager.HORIZONTAL,
        false
    )
    val snapHelper = PagerSnapHelper()
    snapHelper.attachToRecyclerView(recyclerView)
    recyclerView.layoutManager = linearLayoutManager
    recyclerAdapter.setImageLib(picasso)
    recyclerAdapter.setItemListener(listener)
    recyclerAdapter.setLoadDataListener(loadDataListener)
    recyclerView.adapter = recyclerAdapter
}
