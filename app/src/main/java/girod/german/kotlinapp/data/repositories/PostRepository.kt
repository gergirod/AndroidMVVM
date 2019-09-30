package girod.german.kotlinapp.data.repositories

import girod.german.kotlinapp.data.Post
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver

interface PostRepository {

    fun gerPosts(onFinishListener: onFinishListener) :Disposable

    fun getPostDetail(id : String, onDetailFinishListener: OnDetailFinishListener) : Disposable

    interface onFinishListener {
        fun onFinishSuccess(posts : List<Post>)

        fun onfinishError(error : Throwable)
    }

    interface OnDetailFinishListener {
        fun onFinishSuccess(post: Post)

        fun onfinishError(error : Throwable)
    }
}