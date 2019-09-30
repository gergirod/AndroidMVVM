package girod.german.kotlinapp.domain

import girod.german.kotlinapp.data.Post
import girod.german.kotlinapp.data.repositories.PostRepository
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GetPostDetailUseCaseImpl @Inject constructor(private val repository : PostRepository) : GetPostDetailUseCase, PostRepository.OnDetailFinishListener {

    private lateinit var listener: GetPostListener
    private lateinit var subscription : Disposable

    override fun getPostDetail(id: String, getPostListener: GetPostListener) {
        listener = getPostListener
        subscription = repository.getPostDetail(id, this)
    }

    override fun dispose() {
        if(subscription != null) {
            subscription.dispose()
        }
    }

    override fun onfinishError(error: Throwable) {
        listener.showError(error.message!!)
    }

    override fun onFinishSuccess(post: Post) {
        listener.showPost(post)
    }

    interface GetPostListener {
        fun showPost(post : Post)

        fun showError(errorMessage: String)
    }

}