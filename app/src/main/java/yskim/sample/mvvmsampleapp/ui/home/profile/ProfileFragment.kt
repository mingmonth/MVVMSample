package yskim.sample.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.databinding.ProfileFragmentBinding

class ProfileFragment : Fragment(), KodeinAware {

//    companion object {
//        fun newInstance() = ProfileFragment()
//    }

    override val kodein by kodein()

    private lateinit var viewModel: ProfileViewModel
    private val factory: ProfileViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : ProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)
//        return inflater.inflate(R.layout.profile_fragment, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}