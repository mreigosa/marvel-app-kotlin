package com.mreigosa.marvelapp.domain.usecase

import com.mreigosa.marvelapp.domain.executor.UseCase
import com.mreigosa.marvelapp.domain.model.MarvelCharacter
import com.mreigosa.marvelapp.domain.repository.MarvelRepository

class GetCharactersUseCase(
    private val repository: MarvelRepository
) : UseCase<GetCharactersUseCase.Params, List<MarvelCharacter>>() {

    data class Params(
        val offset: Int
    )

    override suspend fun run(): Result<List<MarvelCharacter>> {
        return repository.getCharacters(useCaseParams.offset)
    }
}