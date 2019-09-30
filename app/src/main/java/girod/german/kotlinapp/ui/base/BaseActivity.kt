package girod.german.kotlinapp.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import girod.german.kotlinapp.data.injection.component.DaggerViewModelInjector
import girod.german.kotlinapp.data.injection.component.ViewModelInjector
import girod.german.kotlinapp.data.injection.module.NetworkModule
import girod.german.kotlinapp.ui.post.MainActivity
import girod.german.kotlinapp.ui.post_detail.PostDetailActivity

abstract class BaseActivity : AppCompatActivity() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private fun inject() {
        when(this) {
            is MainActivity -> injector.inject(this)
            is PostDetailActivity -> injector.inject(this)
        }
    }
}