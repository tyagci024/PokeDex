package com.example.pokedex.view

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentDetailListPageBinding
import com.example.pokedex.downloadfromURL

class DetailListPage : Fragment() {
    private lateinit var binding: FragmentDetailListPageBinding
    private val args by navArgs<DetailListPageArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_list_page, container, false)
        binding.pokemon = args.currentPoke

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }


        val startColor = Color.parseColor(args.currentPoke.light_color_code ?: "#e9ec87")
        val endColor = Color.parseColor("#ffffff") // Varsayılan bir bitiş rengi (Siyah)


        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(startColor, endColor)
        )

        binding.detailConstraint.background = gradientDrawable
        binding.toolbar.background = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(startColor, startColor)
        )

        // Inflate the layout for this fragment
        binding.pokeImage.downloadfromURL(args.currentPoke.img)
        return binding.root
    }


}