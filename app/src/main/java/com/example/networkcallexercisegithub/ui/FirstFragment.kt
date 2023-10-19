package com.example.networkcallexercisegithub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.networkcallexercisegithub.MyApplication
import com.example.networkcallexercisegithub.databinding.FragmentFirstBinding

//STEP 5

//Adesso dobbiamo gestire l'istanza del viewmodel nel nostro fragment.
// Per farlo torniamo nell'application e istanziamo il viewmodel factory


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    // private val viewModel : WeatherViewModel by viewModels()    <--- Questo non più, ma questo:
    private lateinit var weatherViewModel: WeatherViewModel
    // poi andiamo dentro l'onViewCreated.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
       
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Adesso dobbiamo prendere in riferimento la MyApplication, e il modo di istanziarla cambia in base a se sei in un fragment o in un'activity. (Nell'activity basterebbe solo scrivere "application")
        //                    qui diamo il riferimento all'application                      qui gli diciamo quale view model usare
        weatherViewModel = (requireActivity().application as MyApplication).viewModelFactory.create(WeatherViewModel::class.java)

        observeData()

        binding.button.setOnClickListener {
            weatherViewModel.getWeather()
        }



    }


    private fun observeData(){
        weatherViewModel.result.observe(viewLifecycleOwner) { weatherData ->
            if (weatherData != null) {
                binding.networkCallTextView.text = weatherData.current?.condition?.text
                binding.networkCallTextView2.text = "${weatherData.current?.tempC} °"



                if(weatherViewModel.isFirstTimeUser.value == true && weatherViewModel.isFirstTimeUser.value != null){
                    Toast.makeText(requireContext(), "Hello, and welcome !",Toast.LENGTH_SHORT).show()
                    weatherViewModel.isFirstTimeUser.value = false
                }




            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}