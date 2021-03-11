package com.basalam.androidtask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.basalam.androidtask.R
import com.basalam.androidtask.databinding.RecyclerviewFragmentBinding
import com.basalam.androidtask.model.MyPojo
import com.basalam.androidtask.model.adapters.MyListAdapter
import com.basalam.androidtask.repository.Rep
import com.basalam.androidtask.ui.fragments.dialogs.LoadingDialog
import com.basalam.androidtask.ui.viewModel.MyObjectViewModel
import com.basalam.androidtask.utils.App
import com.basalam.androidtask.utils.MyFactory
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class RecyclerViewFragment : Fragment(), MyListAdapter.RecyclerViewEventListener{

    lateinit var binding: RecyclerviewFragmentBinding
    private lateinit var recyclerview: RecyclerView
    private lateinit var floatActionBtn: ExtendedFloatingActionButton
    private lateinit var searchView: SearchView
    private lateinit var rootContainer: CoordinatorLayout
    private lateinit var adapter: MyListAdapter
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var myViewModel: MyObjectViewModel
    private lateinit var repository: Rep

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // inflate OR create fragment view //
        binding =
            DataBindingUtil.inflate(inflater, R.layout.recyclerview_fragment, container, false)

        init()

        setUpRecyclerView()

        return binding.root // return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data from server
        request()

    }

    private fun request() {
        lifecycleScope.launchWhenCreated {
            myViewModel.getAllObjects()
                .observe(viewLifecycleOwner, { responseList ->
                    updateUi(responseList as ArrayList<MyPojo>)
                    loadingDialog.dismiss() // hide loading dialog
                    repository.insertAll(responseList) // save in DB
                })
        }
    }


    private fun init() {

        // initialize viewModel
        myViewModel = ViewModelProvider(
            requireActivity(),
            MyFactory(App())
        ).get(MyObjectViewModel::class.java)

        repository = Rep(requireContext())

        // initialize fragment views
        rootContainer = binding.rootContainer
        recyclerview = binding.recyclerview
        floatActionBtn = binding.fab
        searchView = binding.searchView


        // loading dialog initialize
        loadingDialog = LoadingDialog()
        loadingDialog.isCancelable = false
        loadingDialog.show(requireActivity().supportFragmentManager, null) // SHOW DIALOG

    }

    private fun setUpRecyclerView() {
        val fakeList = arrayListOf<MyPojo>()
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerview.layoutManager = layoutManager
        adapter = MyListAdapter(fakeList, this)
        recyclerview.adapter = adapter
    }

    private fun updateUi(list: ArrayList<MyPojo>) {
        adapter.setNewList(list)
    }


    //adapter event listener
    override fun onClickItem(pojo: MyPojo, position: Int) {
        TODO("Not yet implemented")
    }

}

//    val snackBar =
//        Snackbar.make(rootContainer, "خطا در ارتباط با سرور", Snackbar.LENGTH_INDEFINITE)
//    snackBar.setAction("تلاش مجدد") {
//        loadingDialog.show(requireActivity().supportFragmentManager, null) // SHOW DIALOG
//        request()
//    }


