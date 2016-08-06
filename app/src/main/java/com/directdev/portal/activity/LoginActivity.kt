package com.directdev.portal.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.directdev.portal.R
import com.directdev.portal.network.BinusDataService
import com.directdev.portal.utils.savePref
import org.jetbrains.anko.*
import rx.SingleSubscriber

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //For testing Purposes
        verticalLayout {
            padding = dip(30)
            val username = editText {
                hint = "Name"
                textSize = 24f
            }
            val password = editText {
                hint = "Password"
                textSize = 24f
            }
            button("Login") {
                onClick { update(username.text.toString(), password.text.toString()) }
                textSize = 26f
            }
        }
    }

    fun update(username: String, password: String) {
        Log.d("Update", "Starts")
        username.savePref(this, R.string.username)
        password.savePref(this, R.string.password)
        BinusDataService.initiateUpdate(this)
                .subscribe(object : SingleSubscriber<Boolean>() {
                    override fun onSuccess(value: Boolean) {
                        Log.d("Update", value.toString())
                    }

                    override fun onError(error: Throwable?) {
                        Log.d("Update", error.toString())
                    }
                })
    }
}
