package com.example.guessit.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.guessit.R
import com.example.guessit.ui.game.GameViewModel

class ResultFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel

    private lateinit var viewModelFactory: ResultViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory =
            ResultViewModelFactory(
                ResultFragmentArgs.fromBundle(requireArguments()).score
            )

        viewModel = ViewModelProvider(this)[ResultViewModel::class.java]
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

}