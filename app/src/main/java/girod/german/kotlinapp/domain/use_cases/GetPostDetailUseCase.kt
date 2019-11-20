package girod.german.kotlinapp.domain.use_cases

interface GetPostDetailUseCase {

    fun getPostDetail(id : String, listener: GetPostDetailUseCaseImpl.GetPostListener)

    fun dispose()
}