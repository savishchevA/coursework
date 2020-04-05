package io.bsu.mmf.helpme.featuremain.fragment

import android.graphics.BitmapFactory
import android.location.Geocoder
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.viewmodel.MapViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.*


class MapFragment : BaseFragment(R.layout.fragment_map) {

    private val viewModel by inject<MapViewModel>()

    private lateinit var map: MapView
    private lateinit var googleMap: GoogleMap

    private lateinit var geocoder: Geocoder


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val rootView: View = inflater.inflate(R.layout.fragment_map, container, false)

        map = rootView.findViewById(R.id.mapView) as MapView
        map.onCreate(savedInstanceState)
        map.getMapAsync {
            googleMap = it
            addCurrentMarker()
        }
        map.onResume()

        geocoder = Geocoder(requireContext(), Locale.ENGLISH)



        return rootView
    }

    private fun addCurrentMarker() {

        viewModel.allContact.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.forEach {
                addMarker(it)
            }
        })
    }

    private fun addMarker(contact: Contact) {

        val image = if (contact.contactImage == null) {
            BitmapDescriptorFactory.fromResource(R.drawable.ic_robot)
        } else {
            (BitmapDescriptorFactory.fromBitmap(
                BitmapFactory.decodeByteArray(
                    contact.contactImage,
                    0,
                    contact.contactImage!!.size
                )
            ))

        }

        googleMap.addMarker(
            MarkerOptions()
                .position(
                    LatLng(
                        contact.latitude,
                        contact.longitude
                    )
                )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onResume() {
        super.onResume()
        map?.onResume()
    }

    override fun onPause() {
        super.onPause()
        map?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        map?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map?.onLowMemory()
    }
}