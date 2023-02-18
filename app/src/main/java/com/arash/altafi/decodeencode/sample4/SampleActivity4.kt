package com.arash.altafi.decodeencode.sample4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arash.altafi.decodeencode.databinding.ActivitySample4Binding

class SampleActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivitySample4Binding
    private var text: String = ""
    private var encodedText: String = ""
    private var decodedText: String = ""
    private lateinit var key: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "sample with AES", Toast.LENGTH_SHORT).show()
        init()
    }

    private fun init() = binding.apply {
        key = "my_secret_key".toByteArray(Charsets.UTF_8).toString()
        Log.i(TAG, "init: $key")
        btnEncode.setOnClickListener {
            text = etCode.editText?.text.toString().trim()
            if (text != "") {
                etCode.error = ""
                encodedText = encryption(text)
                tvCode.text = encodedText
                Log.d(TAG, "Encrypted: $encodedText")
            } else {
                etCode.error = "please fill text"
            }
        }
        btnDecode.setOnClickListener {
            if (encodedText != "") {
                decodedText = decryption(encodedText)
                tvCode.text = decodedText
                Log.d(TAG, "Decrypted: $decodedText")
            } else
                Toast.makeText(this@SampleActivity4, "please first encode", Toast.LENGTH_SHORT)
                    .show()
        }

    }

    private fun encryption(text: String): String = CryptoHelper.encryptString(text, key)

    private fun decryption(encoded: String): String = CryptoHelper.decryptString(encoded, key)

    companion object {
        private const val TAG = "SampleActivity4"
    }

}