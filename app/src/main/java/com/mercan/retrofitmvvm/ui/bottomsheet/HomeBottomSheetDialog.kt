package com.mercan.retrofitmvvm.ui.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mercan.retrofitmvvm.core.Constants
import com.mercan.retrofitmvvm.data.model.Movie
import com.mercan.retrofitmvvm.databinding.BottomSheetLayoutBinding
import com.squareup.picasso.Picasso

class HomeBottomSheetDialog(private val movie: Movie?) : BottomSheetDialogFragment() {
    private var _binding: BottomSheetLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetLayoutBinding.inflate(inflater, container, false)
        bind(movie)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    private fun bind(
        movie: Movie?
    ) {
        val path = Constants.IMAGE_BASE_URL + movie?.posterPath
        val formattedYear = movie?.releaseDate?.substring(0, 4)

        Picasso.get().load(path).into(binding.imageView)
        binding.titleTextView.text = movie?.title
        binding.yearTextView.text = formattedYear
        binding.descriptionTextView.text = movie?.overview

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.closeFloatingActionButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.detailsButton.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}