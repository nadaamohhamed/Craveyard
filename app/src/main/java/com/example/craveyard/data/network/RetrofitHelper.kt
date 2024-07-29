<<<<<<<< Updated upstream:app/src/main/java/com/example/craveyard/utilities/network/RetrofitHelper.kt
import com.example.craveyard.utilities.network.APIService
========
import com.example.craveyard.data.network.APIService
>>>>>>>> Stashed changes:app/src/main/java/com/example/craveyard/data/network/RetrofitHelper.kt
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    const val BASE_URL = "https://www.themealdb.com"

    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

}