import com.example.craveyard.network.APIService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val gson = GsonBuilder()
        .serializeNulls()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

//    val service = retrofit.create(SimpleService::class.java)

    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }

}