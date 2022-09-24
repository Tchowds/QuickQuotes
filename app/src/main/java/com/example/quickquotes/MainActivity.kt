package com.example.quickquotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.quickquotes.data.configData
import com.example.quickquotes.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = FileHelper.getTextFromResources(application, R.raw.config)
        val config = parseText(text)

        if (config != null) {
            addbuttons(config)
        }

    }

    fun parseText(text: String) : configData?{
        val moshi = Moshi.Builder().build()
        val adapter : JsonAdapter<configData> =
            moshi.adapter(configData::class.java)
        return adapter.fromJson(text)
    }

    fun addbuttons(data: configData){
        var side = 1
        val left : LinearLayout = findViewById(R.id.leftSide)
        val right : LinearLayout = findViewById(R.id.rightSide)

        for(i in 0 until data.quantity){
            Log.i("NUMBERforI", i.toString())
            if(side == 1){
                insertButton(data.names[i], data.imageNames[i], left)
                side++
            }
            else{
                insertButton(data.names[i], data.imageNames[i],right)
                side--
            }
        }
    }



    fun insertButton(name: String, imageName: String, layout: LinearLayout){


        val newButton = ImageButton(this)
        newButton.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val params = newButton.layoutParams
        params.width = findpx(180)
        params.height = findpx(180)
        newButton.scaleType = ImageView.ScaleType.FIT_XY


        val resId = resources.getIdentifier(imageName, "mipmap", applicationContext.packageName)

        newButton.setImageResource(resId)
        newButton.background = null
        newButton.setOnClickListener {
            val intent = Intent(this, ShowQuote::class.java)
            intent.putExtra("NameID", name)
            startActivity(intent)
        }
        layout.addView(newButton)
        addLabel(name, layout)

    }

    fun findpx(dp: Int): Int{
        val scale : Float = resources.displayMetrics.density
        val mGestureThreshold: Int = (dp * scale + 0.5f).toInt()
        return mGestureThreshold
    }

    fun addLabel(name: String, layout: LinearLayout) {
        val text = TextView(this)
        text.setText(name)
        text.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layout.addView(text)

    }

}