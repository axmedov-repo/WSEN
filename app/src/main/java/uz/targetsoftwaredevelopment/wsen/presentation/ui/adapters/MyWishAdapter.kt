package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.WishPostsItemBinding

class MyWishAdapter(val context: Context, val listener: OnWishItemTouchListener) :
    ListAdapter<VideoData, MyWishAdapter.MyViewHolder>(MyDiffUtil) {

    inner class MyViewHolder(private val wishPostsItemBinding: WishPostsItemBinding) :
        RecyclerView.ViewHolder(wishPostsItemBinding.root) {

        init {
            wishPostsItemBinding.apply {
                wishImageCv.setOnClickListener {
                    listener.onPostClick(getItem(absoluteAdapterPosition))
                }

                wishVideoImg.setOnClickListener {
                    listener.onWishClick(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun onBind(videoData: VideoData) {
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context, R.anim.animation_one)

            wishPostsItemBinding.apply {
                Glide.with(context)
                    .load(videoData.preload_img)
                    .centerCrop()
                    .placeholder(R.drawable.default_profile_img)
                    .into(wishImageView)

                wishTitleTv.text = videoData.title
                wishAddressTv.text = videoData.location
                if (videoData.isLikedByCurrentUser) {
                    wishVideoImg.setImageResource(R.drawable.healthcare_selected)
                } else {
                    wishVideoImg.setImageResource(R.drawable.healthcare_unselected)
                }
            }
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<VideoData>() {
        override fun areItemsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: VideoData, newItem: VideoData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            WishPostsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    interface OnWishItemTouchListener {
        fun onWishClick(videoData: VideoData)
        fun onPostClick(videoData: VideoData)
    }
}