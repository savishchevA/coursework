package io.github.zeyomir.extremesportssos.view.map.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.github.zeyomir.extremesportssos.R

class ChooseTimeDialogFragment: BottomSheetDialogFragment() {
    companion object {
        fun newInstance(screenType: String): ChooseTimeDialogFragment {
            return ChooseTimeDialogFragment().apply {
                arguments = bundleOf("screenType" to screenType)
            }
        }
    }

    private val screenType by lazy { arguments?.getString("screenType") }
    private lateinit var adapter: BottomSheetTimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_time_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


       /* adapter = BottomSheetTimeAdapter(currentList, screenType ?: "")
        adapter.listener = this::onEventClickListener

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)*/

    }

}