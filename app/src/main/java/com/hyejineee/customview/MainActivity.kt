package com.hyejineee.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyejineee.customview.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btn.setOnClickListener{
            dust_text.dustDimension += 50
        }

        for(i in 1..3) {
            donut.setValue("name$i", 100 * i)
        }
        donut.strokeWidth = 10

    }
}


