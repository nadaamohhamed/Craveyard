import com.example.craveyard.data.model.Categories
import com.example.craveyard.data.model.Meals

interface RemoteDataSource {

    suspend fun search(query: String): Meals

    suspend fun getAllMeals(): Meals

    suspend fun getRandomMeal() : Meals

    suspend fun getCategories() : Categories

    suspend fun getMealsByCategory(category: String) : Meals

}