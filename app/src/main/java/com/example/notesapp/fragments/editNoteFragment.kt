package com.example.notesapp.fragments

import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentEditNoteBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.viewModel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.*


class editNoteFragment : Fragment() {

    lateinit var binding: FragmentEditNoteBinding       //for binding
    val oldNotes by navArgs<editNoteFragmentArgs>()        //data fetched from the argument will be stored here in oldNotes
    private var priority = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)


        //to bind the layout views in the editFragment

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubTitle.setText(oldNotes.data.subTitle)
        binding.editNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority)       //for binding the priority
        {
            "1" -> {
                binding.edtpGreen.setImageResource(R.drawable.ic_baseline_done_24)                 //sets tick on green dot
                binding.edtpRed.setImageResource(0)                                                //sets resource of other button to 0
                binding.edtpYellow.setImageResource(0)
            }
            "2" -> {
                binding.edtpGreen.setImageResource(0)
                binding.edtpRed.setImageResource(0)
                binding.edtpYellow.setImageResource(R.drawable.ic_baseline_done_24)
            }
            "3" -> {
                binding.edtpGreen.setImageResource(0)
                binding.edtpRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.edtpYellow.setImageResource(0)
            }
        }



        //To change the priority


        binding.edtpGreen.setOnClickListener{
            priority = "1"
            binding.edtpGreen.setImageResource(R.drawable.ic_baseline_done_24)       //sets tick on green dot
            binding.edtpRed.setImageResource(0)                                      //sets resource of other button to 0
            binding.edtpYellow.setImageResource(0)

        }

        binding.edtpYellow.setOnClickListener{
            priority = "2"
            binding.edtpGreen.setImageResource(0)
            binding.edtpRed.setImageResource(0)
            binding.edtpYellow.setImageResource(R.drawable.ic_baseline_done_24)

        }


        binding.edtpRed.setOnClickListener{
            priority = "3"
            binding.edtpGreen.setImageResource(0)
            binding.edtpRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.edtpYellow.setImageResource(0)

        }


        //to update in DB

        binding.editBtnCreateDone.setOnClickListener {
            update(it)
        }


        return binding.root
    }

    private fun update(it: View?)
    {

        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val date = sdf.format(Date()).toString()

        val data = Notes(oldNotes.data.id, title,  subTitle, notes, date, priority)       //the entity whose values needs to be changed

        viewModel.updateNotes(data)                                                  //to update the entity to DB

        Toast.makeText(context, "Note updated successfully", Toast.LENGTH_SHORT).show()       //to show toast message

        Navigation.findNavController(it!!).navigate(R.id.action_editNoteFragment_to_homeFragment)     //to navigate back to home


    }


}