package io.bsu.mmf.helpme.featuremain.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import androidx.lifecycle.Observer
import id.zelory.compressor.Compressor
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.baseAndroid.utils.text
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.AddContactViewModel
import kotlinx.android.synthetic.main.fragment_add_contact.*
import org.koin.android.ext.android.inject
import java.io.ByteArrayOutputStream
import java.io.File


class AddContactFragment : BaseFragment(R.layout.fragment_add_contact) {

    private val viewModel by inject<AddContactViewModel>()

    private var contactBitmap: Bitmap? = null

    private lateinit var scaleBitmap: Bitmap


    private lateinit var imageToDb: ByteArray
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    isPriorityContact = false
                )
            )
        })

        contactImage.setOnClickListener {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, REQUEST_IMAGE_CHOOSE)
        }
    }

    private fun bitmapToBytes(bitmap: Bitmap?): ByteArray? {

        if (bitmap == null) return null

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()

    }

    private lateinit var picturePath: String
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == 123 && resultCode == RESULT_OK) {
////            val file = File(requireContext().cacheDir, FILE_NAME)
////            viewModel.changeLogo(ChangeLogoRequest(convertPhotoToRequest(file)))
////            file.delete()
//        } else if (requestCode == 143 && resultCode == RESULT_OK) {
//            val selectedImage = data?.data
//
//            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//            if (selectedImage != null) {
//                val cursor = activity?.contentResolver?.query(
//                    selectedImage,
//                    filePathColumn, null, null, null
//                )
//                if (cursor != null) {
//                    cursor.moveToFirst()
//                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//                    if (cursor.getString(columnIndex) == null) return
//                    picturePath = cursor.getString(columnIndex)
//
//                    contactBitmap = BitmapFactory.decodeFile(picturePath)
//                    contactImage.setImageBitmap(contactBitmap)
//                }
//
//                cursor?.close()
//            }
//
//
//        }
//    }

    private val REQUEST_IMAGE_CAPTURE = 1
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
        scaleBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val image = stream.toByteArray()
        imageToDb = image
        contactImage.setImageBitmap(scaleBitmap)
        return buildString {
            append("data:image/png;base64,")
            append(Base64.encodeToString(image, Base64.NO_WRAP))
        }
    }


}
