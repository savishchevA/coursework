package io.bsu.mmf.helpme.featuremain.fragment.train

import android.location.Location
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.navigateSafe
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.data.train.TrainItem
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.fragment.train.location.CurrentLocationListener
import io.bsu.mmf.helpme.featuremain.viewmodel.TrainViewModel
import kotlinx.android.synthetic.main.fragment_train.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.*


class TrainFragment : BaseFragment(R.layout.fragment_train) {


    private val viewModel by inject<TrainViewModel>()

    private lateinit var map: MapView
    private lateinit var googleMap: GoogleMap

    private var isNeedZoom = true
    private lateinit var startLocation: Location
    private lateinit var prevLocation: Location
    private lateinit var curLocation: Location
    private var isEmptyStartLocation = true
    private var onPause: Boolean = false

    private val options = PolylineOptions()

    private lateinit var timer: Timer
    private var initTime: Long = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.train_card_bg
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_train, container, false)

        map = rootView.findViewById(R.id.mapView) as MapView
        map.onCreate(savedInstanceState)
        map.getMapAsync {
            it.clear()
            googleMap = it
            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(), R.raw.style_map_silver
                )
            )

            getLocationUpdates()
        }
        map.onResume()

        return rootView
    }


    private fun zoomMap(longitude: Double, latitude: Double) {
        val cameraPosition = CameraPosition.Builder()
            .target(
                LatLng(
                    latitude,
                    longitude
                )
            ) // Sets the center of the map to location user
            .zoom(15f) // Sets the zoom
            //.tilt(40f) // Sets the tilt of the camera to 30 degrees
            .build() // Creates a CameraPosition from the builder

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //   setCardBackground()

        timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                initTime += 1000
                tv_train_time.text = TimeParser.parse(initTime)
            }
        }, 1000, 1000)

        options.apply {
            color(ContextCompat.getColor(requireContext(), R.color.train_card_bg))
            width(15f)
            visible(true)
        }
        viewModel.showCurrentDistance.observeEvent(viewLifecycleOwner) {
            Timber.e("Current distance in m: $it")
            tv_distance.text = getString(R.string.train_distance, String.format("%.2f", it / 1000))
        }

        viewModel.showCurrentSpeed.observeEvent(viewLifecycleOwner) {
            Timber.e("Current speed: $it")
            tv_speed.text = getString(R.string.train_distance, String.format("%.2f", it))
        }

        viewModel.drawPartOfDistance.observeEvent(viewLifecycleOwner) {
            drawPrimaryLinePath(listOf(it.first, it.second))
        }

        viewModel.finishTrain.observeEvent(viewLifecycleOwner) {
            navController.navigateSafe(R.id.toActiveScanningFragment)
        }


//        viewModel.checkDistanceLimit.observe(viewLifecycleOwner, Observer {
//            Timber.e("Current train distance check: ${it}")
//        })



        btn_pause.setOnClickListener {

        }

        btn_stop.setOnClickListener {
            navController.navigateSafe(R.id.showTrainSuccess)
//            viewModel.saveTrain(
//                TrainItem(
//
//                )
//            )
        }

    }


    private fun drawPrimaryLinePath(listLocsToDraw: List<Location>) {
        for (locRecorded in listLocsToDraw) {
            options.add(
                LatLng(
                    locRecorded.latitude,
                    locRecorded.longitude
                )
            )
        }
        if (::googleMap.isInitialized)
            googleMap.addPolyline(options)
    }

    private fun getLocationUpdates() {
        CurrentLocationListener.getInstance(requireContext())
            .observe(viewLifecycleOwner, Observer { location ->
                if (location != null) {
                    viewModel.setCurrentLocation(location)
                    Timber.e("Current location: ${location.toString()}")
//
                    if (isEmptyStartLocation) {
                        startLocation = location
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(
                                    LatLng(
                                        location.latitude,
                                        location.longitude
                                    )
                                )
                        )
                        isEmptyStartLocation = false
                    }
//
                    if (isNeedZoom) {
                        zoomMap(location.longitude, location.latitude)
                        isNeedZoom = false
                    }

                    // drawPrimaryLinePath([startLocation, location])
//
//                    curLocation = location
//
//                    calculateCurrentDistance(location)

                }
            })
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
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cancelTimer()
        googleMap?.clear()
        if (::timer.isInitialized) {
            timer.cancel()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        map?.onLowMemory()
    }
}