package uz.targetsoftwaredevelopment.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentItemViewPagerBinding

class SliderAdapter(item:Int,val context: Context) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var selectHelp = false
    var selectSave = false

    inner class SliderViewHolder(val itemViewPagerBinding: FragmentItemViewPagerBinding) :
        RecyclerView.ViewHolder(itemViewPagerBinding.root) {
        fun onBind(item: Int){


            itemViewPagerBinding.likeVideoImg.startAnimation(AnimationUtils.loadAnimation(context,R.anim.com))
//
//            itemView.setOnClickListener {
//                listener.onItemClick(item)
//            }
//
//            itemViewPagerBinding.shareVideoImg.setOnClickListener {
//                listener.onShareClick(item)
//            }
//
//            itemViewPagerBinding.likeVideoImg.setOnClickListener {
//                listener.onItemClick(item)
//            }
//
//            itemViewPagerBinding.likeVideoImg.setOnClickListener {
//                selectHelp = if(selectHelp){
//                    itemViewPagerBinding.likeVideoImg.setImageResource(R.drawable.healthcare_unselected)
//                    false
//                }else{
//                    itemViewPagerBinding.likeVideoImg.setImageResource(R.drawable.healthcare_selected)
//                    true
//                }
//            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(FragmentItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.onBind(position)

    }

    override fun getItemCount(): Int = 10

    interface OnItemClickListener{
        fun onItemClick(item: Int)
        fun onShareClick(item: Int)
        fun onLikeClick(item: Int)
    }
}