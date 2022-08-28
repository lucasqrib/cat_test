package com.lucasqrib.cats.platform.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasqrib.cats.domain.model.Cat
import com.lucasqrib.cats.domain.usecase.RetrieveCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatSharedViewModel @Inject constructor(
    private val retrieveCats: RetrieveCatsUseCase
) : ViewModel() {
    private var currentPage = 0
    private val cats: ArrayList<Cat> = arrayListOf()
    private val _catsMutableData: MutableLiveData<List<Cat>> by lazy {
        MutableLiveData<List<Cat>>().also {
            loadCats()
        }
    }
    private val _errorMutableData: MutableLiveData<Boolean> = MutableLiveData()

    fun getCatsLiveData(): LiveData<List<Cat>> = _catsMutableData
    fun getErrorLiveData(): LiveData<Boolean> = _errorMutableData

    fun loadCats(reload: Boolean = false) {
        if (reload) currentPage = 0
        _errorMutableData.postValue(false)
        viewModelScope.launch {
            val result = try {
                retrieveCats.invoke(currentPage)
            } catch (error: Throwable) {
                Result.failure(error)
            }
            if (result.isSuccess) updateList(result.getOrDefault(emptyList()))
            else showError(result.exceptionOrNull())
        }
    }

    private fun updateList(retrievedCats: List<Cat>) {
        cats.addAll(retrievedCats)
        _catsMutableData.postValue(cats)
        currentPage++
    }

    private fun showError(error: Throwable?) {
        error?.printStackTrace()
        _errorMutableData.postValue(true)
    }

    fun getCatById(id: String): Cat? =
        cats.find { it.id == id }


}