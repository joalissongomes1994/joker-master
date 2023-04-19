package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey") apiKey: String = HTTPClient.API_KEY): Call<List<String>>

    @GET("jokes/random")
    fun findJokeByCategory(
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY,
        @Query("categoryName") categoryName: String
    ): Call<Joke>

    @GET("jokes/random")
    fun findJokeDay(
        @Query("apiKey") apiKey: String = HTTPClient.API_KEY
    ): Call<Joke>
}