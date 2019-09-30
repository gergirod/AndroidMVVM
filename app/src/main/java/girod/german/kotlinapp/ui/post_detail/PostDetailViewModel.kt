package girod.german.kotlinapp.ui.post_detail

import android.arch.lifecycle.MutableLiveData
import girod.german.kotlinapp.data.Post
import girod.german.kotlinapp.domain.GetPostDetailUseCase
import girod.german.kotlinapp.domain.GetPostDetailUseCaseImpl
import girod.german.kotlinapp.ui.base.BaseViewModel
import girod.german.kotlinapp.ui.util.ScreenState
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(getPostDetailUseCase: GetPostDetailUseCase): BaseViewModel(), GetPostDetailUseCaseImpl.GetPostListener {

    private var screenState : MutableLiveData<ScreenState> = MutableLiveData()
    private var post : MutableLiveData<Post> = MutableLiveData()
    private var errorMessage : MutableLiveData<String> = MutableLiveData()
    private var getPostDetailUseCase : GetPostDetailUseCase = getPostDetailUseCase

    fun getPostLiveData() : MutableLiveData<Post> {
        return post
    }

    fun getScreenState() : MutableLiveData<ScreenState> {
        return  screenState
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMessage
    }

    fun getPostDetail(id: String) {
        screenState.value = ScreenState.Loading
        getPostDetailUseCase.getPostDetail(id, this)
    }

    override fun showPost(post: Post) {
        screenState.value = ScreenState.LoadingFinish
        this.post.value = post
    }


    override fun showError(errorMessage: String) {
        screenState.value = ScreenState.LoadingFinish
        this.errorMessage.value = errorMessage
    }

    override fun onCleared() {
        super.onCleared()
        getPostDetailUseCase.dispose()
    }
}