package com.example.craveyard.recipe.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.craveyard.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kr.co.prnd.readmore.ReadMoreTextView

class RecipeDetailFragment : Fragment() {


    lateinit var mealImage:ImageView
    lateinit var mealName:TextView
    lateinit var mealCategory:TextView
    lateinit var mealArea:TextView
    lateinit var reciepeText:ReadMoreTextView
    lateinit var youTubePlayerView: YouTubePlayerView


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
        reciepeText=view.findViewById(R.id.read_more)
        youTubePlayerView=view.findViewById(R.id.youtube_view)

        val meal=args.meal

        Glide.with(mealImage).load(meal.strMealThumb).into(mealImage)
        mealName.text=meal.strMeal
        mealCategory.text="Category: ${meal.strCategory}"
        mealArea.text=meal.strArea
        reciepeText.text="Recipe details:\n${meal.strInstructions}"



        youTubePlayerView.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val videoid=meal.strYoutube.split("v=")[1]
                youTubePlayer.loadVideo(videoid,0f)
            }
        })




        return view
    }


}