package com.example.pokedex.view

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.AdapterPoke

import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentHomeListPageBinding
import com.example.pokedex.model.Pokemon
import com.example.pokedex.viewmodel.FeedViewModel
import java.util.Locale


class HomeListPage : Fragment() {

    private var recyclerViewState: Parcelable? = null
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: FragmentHomeListPageBinding
    private lateinit var adapterPoke: AdapterPoke
    private val feedViewModel: FeedViewModel by viewModels()
    private lateinit var originalList: List<Pokemon>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeListPageBinding.inflate(inflater, container, false)
        layoutManager = GridLayoutManager(requireContext(), 2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.RcyclerView.layoutManager = layoutManager

        feedViewModel.pokeList.observe(viewLifecycleOwner, Observer {
            originalList = it
            adapterPoke = AdapterPoke(it)
            binding.RcyclerView.adapter = adapterPoke
        })

        if (savedInstanceState != null) {
            recyclerViewState = savedInstanceState.getParcelable("recycler_state")
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Boş
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s.toString().toLowerCase(Locale.getDefault())
                val filteredList = originalList.filter {
                    it.name.toLowerCase(Locale.getDefault()).contains(searchText)
                }
                adapterPoke.updateList(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
                // Boş
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewState = layoutManager.onSaveInstanceState()
        outState.putParcelable("recycler_state", recyclerViewState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (recyclerViewState != null) {
            layoutManager.onRestoreInstanceState(recyclerViewState)
        }
    }
}
