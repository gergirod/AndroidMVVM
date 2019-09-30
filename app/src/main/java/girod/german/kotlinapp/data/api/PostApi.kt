package girod.german.kotlinapp.data.api

import girod.german.kotlinapp.data.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>


    @GET("/posts/{id}")
    fun getPostDetail(@Path("id" )id : String): Observable<Post>
}