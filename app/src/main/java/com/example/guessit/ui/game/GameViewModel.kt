package com.example.guessit.ui.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {


    companion object {

        private const val Done = 0L
        private const val ONE_SECOND = 1000L
        private const val COUNTDOWN_TIME = 60000L

    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score
    private val _word = MutableLiveData<String>("")
    val word: LiveData<String>
        get() = _word
    private lateinit var wordList: MutableList<String>

    private val timer: CountDownTimer

    private val _countDown: MutableLiveData<Long>
    val countDown: LiveData<Long>
        get() = _countDown

    init {
        resetWordList()
        nextWord()
        _countDown = MutableLiveData(0L)

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {


                _countDown.value = Done
                _isGameFinished.value = true
            }

            override fun onTick(milliSecond: Long) {

                _countDown.value = (milliSecond / ONE_SECOND)
            }
        }

        timer.start()


//        DateUtils.formatElapsedTime()
    }

    private fun resetWordList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetWordList()
        }
        _word.value = wordList.removeAt(0)
    }


    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    private val _isGameFinished = MutableLiveData<Boolean>(false)


    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished

    fun onGameFinish() {
        _isGameFinished.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

}