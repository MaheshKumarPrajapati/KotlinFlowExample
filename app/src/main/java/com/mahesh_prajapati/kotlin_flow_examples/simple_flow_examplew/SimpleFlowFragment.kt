package com.mahesh_prajapati.kotlin_flow_examples.simple_flow_examplew

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mahesh_prajapati.kotlin_flow_examples.R
import com.mahesh_prajapati.kotlin_flow_examples.userList
import com.mahesh_prajapati.kotlin_flow_examples.userflowList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import androidx.lifecycle.lifecycleScope
import com.mahesh_prajapati.kotlin_flow_examples.databinding.FragmentSimpleFlowBinding


class SimpleFlowFragment : Fragment() {
    lateinit var binding: FragmentSimpleFlowBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSimpleFlowBinding.inflate(layoutInflater)
        val view = binding.root

        binding.btWay1.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                simpleFlowExample().collect {
                    Log.d(getString(R.string.app_name),"Way 1 $it")
                }
            }
        }

        binding.btWay2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                userflowList.collect {
                    Log.d(getString(R.string.app_name),"Way 2 $it")
                }
            }
        }

        binding.btWay3.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                userList.asFlow().collect {
                    Log.d(getString(R.string.app_name),"Way 3 $it")
                }
            }
        }

        binding.btSyncronized.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                userflowList.collect { Log.d(getString(R.string.app_name),"Syncronized call 1 $it") }
                Log.d(getString(R.string.app_name),"Syncronized call 1 complete")
                userflowList.collect { Log.d(getString(R.string.app_name),"Syncronized call 2 $it") }
                Log.d(getString(R.string.app_name),"Syncronized call 2 complete")
                userflowList.collect { Log.d(getString(R.string.app_name),"Syncronized call 3 $it") }
                Log.d(getString(R.string.app_name),"Syncronized call 3 complete")
            }
        }

        binding.btAsyncronized.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                launch {
                    userflowList.collect { Log.d(getString(R.string.app_name),"Asyncronized call 1 $it") }
                    Log.d(getString(R.string.app_name),"Asyncronized call 1 complete")
                }
                launch {
                    userflowList.collect { Log.d(getString(R.string.app_name),"Asyncronized call 2 $it") }
                    Log.d(getString(R.string.app_name),"Asyncronized call 2 complete")
                }
                launch {
                    userflowList.collect { Log.d(getString(R.string.app_name),"Asyncronized call 3 $it") }
                    Log.d(getString(R.string.app_name),"Asyncronized call 3 complete")
                }

            }
        }

        binding.btCancelFlowWithTimeout.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
              withTimeoutOrNull(5000){
                  simpleFlowExample().collect(){
                      Log.d(getString(R.string.app_name),"withTimeoutOrNull $it")
                  }
              }
            }
        }

        binding.btCancelFlowWithLifecycle.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                  simpleFlowExample().collect(){
                      Log.d(getString(R.string.app_name),"lifecycleScope $it")
                  }
            }
        }

        return view
    }


    private fun simpleFlowExample():Flow<String>{
        return flow {
        userList.forEach {
            delay(2000)
            emit(it)

        }}
    }


}