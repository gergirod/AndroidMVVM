package girod.german.kotlinapp.ui.post

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import girod.german.kotlinapp.data.Post
import girod.german.kotlinapp.R

class PostAdapter(rowClick: RowClick) : RecyclerView.Adapter<ItemRow>() {

    private var posts : List<Post> = ArrayList()
    private var rowClick: RowClick = rowClick

    fun setList(list : List<Post>) {
        posts = emptyList()
        posts = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRow {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ItemRow(view, rowClick)
    }

    override fun onBindViewHolder(row: ItemRow, position: Int) {
        val  post = posts[position]
        row.populateDat(post)

    }

    override fun getItemCount(): Int {
        return posts.size
    }


    interface RowClick {

        fun onRowClicked(post : Post)

    }
}