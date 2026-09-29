package com.hpyk.closet.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.hpyk.closet.presentation.navigation.ClosetNavGraph
import com.hpyk.closet.presentation.recommendation.RecommendationViewModel
import com.hpyk.closet.presentation.upload.UploadViewModel
import com.hpyk.closet.presentation.wardrobe.WardrobeViewModel
import com.hpyk.closet.ui.theme.ClosetTheme

class MainActivity : ComponentActivity() {

    private lateinit var wardrobeViewModel: WardrobeViewModel
    private lateinit var uploadViewModel: UploadViewModel
    private lateinit var recommendationViewModel: RecommendationViewModel

    private val cameraLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == RESULT_OK) {

                val bitmap =
                    result.data
                        ?.extras
                        ?.get("data") as? Bitmap

                bitmap?.let {
                    uploadViewModel.setPhoto(it)
                }
            }
        }

    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {
                openCamera()
            } else {
                uploadViewModel.setCameraError(
                    "Camera permission was denied. You can use Gallery instead."
                )
            }
        }

    private val galleryLauncher =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) { uri ->

            uri?.let {
                uploadViewModel.setPhotoFromUri(it)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wardrobeViewModel =
            ViewModelProvider(this)[WardrobeViewModel::class.java]

        uploadViewModel =
            ViewModelProvider(this)[UploadViewModel::class.java]

        recommendationViewModel =
            ViewModelProvider(this)[RecommendationViewModel::class.java]

        setContent {

            ClosetTheme {

                ClosetNavGraph(
                    wardrobeViewModel = wardrobeViewModel,
                    uploadViewModel = uploadViewModel,
                    recommendationViewModel = recommendationViewModel,

                    onCamera = {
                        requestCamera()
                    },

                    onGallery = {
                        galleryLauncher.launch("image/*")
                    }
                )
            }
        }
    }

    private fun requestCamera() {

        val permission =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            )

        if (permission == PackageManager.PERMISSION_GRANTED) {

            openCamera()

        } else {

            cameraPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }

    private fun openCamera() {

        val intent =
            Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
            )

        if (
            intent.resolveActivity(
                packageManager
            ) != null
        ) {

            cameraLauncher.launch(intent)

        } else {

            uploadViewModel.setCameraError(
                "No camera app is available on this device. You can use Gallery instead."
            )
        }
    }
}