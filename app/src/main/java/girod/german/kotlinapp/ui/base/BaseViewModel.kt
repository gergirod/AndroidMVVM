package girod.german.kotlinapp.ui.base

import android.arch.lifecycle.ViewModel
import girod.german.kotlinapp.data.injection.component.DaggerViewModelInjector
import girod.german.kotlinapp.data.injection.component.ViewModelInjector
import girod.german.kotlinapp.data.injection.module.NetworkModule
import girod.german.kotlinapp.ui.post.PostListViewModel
import girod.german.kotlinapp.ui.post_detail.PostDetailViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostDetailViewModel -> injector.inject(this)
        }
    }

}