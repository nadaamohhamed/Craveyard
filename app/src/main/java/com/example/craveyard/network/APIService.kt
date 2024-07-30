import com.example.craveyard.data.model.Meals
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET("api/json/v1/1/search.php")
    suspend fun getMealsWithChar(
        @Query("s") query: String
    ): Meals

    @GET("api/json/v1/1/random.php")
    suspend fun getRandomMeal(): Meals


}