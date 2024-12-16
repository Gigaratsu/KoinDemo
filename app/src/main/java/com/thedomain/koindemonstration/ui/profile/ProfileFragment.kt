package com.thedomain.koindemonstration.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thedomain.koindemonstration.R
import com.thedomain.koindemonstration.databinding.FragmentProfileBinding
import com.thedomain.koindemonstration.koin.User
import com.thedomain.koindemonstration.koin.UserRepoImpl
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment() {

    private val userRepo: UserRepoImpl by inject()


    private var _binding: FragmentProfileBinding? = null

    var currUser: User? = null

    var displayNameContent: TextView? = null
    var displayNameTitle: TextView? = null

    var firstNameContent: TextView? = null
    var firstNameTitle: TextView? = null

    var uuidContent: TextView? = null
    var uuidTitle: TextView? = null

    var logoutButton: Button? = null
    var defaultText: TextView? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        currUser = userRepo.user

        displayNameContent = binding.profileDisplayNameContent
        displayNameTitle = binding.profileDisplayNameTitle

        firstNameContent = binding.profileFirstNameContent
        firstNameTitle = binding.profileFirstNameTitle

        uuidContent = binding.profileUseridContent
        uuidTitle = binding.profileUseridTitle

        logoutButton = binding.logoutButton
        defaultText = binding.defaultMessage

        logoutButton?.setOnClickListener {
            userRepo.clearUser()

            displayNameContent?.visibility = View.GONE
            displayNameTitle?.visibility = View.GONE

            firstNameContent?.visibility = View.GONE
            firstNameTitle?.visibility = View.GONE

            uuidContent?.visibility = View.GONE
            uuidTitle?.visibility = View.GONE

            logoutButton?.visibility = View.GONE

            defaultText?.visibility = View.VISIBLE
            defaultText?.text = getString(R.string.profile_default)
        }

        setContent()

        return root
    }

    override fun onResume() {
        super.onResume()
        setContent()
    }

    private fun setContent() {
        if (currUser?.userId != 0) {
            displayNameContent?.visibility = View.VISIBLE
            displayNameTitle?.visibility = View.VISIBLE

            firstNameContent?.visibility = View.VISIBLE
            firstNameTitle?.visibility = View.VISIBLE

            uuidContent?.visibility = View.VISIBLE
            uuidTitle?.visibility = View.VISIBLE

            logoutButton?.visibility = View.VISIBLE

            defaultText?.visibility = View.GONE

            displayNameContent?.text = currUser?.displayName
            firstNameContent?.text = currUser?.firstName
            uuidContent?.text = String.format(getString(R.string.profile_uuid_content), currUser?.userId)


        } else {
            displayNameContent?.visibility = View.GONE
            displayNameTitle?.visibility = View.GONE

            firstNameContent?.visibility = View.GONE
            firstNameTitle?.visibility = View.GONE

            uuidContent?.visibility = View.GONE
            uuidTitle?.visibility = View.GONE

            logoutButton?.visibility = View.GONE

            defaultText?.visibility = View.VISIBLE
            defaultText?.text = getString(R.string.profile_default)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}