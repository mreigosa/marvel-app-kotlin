package com.mreigosa.marvelapp.presentation.features.characterlist

import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.mreigosa.marvelapp.R
import com.mreigosa.marvelapp.utils.EspressoTestUtils
import com.mreigosa.marvelapp.utils.EspressoTestUtils.getText

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
        assertDisplayed(getText(R.string.something_went_wrong))
    }

    fun emptyViewShown(){
        assertDisplayed(R.id.empty_data_view, getText(R.string.no_data_retrieved))
    }

    fun loadingIsHidden(){
        assertNotDisplayed(R.id.progress_bar)
    }
}