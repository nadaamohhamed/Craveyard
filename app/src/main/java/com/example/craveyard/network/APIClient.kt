import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

object APIClient : RemoteDataSource {
    override suspend fun search(query: String): Meals {
        return RetrofitHelper.retrofitService.search(query)
    }

}