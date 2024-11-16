package com.mercan.retrofitmvvm.ui.view.detail.trailers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mercan.retrofitmvvm.databinding.FragmentTrailersBinding

class TrailersFragment : Fragment() {
    private var _binding: FragmentTrailersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrailersBinding.inflate(inflater, container, false)

        return binding.root
    }
}