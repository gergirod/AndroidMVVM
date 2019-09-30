package girod.german.kotlinapp.domain

interface GetPostUseCase {

    fun getPosts(listener: GetPostsUseCaseImpl.GetPostListener)

    fun dispose()

}