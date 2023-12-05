package com.example.myapplication.ui.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.bindView
import com.google.android.material.button.MaterialButton

class NotificationsFragment : Fragment(), BottomSheetListener {

    private val notificationsViewModel by viewModels<NotificationsViewModel>()
    private val textView by bindView<TextView>(R.id.text_notifications)
    private val numberView by bindView<TextView>(R.id.text_notifications_view)
    private val button by bindView<MaterialButton>(R.id.button)
    private val button2 by bindView<MaterialButton>(R.id.button2)
    private val button3 by bindView<MaterialButton>(R.id.button3)
    private val lateInitText by bindView<TextView>(R.id.text_notifications_local)
    private val lazyText by bindView<TextView>(R.id.text_notifications_lazy)

    private lateinit var newData: Home
    private var number = 0
    private val home by lazy { Home("NEW", "NAME") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_notifications, container, false)

    private fun initObservables(){
        with(notificationsViewModel){
            text.observe(viewLifecycleOwner) {
                textView.text = it
            }
            number.observe(viewLifecycleOwner) {
                numberView.text = "Viewmodel ${it.name} ${it.rut}"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservables()

        button.setOnClickListener {
            notificationsViewModel.changeText()
            newData = Home("HELLO", "HOW YOU DOING")
            callDialog()
        }

        button2.setOnClickListener {
            lateInitText.text = "Lateinit variable ${newData.name} ${newData.rut}"
        }

        button3.setOnClickListener {
            lazyText.text = "Lazy variable ${home.name} ${home.rut}"
            button3.text = number.toString()
            callBottomDialog()
        }
    }

    private fun callBottomDialog(){
        val bottomSheetDialog = BottomSheetDialog()
        bottomSheetDialog.setBottomSheetListener(this@NotificationsFragment)
        bottomSheetDialog.show(childFragmentManager, "DIALOG" )


    }

    private fun callDialog(){
        val builder = AlertDialog.Builder(requireActivity())
        builder.setMessage("This is an Alert dialog ${home.rut} ${home.name}")
            .setCancelable(false)
            .setTitle("THERE MAY HAPPEN A CRASH WHEN RECREATE")
            .setCancelable(false)
            .setPositiveButton("ok") { dialog, id ->
                newData = Home("HI", "HOW DO YOu DO..")}
                number++
        val alert = builder.create()
        alert.show()
    }

    override fun onPositiveClick() {
        notificationsViewModel.changeTextByDialog()

    }

}

data class Home(
    val name: String,
    val rut: String
)