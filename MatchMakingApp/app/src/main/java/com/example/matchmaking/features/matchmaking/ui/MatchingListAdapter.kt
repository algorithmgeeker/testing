package com.example.matchmaking.features.matchmaking.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.matchmaking.R
import com.example.matchmaking.databinding.RvItemEmptyBinding
import com.example.matchmaking.databinding.RvItemLoadMoreBinding
import com.example.matchmaking.databinding.RvItemMatchMakingBinding
import com.example.matchmaking.features.matchmaking.db.entity.UserEntity
import com.example.matchmaking.features.matchmaking.utils.CurrentUserListener
import com.example.matchmaking.features.matchmaking.utils.LoadData
import com.squareup.picasso.Picasso

class MatchingListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val EMPTY_VIEW = 1;
    private val NO_MORE_VIEW = 2;
    private val MATCHING_CARD_VIEW = 3;
    private val userList = mutableListOf<UserEntity?>()
    private lateinit var listener: CurrentUserListener
    private lateinit var loadMoreListener: LoadData
    private lateinit var picasso: Picasso

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == MATCHING_CARD_VIEW)
            MatchingListViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.rv_item_match_making, parent, false
                ), picasso
            )
        else if (viewType == NO_MORE_VIEW)
            MatchingNoDataViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.rv_item_load_more, parent, false
                )
            )
        else
            MatchingEmptyViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.rv_item_empty, parent, false
                )
            )


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == MATCHING_CARD_VIEW)
            if(holder is MatchingListViewHolder)
                holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size + 1
    }

    fun setImageLib(picasso: Picasso) {
        this.picasso = picasso
    }

    fun setItemListener(listener: CurrentUserListener) {
        this.listener = listener
    }

    fun setLoadDataListener(listener: LoadData) {
        this.loadMoreListener = listener
    }

    fun clearAndSetUsers(userList: List<UserEntity?>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) =
        if (userList.size > 0 && position < userList.size)
            MATCHING_CARD_VIEW
        else if (userList.size > 0)
            NO_MORE_VIEW
        else
            EMPTY_VIEW

    inner class MatchingListViewHolder(
        val binding: RvItemMatchMakingBinding,
        val picasso: Picasso
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserEntity?) {
            binding.item = data
            binding.fabAccept.setOnClickListener {
                listener.accept(data?.id ?: "NA")
            }
            binding.fabReject.setOnClickListener {
                listener.decline(data?.id ?: "NA")
            }
            picasso.load(data?.pictureUrl)
                .fit()
                .into(binding.imgProfile)
        }
    }

    inner class MatchingNoDataViewHolder(val binding: RvItemLoadMoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnLoadMore.setOnClickListener {
                loadMoreListener.onLoadMore()
            }
        }
    }

    inner class MatchingEmptyViewHolder(val binding: RvItemEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnLoadEmpty.setOnClickListener {
                loadMoreListener.onLoadMore()
            }
        }
    }
}