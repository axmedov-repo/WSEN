package uz.targetsoftwaredevelopment.myapplication.presentation.ui.pages

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
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
import uz.targetsoftwaredevelopment.myapplication.databinding.PageAddVideoBinding
import uz.targetsoftwaredevelopment.myapplication.utils.scope

@AndroidEntryPoint
class AddVideoPage : Fragment(R.layout.page_add_video) {

    private val binding by viewBinding(PageAddVideoBinding::bind)
//    private var videoView: VideoView? = null
    private val VIDEO_DIRECTORY = "/demonutsVideoooo"
    private val GALLERY = 1
    private val CAMERA = 2
    private lateinit var mediaController:MediaController

    override fun onViewCreated(view : View, savedInstanceState : Bundle?)=binding.scope {
        super.onViewCreated(view, savedInstanceState)
        requestMultiplePermissions()

        mediaController = MediaController(requireActivity())
        mediaController.setAnchorView(binding.videoView)

        videoView.setMediaController(mediaController)
            binding.fab.setOnClickListener {
                showPictureDialog()
        }

    }


    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select video from gallery", "Record video from camera")
        pictureDialog.setItems(pictureDialogItems) { dialog, which ->
            when (which) {
                0 -> chooseVideoFromGallary()
                1 -> takeVideoFromCamera()
            }
        }
        pictureDialog.show()
    }
    private fun chooseVideoFromGallary() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takeVideoFromCamera() {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            .putExtra(MediaStore.EXTRA_DURATION_LIMIT,15)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("what", "cancel")
            return
        }
        if(requestCode == GALLERY) {
            Log.d("what", "gallery")
            if (data != null) {
                val contentURI = data.data

                val selectedVideoPath = getPath(contentURI)
                Log.d("path", selectedVideoPath!!)

                binding.apply {
                    videoView.setVideoURI(contentURI)
                    videoView.requestFocus()
                    videoView.start()
                }

            }

        } else if (requestCode == CAMERA) {
            Log.d("what", "camera")
            val contentURI = data!!.data
            val recordedVideoPath = getPath(contentURI)
            Log.d("frrr", recordedVideoPath!!)

            binding.apply {
                videoView.setVideoURI(contentURI)
                videoView.requestFocus()
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
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
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
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(
                            requireContext(),
                            "All permissions are granted by user!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                        //openSettingsDialog()
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri:Uri = Uri.fromParts("package",activity?.packageName,null)
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
                Toast.makeText(requireContext(), "Some Error! ", Toast.LENGTH_SHORT).show()
            }
            .onSameThread()
            .check()

    }


}