import android.util.Log
import com.example.craveyard.recipe.model.Meal
import com.example.craveyard.recipe.model.Meals
import com.example.craveyard.recipe.model.Recipe

object APIClient : RemoteDataSource {
    override suspend fun search(query: String): List<Meal> {
        Log.d("asd","5   $query")
        return RetrofitHelper.retrofitService.search(query)
    }

}