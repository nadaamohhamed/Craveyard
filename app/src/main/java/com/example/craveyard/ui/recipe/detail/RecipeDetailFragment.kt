package com.example.craveyard.ui.recipe.detail

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
    lateinit var reciepeText:TextView
    lateinit var readmore:TextView
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
        reciepeText=view.findViewById(R.id.meal_ins)
        readmore=view.findViewById(R.id.readmore)
        youTubePlayerView=view.findViewById(R.id.youtube_view)

        val meal=args.meal

        Glide.with(mealImage).load(meal.strMealThumb).into(mealImage)
        mealName.text=meal.strMeal
        mealCategory.text="Category: ${meal.strCategory}"
        mealArea.text=meal.strArea
        reciepeText.text="Recipe details:\n${meal.strInstructions}"


        readmore.setOnClickListener(){
            if (readmore.text.equals(getString(R.string.read_more))){
                reciepeText.maxLines=50
                readmore.text=getString(R.string.show_less)
            }else{
                reciepeText.maxLines=4
                readmore.text=getString(R.string.read_more)
            }


        }

        youTubePlayerView.addYouTubePlayerListener(object :AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)



                if(meal.strYoutube!=""){
                    val array=meal.strYoutube.split("v=")
                    youTubePlayer.loadVideo(array[1],0f)
                }else
                    youTubePlayer.loadVideo("",0f)


            }
        })




        return view
    }


}