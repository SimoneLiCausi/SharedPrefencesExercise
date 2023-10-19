package com.example.networkcallexercisegithub

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkcallexercisegithub.data.network.WeatherService
import com.example.networkcallexercisegithub.ui.WeatherViewModel
import java.lang.IllegalArgumentException


//STEP 3



// Questo è il view model factory,aggiustiamo il return, e aggiungiamo nel costruttore il service.
// Successivamente dovremmo aggiungere anche nel costruttore del nostro view model
// il service
class MainViewModelFactory(private val service : WeatherService, private val preferences: SharedPreferences) : ViewModelProvider.Factory {

    // Noi qui passiamo un viewModel generico T, quindi gli stiamo dicendo che
    // se il viewmodel è assegnabile, allora trasforma T, nel mio viewmodel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         //super.create(modelClass)
         if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
             return WeatherViewModel(service, preferences) as T
        } else {
            throw IllegalArgumentException("Unknown view model")
         }
    }
    // Se avessimo ad esempio un altro view model, potrestto aggiungere un else if (modelClass.isAssignableFrom(NOMEVIEWMODEL::class.java)), return il view model + service.
}