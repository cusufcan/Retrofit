package com.mercan.retrofitmvvm.ui.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mercan.retrofitmvvm.databinding.FragmentDetailBinding
import com.mercan.retrofitmvvm.ui.view.main.MainActivity

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val mainActivity = activity as MainActivity
        mainActivity.binding.appBottomNavigationView.visibility = View.GONE

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}