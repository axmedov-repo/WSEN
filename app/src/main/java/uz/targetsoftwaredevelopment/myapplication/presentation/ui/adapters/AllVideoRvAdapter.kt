package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.myapplication.databinding.AllVideoRvItemBinding

class AllVideoRvAdapter(val context: Context, var listener: OnItemClickListener) :
    ListAdapter<VideoData, AllVideoRvAdapter.MyVideoHolder>(MyDiffUtil) {
    var selectHelp = false

    inner class MyVideoHolder(private val allVideoRvItemBinding: AllVideoRvItemBinding) :
        RecyclerView.ViewHolder(allVideoRvItemBinding.root) {

        init {
            allVideoRvItemBinding.playImg.setOnClickListener {
                listener.onItemClick(getItem(absoluteAdapterPosition))
            }

            allVideoRvItemBinding.shareVideoImg.setOnClickListener {
                listener.onShareClick(getItem(absoluteAdapterPosition))
            }

            allVideoRvItemBinding.threeDotsTv.setOnClickListener {
                val popupMenu: PopupMenu = PopupMenu(context, allVideoRvItemBinding.threeDotsTv)
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

            allVideoRvItemBinding.unlikeVideoImg.setOnClickListener {
                selectHelp = if (selectHelp) {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart_unlike)
                    false
                } else {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.ic_heart)
                    true
                }
            }
        }

        fun onBind(videoData: VideoData) {
            allVideoRvItemBinding.apply {
                Glide.with(context)
                    .load(videoData.preload_img)
                    .centerCrop()
                    .placeholder(R.drawable.default_profile_img)
                    .into(accountImg)

//                accountNameTv.text = videoData.owner
                dateTv.text = videoData.created_at
            }

            allVideoRvItemBinding.unlikeVideoImg.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.com
                )
            )
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<VideoData>() {
        override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVideoHolder {
        return MyVideoHolder(
            AllVideoRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyVideoHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnItemClickListener {
        fun onItemClick(videoData: VideoData)
        fun onShareClick(videoData: VideoData)
        fun onMenuClick(videoData: VideoData)
    }
}