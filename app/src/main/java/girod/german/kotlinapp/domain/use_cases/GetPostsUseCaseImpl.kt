package girod.german.kotlinapp.domain.use_cases

import girod.german.kotlinapp.domain.entities.Post
import girod.german.kotlinapp.data.repositories.PostRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(private val repository : PostRepository) : GetPostUseCase,
        PostRepository.onFinishListener  {

    lateinit var listener : GetPostListener
    private lateinit var subscription : Disposable

    override fun getPosts(listener: GetPostListener)  {
        this.listener = listener
        subscription = repository.gerPosts(this)
    }

    override fun dispose() {
        if(subscription != null) {
            subscription.dispose()
        }
    }

    override fun onfinishError(error: Throwable) {
        listener.showError(error.message!!)
    }

    override fun onFinishSuccess(posts: List<Post>) {
        listener.showList(posts)
    }

    interface GetPostListener {
        fun showList(posts: List<Post>)

        fun showError(errorMessage: String)
    }

}