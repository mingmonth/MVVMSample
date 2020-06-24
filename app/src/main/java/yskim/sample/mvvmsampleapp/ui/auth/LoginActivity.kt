package yskim.sample.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.data.db.entities.User
import yskim.sample.mvvmsampleapp.databinding.ActivityLoginBinding
import yskim.sample.mvvmsampleapp.ui.home.HomeActivity
import yskim.sample.mvvmsampleapp.util.*

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    //private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private val factory : AuthViewModelFactory by instance()

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = MyApi(networkConnectionInterceptor)
//        val db = AppDatabase(this)
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)

        //setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
//        binding.viewmodel = viewModel
//
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

        binding.buttonSignIn.setOnClickListener {
            println("debug : buttonSignIn")
            loginUser()
        }

        binding.textViewSignUp.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

//        viewModel.getLoggedInUser().observe(this, Observer { user ->
//            //println("getLoggedInUser:" + user.get)
//            if(user != null) {
//                Intent(this, HomeActivity::class.java).also {
//                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(it)
//                }
//            }
//        })

    }

    private fun loginUser() {
        var email = binding.editTextEmail.text.toString().trim()
        var password = binding.editTextPassword.text.toString().trim()

        //@Todo validate user inputs

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userLogin(email, password)

                if(authResponse.user != null) {
                    println("debug : $email $password")
                    viewModel.saveLoggedInUser(authResponse.user)
                } else {
                    binding.rootLayout.snackbar(authResponse.message!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }


//    override fun onStarted() {
//        //toast("Login Started");
//        //progress_bar.visibility = View.VISIBLE
//        progress_bar.show()
//
//    }
//
//    override fun onSuccess(user: User) {
//        progress_bar.hide()
//
//        root_layout.snackbar("${user.username} is Logged In")
//        //toast("${user.username} is Logged In")
//    }
////    override fun onSuccess(loginResponse: LiveData<String>) {
////        //toast("Login Success");
////        progress_bar.hide()
////        loginResponse.observe(this, Observer { toast(it) })
////    }
//
//    override fun onFailure(message: String) {
//        progress_bar.hide()
//        root_layout.snackbar(message)
////        toast(message);
//    }
}