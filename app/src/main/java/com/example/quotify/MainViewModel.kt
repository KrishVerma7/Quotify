package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream =
            context.assets.open("quotes.json")    //accessing/opening quotes.json file under asset folder
        val size: Int = inputStream.available()

        //This block takes input and store in buffer then read it and then closes inputStream
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(
            buffer,
            Charsets.UTF_8
        )     //This will convert our byte array into String format of Json
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]

    fun prevQuote() = quoteList[--index]
}