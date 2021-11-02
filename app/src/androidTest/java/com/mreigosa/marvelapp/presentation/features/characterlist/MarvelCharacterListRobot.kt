package com.mreigosa.marvelapp.presentation.features.characterlist

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.mreigosa.marvelapp.R
import com.mreigosa.marvelapp.utils.EspressoTestUtils

class MarvelCharacterListRobot {

    companion object {
        infix fun characterList(
            func: MarvelCharacterListRobot.() -> Unit
        ) = MarvelCharacterListRobot().apply { func() }
    }

    fun screenIsShown() {
        assertDisplayed(R.id.character_list)
    }

    fun selectCharacter(position: Int = 0) {
        clickListItem(R.id.character_list, position)
    }

    fun errorIsShown(){
        assertDisplayed(EspressoTestUtils.getText(R.string.something_went_wrong))
    }
}