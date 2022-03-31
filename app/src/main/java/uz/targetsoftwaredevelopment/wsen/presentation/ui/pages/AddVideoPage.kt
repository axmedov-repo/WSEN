package uz.targetsoftwaredevelopment.wsen.presentation.ui.pages

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iceteck.silicompressorr.SiliCompressor
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import uz.targetsoftwaredevelopment.wsen.R
import uz.targetsoftwaredevelopment.wsen.app.App
import uz.targetsoftwaredevelopment.wsen.data.remote.requests.AddVideoRequest
import uz.targetsoftwaredevelopment.wsen.data.remote.responses.AddVideoResponse
import uz.targetsoftwaredevelopment.wsen.databinding.DialogCameraBinding
import uz.targetsoftwaredevelopment.wsen.databinding.PageAddVideoBinding
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.AddVideoPageViewModel
import uz.targetsoftwaredevelopment.wsen.presentation.viewmodels.pagesvidemodel.impl.AddVidePageViewModelImpl
import uz.targetsoftwaredevelopment.wsen.utils.gone
import uz.targetsoftwaredevelopment.wsen.utils.scope
import uz.targetsoftwaredevelopment.wsen.utils.visible
import java.io.File
import java.net.URISyntaxException

@AndroidEntryPoint
class AddVideoPage : Fragment(R.layout.page_add_video) {
    private val binding by viewBinding(PageAddVideoBinding::bind)
    private val viewModel: AddVideoPageViewModel by viewModels<AddVidePageViewModelImpl>()
    //    private var videoView: VideoView? = null
    private val VIDEO_DIRECTORY = "/demonutsVideoooo"
    private val GALLERY = 1
    private val CAMERA = 2
    private lateinit var mediaController: MediaController
    private var isGranted = false
    private var videoUri: Uri = Uri.EMPTY

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        mediaController = MediaController(requireActivity())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        requestMultiplePermissions()

        fab.setOnClickListener {
            requestMultiplePermissions()
            if (isGranted) {
                showPictureDialog()
            }
        }

        addVideoCv.setOnClickListener {
            if (videoUri != Uri.EMPTY && createTitleEt.text.isNotEmpty() && descriptionEt.text.isNotEmpty() ) {
                getVideoFile(videoUri)
            } else {
                FancyToast.makeText(requireContext(),getString(R.string.fill_our_this_fields),FancyToast.LENGTH_LONG,FancyToast.INFO,true)
                    .show()
            }
        }

        viewModel.addVideoResponseLiveData.observe(viewLifecycleOwner, addVideoObserver)
        viewModel.videoCompressedLiveData.observe(viewLifecycleOwner, videoCompressedObserver)
    }

    private val addVideoObserver = Observer<AddVideoResponse> {
        binding.progressView.gone()
        binding.progressView.clearAnimation()
        binding.createTitleEt.setText("")
        binding.descriptionEt.setText("")
        binding.locationEt.setText("")

        FancyToast.makeText(requireContext(),getString(R.string.video_success_add),FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true)
            .show()
    }

    private val videoCompressedObserver = Observer<File> { compressedVideoFile ->
        viewModel.addVideo(
            AddVideoRequest(
                compressedVideoFile,
                binding.createTitleEt.text.toString(),
                binding.locationEt.text.toString(),
                binding.descriptionEt.text.toString()
            )
        )
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
                videoUri = contentURI!!
                val selectedVideoPath = getPath(contentURI)
                binding.apply {
                    videoView.setVideoURI(contentURI)
//                    videoView.requestFocus()
                    videoView.start()
                }

            }

        } else if (requestCode == CAMERA) {
            val contentURI = data!!.data
            videoUri = contentURI!!
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
                        isGranted = true
//                        showPictureDialog()
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
                Toast.makeText(requireContext(), getString(R.string.some_error), Toast.LENGTH_SHORT)
                    .show()
            }
            .onSameThread()
            .check()
    }

    private fun getVideoFile(videoUri: Uri) {
        val file = File(Environment.getExternalStorageDirectory().absolutePath)
        CompressVideo().execute("false", videoUri.toString(), file.path)
    }

    @SuppressLint("StaticFieldLeak")
    inner class CompressVideo : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            binding.progressView.visible()
            binding.progressView.animate()
        }

        override fun doInBackground(vararg strings: String?): String {
            var videoPath: String? = null
            try {
                var uri: Uri = Uri.parse(strings[1])
                videoPath = SiliCompressor.with(App.instance).compressVideo(uri, strings[2])
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            return videoPath!!
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            var file: File = File(result)
            viewModel.videoCompressed(file)
        }
    }
}

