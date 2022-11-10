package com.mahesh_prajapati.kotlin_flow_examples


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mahesh_prajapati.kotlin_flow_examples.databinding.ActivityMainBinding
import com.mahesh_prajapati.kotlin_flow_examples.operators.OperatorsFragment
import com.mahesh_prajapati.kotlin_flow_examples.simple_flow_examplew.SimpleFlowFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding= ActivityMainBinding.inflate(layoutInflater)

        binding.simpleFlow.setOnClickListener {
             openFragment(SimpleFlowFragment())

         }

        binding.operatorFlow.setOnClickListener {
              openFragment(OperatorsFragment())
         }
    }

    private fun openFragment(fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.add(R.id.container,fragment, fragment.tag)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}