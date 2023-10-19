package com.example.networkcallexercisegithub.ui

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkcallexercisegithub.data.WeatherData
import com.example.networkcallexercisegithub.data.network.WeatherService

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

//STEP 4

// Aggiungiamo il service nel costruttore del nostro view model
sealed class PersistenceViewModel {
    object FirstTimeUser : PersistenceViewModel()
}

        const val KEY_FIRST_TIME_USER = "first_time_user"

    class WeatherViewModel(private val service: WeatherService, private val preferences: SharedPreferences) :
        ViewModel() {

        val result = MutableLiveData<WeatherData>()
        val isFirstTimeUser = MutableLiveData<Boolean>()
//?????? Qui andiamo a creare il metodo che abbiamo citato nella main acivity?
        // lo metteremo in init, così verrà inizializzato in automatico all'apertura dell'app
        // dopodichè aggiungiamo le shared preferences al costruttore del view model, poi passiamo la stessa cosa
        // al costruttore della nostra funzione checkFirstTimeUserLogin.
        // Creiamo una sealed class??????

        init {
            checkFirstTimeUserLogin(preferences)
        }

        // ?????? Rendiamolo un valore booleano, quando facciamo ciò abbiamo bisogno di una Key univoca da assegnare
        // la creeremo all'inizio della classe.
        // ??????
        private fun checkFirstTimeUserLogin(preferences: SharedPreferences) {
            val firstLoginTimeUser = preferences.getBoolean(KEY_FIRST_TIME_USER, true)
            if (firstLoginTimeUser) {
                preferences.edit().putBoolean(KEY_FIRST_TIME_USER, false).apply()
                isFirstTimeUser.value = true
            }
        }


        fun getWeather() {

            viewModelScope.launch(IO) {
// Abbiamo modificato la response adesso.
                var response = service.getWeather("q")
                // Non serve più il true, perchè prima il service era nella repo come null, adesso invece
                // non lo è più.
                if (response.isSuccessful) {
                    result.postValue(response.body())
                    Log.i("CURRENT WEATHER", "${response.body()}")
                } else {
                    Log.e("NETWORK ERROR", "Couldn't achieve network call")
                }
            }
        }

    }



