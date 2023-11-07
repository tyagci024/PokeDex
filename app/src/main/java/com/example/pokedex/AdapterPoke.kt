package com.example.pokedex

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.FragmentHomeListPageBinding
import com.example.pokedex.databinding.ItemLayoutBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.view.HomeListPageDirections

class AdapterPoke(var list : List<Pokemon>) :RecyclerView.Adapter<AdapterPoke.PokemonHolder>(){
    class PokemonHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPoke.PokemonHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: AdapterPoke.PokemonHolder, position: Int) {
        val currentPoke = list[position]
        holder.binding.pokemon=currentPoke
        val imageView = holder.binding.pokemonImage // ImageView öğesini alın
        val imageUrl = currentPoke.img
        println("pokepoke "+position)
        holder.itemView.setOnClickListener {
          val action = HomeListPageDirections.actionHomeListPageToDetailListPage(currentPoke)
            holder.itemView.findNavController().navigate(action)
        }

        holder.binding.pokemonImage.downloadfromURL(imageUrl)

        //////////////////

       /* Glide.with(holder.itemView)
            .load(imageUrl)
            .into(imageView)

        holder.binding.executePendingBindings()*/
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateList(newList: List<Pokemon>) {
        list = newList
        notifyDataSetChanged()
    }

}