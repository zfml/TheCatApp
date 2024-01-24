package com.zfml.thecatapp.presentation.cat_list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.zfml.thecatapp.domain.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatImageViewModel @Inject constructor(
    private val repository: CatRepository
): ViewModel() {
    val catImages = repository.getAllCatImages()


}