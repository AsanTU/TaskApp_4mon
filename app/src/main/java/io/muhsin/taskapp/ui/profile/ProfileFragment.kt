package io.muhsin.taskapp.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import io.muhsin.taskapp.data.local.Pref
import io.muhsin.taskapp.databinding.FragmentProfileBinding
import io.muhsin.taskapp.utils.loadImageGallery


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.etName.setText(pref.getUserName())
        binding.profileImage.setOnClickListener {
            saveImage()
        }
        binding.profileImage.loadImageGallery(pref.getImage())
        saveName()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri = data.data
            pref.saveImage(imageUri.toString())
            binding.profileImage.loadImageGallery(pref.getImage())
        }
    }

    private fun saveImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    private fun saveName() {
        binding.etName.addTextChangedListener {
            pref.saveUserName(binding.etName.text.toString())
        }
    }
    companion object{
        const val PICK_IMAGE_REQUEST = 1
    }
}