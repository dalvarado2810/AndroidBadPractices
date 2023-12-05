package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.google.android.material.button.MaterialButton

const val DASHBOARD_DATA = "DASHBOARD_DATA"

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // this active crash that happend in app
       // val button: MaterialButton = binding.button2
       // button.setOnClickListener {
       //     val bundle = bundleOf(DASHBOARD_DATA to "BUNDLE FROM DASHBOARD")
       //     findNavController().navigate(R.id.action_dashboard_to_home, bundle)
       // }

        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: MaterialButton = binding.button2
        button.setOnClickListener {
            val bundle = bundleOf(DASHBOARD_DATA to "BUNDLE FROM DASHBOARD")
            findNavController().navigate(R.id.action_dashboard_to_home, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}