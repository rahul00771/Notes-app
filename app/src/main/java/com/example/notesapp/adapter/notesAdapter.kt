package com.example.notesapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.databinding.ItemListBinding
import com.example.notesapp.entity.Notes
import com.example.notesapp.fragments.HomeFragment
import com.example.notesapp.fragments.HomeFragmentDirections

class notesAdapter(val requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<notesAdapter.notesViewHolder>() {

    /*fun filtering(newFilteredList: ArrayList<Notes>) {

        notesList = newFilteredList     //receiving new list of items(notes) filtered
        notifyDataSetChanged()

    }*/

    class notesViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {        //inflates the layout
        return notesViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {                     //binds the elements with data

        val data = notesList[position]
        holder.binding.noteTitle.text = data.title
        holder.binding.noteSubTitle.text = data.subTitle
        holder.binding.noteDate.text = data.date

        when(data.priority){
            "1" -> { holder.binding.priority.setBackgroundResource(R.drawable.green_dot)}
            "2" -> { holder.binding.priority.setBackgroundResource(R.drawable.yellow_dot)}
            "3" -> { holder.binding.priority.setBackgroundResource(R.drawable.red_dot)}
        }

        holder.binding.root.setOnClickListener{
                                                                //onclickListener on the list item or the root element

                val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)  //sent data along with the action(navigation)
                Navigation.findNavController(it).navigate(action)       //navigate

        }

    }

    override fun getItemCount() = notesList.size    //returns the size of the entities


}

