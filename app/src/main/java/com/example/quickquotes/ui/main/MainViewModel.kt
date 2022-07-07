package com.example.quickquotes.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.quickquotes.R
import com.example.quickquotes.utilities.FileHelper

class MainViewModel(app: Application) : AndroidViewModel(app) {
    init{
        val text = FileHelper.getTextFromResources(app, R.raw.quotes)
        Log.i("quotes", text)
    }
}