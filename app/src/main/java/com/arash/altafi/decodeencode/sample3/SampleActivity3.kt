package com.arash.altafi.decodeencode.sample3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.arash.altafi.decodeencode.databinding.ActivitySample3Binding
import se.simbio.encryption.Encryption

class SampleActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivitySample3Binding
    private var text: String = ""
    private var encodedText: String = ""
    private var decodedText: String = ""
    private lateinit var encryption: Encryption

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "sample with base64 library", Toast.LENGTH_SHORT).show()
        setup()
        init()
    }

    private fun setup() {
        val key = "YourKey"
        val salt = "YourSalt"
        val iv = ByteArray(16)
        encryption = Encryption.getDefault(key, salt, iv)
    }

    private fun init() = binding.apply {
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
                Toast.makeText(this@SampleActivity3, "please first encode", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun encryption(text: String) = encryption.encryptOrNull(text)

    private fun decryption(encodedText: String) = encryption.decryptOrNull(encodedText)

    companion object {
        private const val TAG = "SampleActivity3"
    }

}