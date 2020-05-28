package io.bsu.mmf.helpme.featuremain.fragment.train

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import io.bsu.mmf.helpme.baseAndroid.BaseFragment
import io.bsu.mmf.helpme.baseAndroid.customview.ProgressItemDecoration
import io.bsu.mmf.helpme.baseAndroid.customview.recycler.TrainItemDecoration
import io.bsu.mmf.helpme.featuremain.R
import io.bsu.mmf.helpme.featuremain.adapter.controller.TrainController
import io.bsu.mmf.helpme.featuremain.utils.lerp
import io.bsu.mmf.helpme.featuremain.utils.lerpArgb
import io.bsu.mmf.helpme.featuremain.viewmodel.TrainListViewModel
import kotlinx.android.synthetic.main.fragment_train_list.*
import org.koin.android.ext.android.inject

class TrainListFragment : BaseFragment(R.layout.fragment_train_list) {

    val viewModel by inject<TrainListViewModel>()

    private val trainController = TrainController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val behavior = BottomSheetBehavior.from(lessons_sheet)
        val backCallback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, false) {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        val sheetStartColor = lessons_sheet.context.getColor(R.color.btn_main_start)
        val sheetEndColor =
            lessons_sheet.context.getColorStateList(R.color.white).defaultColor
        val sheetBackground = MaterialShapeDrawable(
            ShapeAppearanceModel.builder(
                lessons_sheet.context,
                R.style.ShapeAppearance_Owl_MinimizedSheet,
                0
            ).build()
        ).apply {
            fillColor = ColorStateList.valueOf(sheetStartColor)
        }
        lessons_sheet.background = sheetBackground
        lessons_sheet.doOnLayout {
            val peek = behavior.peekHeight
            val maxTranslationX = (it.width - peek).toFloat()
            lessons_sheet.translationX = (lessons_sheet.width - peek).toFloat()

            // Alter views based on the sheet expansion
            behavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    backCallback.isEnabled = newState == BottomSheetBehavior.STATE_EXPANDED
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    lessons_sheet.translationX =
                        lerp(maxTranslationX, 0f, 0f, 0.15f, slideOffset)
                    sheetBackground.interpolation = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                    sheetBackground.fillColor = ColorStateList.valueOf(
                        lerpArgb(
                            sheetStartColor,
                            sheetEndColor,
                            0f,
                            0.3f,
                            slideOffset
                        )
                    )
                    playlist_icon.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                    sheet_expand.alpha = lerp(1f, 0f, 0f, 0.15f, slideOffset)
                    sheet_expand.visibility = if (slideOffset < 0.5) View.VISIBLE else View.GONE
                    playlist_title.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                    collapse_playlist.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                    playlist_title_divider.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                    playlist.alpha = lerp(0f, 1f, 0.2f, 0.8f, slideOffset)
                }
            })
//            lessons_sheet.doOnApplyWindowInsets { _, insets, _, _ ->
//                behavior.peekHeight = peek + insets.systemWindowInsetBottom
//            }
        }
        collapse_playlist.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        sheet_expand.setOnClickListener {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
       // playlist.adapter = lessonAdapter
//        playlist.addItemDecoration(
//            InsetDivider(
//                resources.getDimensionPixelSize(R.dimen.divider_inset),
//                resources.getDimensionPixelSize((R.dimen.divider_height)),
//                playlist.context.getColor(R.color.divider)
//            )
//        )

        viewModel.trainList.observe(viewLifecycleOwner, Observer {
            trainController.trains = it
            playlist.addItemDecoration(TrainItemDecoration(requireContext(), it))
            playlist.adapter = trainController.adapter
        })
    }

}