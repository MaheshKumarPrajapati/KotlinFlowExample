package com.mahesh_prajapati.kotlin_flow_examples.operators

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.mahesh_prajapati.kotlin_flow_examples.R
import com.mahesh_prajapati.kotlin_flow_examples.ageList
import com.mahesh_prajapati.kotlin_flow_examples.databinding.FragmentOperatorsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch


class OperatorsFragment : Fragment() {
    lateinit var binding: FragmentOperatorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= FragmentOperatorsBinding.inflate(layoutInflater)
         val view:View= binding.root

        binding.filterOperator.setOnClickListener {
          lifecycleScope.launch (Dispatchers.IO){
          ageList.asFlow().filter { age-> age%2==1 }
              .collect {
                  Log.d(getString(R.string.app_name),"Filter Operator $it")
              }

          }

        }

         return view
    }

}