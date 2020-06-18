package yskim.sample.mvvmsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.databinding.ActivityLoginBinding
import yskim.sample.mvvmsampleapp.util.hide
import yskim.sample.mvvmsampleapp.util.show
import yskim.sample.mvvmsampleapp.util.toast

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {
        //toast("Login Started");
        //progress_bar.visibility = View.VISIBLE
        progress_bar.show()

    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        //toast("Login Success");
        progress_bar.hide()
        loginResponse.observe(this, Observer { toast(it) })
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        toast(message);
    }
}