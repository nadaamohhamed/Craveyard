import com.example.craveyard.recipe.model.Recipe

object APIClient : RemoteDataSource {
    override suspend fun search(query: String): List<Recipe> {
        return RetrofitHelper.retrofitService.search(query)
    }

}