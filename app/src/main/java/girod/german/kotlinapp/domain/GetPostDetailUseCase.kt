package girod.german.kotlinapp.domain

import io.reactivex.disposables.Disposable

interface GetPostDetailUseCase {

    fun getPostDetail(id : String, listener: GetPostDetailUseCaseImpl.GetPostListener)

    fun dispose()
}