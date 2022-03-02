package com.example.rickandmortytest.ui.characters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.rickandmortytest.R
import com.example.rickandmortytest.data.model.Character
import com.example.rickandmortytest.databinding.ItemCharacterBinding

class CharacterAdapter (context: Context) : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterDiffItemCallback) {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_character, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewBinding by viewBinding(ItemCharacterBinding::bind)

        fun bind(character: Character?) {
            with(viewBinding) {
                image.load(character?.image) {
                    crossfade(300)
                    placeholder(ColorDrawable(Color.TRANSPARENT))
                }
                name.text = character?.name
            }
        }
    }

}

private object CharacterDiffItemCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }
}