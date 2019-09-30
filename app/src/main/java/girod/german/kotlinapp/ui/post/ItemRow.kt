package girod.german.kotlinapp.ui.post

import android.support.v7.widget.RecyclerView
import android.view.View
import girod.german.kotlinapp.data.Post
import kotlinx.android.synthetic.main.row_item.view.*

class ItemRow(itemView: View, rowClick: PostAdapter.RowClick) : RecyclerView.ViewHolder(itemView) {

    var rowCLick: PostAdapter.RowClick = rowClick

    fun populateDat(post: Post) {

        itemView.title.text = post.title
        itemView.body.text = post.body

        itemView.setOnClickListener({rowCLick.onRowClicked(post)})
    }

}