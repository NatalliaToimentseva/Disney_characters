package com.example.disney_characters.utils

import com.example.disney_characters.dataSources.network.responses.CharacterResponse
import com.example.disney_characters.dataSources.network.responses.ListCharactersResponse
import com.example.disney_characters.dataSources.roomDB.entities.CharacterDbEntity
import com.example.disney_characters.models.CharacterItemModel
import com.example.disney_characters.models.CharacterMainData
import com.example.disney_characters.models.CharacterFieldsModel

private const val CHARACTER_NAME = "name"
private const val CHARACTER_IMAGE_URL = "imageUrl"

fun ListCharactersResponse.toListCharacterModel(): List<CharacterItemModel> {
    val characterList = ArrayList<CharacterItemModel>()
    for (item in this.data) {
        characterList.add(
            CharacterItemModel(
                id = item.id,
                name = item.name,
                imageUrl = item.imageUrl
            )
        )
    }
    return characterList
}

fun CharacterResponse.toCharacterMainData(): CharacterMainData {
    val list = arrayListOf<CharacterFieldsModel>()
    var cName = ""
    var url = ""
    this.data.forEach { (name, data) ->
        if (name == CHARACTER_NAME) {
            cName = data.toString()
        } else if (name == CHARACTER_IMAGE_URL) {
            url = data.toString()
        } else {
            val subList = if (data is List<*>) data.map { it.toString() } else arrayListOf()
            if (subList.isNotEmpty()) {
                list.add(
                    CharacterFieldsModel(
                        name = name,
                        movies = subList
                    )
                )
            }
        }
    }
    return CharacterMainData(
        name = cName,
        imgUrl = url,
        isFavorite = false,
        fields = list
    )
}

fun CharacterDbEntity.toCharacterItemModel(): CharacterItemModel {
    return CharacterItemModel(id, name, imageUrl)
}

fun List<CharacterDbEntity>.toCharacterItemModelList(): List<CharacterItemModel> {
    return map { dbEntity ->
        dbEntity.toCharacterItemModel()
    }
}

fun CharacterDbEntity.toCharacterMainData(): CharacterMainData {
    return CharacterMainData(
        name = this.name,
        imgUrl = this.imageUrl,
        isFavorite = true,
        fields = null
    )
}

fun CharacterItemModel.toCharacterDBEntity(): CharacterDbEntity {
    return CharacterDbEntity(0, id, name, imageUrl)
}