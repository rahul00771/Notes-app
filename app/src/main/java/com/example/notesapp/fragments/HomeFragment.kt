package com.example.notesapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapter.notesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding       //for view binding
    val viewModel : NotesViewModel by viewModels()
    //var oldMyNotes = ArrayList<Notes>()       //for search
    //private lateinit var adapter: notesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)         //for view binding

        setHasOptionsMenu(true)     //to show the search in menu option

        //by default fetching all notes

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
            //oldMyNotes = notesList as ArrayList<Notes>      //for searching
            binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

        }


        binding.filterBtnHigh.setOnClickListener {
                                                                                    //fetches notes with high priority
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                //oldMyNotes = notesList as ArrayList<Notes>      //for searching
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with HIGH priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnLow.setOnClickListener {
                                                                                    //fetches notes with low priority
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                //oldMyNotes = notesList as ArrayList<Notes>      //for searching
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with LOW priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnMedium.setOnClickListener {
                                                                                        //fetches notes with medium priority
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                //oldMyNotes = notesList as ArrayList<Notes>      //for searching
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

                Toast.makeText(context, "Showing notes with MEDIUM priority",Toast.LENGTH_SHORT).show()

            }
        }


        binding.filterBtn.setOnClickListener{
                                                                                        //fetches all notes
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                //oldMyNotes = notesList as ArrayList<Notes>      //for searching
                binding.rcvAllNotes.adapter = notesAdapter(requireContext(), notesList)

            }

        }


        binding.btnAddNotes.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createFragment)       //to navigate to create notes fragment

        }


        // Inflate the layout for this fragment
        return binding.root
    }


    //for searching notes

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {      //for toolbar menu

        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.menu_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search for notes..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false        //executes if we submit the search text field
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                notesFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFiltering(p0 : String?)
    {
        val newFilteredList = arrayListOf<Notes>()      //new list for search(sending to adapter)

        for(i in oldMyNotes)
        {
            if ((i.title.contains(p0!!)) || (i.subTitle.contains(p0!!)))
            {
                newFilteredList.add(i)             //newFilteredList to be sent to adapter for showing filtered
            }

        }
        adapter.filtering(newFilteredList)      //sending new list of filtered notes
    }*/

}