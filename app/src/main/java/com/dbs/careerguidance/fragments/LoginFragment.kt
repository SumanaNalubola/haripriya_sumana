package com.dbs.careerguidance.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dbs.careerguidance.R
import com.dbs.careerguidance.data.api.RetrofitInstance
import com.dbs.careerguidance.databinding.FragmentLoginBinding
import com.dbs.careerguidance.model.ServerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener {

            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()

            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                authenticate(email,password)

            } else {
                Toast.makeText(context, "Please enter all data", Toast.LENGTH_LONG).show()
            }

        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun authenticate(email: String, password: String) {

        RetrofitInstance.api.checkUser(email,password).enqueue(object: Callback<ServerResponse> {
            override fun onResponse(call: Call<ServerResponse>, response: Response<ServerResponse>) {
                if(response.isSuccessful) {

                    val checkResponse: ServerResponse? = response.body()

                    when (checkResponse?.status) {
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
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}
