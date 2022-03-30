package uz.targetsoftwaredevelopment.wsen.presentation.ui.adapters

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
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.VideoData
import uz.targetsoftwaredevelopment.wsen.databinding.MyPostsItemBinding

class MyPostsAdapter(val listener:OnPostItemTouchListener)
    :ListAdapter<VideoData,MyPostsAdapter.MyViewHolder>(MyDiffUtil) {

    inner class MyViewHolder(private val myPostsItemBinding : MyPostsItemBinding)
        :RecyclerView.ViewHolder(myPostsItemBinding.root){
        fun onBind(videoData : VideoData){
            myPostsItemBinding.apply {

                Glide.with(myPostItemImageView.context)
                    .load(videoData.preload_img)
                    .error(R.drawable.ic_place_holder)
                    .into(myPostItemImageView)

                myPostTitleTv.text = videoData.title
                myPostAddressTv.text = videoData.location

                menuTv.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(App.instance,myPostsItemBinding.menuTv)
                    popupMenu.inflate(R.menu.rv_posts_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.menu_edit_posts->{
                                listener.onMenuEdit(videoData)
                            }
                            R.id.menu_delete_posts->{
                                listener.onMenuDelete(videoData)
                            }
//                            R.id.menu_waiting->{
//                                listener.onMenuWaiting(videoData)
//                            }
//                            R.id.menu_finished->{
//                                listener.onMenuFinished(videoData)
//                            }
                        }
                        true
                    }
                    popupMenu.show()
                }
                imageCv.setOnClickListener {
                    listener.onPostClick(videoData)
                }

            }


            itemView.animation = AnimationUtils.loadAnimation(itemView.context,R.anim.animation_one)
        }

    }

    object MyDiffUtil:DiffUtil.ItemCallback<VideoData>() {
        override fun areItemsTheSame(oldItem : VideoData, newItem : VideoData) : Boolean {
            return oldItem==newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem : VideoData, newItem : VideoData) : Boolean {
            return oldItem.id==newItem.id
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        return MyViewHolder(MyPostsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.onBind(getItem(position))
    }

    interface OnPostItemTouchListener{
        fun onMenuEdit(videoData : VideoData)
        fun onMenuDelete(videoData : VideoData)
        fun onPostClick(videoData : VideoData)
//        fun onMenuWaiting(videoData : VideoData)
//        fun onMenuFinished(videoData : VideoData)
    }
}