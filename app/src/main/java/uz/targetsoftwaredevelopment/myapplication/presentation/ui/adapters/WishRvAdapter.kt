package uz.targetsoftwaredevelopment.myapplication.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.targetsoftwaredevelopment.myapplication.databinding.MyVidoesItemBinding

class WishRvAdapter:RecyclerView.Adapter<WishRvAdapter.MyViewHolder>() {

    inner class MyViewHolder(val myVideosItemBinding:MyVidoesItemBinding):RecyclerView.ViewHolder(myVideosItemBinding.root){
        fun onBind(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(MyVidoesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return 20
    }
}