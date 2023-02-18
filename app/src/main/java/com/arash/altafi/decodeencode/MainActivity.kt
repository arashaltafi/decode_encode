package com.arash.altafi.decodeencode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.arash.altafi.decodeencode.databinding.ActivityMainBinding
import com.arash.altafi.decodeencode.sample1.SampleActivity1
import com.arash.altafi.decodeencode.sample2.SampleActivity2
import com.arash.altafi.decodeencode.sample3.SampleActivity3
import com.arash.altafi.decodeencode.sample4.SampleActivity4
import se.simbio.encryption.Encryption
import java.io.UnsupportedEncodingException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() = binding.apply {
        btnSample1.setOnClickListener {
            startActivity(Intent(this@MainActivity, SampleActivity1::class.java))
        }
        btnSample2.setOnClickListener {
            startActivity(Intent(this@MainActivity, SampleActivity2::class.java))
        }
        btnSample3.setOnClickListener {
            startActivity(Intent(this@MainActivity, SampleActivity3::class.java))
        }
        btnSample4.setOnClickListener {
            startActivity(Intent(this@MainActivity, SampleActivity4::class.java))
        }
    }

}