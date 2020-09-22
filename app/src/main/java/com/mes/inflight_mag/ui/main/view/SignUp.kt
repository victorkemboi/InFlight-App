package com.mes.inflight_mag.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.mes.inflight_mag.R
import com.mes.inflight_mag.ui.main.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_sign_up.*

@AndroidEntryPoint
class SignUp : AppCompatActivity() {
    private val signUpVM: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setObservers()

        go_to_sign_in.setOnClickListener {
            startActivity(
                Intent(
                    this@SignUp, SignIn::class.java
                )
            )
            finish()
        }
        sign_up_btn.setOnClickListener {
            signUp()
        }


    }

    private fun setObservers(){
        signUpVM.loading.observe(
            this,{
                    loading ->run{
                if (loading) {
                    sign_up_loader.visibility = View.VISIBLE

                }else{
                    sign_up_loader.visibility = View.GONE
                }

            }
            }
        )


        signUpVM.customer.observe(
            this,{
                    customer -> run{
                if (customer != null){

                    startActivity(
                        Intent(
                            this@SignUp, SignIn::class.java
                        )
                    )
                    finish()



                }
            }
            }
        )
        signUpVM.message.observe(
            this,{
                    message->run{
                if(message==""){
                    sign_up_message.visibility = View.GONE
                }else{

                    sign_up_message.text = message
                    sign_up_message.visibility = View.VISIBLE
                }
            }
            }
        )
    }

    private fun signUp(){
        if (isValidInput()){
            signUpVM.username = sign_up_username.text.toString().trim()
            signUpVM.email = sign_up_email.text.toString().trim()
            signUpVM.phoneNumber= sign_up_mobile.text.toString().toInt()
            signUpVM.password= sign_up_password.text.toString().trim()

            signUpVM.signUp()
        }else{
            sign_up_loader.visibility = View.GONE
        }
    }

    private fun isValidInput():Boolean{
        if (sign_up_username.text.toString().isEmpty()){
            sign_up_user_IL.error = "Required!"
            sign_up_user_IL.isErrorEnabled = true
            return false
        }else{
            sign_up_user_IL.isErrorEnabled = true
        }

        if (sign_up_email.text.toString().isEmpty()){
            sign_up_email_IL.error = "Required!"
            sign_up_email_IL.isErrorEnabled = true
            return false
        }else{
            sign_up_email_IL.isErrorEnabled = false
        }
        if (sign_up_mobile.text.toString().isEmpty()){
            sign_in_mobile_IL.error = "Required!"
            sign_in_mobile_IL.isErrorEnabled = true
            return false
        }else{
            sign_in_mobile_IL.isErrorEnabled = false
        }
        if (sign_up_password.text.toString().isEmpty()){
            sign_in_password_IL.error = "Required!"
            sign_in_password_IL.isErrorEnabled = true
            return false
        }else{
            sign_in_password_IL.isErrorEnabled = false
        }

        return true
    }
}