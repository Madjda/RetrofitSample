package sayaradz.api

import com.example.tasklist_retrofit.Tasks.Task
import retrofit2.http.GET



interface ServiceProvider {

    // Getting all the brands
    @GET("todos")
    fun getTasks(): retrofit2.Call<List<Task>>

}

