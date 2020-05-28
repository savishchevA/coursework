package io.bsu.mmf.helpme.featuremain.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.data.entity.local.Contact
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.fragment.map.directionhelpers.FetchURL
import io.bsu.mmf.helpme.featuremain.fragment.map.directionhelpers.TaskLoadedCallback
import io.bsu.mmf.helpme.featuremain.viewmodel.MapViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.*


class MapFragment : BaseFragment(R.layout.fragment_map) {



    private val place1: MarkerOptions? = null
    private var place2: MarkerOptions? = null
    var getDirection: Button? = null
    private var currentPolyline: Polyline? = null

    private val viewModel by inject<MapViewModel>()

    private lateinit var map: MapView
    private lateinit var googleMap: GoogleMap

    private lateinit var geocoder: Geocoder


    private val taskObject =
        TaskLoadedCallback { values ->
            if (currentPolyline != null)
                currentPolyline?.remove()
            currentPolyline = googleMap.addPolyline(values[0] as PolylineOptions?)
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), android.R.color.transparent
        )
    }

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

    private val locationsList = mutableListOf<Location>()
    private fun addCurrentMarker() {

        viewModel.allContact.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it.forEach {
                val contactLocation = Location(it.name)
                contactLocation.latitude = it.latitude
                contactLocation.longitude = it.longitude

                locationsList.add(
                    contactLocation
                )

                if (myLocation != null) {
                    FetchURL(taskObject).execute(
                        getUrl(
                            it.latitude,
                            it.longitude,
                            myLocation!!.latitude,
                            myLocation!!.longitude,
                            "driving"
                        ), "driving"
                    )
                }
                addMarker(it)
            }

        })


    }
    private fun getUrl(
        coor1Lat: Double,
        coor1Lon: Double,
        coor2Lat: Double,
        coor2Lon: Double,
        directionMode: String
    ): String? {
        // Origin of route
        val str_origin = "origin=" + coor1Lat + "," + coor1Lon
        // Destination of route
        val str_dest = "destination=" + coor2Lat + "," + coor2Lon
        // Mode
        val mode = "mode=$directionMode"
        // Building the parameters to the web service
        val parameters = "$str_origin&$str_dest&$mode"
        // Output format
        val output = "json"
        // Building the url to the web service
        return "https://maps.googleapis.com/maps/api/directions/$output?$parameters&key=AIzaSyCsAzdUuDCEKgul7ZYYJH9t8CIyWdGGeMk"
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

        Timber.e("Current contact coor: ${contact.latitude} ___ ${contact.longitude}")
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

        getMyLocation()

    }

    private var myLocation: Location? = null
    private var locationPermission = false
    private fun getMyLocation() {
        locationPermission =
            (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)
                    || (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)

        if (locationPermission) {
            LocationServices.getFusedLocationProviderClient(requireActivity())
                .lastLocation.addOnCompleteListener {
                    myLocation = it.result

                    Timber.e("My location ${it.result.toString()}")


                }
        } else {

        }
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