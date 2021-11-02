package com.mreigosa.marvelapp.presentation.features.characterdetail

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.mreigosa.marvelapp.R

class MarvelCharacterDetailRobot {

    companion object {
        infix fun characterDetail(
            func: MarvelCharacterDetailRobot.() -> Unit
        ) = MarvelCharacterDetailRobot().apply { func() }
    }

    fun screenIsShown() {
        assertDisplayed(R.id.character_detail_name)
    }
}