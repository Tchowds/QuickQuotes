package com.example.quickquotes.utilities

import android.content.Context

class FileHelper {
    companion object {
        fun getTextFromResources(context: Context, resourcesId: Int) : String{
            return context.resources.openRawResource(resourcesId).use{
                it.bufferedReader().use{
                    it.readText()
                }
            }
        }
    }
}