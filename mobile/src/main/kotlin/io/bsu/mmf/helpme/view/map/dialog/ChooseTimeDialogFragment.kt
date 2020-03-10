package io.bsu.mmf.helpme.view.map.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.bsu.mmf.helpme.R
import kotlinx.android.synthetic.main.choose_time_dialog.*
import org.greenrobot.eventbus.EventBus

class ChooseTimeDialogFragment: BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = ChooseTimeDialogFragment()
    }

    private val screenType by lazy { arguments?.getString("screenType") }
    private lateinit var adapter: BottomSheetTimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_time_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val timeList = arrayListOf(1,5,10,15,20)
        adapter = BottomSheetTimeAdapter(timeList)
        adapter.listener = this::onEventClickListener

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

    }

    fun onEventClickListener(event: BottomSheetFilterViewEvent) {
        when (event) {
            is ClickEvent -> {
                dismiss()
                EventBus.getDefault().post(EventCode(event.position))
            }
        }

    }

}
sealed class BottomSheetFilterViewEvent

data class ClickEvent(val position: Int) : BottomSheetFilterViewEvent()

data class EventCode(val code: Int)
