package com.miqdad.noteapp.ui.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.miqdad.noteapp.MainActivity
import com.miqdad.noteapp.R
import com.miqdad.noteapp.data.locale.entity.Notes
import com.miqdad.noteapp.data.locale.entity.Priority
import com.miqdad.noteapp.databinding.FragmentAddBinding
import com.miqdad.noteapp.ui.NotesViewModel
import com.miqdad.noteapp.utils.ExtensionFunction.setActionBar
import com.miqdad.noteapp.utils.HelperFunction.parseToPriority
import com.miqdad.noteapp.utils.HelperFunction.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

    private val addViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.apply {
            toolbarAdd.setActionBar(requireActivity())
            spinnerPriorities.onItemSelectedListener = context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {

            insertNotes()

        }
    }

    private fun insertNotes() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(calendar)

            val priority = spinnerPriorities.selectedItem.toString()

            if(edtTitle.text.isEmpty() || edtDescription.text.isEmpty()){
                if (edtTitle.text.isEmpty()){
                    edtTitle.error = "Tolong Di isi."
                }else if(edtDescription.text.isEmpty()){
                    edtDescription.error = "Tolong Di isi."
                }else{
                    edtTitle.error = "Tolong Di isi."
                    edtDescription.error = "Tolong Di isi."
                }

            }else{
                val data = Notes(0, title, desc, date, parseToPriority(context, priority))
                addViewModel.insertNotes(data)
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
                Toast.makeText(context, "Sukses NgeAdd Note", Toast.LENGTH_SHORT).show()
                Log.d("AddFragment","insertNote : $data")
            }


        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}