package com.arash.altafi.decodeencode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import se.simbio.encryption.Encryption
import java.io.UnsupportedEncodingException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        sample1()
//        sample2()


        // sample 3 => Don't Need Use Library
        // In PHP => https://arashaltafi.ir/encode_decode/encode_docode.php
        val text = "Arash Altafi"
        Log.i("test123321", "encode => ${sample3Encode(text)}")
        Log.i("test123321", "decode => ${sample3Decode(sample3Encode(text))}")

    }

    private fun sample1() {
        val key = "YourKey"
        val salt = "YourSalt"
        val iv = ByteArray(16)
        val encryption: Encryption = Encryption.getDefault(key, salt, iv)

        val encrypted = encryption.encryptOrNull("Text to be encrypt")
        Log.i("test123321", "encrypted => $encrypted")

        val decrypted = encryption.decryptOrNull(encrypted)
        Log.i("test123321", "decrypted => $decrypted")
    }

    private fun sample2() {
        val iv = ByteArray(16)
        val encryption = Encryption.Builder()
            .setKeyLength(128)
            .setKey("YourKey")
            .setSalt("YourSalt")
            .setIv(iv)
            .setCharsetName("UTF8")
            .setIterationCount(1)
            .setDigestAlgorithm("SHA1")
            .setBase64Mode(Base64.DEFAULT)
            .setAlgorithm("AES/CBC/PKCS5Padding")
            .setSecureRandomAlgorithm("SHA1PRNG")
            .setSecretKeyType("PBKDF2WithHmacSHA1")
            .build()
    }

    private fun sample3Encode(s: String): String {
        var data = ByteArray(0)
        try {
            data = s.toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            return Base64.encodeToString(data, Base64.DEFAULT)
        }
    }

    private fun sample3Decode(encoded: String): String {
        val dataDec = Base64.decode(encoded, Base64.DEFAULT)
        var decodedString = ""
        try {
            decodedString = String(dataDec, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } finally {
            return decodedString
        }
    }

    private fun sample3PhpCodes() {
        /**
        https://arashaltafi.ir/encode_decode/encode_docode.php

        $input1 = base64_decode('QXJhc2ggQWx0YWZp');
        $input_encoding1 = 'iso-2022-jp';
        echo iconv($input_encoding1, 'UTF-8', $input1);

        echo "<br/>";
        echo "<br/>";
        echo "<br/>";

        $input2 = base64_encode('Arash Altafi');
        $input_encoding2 = 'iso-2022-jp';
        echo iconv($input_encoding2, 'UTF-8', $input2);

         */
    }

}