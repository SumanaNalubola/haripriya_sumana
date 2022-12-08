package com.dbs.careerguidance.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.dbs.careerguidance.R
import com.dbs.careerguidance.data.api.RetrofitInstance
import com.dbs.careerguidance.databinding.FragmentRegisterBinding
import com.dbs.careerguidance.model.ServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.signupBtn.setOnClickListener {
            val username = binding.signupUsername.text.toString().trim()
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signupPassword.text.toString().trim()

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                register(username,email,password)
            } else {
                Toast.makeText(context, "Please enter all data", Toast.LENGTH_LONG).show()
            }
        }

        binding.signin.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun register(username: String, email: String, password: String) {
        RetrofitInstance.api.registerUser(username,email,password).enqueue(object: Callback<ServerResponse> {
            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if(response.isSuccessful) {

                    val signupResponse: ServerResponse? = response.body()

                    when (signupResponse?.status) {
                        1 -> {
                            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_LONG).show()
                            navigate()
                        }
                        0 -> {
                            Toast.makeText(context, response.body()!!.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ServerResponse>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }

        })
    }

    private fun navigate() {
        findNavController().popBackStack()
    }

}