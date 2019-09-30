package girod.german.kotlinapp.data.repositories

import girod.german.kotlinapp.data.Post
import girod.german.kotlinapp.data.api.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.DefaultObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val postApi: PostApi): PostRepository {


    override fun gerPosts(onFinishListener: PostRepository.onFinishListener) : Disposable {
         return postApi.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    list->
                    onFinishListener.onFinishSuccess(list)
                }, {
                    t ->
                    onFinishListener.onfinishError(t)
                })
    }

    override fun getPostDetail(id: String, onDetailFinishListener: PostRepository.OnDetailFinishListener): Disposable {
        return postApi.getPostDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    post-> onDetailFinishListener.onFinishSuccess(post)
                }, {t ->
                    onDetailFinishListener.onfinishError(t)
                })
    }
}