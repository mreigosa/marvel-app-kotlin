package com.mreigosa.marvelapp.presentation.features.characterlist

import com.mreigosa.marvelapp.presentation.MainActivity
import com.mreigosa.marvelapp.presentation.features.characterdetail.MarvelCharacterDetailRobot.Companion.characterDetail
import com.mreigosa.marvelapp.presentation.features.characterlist.MarvelCharacterListRobot.Companion.characterList
import com.mreigosa.marvelapp.presentation.mock.BaseUiTest
import com.mreigosa.marvelapp.rule.lazyActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MarvelCharacterListFragmentTest : BaseUiTest() {

    @get:Rule
    val activity = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    @Test
    fun givenCharacterList_whenCharacterSelected_ThenShowDetail() {
        givenGetCharacterListSuccess()

        activity.launch()

        characterList {
            screenIsShown()
            selectCharacter(1)
        }

        characterDetail {
            screenIsShown()
        }
    }

    @Test
    fun whenErrorRetrieved_SnackBarShown() {
        givenGetCharacterListError()

        activity.launch()

        characterList {
            screenIsShown()
            errorIsShown()
        }
    }

}