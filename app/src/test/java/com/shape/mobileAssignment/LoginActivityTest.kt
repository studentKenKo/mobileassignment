package com.shape.mobileAssignment

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class LoginActivityTest {

    @Test
    fun login() {
    }

    @Test
    fun goToRegister() {
    }

    @Test
    fun performLogin() {

        val sharedPrefs = Mockito.mock(SharedPreferences::class.java)
        val sharedPrefsEditor = Mockito.mock(SharedPreferences.Editor::class.java)
        val context = Mockito.mock(Context::class.java)

        //simulate the behaviour of the objects
        Mockito.`when`(context.getSharedPreferences(Mockito.anyString(), Mockito.anyInt()))
            .thenReturn(sharedPrefs)
        Mockito.`when`(sharedPrefs.edit()).thenReturn(sharedPrefsEditor)
        Mockito.`when`(sharedPrefsEditor.putString(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(sharedPrefsEditor)

        val email = "abc@gmail.com"
        val preKey = "LOGINEMAIL_KEY"
        val preKey2 = "PASSWORD_KEY"

        val login = LoginActivity(email)
        login.performLogin(email)
        //verify is the method call
        Mockito.verify(sharedPrefsEditor).putString(Mockito.argThat { key -> key == preKey },
          Mockito.argThat { value -> value == email })
        Mockito.verify(sharedPrefsEditor).commit()
    }
}