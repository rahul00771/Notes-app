package com.example.notesapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentCreateBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*


class createFragment : Fragment() {



    lateinit var binding: FragmentCreateBinding     //for viewBinding
    var priority : String = "1"                     //default priority = 1(green)
    private val viewModel: NotesViewModel by viewModels()    //for viewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentCreateBinding.inflate(inflater, container, false)     // Inflate the layout for this fragment
        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)                     //by default setting the tick on green dot

        binding.pGreen.setOnClickListener{
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)                 //sets tick on green dot
            binding.pRed.setImageResource(0)                                                //sets resource of other button to 0
            binding.pYellow.setImageResource(0)

        }

        binding.pYellow.setOnClickListener{
            priority = "2"
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)

        }


        binding.pRed.setOnClickListener{
            priority = "3"
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)

        }



        binding.btnCreateDone.setOnClickListener {
            createNotes(it)                                                //when the done button is touched after entering the values
        }


        return binding.root

    }

    private fun createNotes(it: View?)
    {
        val title = binding.createTitle.text.toString()
        val subTitle = binding.createSubTitle.text.toString()
        val notes = binding.createNotes.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val date = sdf.format(Date()).toString()

        val data = Notes(null, title,  subTitle, notes, date, priority)          //a single entity or entry

        viewModel.addNotes(data)                                                    //to add the entity to DB

        Toast.makeText(context, "Note added successfully", Toast.LENGTH_SHORT).show()       //to show toast message

        Navigation.findNavController(it!!).navigate(R.id.action_createFragment_to_homeFragment)     //to navigate back to home
        //findNavController().navigate(R.id.action_createFragment_to_homeFragment)

    }


}


