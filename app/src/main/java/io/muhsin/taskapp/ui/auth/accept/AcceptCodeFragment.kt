package io.muhsin.taskapp.ui.auth.accept

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import io.muhsin.taskapp.R
import io.muhsin.taskapp.databinding.FragmentAcceptCodeBinding
import io.muhsin.taskapp.utils.showToast


class AcceptCodeFragment : Fragment() {

    private lateinit var binding: FragmentAcceptCodeBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAcceptCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString("verId")
        auth = FirebaseAuth.getInstance()
        binding.btnAccept.setOnClickListener {
            acceptCode(verId)
        }
    }

    private fun acceptCode(verId: String?) {
        val code = binding.etCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(verId!!, code)
        auth.signInWithCredential(credential).addOnSuccessListener {
            findNavController().navigate(R.id.navigation_home)
        }.addOnFailureListener {
            showToast(it.message.toString())
        }
    }

}