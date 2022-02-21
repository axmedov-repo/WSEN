package uz.targetsoftwaredevelopment.myapplication.presentation.ui

// create by khumoyun 11.02.2022

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.DialogCameraBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.DialogPermissionBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.FragmentProfileBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ProfileFragment : Fragment() {

    private lateinit var binding:FragmentProfileBinding
    var OLD_REQUEST_CODE = 1
    var CAMERA_REQUEST_CODE = 1
    lateinit var currentImagePath: String
    lateinit var uri:Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)

        binding.apply {

            addImg.setOnClickListener {
                onClickAddImg()
            }

            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack(R.id.homeScreen,false)
            }


        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            if(uri!=null){
                binding.profileImg.setImageURI(uri)
                val openInputStream = activity?.contentResolver?.openInputStream(uri)
                val m = System.currentTimeMillis()
                val file = File(activity?.filesDir, "$m.jpg")
                val fileOutputStream = FileOutputStream(file)
                openInputStream?.copyTo(fileOutputStream)
                openInputStream?.close()
//            val readBytes = file.readBytes()
            }
        }

    private fun getCameraPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object :PermissionListener{
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    Toast.makeText(requireContext(), "Allowed", Toast.LENGTH_SHORT).show()

                    //open camera
                    val imageFile = createImageFile()
                    uri = FileProvider.getUriForFile(requireContext(),uz.targetsoftwaredevelopment.myapplication.BuildConfig.APPLICATION_ID,imageFile)
                    takePhoto.launch(uri)
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(requireContext(), "Deny", Toast.LENGTH_SHORT).show()
                    if(response.isPermanentlyDenied){
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri:Uri = Uri.fromParts("package",activity?.packageName,null)
                        intent.data = uri
                        startActivity(intent)
                    }

                }

                override fun onPermissionRationaleShouldBeShown(
                    request: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    Toast.makeText(requireContext(), "Beshown", Toast.LENGTH_SHORT).show()
                    val dialogCamera= AlertDialog.Builder(requireContext())
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
                Toast.makeText(requireContext(), "Some Error! ", Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()


    }

    private fun getGalleryPermission() {
        Dexter.withContext(requireContext())
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object :PermissionListener{
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    Toast.makeText(requireContext(), "Allowed", Toast.LENGTH_SHORT).show()

                    // open gallery
                    getGalleryImage.launch("image/*")

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    Toast.makeText(requireContext(), "Deny", Toast.LENGTH_SHORT).show()
                    if(response.isPermanentlyDenied){
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package",activity?.packageName,null)
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
                Toast.makeText(requireContext(), "Some Error! ", Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("what", "cancle")
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
//                Log.d(TAG, "onActivityResult: $currentImagePath")
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
//                Log.d(TAG, "File: $uri")
                binding.profileImg.setImageURI(uri)
            }
        }

}