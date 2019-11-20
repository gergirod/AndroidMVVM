package girod.german.kotlinapp.domain.use_cases

interface GetPostUseCase {

    fun getPosts(listener: GetPostsUseCaseImpl.GetPostListener)

    fun dispose()

}