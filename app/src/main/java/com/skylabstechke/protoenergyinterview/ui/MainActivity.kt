package com.skylabstechke.protoenergyinterview.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.protoenergyinterview.R
import com.skylabstechke.protoenergyinterview.adapters.RecyclerViewAdapter
import com.skylabstechke.protoenergyinterview.databinding.ActivityMainBinding
import com.skylabstechke.protoenergyinterview.utils.Constants.APP_TITLE
import com.skylabstechke.protoenergyinterview.utils.Constants.QUERY_CANCELED
import com.skylabstechke.protoenergyinterview.utils.Constants.QUERY_PENDING
import com.skylabstechke.protoenergyinterview.utils.NetworkResult
import com.skylabstechke.protoenergyinterview.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mAdapter by lazy { RecyclerViewAdapter() }
    private val orderViewModel by viewModels<OrderViewModel>()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        supportActionBar?.apply {
            title = APP_TITLE
        }
        setUpRecyclerView()
        requestApi()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_menu_canceled -> {
                filter(QUERY_CANCELED)
                true
            }
            R.id.filter_menu_pending -> {
                filter(QUERY_PENDING)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.payment_options_menu, menu)
        return true
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        showShimmerEffect()
    }

    private fun hideShimmerEffect() {

        binding.recyclerView.hideShimmer()
    }

    private fun showShimmerEffect() {

        binding.recyclerView.showShimmer()
    }


    private fun requestApi() {
        val request = orderViewModel.getOrders()
        Log.d("REQUEST DATA", request.toString())
        orderViewModel.orderResponse.observe(this, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("REQUEST DATA SUCCESS", "Requesting DATA SUCCESS")
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {

                    binding.errorImage.visibility = View.VISIBLE
                    binding.errText.visibility = View.VISIBLE
                    binding.errText.text = response.message.toString()
                    hideShimmerEffect()
                    Toast.makeText(
                        this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun filter(query: String) {
        showShimmerEffect()
        orderViewModel.orderResponse.observe(this, Observer { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    binding.errorImage.visibility = View.VISIBLE
                    binding.errText.visibility = View.VISIBLE
                    binding.errText.text = response.message.toString()
                    hideShimmerEffect()
                    Toast.makeText(
                        this,
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        mAdapter.setData(
                            it.filter { value ->
                                value.status == query
                            }
                        )
                    }
                }
            }
        })
    }


}
