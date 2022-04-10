package com.example.moneyexchanger

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.moneyexchanger.databinding.FragmentExchangeBinding

class ExchangeFragment : Fragment() {
    private lateinit var binding : FragmentExchangeBinding
    private val modelView : ExchangeViewModel by viewModels()
    private var fromExchange = "BDT"
    private var toExchange = "BDT"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExchangeBinding.inflate(inflater,container,false)
        initiateExchanger()
        modelView.getData().observe(viewLifecycleOwner){
            binding.convertAmountTo.text = it.toString()
        }
        
        binding.convertButton.setOnClickListener {

            if(binding.convertAmountFrom.text.isEmpty()){
                Toast.makeText(activity, "Please Insert Amount!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = binding.convertAmountFrom.text.toString().toDouble()

            if(fromExchange == "BDT"){
                if(toExchange == "BDT"){
                    Toast.makeText(activity, "Please Change Currency!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }else if (toExchange == "USD"){
                    val usdAmount = amount * 0.012
                    modelView.setData(usdAmount)
                }else if(toExchange == "INR"){
                    val inrAmount = amount * 0.88
                    modelView.setData(inrAmount)
                }
            }

            if(fromExchange == "USD"){
                if(toExchange == "BDT"){
                    val bdtAmount = amount * 86.32
                    modelView.setData(bdtAmount)
                }else if (toExchange == "USD"){
                    Toast.makeText(activity, "Please Change Currency!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }else if(toExchange == "INR"){
                    val inrAmount = amount * 75.92
                    modelView.setData(inrAmount)
                }
            }

            if(fromExchange == "INR"){
                if(toExchange == "BDT"){
                    val bdtAmount = amount * 1.14
                    modelView.setData(bdtAmount)
                }else if (toExchange == "USD"){
                    val usdAmount = amount * 0.013
                    modelView.setData(usdAmount)
                }else if(toExchange == "INR"){
                    Toast.makeText(activity, "Please Change Currency!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

        }
        
        return binding.root
    }

    private fun initiateExchanger() {
        val exchangeAdapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            exchangeFromToList
        )

        binding.exchangeFrom.adapter = exchangeAdapter
        binding.exchangeTo.adapter = exchangeAdapter

        binding.exchangeFrom.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    fromExchange = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

        binding.exchangeTo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    toExchange = p0?.getItemAtPosition(p2).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

    }

}

val exchangeFromToList = listOf("BDT","USD","INR")