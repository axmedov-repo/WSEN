package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.ContributeVideosItemBinding

class ContributeVideosAdapter(val listener : OnWishItemTouchListener):
    ListAdapter<VideoData , ContributeVideosAdapter.MyViewHolder>(MyDiffUtil) {

    inner class MyViewHolder(private val contributeVideosItemBinding : ContributeVideosItemBinding):
        RecyclerView.ViewHolder(contributeVideosItemBinding.root) {

        init {
            contributeVideosItemBinding.apply {
                wishImageCv.setOnClickListener {
                    listener.onPostClick(getItem(absoluteAdapterPosition))
                }

                wishVideoImg.setOnClickListener {
                    listener.onWishClick(getItem(absoluteAdapterPosition))
                }
            }
        }

        fun onBind(videoData : VideoData) {
            itemView.animation =
                AnimationUtils.loadAnimation(itemView.context , R.anim.animation_one)

            contributeVideosItemBinding.apply {
                Glide.with(wishImageView.context)
                    .load(videoData.preload_img)
                    .centerCrop()
                    .placeholder(R.drawable.default_profile_img)
                    .into(wishImageView)

                wishTitleTv.text = videoData.title
                wishAddressTv.text = videoData.location

//                if (videoData.is_liked_by_currentUser) {
//                    wishVideoImg.setImageResource(R.drawable.ic_heart)
//                } else {
//                    wishVideoImg.setImageResource(R.drawable.ic_heart_unlike)
//                }
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

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyViewHolder {
        return MyViewHolder(
            ContributeVideosItemBinding.inflate(
                LayoutInflater.from(parent.context) ,
                parent ,
                false
            )
        )
    }

    override fun onBindViewHolder(holder : MyViewHolder , position : Int) {
        holder.onBind(getItem(position))
    }

    interface OnWishItemTouchListener {
        fun onWishClick(videoData : VideoData)
        fun onPostClick(videoData : VideoData)
    }
}