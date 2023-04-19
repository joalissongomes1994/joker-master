package co.tiagoaguiar.tutorial.jokerappdev.data

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRemoteDataSource() {

    fun findAllCategories(callback: ListCategoryCallback) {
        HTTPClient.retrofit().create(ChuckNorrisAPI::class.java).findAllCategories()
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    if(response.isSuccessful) {
                        val categories = response.body()
                        callback.onSuccess(categories ?: emptyList())
                    } else {
                        val error = JSONObject(response.errorBody()?.string() ?: "")
                        val message = error.getString("message")
                        callback.onError(message ?: "Unknown error")
                    }
                    callback.onComplete()
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    callback.onError(t.message ?: "Internal error")
                    callback.onComplete()
                }

            })
    }
}