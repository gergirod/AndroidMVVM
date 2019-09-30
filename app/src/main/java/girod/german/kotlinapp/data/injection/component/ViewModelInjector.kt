package girod.german.kotlinapp.data.injection.component

import dagger.Component
import girod.german.kotlinapp.data.injection.module.NetworkModule
import girod.german.kotlinapp.data.injection.module.ViewModelModule
import girod.german.kotlinapp.ui.post.MainActivity
import javax.inject.Singleton
import girod.german.kotlinapp.ui.post.PostListViewModel
import girod.german.kotlinapp.ui.post_detail.PostDetailActivity
import girod.german.kotlinapp.ui.post_detail.PostDetailViewModel

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)
    fun inject(postDetailViewModel: PostDetailViewModel)
    fun inject(mainActivity: MainActivity)
    fun inject(postDetailActivity: PostDetailActivity)

}