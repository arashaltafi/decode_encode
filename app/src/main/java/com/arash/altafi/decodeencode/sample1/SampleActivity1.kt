package com.arash.altafi.decodeencode.sample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.arash.altafi.decodeencode.databinding.ActivitySample1Binding

class SampleActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivitySample1Binding
    private var text: String = ""
    private var encodedText: String = ""
    private var decodedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "sample with base64", Toast.LENGTH_SHORT).show()
        init()
    }

    private fun init() = binding.apply {
        btnEncode.setOnClickListener {
            text = etCode.editText?.text.toString().trim()
            if (text != "") {
                etCode.error = ""
                encodedText = encodeString(text)
                tvCode.text = encodedText
                Log.i(TAG, "encodedText => $encodedText")
            } else {
                etCode.error = "please fill text"
            }
        }
        btnDecode.setOnClickListener {
            if (encodedText != "") {
                decodedText = decodeString(encodedText)
                tvCode.text = decodedText
                Log.i(TAG, "decodedText => $decodedText")
            } else
                Toast.makeText(this@SampleActivity1, "please first encode", Toast.LENGTH_SHORT)
                    .show()
        }

    }

    private fun encodeString(text: String): String {
        val data = text.toByteArray(Charsets.UTF_8)
        return Base64.encodeToString(data, Base64.DEFAULT)
    }

    private fun decodeString(encodedText: String): String {
        val data = Base64.decode(encodedText, Base64.DEFAULT)
        return String(data, Charsets.UTF_8)
    }

    private fun phpCodes() {
        /**
        <?php

        $input1 = base64_decode('QXJhc2ggQWx0YWZp');
        $input_encoding1 = 'iso-2022-jp';
        echo iconv($input_encoding1, 'UTF-8', $input1);

        echo "<br/>";
        echo "<br/>";
        echo "<br/>";

        $input2 = base64_encode('Arash Altafi');
        $input_encoding2 = 'iso-2022-jp';
        echo iconv($input_encoding2, 'UTF-8', $input2);

        ?>
         */
    }

    companion object {
        private const val TAG = "SampleActivity1"
    }

}