package uz.targetsoftwaredevelopment.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentItemViewPagerBinding

class SliderAdapter(item:Int) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(itemViewPagerBinding: FragmentItemViewPagerBinding) : RecyclerView.ViewHolder(itemViewPagerBinding.root) {
        fun onBind(item: Int){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(FragmentItemViewPagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.onBind(position)

    }

    override fun getItemCount(): Int = 10
}