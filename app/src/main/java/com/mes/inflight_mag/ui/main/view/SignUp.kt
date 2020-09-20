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
                    this@SignUp, SignUp::class.java
                )
            )
        }
        sign_up_btn.setOnClickListener {
            signUp()
        }


    }

    private fun setObservers(){
        signUpVM.loading.observe(
            this,{
                    loading ->run{
                Log.d("Loading changed",loading.toString())
                if (loading) {
                    sign_up_loader.visibility = View.VISIBLE

                }else{
                    sign_up_loader.visibility = View.GONE
                }

            }
            }
        )

        signUpVM.signUpSuccess.observe(
            this,{
                    responseSuccess ->run{
                if (responseSuccess){
                    //succeeded

                }else{
                    Log.d("Loading changed",responseSuccess.toString())
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
            return false
        }

        if (sign_up_email.text.toString().isEmpty()){
            return false
        }
        if (sign_up_mobile.text.toString().isEmpty()){
            return false
        }
        if (sign_up_password.text.toString().isEmpty()){
            return false
        }

        return true
    }
}