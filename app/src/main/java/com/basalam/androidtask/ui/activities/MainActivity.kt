package com.basalam.androidtask.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.basalam.androidtask.R
import com.basalam.androidtask.databinding.ActivityMainBinding
import com.basalam.androidtask.ui.viewModel.MyObjectViewModel
import com.basalam.androidtask.utils.App
import com.basalam.androidtask.utils.MyFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MyObjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate view
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setContentView(binding.root) // return view

        // initialize viewModel
        viewModel = ViewModelProvider(this,MyFactory(App())).get(MyObjectViewModel::class.java)

    }
}