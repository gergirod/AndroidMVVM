package girod.german.kotlinapp.data.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import girod.german.kotlinapp.data.injection.ViewModelFactory
import girod.german.kotlinapp.data.injection.ViewModelKey
import girod.german.kotlinapp.ui.post.PostListViewModel
import girod.german.kotlinapp.ui.post_detail.PostDetailViewModel

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: PostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    abstract fun bindPostDetailViewModel(postDetailViewModel: PostDetailViewModel): ViewModel

}