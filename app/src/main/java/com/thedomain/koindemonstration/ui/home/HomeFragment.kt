package com.thedomain.koindemonstration.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thedomain.koindemonstration.databinding.FragmentHomeBinding
import com.thedomain.koindemonstration.koin.User
import com.thedomain.koindemonstration.koin.UserRepoImpl
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val userRepo: UserRepoImpl by inject()

    var textView: TextView? = null

    private var firstName: EditText? = null
    private var userName : EditText? = null
    private var submitButton: Button? = null

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textView = binding.textHome
        firstName = binding.firstnameEt
        userName = binding.userInputEt
        submitButton = binding.homeSubmit

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView?.text = it
        }

        submitButton?.setOnClickListener {

            userName?.visibility = View.GONE
            firstName?.visibility = View.GONE
            submitButton?.visibility = View.GONE

            val newUser = User(
                userName?.text.toString(),
                firstName?.text.toString(),
                1
            )

            userRepo.addUser(newUser)

            textView?.text = "Welcome, " + newUser.displayName
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        if (userRepo.user.userId != 0) {
            userName?.visibility = View.GONE
            firstName?.visibility = View.GONE
            submitButton?.visibility = View.GONE

            textView?.text = "Welcome back " + userRepo.user.displayName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}