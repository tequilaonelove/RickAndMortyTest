package com.example.rickandmortytest.ui.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.rickandmortytest.R
import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.databinding.ItemCharacterBinding

class CharacterAdapter(context: Context) :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffItemCallback) {

    var characterClickListener: CharacterClickListener? = null

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewBinding by viewBinding(ItemCharacterBinding::bind)

        init {
            itemView.setOnClickListener {
                characterClickListener?.onCharacterClicked(
                    getItem(absoluteAdapterPosition) as Character
                )
            }
        }

        fun bind(character: Character?) {
            with(viewBinding) {
                Glide.with(itemView)
                    .load(character?.image)
                    .transition(withCrossFade().crossFade(500))
                    .into(image)
                name.text = character?.name
            }
        }
    }

    interface CharacterClickListener {
        fun onCharacterClicked(character: Character)
    }

    object CharacterDiffItemCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name && oldItem.image == newItem.image
        }
    }
}