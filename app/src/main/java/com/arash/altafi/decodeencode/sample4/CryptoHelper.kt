package com.arash.altafi.decodeencode.sample4

import android.util.Base64
import java.security.Key
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptoHelper {
    private const val AES_KEY_SIZE = 256
    private const val AES_ALGORITHM = "AES/CBC/PKCS5Padding"
    private const val IV_SIZE = 16

    fun encryptString(input: String, key: String): String {
        val secretKey = generateSecretKey(key)
        val cipher = Cipher.getInstance(AES_ALGORITHM)
        val iv = ByteArray(IV_SIZE)
        SecureRandom().nextBytes(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))
        val encrypted = cipher.doFinal(input.toByteArray(Charsets.UTF_8))
        val ivAndEncrypted = ByteArray(iv.size + encrypted.size)
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.size)
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.size, encrypted.size)
        return Base64.encodeToString(ivAndEncrypted, Base64.DEFAULT)
    }

    fun decryptString(input: String, key: String): String {
        val secretKey = generateSecretKey(key)
        val cipher = Cipher.getInstance(AES_ALGORITHM)
        val ivAndEncrypted = Base64.decode(input, Base64.DEFAULT)
        val iv = ByteArray(IV_SIZE)
        val encrypted = ByteArray(ivAndEncrypted.size - IV_SIZE)
        System.arraycopy(ivAndEncrypted, 0, iv, 0, IV_SIZE)
        System.arraycopy(ivAndEncrypted, IV_SIZE, encrypted, 0, encrypted.size)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        return String(cipher.doFinal(encrypted), Charsets.UTF_8)
    }

    private fun generateSecretKey(key: String): Key {
        val keyGenerator = KeyGenerator.getInstance("AES")
        val secureRandom = SecureRandom.getInstance("SHA1PRNG")
        secureRandom.setSeed(key.toByteArray(Charsets.UTF_8))
        keyGenerator.init(AES_KEY_SIZE, secureRandom)
        val secretKey = keyGenerator.generateKey()
        return SecretKeySpec(secretKey.encoded, "AES")
    }
}