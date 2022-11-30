package com.example.notesapp.fragments

import android.content.Context
import android.icu.lang.UCharacter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapter.notesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding       //for view binding
    val viewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)         //for view binding


        //by default fetching all notes

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
            binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

        }



        binding.btnAddNotes.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)       //to navigate to create notes fragment

        }


        binding.filterBtnHigh.setOnClickListener {
                                                                                    //fetches notes with high priority
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with HIGH priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnLow.setOnClickListener {
                                                                                    //fetches notes with low priority
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with LOW priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnMedium.setOnClickListener {
                                                                                        //fetches notes with medium priority
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with MEDIUM priority",Toast.LENGTH_SHORT).show()

            }
        }


        binding.filterBtn.setOnClickListener{
                                                                                        //fetches all notes
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

            }

        }


        // Inflate the layout for this fragment
        return binding.root
    }


}