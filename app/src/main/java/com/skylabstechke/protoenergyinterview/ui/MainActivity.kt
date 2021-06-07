package com.skylabstechke.protoenergyinterview.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skylabstechke.protoenergyinterview.R
import com.skylabstechke.protoenergyinterview.adapters.OrderRecyclerViewAdapter
import com.skylabstechke.protoenergyinterview.databinding.ActivityMainBinding
import com.skylabstechke.protoenergyinterview.utils.NetworkResult
import com.skylabstechke.protoenergyinterview.viewmodels.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val mAdapter by lazy { OrderRecyclerViewAdapter() }
    private lateinit var orderViewModel: OrderViewModel


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.apply {
            title = "Payment Options"
        }

    }

    override fun onResume() {
        super.onResume()
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        setUpRecyclerView()
        requestApi()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_menu_canceled -> {
                Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show()
                true
            }
            R.id.filter_menu_pending -> {
                Toast.makeText(this, "Pending", Toast.LENGTH_LONG).show()
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
        orderViewModel.getOrders()
        orderViewModel.orderResponse.observe(this, Observer { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("REQUEST DATA SUCCESS", "Requesting DATA SUCCESS")
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
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
}