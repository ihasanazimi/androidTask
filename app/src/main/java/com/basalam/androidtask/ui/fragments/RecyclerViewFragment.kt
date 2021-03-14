package com.basalam.androidtask.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
import java.util.ArrayList

class RecyclerViewFragment : Fragment(), MyListAdapter.RecyclerViewEventListener {

    lateinit var binding: RecyclerviewFragmentBinding
    private lateinit var recyclerview: RecyclerView
    private lateinit var floatActionBtn: ExtendedFloatingActionButton
    private lateinit var searchView: SearchView
    private lateinit var rootContainer: CoordinatorLayout
    private lateinit var adapter: MyListAdapter
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
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

        swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.myLooper()!!).postDelayed(
                { responseRequest() },
                700
            )
        }

        return binding.root // return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data from server
        responseRequest()
        errorObserver()

    }

    private fun responseRequest() {
        swipeRefreshLayout.isRefreshing = true
        loadingDialog.show(requireActivity().supportFragmentManager, null) // SHOW DIALOG
        lifecycleScope.launchWhenCreated {
            myViewModel.getAllObjects(1) // send album Id
                .observe(viewLifecycleOwner, { responseList ->
                    updateUi(responseList as ArrayList<MyPojo>)
                    loadingDialog.dismiss() // hide loading dialog
                    swipeRefreshLayout.isRefreshing = false
                })
        }
    }

    private fun errorObserver() {
        myViewModel.errorLiveData.observe(requireActivity(),{
            if (it.isNotEmpty()){
                loadingDialog.dismiss()
                swipeRefreshLayout.isRefreshing = false
                val snackBar = Snackbar.make(rootContainer, it, Snackbar.LENGTH_INDEFINITE)
                snackBar.setAction("تلاش مجدد") {
                    responseRequest()
                }
                snackBar.show()
            }
        })
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
        swipeRefreshLayout = binding.SwipeRefreshLayout
        swipeRefreshLayout.isRefreshing = true


        // loading dialog initialize
        loadingDialog = LoadingDialog()
        loadingDialog.isCancelable = false

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
        Snackbar.make(rootContainer, pojo.thumbnailUrl, Snackbar.LENGTH_LONG)
            .show()
    }

}




