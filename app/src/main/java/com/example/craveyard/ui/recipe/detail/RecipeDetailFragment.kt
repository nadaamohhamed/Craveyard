package com.example.craveyard.ui.recipe.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.example.craveyard.data.db.localdata.LocalDs
import com.example.craveyard.data.model.entity.FavMeal
import com.example.craveyard.ui.recipe.favorite.repo.FavRepo
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModel
import com.example.craveyard.ui.recipe.favorite.viewmodel.FavViewModelFactory
import com.example.craveyard.ui.recipe.utils.adapter.MealsAdapter.ViewHolder
import com.example.craveyard.ui.recipe.utils.connection.ConnectionManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {


    lateinit var mealImage:ImageView
    lateinit var mealName:TextView
    lateinit var mealCategory:TextView
    lateinit var mealArea:TextView
    lateinit var reciepeText:TextView
    lateinit var readmore:TextView
    lateinit var youTubePlayerView: YouTubePlayerView
    lateinit var favBtn : ImageButton
    lateinit var favViewModel:FavViewModel


    private val args : RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_recipe_detail, container, false)


        mealImage=view.findViewById(R.id.detail_meal_image)
        mealName=view.findViewById(R.id.detail_meal_name)
        mealCategory=view.findViewById(R.id.detail_category)
        mealArea=view.findViewById(R.id.detail_meal_area)
        reciepeText=view.findViewById(R.id.readmore)
        youTubePlayerView=view.findViewById(R.id.youtube_view)
        favBtn=view.findViewById(R.id.detail_image_Button)

        val meal=args.meal


        val localDs= LocalDs(requireContext())
        val favViewModelFactory = FavViewModelFactory(FavRepo(localDs))
       favViewModel = ViewModelProvider(this, favViewModelFactory).get(FavViewModel::class.java)



        Glide.with(mealImage).load(meal.strMealThumb).into(mealImage)
        mealName.text=meal.strMeal
        mealCategory.text="Category: ${meal.strCategory}"
        mealArea.text=meal.strArea
        reciepeText.text="Recipe details:\n${meal.strInstructions}"


//        readmore.setOnClickListener(){
//            if (readmore.text.equals(getString(R.string.read_more))){
//                reciepeText.maxLines=50
//                readmore.text=getString(R.string.show_less)
//            }else{
//                reciepeText.maxLines=4
//                readmore.text=getString(R.string.read_more)
//            }
//
//
//        }


        favViewModel.viewModelScope.launch {

           val  isFavorite = favViewModel.isFavorite(meal, Firebase.auth.currentUser!!.email!!)

                if (isFavorite) {

                    favBtn.setImageResource(R.drawable.ic_favorite)
                }
                else {
                    favBtn.setImageResource(R.drawable.ic_favorite_border)
                }



        }
        favBtn.setOnClickListener(){
            favViewModel.viewModelScope.launch {
                val isFavorite = favViewModel.isFavorite(meal,Firebase.auth.currentUser!!.email!!)
                if (isFavorite) {
                    val favMeal=favViewModel.getMeal(Firebase.auth.currentUser!!.email!!,meal.idMeal)
                    removeMealFromFavorites(favMeal)
                } else {

                    val favMeal= FavMeal(Firebase.auth.currentUser!!.email!!,meal.idMeal,meal.strMeal!!,
                        meal.strArea,meal.strCategory,meal.strInstructions!!,meal.strYoutube, meal.strMealThumb!!)

                    addMealToFavorites(favMeal)
                }
            }

        }


        if(ConnectionManager.isNetworkAvailable(requireContext()) && meal.strYoutube!=""){
            youTubePlayerView.visibility=View.VISIBLE

            youTubePlayerView.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)

                        val array=meal.strYoutube.split("v=")
                        youTubePlayer.loadVideo(array[1],0f)


                }
            })

        }
        else{
            youTubePlayerView.visibility=View.GONE
        }

        return view
    }


    private fun addMealToFavorites(favMeal: FavMeal) {

        favViewModel.insertFavMeal(favMeal)
        favBtn.setImageResource(R.drawable.ic_favorite)

        Toast.makeText(context, "Added to your favorites!", Toast.LENGTH_SHORT).show()
    }

    private fun removeMealFromFavorites(favMeal: FavMeal) {
        AlertDialog.Builder(requireContext())
            .setTitle("Remove Favorite")
            .setMessage("Are you sure you want to remove this meal from favorites?")
            .setPositiveButton("Yes") { _, _ ->
                favViewModel.deleteFavMeal(favMeal)
                favViewModel.getFavMeals(Firebase.auth.currentUser!!.email!!)
                favBtn.setImageResource(R.drawable.ic_favorite_border)

                Toast.makeText(context, "Removed from your favorites!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }


}