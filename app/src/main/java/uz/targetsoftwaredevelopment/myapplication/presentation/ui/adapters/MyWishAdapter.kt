package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.WishPostsItemBinding

class MyWishAdapter(val context:Context, val listener:OnWishItemTouchListener)
    :ListAdapter<Any,MyWishAdapter.MyViewHolder>(MyDiffUtil) {

    inner class MyViewHolder(private val wishPostsItemBinding : WishPostsItemBinding)
        :RecyclerView.ViewHolder(wishPostsItemBinding.root){
        fun onBind(){
            wishPostsItemBinding.apply {

                wishImageCv.setOnClickListener {
                    listener.onPostClick()
                }

                wishVideoImg.setOnClickListener {
                    listener.onWishClick()
                }

            }


            itemView.animation = AnimationUtils.loadAnimation(itemView.context,R.anim.animation_one)
        }

    }

    object MyDiffUtil:DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem : Any, newItem : Any) : Boolean {
            return oldItem==newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem : Any, newItem : Any) : Boolean {
            return oldItem==newItem
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : MyViewHolder {
        return MyViewHolder(WishPostsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.onBind()
    }

    interface OnWishItemTouchListener{
        fun onWishClick()
        fun onPostClick()
    }
}