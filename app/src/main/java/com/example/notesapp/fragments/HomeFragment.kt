package com.example.notesapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapp.R
import com.example.notesapp.adapter.notesAdapter
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding       //for view binding
    val viewModel : NotesViewModel by viewModels()
    var oldMyNotes = ArrayList<Notes>()       //for search
    private lateinit var adapter: notesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)         //for view binding

        setHasOptionsMenu(true)     //to show the search in menu option

        //by default fetching all notes

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
            oldMyNotes = notesList as ArrayList<Notes>      //for searching
            adapter = notesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter

        }


        binding.filterBtnHigh.setOnClickListener {
                                                                                    //fetches notes with high priority
            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                oldMyNotes = notesList as ArrayList<Notes>      //for searching
                adapter = notesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

                Toast.makeText(context, "Showing notes with HIGH priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnLow.setOnClickListener {
                                                                                    //fetches notes with low priority
            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                oldMyNotes = notesList as ArrayList<Notes>      //for searching
                adapter = notesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

                Toast.makeText(context, "Showing notes with LOW priority",Toast.LENGTH_SHORT).show()

            }
        }

        binding.filterBtnMedium.setOnClickListener {
                                                                                        //fetches notes with medium priority
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                oldMyNotes = notesList as ArrayList<Notes>      //for searching
                adapter = notesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

                Toast.makeText(context, "Showing notes with MEDIUM priority",Toast.LENGTH_SHORT).show()

            }
        }


        binding.filterBtn.setOnClickListener{
                                                                                        //fetches all notes
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(2, 1)
                oldMyNotes = notesList as ArrayList<Notes>      //for searching
                adapter = notesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter

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



    //from gfg code

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // below line is to get our inflater


        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.app_bar_search)

        // getting search view of our item.
        val searchView = searchItem.actionView as SearchView?


        // below line is to call set on query text listener method.
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText)
                return false
            }
        })
    }

    private fun filter(newText : String)
    {
        val newFilteredList = ArrayList<Notes>()      //new list for search(sending to adapter)

        for(i in oldMyNotes)
        {
            if ((i.title.contains(newText!!)) || (i.subTitle.contains(newText!!)))
            {
                newFilteredList.add(i)             //newFilteredList to be sent to adapter for showing filtered
            }

        }

        if (newFilteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(context, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filtering(newFilteredList)
        }

              //sending new list of filtered notes
    }


}