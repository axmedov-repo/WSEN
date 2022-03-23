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
import uz.targetsoftwaredevelopment.myapplication.databinding.AllVideoRvItemBinding

class AllVideoRvAdapter(val context: Context, var listener:OnItemClickListener):
    ListAdapter<Any,AllVideoRvAdapter.MyVideoHolder>(MyDiffUtil){

    var selectHelp = false

    inner class MyVideoHolder(private val allVideoRvItemBinding : AllVideoRvItemBinding) :
        RecyclerView.ViewHolder(allVideoRvItemBinding.root) {
        fun onBind(item: Int){

            allVideoRvItemBinding.unlikeVideoImg.startAnimation(AnimationUtils.loadAnimation(context,R.anim.com))

            allVideoRvItemBinding.playImg.setOnClickListener {
                listener.onItemClick(item)
            }

            allVideoRvItemBinding.shareVideoImg.setOnClickListener {
                listener.onShareClick(item)
            }

            allVideoRvItemBinding.threeDotsTv.setOnClickListener {
                val popupMenu:PopupMenu = PopupMenu(context,allVideoRvItemBinding.threeDotsTv)
                popupMenu.inflate(R.menu.rv_item_menu)
                popupMenu.setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_spam->{
                            listener.onMenuClick(item)
                        }
                    }
                    true
                }
                popupMenu.show()
            }


            allVideoRvItemBinding.unlikeVideoImg.setOnClickListener {
                selectHelp = if(selectHelp){
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_unselected)
                    false
                }else{
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_selected)
                    true
                }
            }

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

    override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : MyVideoHolder {
        return MyVideoHolder(AllVideoRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder : MyVideoHolder , position : Int) {
        holder.onBind(0)
    }

    interface OnItemClickListener{
        fun onItemClick(item: Int)
        fun onShareClick(item: Int)
        fun onMenuClick(item:Int)
    }

}