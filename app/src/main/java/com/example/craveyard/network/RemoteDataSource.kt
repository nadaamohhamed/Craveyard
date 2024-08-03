import com.example.craveyard.data.model.meals.Meals
import com.example.craveyard.data.model.Categories

interface RemoteDataSource {

    suspend fun search(query: String): Meals

    suspend fun getAllMeals(): Meals

    suspend fun getRandomMeal() : Meals

    suspend fun getCategories() : Categories

    suspend fun getMealsByCategory(category: String) : Meals


    suspend fun searchById(id:String):Meals

}