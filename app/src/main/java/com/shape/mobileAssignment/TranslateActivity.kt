package com.shape.mobileAssignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions


// source form git@arunk7839 and firebase ML kit started
class TranslateActivity : AppCompatActivity() {

    private val msg = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        val edtSourceLangText: EditText = findViewById(R.id.edt_source_lang_text)
        val tvTargetLangText: TextView = findViewById(R.id.tv_target_lang_text)
        val btnTranslate: Button = findViewById(R.id.btn_translate)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.CHINESE)
            .build()

        val englishChineseTranslator = Translation.getClient(options)
        lifecycle.addObserver(englishChineseTranslator)

        btnTranslate.setOnClickListener {
            if (edtSourceLangText.text.isNotEmpty()) {

                progressBar.visibility = View.VISIBLE
                btnTranslate.visibility = View.GONE
                //Downloads the model files required for translation, if they are not already present,
                //when the given conditions are met.
                englishChineseTranslator.downloadModelIfNeeded()
                    .addOnSuccessListener {
                        Log.e(msg, "Download Successful")
                        progressBar.visibility = View.GONE
                        btnTranslate.visibility = View.VISIBLE

                        //Translates the given input from the source language into the target language.
                        englishChineseTranslator.translate(edtSourceLangText.text.toString())
                            .addOnSuccessListener {
                                //Translation successful
                                tvTargetLangText.setText(it)
                            }
                            .addOnFailureListener {
                                //Error
                                Log.e(msg, "Error: " + it.localizedMessage)
                            }
                    }
                    .addOnFailureListener {
                        Log.e(msg, "Download Error: " + it.localizedMessage)
                        progressBar.visibility = View.GONE
                        btnTranslate.visibility = View.VISIBLE

                    }
            }
        }
    }
}