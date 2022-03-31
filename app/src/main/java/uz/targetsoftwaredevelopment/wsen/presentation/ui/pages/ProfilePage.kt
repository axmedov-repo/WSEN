package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.BuildConfig
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.UserDataRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.UserDataResponse
import uz.targetsoftwaredevelopment.wsen.databinding.DialogCameraBinding
import uz.targetsoftwaredevelopment.wsen.databinding.DialogPermissionBinding
import uz.targetsoftwaredevelopment.wsen.databinding.PageProfileBinding
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.ProfilePageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.ProfilePageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.scope
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@AndroidEntryPoint
class ProfilePage : Fragment(R.layout.page_profile) {
    private val binding by viewBinding(PageProfileBinding::bind)
    private val viewModel: ProfilePageViewModel by viewModels<ProfilePageViewModelImpl>()
    private var isReadyPhone = true

    var OLD_REQUEST_CODE = 1
    var CAMERA_REQUEST_CODE = 1
    lateinit var currentImagePath: String
    var imageUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserData()
        viewModel.getUserPhoneNumber()

        addImg.setOnClickListener {
            onClickAddImg()
        }

        phoneNumberEt.apply {
            setOnFocusChangeListener { view, focused ->
                if (!focused && this.text != null && this.text.toString()
                        .isNotEmpty() && !this.text.toString().contains("+998")
                ) {
                    isReadyPhone = false
                    phoneEtLayout.isErrorEnabled = true
                    phoneEtLayout.error = getString(R.string.error_phone_not_contains_998)
                }
            }
            addTextChangedListener {
                it?.let {
                    phoneEtLayout.isErrorEnabled = false
                    isReadyPhone = it.isEmpty() || (it.isNotEmpty() && it.contains("+998"))
                    check()
                }
            }
        }

        btnSaveProfile.setOnClickListener {
            if (isReadyPhone) {
                viewModel.setUserPhoneNumber(phoneNumberEt.text.toString())
            }
            viewModel.editUserData(
                UserDataRequest(
                    lastnameEt.text.toString(),
                    if (imageUri != null) {
                        File(imageUri!!.path!!)
                    } else {
                        null
                    },
                    firstnameEt.text.toString(),
                    emailTv.text.toString(),
                    usernameTv.text.toString()
                )
            )
        }

        viewModel.getUserDataLiveDataRequest.observe(viewLifecycleOwner, getUserDataObserver)
        viewModel.getUserPhoneNumberLiveData.observe(viewLifecycleOwner, getUserPhoneNumberObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
        viewModel.editUserDataLiveDataRequest.observe(viewLifecycleOwner, editUserDataObserver)
    }

    private val getUserDataObserver = Observer<UserDataResponse> { userData ->
        binding.apply {

            Glide.with(profileImg.context)
                .load(userData.photo)
                .placeholder(R.drawable.default_profile_img2)
                .error(R.drawable.default_profile_img2)
                .into(profileImg)

            usernameTv.text = userData.username
            firstnameEt.setText(userData.first_name)
            lastnameEt.setText(userData.last_name)
            emailTv.text = userData.email
        }
    }

    private val getUserPhoneNumberObserver = Observer<String> { userPhoneNumber ->
        binding.phoneNumberEt.setText(userPhoneNumber)
    }

    private val errorObserver = Observer<String> { errorMessage ->
        FancyToast.makeText(
            requireContext(),
            errorMessage,
            FancyToast.LENGTH_LONG,
            FancyToast.ERROR,
            false
        ).show()
    }

    private val editUserDataObserver = Observer<UserDataResponse> {
        viewModel.getUserData()
    }

    private fun onClickAddImg() {
        val dialog = AlertDialog.Builder(requireContext())
        val dialogCameraBinding = DialogCameraBinding.inflate(layoutInflater)
        dialog.setView(dialogCameraBinding.root)
        val builder = dialog.create()
        builder.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogCameraBinding.galleryView.setOnClickListener {
            builder.dismiss()
            getGalleryPermission()
        }

        dialogCameraBinding.cameraView.setOnClickListener {
            builder.dismiss()
            getCameraPermission()
        }

        builder.show()

    }

    private var getGalleryImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                binding.profileImg.setImageURI(uri)
                val openInputStream = activity?.contentResolver?.openInputStream(uri)
                val m = System.currentTimeMillis()
                val file = File(activity?.filesDir, "$m.jpg")
                val fileOutputStream = FileOutputStream(file)
                openInputStream?.copyTo(fileOutputStream)
                openInputStream?.close()
                val readBytes = file.readBytes()
            }
        }

    private fun getCameraPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(permission: PermissionGrantedResponse?) {
                    //open camera
                    val imageFile = createImageFile()
                    imageUri = FileProvider.getUriForFile(
                        requireContext(),
                        BuildConfig.APPLICATION_ID, imageFile
                    )
                    takePhoto.launch(imageUri)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", activity?.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    request: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    val dialogCamera = AlertDialog.Builder(requireContext())
                    val dialogPermissionBinding = DialogPermissionBinding.inflate(layoutInflater)
                    dialogCamera.setView(dialogPermissionBinding.root)
                    val builderCamera = dialogCamera.create()
                    builderCamera.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogPermissionBinding.cancelCv.setOnClickListener {
                        token?.cancelPermissionRequest()
                        builderCamera.dismiss()
                    }

                    dialogPermissionBinding.allowCv.setOnClickListener {
                        token?.continuePermissionRequest()
                        builderCamera.dismiss()
                    }
                    builderCamera.show()

                }
            }).withErrorListener {
                FancyToast.makeText(
                    requireContext(),
                    getString(R.string.some_error),
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                )
                    .show()
            }
            .onSameThread()
            .check()


    }

    private fun getGalleryPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    // open gallery
                    getGalleryImage.launch("image/*")

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", activity?.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    requset: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    val dialogGallery = AlertDialog.Builder(requireContext())
                    val dialogPermissionBinding = DialogPermissionBinding.inflate(layoutInflater)
                    dialogGallery.setView(dialogPermissionBinding.root)
                    val builderGallery = dialogGallery.create()
                    builderGallery.window?.setBackgroundDrawableResource(android.R.color.transparent)

                    dialogPermissionBinding.cancelCv.setOnClickListener {
                        token?.cancelPermissionRequest()
                        builderGallery.dismiss()
                    }

                    dialogPermissionBinding.allowCv.setOnClickListener {
                        token?.continuePermissionRequest()
                        builderGallery.dismiss()
                    }
                    builderGallery.show()
                }
            }).withErrorListener {
                FancyToast.makeText(
                    requireContext(),
                    getString(R.string.some_error),
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                )
                    .show()
            }
            .onSameThread()
            .check()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        if (requestCode == OLD_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val uri = data?.data ?: return
            binding.profileImg.setImageURI(uri)
            val openInputStream = activity?.contentResolver?.openInputStream(uri)
            val m = System.currentTimeMillis()
            val file = File(activity?.filesDir, "$m.jpg")
            val fileOutputStream = FileOutputStream(file)
            openInputStream?.copyTo(fileOutputStream)
            openInputStream?.close()
        }

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            if (::currentImagePath.isInitialized) {
                binding.profileImg.setImageURI(Uri.fromFile(File(currentImagePath)))
            }
        }

    }

    @Throws(IOException::class)
    fun createImageFile(): File {
        val m = System.currentTimeMillis()
        val externalFilesDir = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(m.toString(), ".jpg", externalFilesDir).apply {
            currentImagePath = absolutePath
        }
    }

    private var takePhoto =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                binding.profileImg.setImageURI(imageUri)
            }
        }

    private fun check() {
        binding.btnSaveProfile.isEnabled = isReadyPhone
    }
}