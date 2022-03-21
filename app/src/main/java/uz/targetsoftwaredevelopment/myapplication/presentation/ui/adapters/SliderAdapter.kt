package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.AllVideoRvItemBinding

class SliderAdapter(item: Int, val context: Context, var listener: OnItemClickListener) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    var selectHelp = false

    inner class SliderViewHolder(private val allVideoRvItemBinding: AllVideoRvItemBinding) :
        RecyclerView.ViewHolder(allVideoRvItemBinding.root) {
        fun onBind(item: Int) {

            allVideoRvItemBinding.unlikeVideoImg.startAnimation(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.com
                )
            )

            allVideoRvItemBinding.playImg.setOnClickListener {
                listener.onItemClick(item)
            }

            allVideoRvItemBinding.shareVideoImg.setOnClickListener {
                listener.onShareClick(item)
            }

            allVideoRvItemBinding.threeDotsTv.setOnClickListener {
                val popupMenu: PopupMenu = PopupMenu(context,allVideoRvItemBinding.threeDotsTv)
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
                selectHelp = if (selectHelp) {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_unselected)
                    false
                } else {
                    allVideoRvItemBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_selected)
                    true
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            AllVideoRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = 10

    interface OnItemClickListener {
        fun onItemClick(item: Int)
        fun onShareClick(item: Int)
        fun onMenuClick(item:Int)
    }
}