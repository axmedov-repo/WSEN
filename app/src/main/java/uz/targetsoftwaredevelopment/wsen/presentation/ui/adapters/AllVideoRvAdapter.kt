package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.AllVideoRvItemBinding

class AllVideoRvAdapter(var listener : OnItemClickListener):
    ListAdapter<VideoData , AllVideoRvAdapter.MyVideoHolder>(MyDiffUtil) {

    inner class MyVideoHolder(private val allVideoRvItemBinding : AllVideoRvItemBinding):
        RecyclerView.ViewHolder(allVideoRvItemBinding.root) {

        init {
            allVideoRvItemBinding.playImg.setOnClickListener {
                listener.onItemClick(getItem(absoluteAdapterPosition))
            }

            allVideoRvItemBinding.shareVideoImg.setOnClickListener {
                listener.onShareClick(getItem(absoluteAdapterPosition))
            }

            allVideoRvItemBinding.threeDotsTv.setOnClickListener {
                val popupMenu : PopupMenu =
                    PopupMenu(App.instance , allVideoRvItemBinding.threeDotsTv)
                popupMenu.inflate(R.menu.rv_item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.menu_spam -> {
                            listener.onMenuClick(getItem(absoluteAdapterPosition))
                        }
                    }
                    true
                }
                popupMenu.show()
            }

        }

        fun onBind(videoData : VideoData) {
            allVideoRvItemBinding.apply {
                Glide.with(accountImg.context)
                    .load(videoData.owner?.photo)
                    .centerCrop()
                    .placeholder(R.drawable.default_profile_img)
                    .into(accountImg)

                accountNameTv.text = videoData.owner?.username
                dateTv.text = videoData.created_at

                Glide.with(itemAllVideosImg.context).load(videoData.preload_img)
                    .centerCrop()
                    .placeholder(R.drawable.ic_place_holder)
                    .into(itemAllVideosImg)

                unlikeVideoImg.startAnimation(
                    AnimationUtils.loadAnimation(
                        unlikeVideoImg.context,
                        R.anim.com
                    )
                )

                if (videoData.is_liked_by_currentUser) {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart)
                } else {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart_unlike)
                }

                allVideoRvItemBinding.unlikeVideoImg.setOnClickListener {
                    if (videoData.is_liked_by_currentUser) {
                        allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart_unlike)
                      listener.onClickLike(getItem(absoluteAdapterPosition))
                    } else {
                        allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart)
                        listener.onClickLike(getItem(absoluteAdapterPosition))
                    }
                }

                allVideosItemTitleTv.text = videoData.title
                allVideosItemAddressVideoTv.text = videoData.location
            }
        }
    }

    object MyDiffUtil:DiffUtil.ItemCallback<VideoData>() {
        override fun areItemsTheSame(oldItem : VideoData , newItem : VideoData) : Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem : VideoData , newItem : VideoData) : Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyVideoHolder {
        return MyVideoHolder(
            AllVideoRvItemBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyVideoHolder , position : Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemClickListener {
        fun onItemClick(videoData : VideoData)
        fun onShareClick(videoData : VideoData)
        fun onMenuClick(videoData : VideoData)
        fun onClickLike(videoData : VideoData)
    }
}
