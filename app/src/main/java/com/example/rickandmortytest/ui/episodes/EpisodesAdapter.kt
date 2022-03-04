package com.example.rickandmortytest.ui.episodes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.rickandmortytest.R
import com.example.rickandmortytest.data.model.Episode
import com.example.rickandmortytest.databinding.ItemEpisodeBinding

class EpisodesAdapter(context: Context) :
    ListAdapter<Episode, EpisodesAdapter.EpisodeViewHolder>(EpisodeDiffItemCallback) {

    var episodeClickListener: EpisodeClickListener? = null
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodesAdapter.EpisodeViewHolder {
        return EpisodeViewHolder(layoutInflater.inflate(R.layout.item_episode, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewBinding by viewBinding(ItemEpisodeBinding::bind)

        init {
            itemView.setOnClickListener {
                episodeClickListener?.onEpisodeClicked(
                    getItem(absoluteAdapterPosition) as Episode
                )
            }
        }

        fun bind(episode: Episode) {
            with(viewBinding) {
                name.text = episode.name
                date.text = episode.airDate
            }
        }
    }

    fun sortEpisodesByName() {
        submitList(currentList.sortedBy { it.name })
    }

    fun sortEpisodesByDate() {
        submitList(currentList.sortedBy { it.airDate })
    }

    interface EpisodeClickListener {
        fun onEpisodeClicked(episode: Episode)
    }

    object EpisodeDiffItemCallback : DiffUtil.ItemCallback<Episode>() {

        override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
            return oldItem.name == newItem.name && oldItem.characters == newItem.characters
        }
    }

}