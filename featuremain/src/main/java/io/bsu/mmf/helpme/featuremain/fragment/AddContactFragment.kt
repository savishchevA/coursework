package io.bsu.mmf.helpme.featuremain.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import com.github.florent37.runtimepermission.kotlin.PermissionException
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import id.zelory.compressor.Compressor
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.baseAndroid.utils.text
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.AddContactViewModel
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream
import java.io.File


class AddContactFragment : BaseFragment(R.layout.fragment_add_contact) {

    private val viewModel by inject<AddContactViewModel>()

    private var scaleBitmap: Bitmap? = null


    private var imageToDb: ByteArray? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        askStoragePermission()
        btn_save.setOnClickListener {

            viewModel.getAddressCoordinate(contact_address.text)

        }

        viewModel.successSavingContact.observeEvent(viewLifecycleOwner) {
            navController.navigateUp()
        }

        viewModel.addressCoordinate.observe(viewLifecycleOwner, Observer {
            viewModel.saveContact(
                Contact(
                    name = contact_name.text,
                    phoneNumber = contact_phone.text,
                    message = contact_message.text,
                    address = contact_address.text,
                    longitude = it.longitude,
                    latitude = it.latitude,
                    contactImage = imageToDb,
                    isPriorityContact = viewModel.getNeedPriorityContactStatus()
                )
            )
        })

        contactImage.setOnClickListener {
            if (storagePermission) {
                val pickPhoto = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )

                startActivityForResult(pickPhoto, REQUEST_IMAGE_CHOOSE)
            } else {
                askStoragePermission()
            }


        }
    }

    private var storagePermission = false


    @SuppressLint("MissingPermission")
    private fun askStoragePermission() {
        if ((ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED)
        ) {
            storagePermission = true
        } else {
            lifecycle.coroutineScope.launch {
                try {
                    val result = askPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    //all permissions already granted or just granted
                    //your action
                    storagePermission = result.isAccepted


                } catch (e: PermissionException) {
                }
            }
        }
    }


    private lateinit var picturePath: String

    private val REQUEST_IMAGE_CHOOSE = 2

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CHOOSE && resultCode == RESULT_OK) {
            val selectedImage = data?.data

            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            if (selectedImage != null) {
                val cursor = activity?.contentResolver?.query(
                    selectedImage,
                    filePathColumn, null, null, null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    if (cursor.getString(columnIndex) == null) return
                    picturePath = cursor.getString(columnIndex)
                }

                cursor?.close()
            }

            val file = File(picturePath)
            convertPhotoToRequest(file)
        }
    }

    private fun convertPhotoToRequest(file: File): String {
        val compressImage = Compressor(context)
            .setMaxWidth(640)
            .setMaxHeight(640)
            .setQuality(95)
            .setCompressFormat(Bitmap.CompressFormat.WEBP)
            .compressToBitmap(file)

        val stream = ByteArrayOutputStream()
        scaleBitmap = Bitmap.createScaledBitmap(compressImage, 500, 500, true)
        scaleBitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val image = stream.toByteArray()
        imageToDb = image
        contactImage.setImageBitmap(scaleBitmap)
        return buildString {
            append("data:image/png;base64,")
            append(Base64.encodeToString(image, Base64.NO_WRAP))
        }
    }


}
