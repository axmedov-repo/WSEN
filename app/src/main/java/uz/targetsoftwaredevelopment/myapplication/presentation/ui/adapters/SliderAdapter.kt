package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentItemViewPagerBinding

class SliderAdapter(item:Int,val context: Context,var listener:OnItemClickListener) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var selectHelp = false
    var selectSave = false

    inner class SliderViewHolder(val itemViewPagerBinding: FragmentItemViewPagerBinding) :
        RecyclerView.ViewHolder(itemViewPagerBinding.root) {
        fun onBind(item: Int){


            itemViewPagerBinding.unlikeVideoImg.startAnimation(AnimationUtils.loadAnimation(context,R.anim.com))

            itemViewPagerBinding.playImg.setOnClickListener {
                listener.onItemClick(item)
            }

            itemViewPagerBinding.shareVideoImg.setOnClickListener {
                listener.onShareClick(item)
            }


            itemViewPagerBinding.unlikeVideoImg.setOnClickListener {
                selectHelp = if(selectHelp){
                    itemViewPagerBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_unselected)
                    false
                }else{
                    itemViewPagerBinding.unlikeVideoImg.setImageResource(R.drawable.healthcare_selected)
                    true
                }
            }

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
    }
}