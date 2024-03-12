package com.example.guessit.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel


    private lateinit var binding: FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.gameViewModel = gameViewModel
        gameViewModel.score.observe(
            viewLifecycleOwner,
        ) {
            binding.scoreText.text = it.toString()
        }
        binding.lifecycleOwner = this
        return binding.root
    }


    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment(
            gameViewModel.score.value ?: 0
        )
        findNavController(this).navigate(action)
    }


}