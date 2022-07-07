package com.example.quickquotes


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.quickquotes.data.quoteBase
import com.example.quickquotes.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_show_quote.*
import kotlin.random.Random

class ShowQuote : AppCompatActivity() {

    private val ListType = Types.newParameterizedType(
        List::class.java, quoteBase::class.java
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_quote)

        val text = FileHelper.getTextFromResources(application, R.raw.quotes)
        //Log.i("quotes", text)
        val line = getLine(text)
        QuoteView.text = line


        val fadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        QuoteView.startAnimation(fadeIn)
        AuthorView.startAnimation(fadeIn)

    }

    fun getLine(text: String) : String{
        val quotes = parseText(text)
        if (quotes != null) {
            return findLine(quotes[0])
        }
        else{
            return "default"
        }
    }

    fun parseText(text: String) : List<quoteBase>?{
        val moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<List<quoteBase>> =
            moshi.adapter(ListType)
        return adapter.fromJson(text)
    }

    fun findLine(quotes: quoteBase) : String{
        val quantity = quotes.quantity
        val quotes = quotes.quotes

        //NOTE: the below random function is the only version of random
        //That I have found to return differently on different runs
        // Big O(n)
        val index = (0..quantity-1).shuffled().last()
        return quotes[index]
    }


}