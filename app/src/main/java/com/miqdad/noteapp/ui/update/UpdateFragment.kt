package com.miqdad.noteapp.ui.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.miqdad.noteapp.MainActivity
import com.miqdad.noteapp.R
import com.miqdad.noteapp.databinding.FragmentUpdateBinding
import com.miqdad.noteapp.utils.ExtensionFunction.setActionBar
import com.miqdad.noteapp.utils.HelperFunction.setPriorityColor

class UpdateFragment : Fragment() {

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

//        val navController = findNavController()
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//
//        binding.toolbarUpdate.apply {
//            setupWithNavController(navController, appBarConfiguration)
//            (requireActivity() as MainActivity).setSupportActionBar(this)
//            navController.addOnDestinationChangedListener{_, destination, _ ->
//                when (destination.id){
//                    R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
//                }
//            }
//        }
        binding.apply {
            toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener= context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_detailFragment)
            Toast.makeText(context, "Note has been update ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



