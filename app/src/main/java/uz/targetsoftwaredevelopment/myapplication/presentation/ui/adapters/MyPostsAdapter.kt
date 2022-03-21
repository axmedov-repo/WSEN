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
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.MyPostsItemBinding

class MyPostsAdapter(val context:Context,val listener:OnPostItemTouchListener)
    :ListAdapter<Any,MyPostsAdapter.MyViewHolder>(MyDiffUtil) {

    inner class MyViewHolder(private val myPostsItemBinding : MyPostsItemBinding)
        :RecyclerView.ViewHolder(myPostsItemBinding.root){
        fun onBind(){
            myPostsItemBinding.apply {
                menuTv.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(context,myPostsItemBinding.menuTv)
                    popupMenu.inflate(R.menu.rv_posts_menu)
                    popupMenu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.menu_edit_posts->{
                                listener.onMenuEdit()
                            }
                            R.id.menu_delete_posts->{
                                listener.onMenuDelete()
                            }
                        }
                        true
                    }
                    popupMenu.show()
                }

                imageCv.setOnClickListener {
                    listener.onPostClick()
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
        return MyViewHolder(MyPostsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder : MyViewHolder, position : Int) {
        holder.onBind()
    }

    interface OnPostItemTouchListener{
        fun onMenuEdit()
        fun onMenuDelete()
        fun onPostClick()
    }
}