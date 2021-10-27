package com.mreigosa.marvelapp.data.sources.remote.mapper

import com.mreigosa.marvelapp.data.model.MarvelCharacterEntity
import com.mreigosa.marvelapp.data.sources.remote.forceHttps
import com.mreigosa.marvelapp.data.sources.remote.mapper.MarvelCharacterRemoteEntityMapper.IMAGE_VARIANT
import com.mreigosa.marvelapp.data.sources.remote.model.MarvelCharacterRemoteEntity
import com.mreigosa.marvelapp.data.sources.remote.model.Thumbnail
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MarvelCharacterRemoteEntityMapperTest {

    @Test
    fun `that can map a remote post entity to data entity`() {
        val remoteEntity = ANY_MARVEL_CHARACTER

        val mappedInstance: Any = MarvelCharacterRemoteEntityMapper.mapFromRemote(remoteEntity)

        assertThat(mappedInstance is MarvelCharacterEntity).isTrue
        assertThat((mappedInstance as MarvelCharacterEntity).id).isEqualTo(remoteEntity.id)
        assertThat(mappedInstance.name).isEqualTo(remoteEntity.name)
        assertThat(mappedInstance.description).isEqualTo(remoteEntity.description)

        val expectedImage = with(remoteEntity){
            "${thumbnail?.path}/$IMAGE_VARIANT.${thumbnail?.extension}".forceHttps()
        }

        assertThat(mappedInstance.image).isEqualTo(expectedImage)
    }

    companion object {
        private val ANY_MARVEL_CHARACTER = MarvelCharacterRemoteEntity(
            id = 1,
            name = "Marvel Hero",
            description = "marvel character description",
            thumbnail = Thumbnail(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/3/80/4c00358ec7548",
                extension = "jpg"
            )
        )
    }
}