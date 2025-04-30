package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.ui.fragments.data.models.Note
import com.example.noteapp.utils.PreferenceHelper
import com.example.noteapp.ui.adapters.NoteAdapter


class NoteFragment : Fragment(), OnClickNote{
    private lateinit var binding : FragmentNoteBinding
    private val preference = PreferenceHelper()
    private lateinit var noteAdapter : NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preference.unit(requireContext())
        initialize()
        setupListeners()

        App.appDatabase?.noteDao()?.getAll()?.observeForever { notes ->
            binding.emptyNotesTextView.isVisible = notes.isEmpty()
            noteAdapter.submitList(notes)
        }

    }

    private fun initialize() {
        preference.unit(requireContext())
        noteAdapter = NoteAdapter(preference.layoutManager, this)

        binding.notesRecyclerView.apply {
            if (preference.layoutManager == NoteAdapter.VIEW_TYPE_LINEAR) {
                layoutManager = LinearLayoutManager(requireContext())
                binding.changeLayoutManagerImageView.setImageResource(R.drawable.ic_grid_layout)
            } else {
                layoutManager = GridLayoutManager(requireContext(), 2)
                binding.changeLayoutManagerImageView.setImageResource(R.drawable.ic_linear_layout)
            }
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {

        alertDialogScreen.setOnClickListener {
            // EMPTY
        }
        addNoteImageButton.setOnClickListener {
            findNavController().navigate(
                NoteFragmentDirections.actionNoteFragmentToDetailFragment(
                    -1
                )
            )
        }
        changeLayoutManagerImageView.setOnClickListener {
            if (noteAdapter.viewType == NoteAdapter.VIEW_TYPE_LINEAR) {
                binding.notesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                noteAdapter.updateViewType(NoteAdapter.VIEW_TYPE_GRID)
                preference.layoutManager = NoteAdapter.VIEW_TYPE_GRID
                binding.changeLayoutManagerImageView.setImageResource(R.drawable.ic_linear_layout)
            } else {
                binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                noteAdapter.updateViewType(NoteAdapter.VIEW_TYPE_LINEAR)
                preference.layoutManager = NoteAdapter.VIEW_TYPE_LINEAR
                binding.changeLayoutManagerImageView.setImageResource(R.drawable.ic_grid_layout)
            }
        }
    }

    override fun onLongClick(note: Note) {

        binding.alertDialogScreen.visibility = View.VISIBLE

        binding.negativeButton.setOnClickListener {
            binding.alertDialogScreen.visibility = View.GONE
        }
        binding.positiveButton.setOnClickListener {
            App.appDatabase?.noteDao()?.delete(note)
            binding.alertDialogScreen.visibility = View.GONE
        }

    }

    override fun onClick(note: Note) {
        findNavController().navigate(NoteFragmentDirections.actionNoteFragmentToDetailFragment(note.id))
    }

}