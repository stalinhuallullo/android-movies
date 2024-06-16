package peruapps.movies.presentation.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import peruapps.movies.databinding.ItemMovieBinding
import peruapps.movies.databinding.ItemShimmerBinding

class ListMoviesAdapter(private val onClickItemListener: OnClickItemListener) :
    RecyclerView.Adapter<ListMoviesAdapter.ListMoviesViewHolder>() {

    private var list: MutableList<MovieModel>? = null
    private var showInitialShimmer = false
    private var showPaginateShimmer = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMoviesViewHolder {
        return if (viewType == CELL_TYPE_MOVIE) {
            ListMoviesViewHolder(
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ListMoviesViewHolder(
                ItemShimmerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemCount() = list?.size ?: 0

    override fun onBindViewHolder(holder: ListMoviesViewHolder, position: Int) {
        list?.get(position)?.run {
            if (!isShimmer) {
                holder.bind(this)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list?.get(position)?.isShimmer == true) {
            CELL_TYPE_SHIMMER
        } else {
            CELL_TYPE_MOVIE
        }
    }

    fun addMovies(movies: MutableList<MovieModel>) {
        if (list == null || showInitialShimmer) {
            showInitialShimmer = false
            list = mutableListOf()
        }

        if (showPaginateShimmer) {
            showPaginateShimmer = false
            list?.removeAt(list?.size?.minus(1) ?: 0)
        }

        if (movies.isEmpty()) {
            notifyItemRemoved(list?.size?.minus(1) ?: 0)
        } else {
            list?.addAll(movies)
            notifyItemRangeInserted(list?.size ?: 0, movies.size)
        }
    }

    fun showShimmerData() {
        showInitialShimmer = true
        list = mutableListOf(MovieModel(isShimmer = true), MovieModel(isShimmer = true))
        notifyDataSetChanged()
    }

    fun showShimmerPaginate() {
        if (!showPaginateShimmer && !showInitialShimmer) {
            showPaginateShimmer = true
            list?.add(MovieModel(isShimmer = true))
            notifyItemInserted(list?.size ?: 0)
        }
    }

    fun onClickItem(view: View, movieModel: MovieModel) {
        onClickItemListener.onClickItem(view, movieModel)
    }

    inner class ListMoviesViewHolder : RecyclerView.ViewHolder {

        private var itemMovieBinding: ItemMovieBinding? = null
        private var itemShimmerBinding: ItemShimmerBinding? = null

        constructor(binding: ItemMovieBinding) : super(binding.root) {
            this.itemMovieBinding = binding
        }

        constructor(binding: ItemShimmerBinding) : super(binding.root) {
            this.itemShimmerBinding = binding
        }

        fun bind(movieModel: MovieModel) {
            itemMovieBinding?.movie = movieModel
            itemMovieBinding?.adapter = this@ListMoviesAdapter
            itemMovieBinding?.executePendingBindings()
        }
    }

    interface OnClickItemListener {
        fun onClickItem(view: View, movie: MovieModel)
    }

    companion object {
        const val CELL_TYPE_MOVIE = 1
        const val CELL_TYPE_SHIMMER = 2
    }
}