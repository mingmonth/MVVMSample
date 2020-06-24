package yskim.sample.mvvmsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.progress_bar
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.data.db.entities.User
import yskim.sample.mvvmsampleapp.databinding.ActivityLoginBinding
import yskim.sample.mvvmsampleapp.databinding.ActivitySignupBinding
import yskim.sample.mvvmsampleapp.ui.home.HomeActivity
import yskim.sample.mvvmsampleapp.util.*

class SignupActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
//        binding.viewmodel = viewModel

//        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            println(user)
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            userSignup()
        }

    }

    private fun userSignup() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val password1 = binding.editTextPasswordConfirm.text.toString().trim()

        //@todo add input validations

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userSignup(name, email, password)

                if(authResponse.user != null) {
                    viewModel.saveLoggedInUser(authResponse.user)
                } else {
                    binding.root.snackbar(authResponse.message!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }

    }

//    override fun onStarted() {
//        progress_bar.show()
//    }
//
//    override fun onSuccess(user: User) {
//        progress_bar.hide()
//        println("${user.username} is Logged In")
//        //root_layout.snackbar("${user.username} is Logged In")
//    }
//
//    override fun onFailure(message: String) {
//        progress_bar.hide()
//        println(message)
//        signup_root_layout.snackbar(message)
////        root_layout.snackbar(message)
//    }
}