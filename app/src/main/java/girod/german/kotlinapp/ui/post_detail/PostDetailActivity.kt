package girod.german.kotlinapp.ui.post_detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.Toast
import girod.german.kotlinapp.R
import girod.german.kotlinapp.domain.entities.Post
import girod.german.kotlinapp.ui.base.BaseActivity
import girod.german.kotlinapp.ui.util.ScreenState
import kotlinx.android.synthetic.main.post_detail_activity.*
import javax.inject.Inject

class PostDetailActivity : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var model : PostDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post_detail_activity)

        var  post : Post = intent.getParcelableExtra("post")

        model = ViewModelProviders.of(this, viewModelFactory)[PostDetailViewModel::class.java]

        model.getPostDetail(post.id.toString())

        model.getPostLiveData().observe(this, Observer {
            post-> populatePostDetail(post!!)
        })

        model.getScreenState().observe(this, Observer {
            state-> populateProgressBar(state!!)
        })

        model.getErrorMessage().observe(this, Observer {
            error-> populateError(error!!)
        })
    }


    private fun populatePostDetail(post: Post) {
        post_title.text = post.title
        post_detail.text = post.body
    }

    private fun populateProgressBar(state : ScreenState) {
        when(state) {
            ScreenState.Loading -> progress_bar.visibility = View.VISIBLE
            ScreenState.LoadingFinish -> progress_bar.visibility = View.GONE
        }
    }

    private fun populateError(error : String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}