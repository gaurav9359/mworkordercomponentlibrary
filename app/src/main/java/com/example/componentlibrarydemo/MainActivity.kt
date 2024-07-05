package com.example.componentlibrarydemo

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.buttonstesing.ButtonFragment
import com.example.buttonstesing.ChatFragment
import com.example.buttonstesing.ListCardFragment
import com.example.buttonstesing.MediaPickerFragment
import com.example.buttonstesing.TabFragment
import com.example.componentlibrarydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
    }

    private fun setupSpinner() {
        binding.inputSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> showFragment(TabFragment())
                    1 -> showFragment(ButtonFragment())
                    2 -> showFragment(ListCardFragment())
                    3 -> showFragment(ChatFragment())
                    4->showFragment(MediaPickerFragment())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
