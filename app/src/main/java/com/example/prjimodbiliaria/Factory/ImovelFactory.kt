package com.example.prjimodbiliaria.Factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.prjimodbiliaria.Repository.ImovelRepository
import com.example.prjimodbiliaria.ViewModel.ImovelViewModel

class ImovelViewModelFactory(private val repository: ImovelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImovelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImovelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}