package co.tiagoaguiar.tutorial.jokerappdev.data

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JokeRemoteDataSource {

    fun findJokeByCategory(callback: JokeCallback, categoryName: String) {
        HTTPClient.retrofit().create(ChuckNorrisAPI::class.java)
            .findJokeByCategory(categoryName = categoryName).enqueue(
            object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if(response.isSuccessful) {
                        val joke = response.body()
                        callback.onSuccess(joke ?: throw RuntimeException("Joke not found"))
                    } else {
                        val errorMessage = JSONObject(response.errorBody().toString()).getString("message")
                        callback.onError(errorMessage)
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Internal error")
                    callback.onComplete()
                }
            }
        )
    }

    fun finJokeDay(callback: JokeCallback) {
        HTTPClient.retrofit().create(ChuckNorrisAPI::class.java)
            .findJokeDay().enqueue(object : Callback<Joke> {
                override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                    if(response.isSuccessful) {
                        val jokeDay = response.body() ?: throw RuntimeException("Jack day not found")
                        callback.onSuccess(jokeDay)
                    } else {
                        val errorMessage = JSONObject(response.errorBody().toString()).getString("message")
                        callback.onError(errorMessage)
                    }

                    callback.onComplete()
                }

                override fun onFailure(call: Call<Joke>, t: Throwable) {
                    callback.onError(t.message ?: "Internal error")
                    callback.onComplete()
                }
            })
    }
}