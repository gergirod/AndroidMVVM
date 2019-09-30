package girod.german.kotlinapp.ui.post

import android.arch.lifecycle.MutableLiveData
import girod.german.kotlinapp.data.Post
import girod.german.kotlinapp.domain.GetPostUseCase
import girod.german.kotlinapp.domain.GetPostsUseCaseImpl
import girod.german.kotlinapp.ui.util.ScreenState
import girod.german.kotlinapp.ui.base.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class PostListViewModel @Inject constructor(getPost: GetPostUseCase) : BaseViewModel(),
        GetPostsUseCaseImpl.GetPostListener{

    private var postList : MutableLiveData<List<Post>> = MutableLiveData()
    private var screenState : MutableLiveData<ScreenState> = MutableLiveData()
    private var errorMessage : MutableLiveData<String> = MutableLiveData()
    private var getPostUseCase : GetPostUseCase = getPost

    init {
        screenState.value = ScreenState.Loading
        getPostUseCase.getPosts(this)
    }

    fun getPostList() : MutableLiveData<List<Post>> {
        return postList
    }

    fun getScreenState() : MutableLiveData<ScreenState> {
        return screenState
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return errorMessage
    }

    override fun showList(posts: List<Post>) {
        screenState.value = ScreenState.LoadingFinish
        postList.value = posts
    }

    override fun showError(error: String) {
        screenState.value = ScreenState.LoadingFinish
        errorMessage.value = error
    }

    override fun onCleared() {
        super.onCleared()
        getPostUseCase.dispose()
    }
}