package com.miqdad.noteapp.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.miqdad.noteapp.MainActivity
import com.miqdad.noteapp.R
import com.miqdad.noteapp.databinding.FragmentDetailBinding
import com.miqdad.noteapp.ui.NotesViewModel
import com.miqdad.noteapp.utils.ExtensionFunction.setActionBar

class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()
    private val detailViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarDetail.setActionBar(requireActivity())
        binding.detail = args

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_edit -> {
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(args.notes)
                findNavController().navigate(action)
            }
            R.id.menu_delete -> confirmDeletedNote()
        }
        return super.onOptionsItemSelected(item)
    }

    //alert dialog button
    private fun confirmDeletedNote() {
        AlertDialog.Builder(context)
            .setTitle("Delete '${args.notes.title}'?")
            .setMessage("Are you sure want to remove '${args.notes.title}'?")
            .setPositiveButton("Yes"){_, _ ->
                detailViewModel.deleteNote(args.notes)
                Toast.makeText(context, "Successfully deleted '${args.notes.title}' note", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            .setNegativeButton("No"){
                _, _ -> }
                //Button cancel
//            .setNeutralButton("Cancel"){
//                _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}