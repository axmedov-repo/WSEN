package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.myapplication.R
import uz.targetsoftwaredevelopment.myapplication.databinding.DialogCameraBinding
import uz.targetsoftwaredevelopment.myapplication.databinding.PageAddVideoBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class AddVideoPage : Fragment(R.layout.page_add_video) {

    private val binding by viewBinding(PageAddVideoBinding::bind)
    private val VIDEO_DIRECTORY = "/demonutsVideoooo"
    private val GALLERY = 1
    private val CAMERA = 2
    private lateinit var mediaController: MediaController
    private var isGranted = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)


        mediaController = MediaController(requireActivity())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        fab.setOnClickListener {
            requestMultiplePermissions()
            if(isGranted){
                showPictureDialog()
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        val dialogCameraBinding = DialogCameraBinding.inflate(layoutInflater)
        pictureDialog.setView(dialogCameraBinding.root)
        val pictureBuilder = pictureDialog.create()
        pictureBuilder.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogCameraBinding.galleryView.setOnClickListener {
            pictureBuilder.dismiss()
            chooseVideoFromGallery()
        }

        dialogCameraBinding.cameraView.setOnClickListener {
            pictureBuilder.dismiss()
            takeVideoFromCamera()
        }
        pictureBuilder.show()
    }

    private fun chooseVideoFromGallery() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takeVideoFromCamera() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            .putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            return
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                val contentURI = data.data
                val selectedVideoPath = getPath(contentURI)
                binding.apply {
                    videoView.setVideoURI(contentURI)
//                    videoView.requestFocus()
                    videoView.start()
                }

            }

        } else if (requestCode == CAMERA) {
            val contentURI = data!!.data
            val recordedVideoPath = getPath(contentURI)
            binding.apply {
                videoView.setVideoURI(contentURI)
//                videoView.requestFocus()
                videoView.start()
            }
        }
    }

    private fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Video.Media.DATA)
        val cursor = activity?.contentResolver?.query(
            uri!!,
            projection,
            null,
            null,
            null
        )
        return if (cursor != null) {
            val column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } else
            null
    }


    private fun requestMultiplePermissions() {
        Dexter.withActivity(requireActivity())
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        isGranted=true
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                        //openSettingsDialog()
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", activity?.packageName, null)
                        intent.data = uri
                        startActivity(intent)
                    }
                }


                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(requireContext(), getString(R.string.some_error), Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()
    }

}