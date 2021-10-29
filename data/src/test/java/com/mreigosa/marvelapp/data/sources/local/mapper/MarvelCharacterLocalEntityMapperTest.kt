package com.mreigosa.marvelapp.data.sources.local.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.local.model.MarvelCharacterLocalEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MarvelCharacterLocalEntityMapperTest {

    private val sut = MarvelCharacterLocalEntityMapper

    @Test
    fun `that can map a local character entity to data entity`() {
        val localEntity = MarvelCharacterLocalEntity(
            id = "1",
            name = "Marvel Hero",
            description = "marvel character description",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548"
        )

        val mappedInstance: Any = sut.mapFromLocal(localEntity)

        assertThat(mappedInstance is MarvelCharacterEntity).isTrue
        assertThat((mappedInstance as MarvelCharacterEntity).id).isEqualTo(localEntity.id)
        assertThat(mappedInstance.name).isEqualTo(localEntity.name)
        assertThat(mappedInstance.description).isEqualTo(localEntity.description)
        assertThat(mappedInstance.image).isEqualTo(localEntity.image)
    }

    @Test
    fun `that can map a data character entity to local entity`() {
        val dataEntity = MarvelCharacterEntity(
            id = "1",
            name = "Marvel Hero",
            description = "marvel character description",
            image = "http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548"
        )

        val mappedInstance: Any = sut.mapToLocal(dataEntity)

        assertThat(mappedInstance is MarvelCharacterLocalEntity).isTrue
        assertThat((mappedInstance as MarvelCharacterLocalEntity).id).isEqualTo(dataEntity.id)
        assertThat(mappedInstance.name).isEqualTo(dataEntity.name)
        assertThat(mappedInstance.description).isEqualTo(dataEntity.description)
        assertThat(mappedInstance.image).isEqualTo(dataEntity.image)
    }
}