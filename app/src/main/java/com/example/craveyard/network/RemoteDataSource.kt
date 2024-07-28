import com.example.craveyard.recipe.model.Recipe

interface RemoteDataSource {
    suspend fun search(query: String): List<Recipe>
}