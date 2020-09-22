package com.mes.inflight_mag.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.mes.inflight_mag.R
import com.mes.inflight_mag.ui.main.viewmodel.SignInViewModel
import com.mes.inflight_mag.utils.SharedPrefs
import com.mes.inflight_mag.utils.UtilityClass
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject


@AndroidEntryPoint
class SignIn : AppCompatActivity() {
    private val signInVM: SignInViewModel by viewModels()

    @Inject
    lateinit var settings: SharedPrefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setUpObservers()
        sign_in_btn.setOnClickListener {
            login()
        }
        go_to_sign_up.setOnClickListener {
            startActivity(
                Intent(
                    this@SignIn, SignUp::class.java
                )
            )
            finish()
        }
    }


    private fun setUpObservers(){
        signInVM.loading.observe(
            this,{
                    loading ->run{
                if (loading) {
                    sign_in_loader.visibility = View.VISIBLE

                }else{
                    sign_in_loader.visibility = View.GONE
                }

            }
            }
        )

        signInVM.customer.observe(
            this,{
                    customer -> run{
                if (customer != null){
                    //succeeded
                    settings.token = customer.token
                    settings.user = customer.name
                    settings.tokenTime = UtilityClass.getCurrentDateAsString()

                    startActivity(
                        Intent(
                            this@SignIn, Home::class.java
                        )
                    )
                    finish()



                }
            }
            }
        )

        signInVM.message.observe(
            this,{
                message->run{
               if(message==""){
                   sign_in_message.visibility = View.GONE
                }else{

                    sign_in_message.text = message
                    sign_in_message.visibility = View.VISIBLE
                }
            }
            }
        )
    }

    private fun login(){


        if (isValidInput()){

            signInVM.username = sign_in_username.text.toString().trim()
            signInVM.password = sign_in_password.text.toString().trim()

            signInVM.signIn()

        }else{
            sign_in_loader.visibility = View.GONE
        }
    }

    private fun isValidInput():Boolean{
        if (sign_in_username.text.toString().isEmpty()){
            sign_in_user_IL.error = "Required!"
            sign_in_user_IL.isErrorEnabled = true
            return false
        }else{
            sign_in_user_IL.isErrorEnabled = false
        }

        if (sign_in_password.text.toString().isEmpty()){
            sign_in_password_IL.error = "Required!"
            sign_in_password_IL.isErrorEnabled = true
            return false
        }else{
            sign_in_password_IL.isErrorEnabled = false
        }

        return true
    }


}