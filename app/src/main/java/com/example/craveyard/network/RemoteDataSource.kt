import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

interface RemoteDataSource {
    suspend fun search(query: String): List<Meal>
}