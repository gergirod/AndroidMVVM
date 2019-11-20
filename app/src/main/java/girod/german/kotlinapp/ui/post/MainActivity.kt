package girod.german.kotlinapp.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import girod.german.kotlinapp.R
import girod.german.kotlinapp.domain.entities.Post
import girod.german.kotlinapp.ui.util.ScreenState
import girod.german.kotlinapp.ui.post_detail.PostDetailActivity
import girod.german.kotlinapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), PostAdapter.RowClick {

    private val adapter : PostAdapter by lazy { PostAdapter(this) }
    @Inject lateinit var viewModelFactory : ViewModelProvider.Factory

    private lateinit var model : PostListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = adapter

        model = ViewModelProviders.of(this, viewModelFactory)[PostListViewModel::class.java]
        model.getPostList().observe(this, Observer { list ->
            populateList(list!!)

        })

        model.getScreenState().observe(this, Observer { state ->
            populateProgressBar(state!!)
        })

        model.getErrorMessage().observe(this, Observer { error ->
            populateErrorMessage(error!!)
        })
    }

    private fun populateList(posts : List<Post>) {
        adapter.setList(posts)
    }

    private fun populateProgressBar(state : ScreenState) {

        when(state) {
            ScreenState.Loading -> progress_bar.visibility = View.VISIBLE
            ScreenState.LoadingFinish -> progress_bar.visibility = View.GONE
        }
    }

    private fun populateErrorMessage(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onRowClicked(post: Post) {
        var intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("post", post)
        startActivity(intent)
    }

}
