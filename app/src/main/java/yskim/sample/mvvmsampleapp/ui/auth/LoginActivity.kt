package yskim.sample.mvvmsampleapp.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.data.db.AppDatabase
import yskim.sample.mvvmsampleapp.data.db.entities.User
import yskim.sample.mvvmsampleapp.data.network.MyApi
import yskim.sample.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository
import yskim.sample.mvvmsampleapp.databinding.ActivityLoginBinding
import yskim.sample.mvvmsampleapp.ui.home.HomeActivity
import yskim.sample.mvvmsampleapp.util.hide
import yskim.sample.mvvmsampleapp.util.show
import yskim.sample.mvvmsampleapp.util.snackbar

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    //private val factory : AuthViewModelFactory by instance<AuthViewModelFactory>()
    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = MyApi(networkConnectionInterceptor)
//        val db = AppDatabase(this)
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)

        //setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            println(user)
            if(user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

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

    override fun onStarted() {
        //toast("Login Started");
        //progress_bar.visibility = View.VISIBLE
        progress_bar.show()

    }

    override fun onSuccess(user: User) {
        progress_bar.hide()

        root_layout.snackbar("${user.username} is Logged In")
        //toast("${user.username} is Logged In")
    }
//    override fun onSuccess(loginResponse: LiveData<String>) {
//        //toast("Login Success");
//        progress_bar.hide()
//        loginResponse.observe(this, Observer { toast(it) })
//    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
//        toast(message);
    }
}