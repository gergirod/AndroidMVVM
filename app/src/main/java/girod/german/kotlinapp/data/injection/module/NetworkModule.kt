package girod.german.kotlinapp.data.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import girod.german.kotlinapp.BuildConfig
import girod.german.kotlinapp.data.api.PostApi
import girod.german.kotlinapp.data.repositories.PostRepository
import girod.german.kotlinapp.data.repositories.PostRepositoryImpl
import girod.german.kotlinapp.domain.use_cases.GetPostDetailUseCase
import girod.german.kotlinapp.domain.use_cases.GetPostDetailUseCaseImpl
import girod.german.kotlinapp.domain.use_cases.GetPostUseCase
import girod.german.kotlinapp.domain.use_cases.GetPostsUseCaseImpl
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module

@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }

    @Provides @Singleton internal fun providePostRepository(dataSource : PostRepositoryImpl) : PostRepository {
        return dataSource
    }

    @Provides @Singleton internal fun provideGetPost(getPost : GetPostsUseCaseImpl) : GetPostUseCase {
        return getPost
    }

    @Provides @Singleton internal fun provideGetPostDetail(getPostDetail : GetPostDetailUseCaseImpl) : GetPostDetailUseCase {
        return getPostDetail
    }
}