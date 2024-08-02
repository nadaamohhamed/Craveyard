import com.example.craveyard.data.model.Categories
import com.example.craveyard.data.model.Meal
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

    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories() : Categories

    @GET("api/json/v1/1/filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): Meals

    @GET("api/json/v1/1/lookup.php")
    suspend fun searchById(@Query("i") id:String):Meals


}