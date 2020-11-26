package com.ydh.todoapprooom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.ydh.todoapprooom.databinding.ActivityMainBinding
import com.ydh.todoapprooom.view.FavoritesFragment
import com.ydh.todoapprooom.view.ProfileFragment
import com.ydh.todoapprooom.view.TodoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var menuItem: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupViewPager()
        binding.navTodo.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.menuTodo -> binding.vpTodo.currentItem = 0
                R.id.menuFavorite -> binding.vpTodo.currentItem = 1
                R.id.menuProfile -> binding.vpTodo.currentItem = 2
            }
            return@setOnNavigationItemSelectedListener false
        }
        setContentView(binding.root)
    }

    private fun setupViewPager() {
        binding.run {
            val pages = listOf(
                Page("Todo", TodoFragment()),
                Page("Favorite", FavoritesFragment()),
                Page("Profile", ProfileFragment()))
            val adapter = ViewPagerAdapter(pages, supportFragmentManager, lifecycle)
            vpTodo.adapter = adapter

            vpTodo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    navTodo.menu.getItem(0).isChecked = false
                    navTodo.menu.getItem(position).isChecked = true
                    menuItem = navTodo.menu.getItem(position)
                }
            })
        }
    }
}