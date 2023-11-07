package com.example.pokedex.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokedex.model.Pokemon
import com.example.pokedex.service.PokemonApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel(application: Application): AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private val apiService = PokemonApiService()
    val pokeList = MutableLiveData<List<Pokemon>>()
    init{
        getDataFromAPi()
    }

    fun getDataFromAPi() {
        disposable.add(
            apiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Pokemon>>(){
                    override fun onSuccess(t: List<Pokemon>) {
                        pokeList.value=t
                        println("API verileri başarıyla çekildi. Çekilen veri sayısı: ${t.size}")

                    }

                    override fun onError(e: Throwable) {
                        print("error: hata "+e)
                        println("API verileri başarıyla çekilmedi")

                    }

                })
        )


    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}