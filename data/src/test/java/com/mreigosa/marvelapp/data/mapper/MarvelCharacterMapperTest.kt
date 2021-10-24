package com.mreigosa.marvelapp.data.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MarvelCharacterMapperTest {

    @Test
    fun `that can map a character repository entity to domain entity`() {
        val repositoryEntity = ANY_MARVEL_CHARACTER

        val mappedInstance: Any = MarvelCharacterMapper.mapFromEntity(repositoryEntity)

        assertThat(mappedInstance is MarvelCharacter).isTrue
        assertThat((mappedInstance as MarvelCharacter).id).isEqualTo(repositoryEntity.id)
        assertThat(mappedInstance.name).isEqualTo(repositoryEntity.name)
        assertThat(mappedInstance.description).isEqualTo(repositoryEntity.description)
        assertThat(mappedInstance.image).isEqualTo(repositoryEntity.image)
    }

    companion object {
        private val ANY_MARVEL_CHARACTER = MarvelCharacterEntity(
            id = 1,
            name = "Marvel Hero",
            description = "marvel character description",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548.jpg",
        )
    }
}