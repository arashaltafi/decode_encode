package com.arash.altafi.decodeencode.sample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.arash.altafi.decodeencode.databinding.ActivitySample2Binding
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class SampleActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySample2Binding
    private var text: String = ""
    private var encodedText: String = ""
    private var decodedText: String = ""
    private lateinit var key: String
    private lateinit var iv: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySample2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "sample with AES", Toast.LENGTH_SHORT).show()
        init()
    }

    private fun init() = binding.apply {
        key = "1123456788abcdef"
        iv = "1edcba9876843210"
        btnEncode.setOnClickListener {
            text = etCode.editText?.text.toString().trim()
            if (text != "") {
                etCode.error = ""
                encodedText = encryption(text, key, iv)
                tvCode.text = encodedText
                Log.d(TAG, "Encrypted: $encodedText")
            } else {
                etCode.error = "please fill text"
            }
        }
        btnDecode.setOnClickListener {
            if (encodedText != "") {
                decodedText = decryption(encodedText, key, iv)
                tvCode.text = decodedText
                Log.d(TAG, "Decrypted: $decodedText")
            } else
                Toast.makeText(this@SampleActivity2, "please first encode", Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun encryption(input: String, key: String, iv: String): String {
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        val ivSpec = IvParameterSpec(iv.toByteArray())
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec)
        val encryptedBytes = cipher.doFinal(input.toByteArray())
        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
    }

    private fun decryption(input: String, key: String, iv: String): String {
        val secretKey = SecretKeySpec(key.toByteArray(), "AES")
        val ivSpec = IvParameterSpec(iv.toByteArray())
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)
        val decodedBytes = Base64.decode(input, Base64.DEFAULT)
        val decryptedBytes = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    }

    companion object {
        private const val TAG = "SampleActivity2"
    }

}