package io.bsu.mmf.helpme.fragments

import android.Manifest
import android.app.Activity
import android.content.*
import android.hardware.Sensor
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import dagger.Lazy
import io.bsu.mmf.helpme.R
import io.bsu.mmf.helpme.presenter.BasePresenter
import io.bsu.mmf.helpme.presenter.map.MapActivityPresenter
import io.bsu.mmf.helpme.view.map.*
import kotlinx.android.synthetic.main.activity_map.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import permissions.dispatcher.*
import javax.inject.Inject

class HelpFragment : BaseFragment(), MapView {

    private val REQUEST_CHECK_GPS_SETTINGS: Int = 1
    private val REQUEST_CHECKGPSSETTINGS: Int = 0
    val PERMISSION_CHECKGPSSETTINGS: Array<String> = arrayOf("android.permission.ACCESS_FINE_LOCATION", "android.permission.SEND_SMS")



    @Inject
    lateinit var daggerPresenter: Lazy<MapActivityPresenter>

    @InjectPresenter
    lateinit var presenter: MapActivityPresenter

    @ProvidePresenter
    fun providePresenter(): MapActivityPresenter = daggerPresenter.get()

    override val layout: Int
        get() = R.layout.activity_map
    override val basePresenter: BasePresenter<*>?
        get() = presenter


    private lateinit var shakeDetector: ShakeDetector

    private lateinit var mSensorManager: SensorManager
    private lateinit var mAccelerometer: Sensor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        shakeDetector = ShakeDetector()
        shakeDetector.setOnShakeListener {
            presenter.helpNeeded()
        }


        //presenter.applyState(lastCustomNonConfigurationInstance as AlarmStatus?)
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(shakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI)

    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(shakeDetector)
    }

    override fun onStart() {
        super.onStart()
        if (PermissionUtils.hasSelfPermissions(requireContext(), *PERMISSION_CHECKGPSSETTINGS)) {
            checkGpsSettings()
        } else {
            ActivityCompat.requestPermissions(requireActivity(), PERMISSION_CHECKGPSSETTINGS,
                REQUEST_CHECKGPSSETTINGS
            )
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CHECKGPSSETTINGS ->
            {
                if (PermissionUtils.verifyPermissions(*grantResults)) {
                    checkGpsSettings()
                } else {
                    permissionsDenied()
                }
            }
        }    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_GPS_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                presenter.startMonitoringLocation()
            } else {
                Toast.makeText(requireContext(), R.string.map_gps_off, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)
    fun checkGpsSettings() {
        val testLocationRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY))
            .build()

        val client = LocationServices.getSettingsClient(requireActivity())
        val task = client.checkLocationSettings(testLocationRequest)

        task.addOnSuccessListener(requireActivity()) {
            presenter.startMonitoringLocation()
        }

        task.addOnFailureListener(requireActivity()) { e ->
            if (e is ResolvableApiException) {
                try {
                    e.startResolutionForResult(requireActivity(), REQUEST_CHECK_GPS_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.e("MapActivity", sendEx.message)
                }
            }
        }
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS)
    fun permissionsDenied() {
        presenter.permissionsMissing()
    }

    override fun displayPermissionsMessage() {
        permissions_needed.visibility = View.VISIBLE
        permissions_settings.setOnClickListener {
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity?.packageName, null)
            intent.data = uri
            startActivity(intent)
        }
    }

    override fun displayMap() {
        map.visibility = View.VISIBLE

     //   window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

        rippleBg.startRippleAnimation()
        help.setOnClickListener { presenter.helpNeeded() }
        finish.setOnClickListener {
            navController.navigateUp()
//            val i = Intent(requireContext(), MainActivity::class.java)
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(i)
        }
    }

    override fun triggerAlarm() {
        navController.navigate(R.id.action_helpFragment_to_alarmFragment)
    }

    override fun goToSendMessageScreen() {
        navController.navigate(R.id.action_helpFragment_to_sendMessageFragment)
    }
}