package io.bsu.mmf.helpme.featureregistration.fragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.shape.*
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.utils.observeEvent
import io.bsu.mmf.helpme.featureregistration.R
import io.bsu.mmf.helpme.featureregistration.viewmodel.AuthMainViewModel
import kotlinx.android.synthetic.main.fragment_auth_main.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class AuthMainFragment : BaseFragment(R.layout.fragment_auth_main) {


    private val viewModel by inject<AuthMainViewModel>()


    private lateinit var host: NavHostFragment

    private lateinit var authNavController: NavController
    private lateinit var graph: NavGraph

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.window?.statusBarColor = ContextCompat.getColor(
            requireContext(), android.R.color.transparent
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initContainerBackground()
        host = parentFragmentManager
            .findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment? ?: return

        authNavController = host.navController
        val inflater = navController.navInflater

        graph = inflater.inflate(R.navigation.nav_graph_main)

        host.navController.graph = graph


    }


    //TODO: replace this logic with something better
    fun navigateToMainScreen() {
        navController.navigate(R.id.fromAuthToMainScreen)
    }

    private fun initContainerBackground() {
        val shapePathModel = ShapeAppearanceModel.builder()
            .setTopLeftCorner(RoundedCornerTreatment())
            .setTopRightCorner(RoundedCornerTreatment())
            .setTopLeftCornerSize(100f)
            .setTopRightCornerSize(100f)
            .build()


        val roundedMaterialShape = MaterialShapeDrawable(shapePathModel)
        roundedMaterialShape.fillColor =
            ContextCompat.getColorStateList(requireContext(), R.color.white)
        //  auth_nav_host_fragment.background = roundedMaterialShape

    }
}