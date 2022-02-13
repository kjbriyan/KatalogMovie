package com.kjbriyan.katalogmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.kjbriyan.katalogmovie.ui.Genre.GenreFragment
import com.kjbriyan.katalogmovie.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<MeowBottomNavigation>(R.id.navigation)
        setFragment(MovieFragment.newIntance())
        navigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_baseline_movie_24))
//        navigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_baseline_movie_filter_24))

        navigation.setOnClickMenuListener{
            when(it.id){
                1 -> setFragment(MovieFragment.newIntance())
//                2 -> setFragment(GenreFragment.newIntance())

                else -> ""
            }
        }
        navigation.show(1)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout, fragment, "HomeActivity")
            .commit()
    }
}