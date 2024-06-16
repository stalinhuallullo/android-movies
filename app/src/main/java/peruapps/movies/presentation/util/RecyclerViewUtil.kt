package peruapps.movies.presentation.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun RecyclerView.onEndless(crossinline onEndless: () -> Unit) {

    var pastVisiblesItems: Int
    var totalItemCount: Int

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val mLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            if (dx > 0) {
                totalItemCount = mLayoutManager.itemCount
                pastVisiblesItems = mLayoutManager.findLastCompletelyVisibleItemPosition()

                if (pastVisiblesItems.plus(1) >= totalItemCount) {
                    onEndless()
                }
            }
        }
    })
}