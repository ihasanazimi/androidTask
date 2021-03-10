package com.basalam.androidtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.basalam.androidtask.R
import com.basalam.androidtask.databinding.RecyclerviewFragmentBinding

class RecyclerViewFragment : Fragment() {

    lateinit var binding : RecyclerviewFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // inflate OR create view
        binding = DataBindingUtil.inflate(inflater, R.layout.recyclerview_fragment,container,false)

        // initialize views
        val rootContainer = binding.nestedScrollView
        val recyclerview = binding.recyclerview
        val floatActionBtn = binding.fab
        val searchView = binding.searchView

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO

    }
}