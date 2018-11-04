package irul.com.footballmatchschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.MenuItem
import android.widget.FrameLayout
import irul.com.footballmatchschedule.R
import irul.com.footballmatchschedule.fragment.*
import org.jetbrains.anko.alert


class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    private val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.navigation_mathces -> {
                    val fragment = FragmentMatches()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_teams -> {
                    val fragment = FragmentLeague()
                    addFragment(fragment)
                    return true
                }
                R.id.navigation_favorite -> {
                    val fragment = FragmentFavorite()
                    addFragment(fragment)
                    return true
                }
            }
            return false
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                //untuk menambah animasi
                //.setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        content = findViewById(R.id.content) //as FrameLayout
        val navigation = findViewById<BottomNavigationView>(R.id.navigation) //as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_mathces
        val fragment = FragmentMatches()
        addFragment(fragment)
    }

    override fun onBackPressed() {
        alert("Are you sure to close app?") {
            title = "Confirmation"
            positiveButton("Oyeah!") {
                finish()
                super.onBackPressed()
            }
            negativeButton("No") { }
        }.show()
    }
}
