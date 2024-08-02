import com.example.craveyard.data.model.meals.Meals

interface RemoteDataSource {

    suspend fun search(query: String): Meals

    suspend fun getAllMeals(): Meals

    suspend fun getRandomMeal() : Meals

}