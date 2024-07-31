
import com.example.craveyard.data.model.Meals
import com.example.craveyard.network.RetrofitHelper
import kotlin.random.Random


object APIClient : RemoteDataSource {

    override suspend fun search(query: String): Meals {
        return RetrofitHelper.retrofitService.getMealsWithChar(query)
    }

    override suspend fun getAllMeals(): Meals {
        val mealsRandomChar = (Random.nextInt(26) + 'a'.code).toChar().toString()
        return RetrofitHelper.retrofitService.getMealsWithChar(mealsRandomChar)
    }

    override suspend fun getRandomMeal(): Meals {
        return RetrofitHelper.retrofitService.getRandomMeal()
    }


}