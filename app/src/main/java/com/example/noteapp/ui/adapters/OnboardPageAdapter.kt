package com.example.noteapp.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.noteapp.ui.fragments.board.BoardFragment
import com.example.noteapp.ui.fragments.board.OnboardPageFragment
import com.example.noteapp.ui.models.OnboardPage

class OnboardPageAdapter(fragment: Fragment, private val onboardPages : ArrayList<OnboardPage>) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("ON_BOARD_PAGE", onboardPages[position])
        val fragment = OnboardPageFragment()
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemCount(): Int {
        return  onboardPages.size
    }
}