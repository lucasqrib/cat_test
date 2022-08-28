package com.lucasqrib.cats.data.mapper

import com.lucasqrib.cats.data.dto.CatNetworkDTO
import com.lucasqrib.cats.domain.model.Cat

object CatMapper : Mapper<CatNetworkDTO, Cat> {
    override fun invoke(dto: CatNetworkDTO): Cat = Cat(
        id = dto.id,
        adaptability = dto.adaptability,
        affectionLevel = dto.affectionLevel,
        bidability = dto.bidability,
        catFriendly = dto.catFriendly,
        cfaUrl = dto.cfaUrl,
        childFriendly = dto.childFriendly,
        countryCode = dto.countryCode,
        countryCodes = dto.countryCodes,
        description = dto.description,
        dogFriendly = dto.dogFriendly,
        energyLevel = dto.energyLevel,
        experimental = 1 == dto.experimental,
        grooming = dto.grooming,
        hairless = 1 == dto.hairless,
        healthIssues = dto.healthIssues,
        hypoallergenic = 1 == dto.hypoallergenic,
        image = dto.image?.let { CatImageMapper(it) },
        imageId = dto.referenceImageId,
        indoor = 1 == dto.indoor,
        intelligence = dto.intelligence,
        lap = 1 == dto.lap,
        lifeSpan = dto.lifeSpan,
        name = dto.name,
        natural = 1 == dto.natural,
        origin = dto.origin,
        rare = 1 == dto.rare,
        rex = 1 == dto.rex,
        sheddingLevel = dto.sheddingLevel,
        shortLegs = 1 == dto.shortLegs,
        socialNeeds = dto.socialNeeds,
        strangerFriendly = dto.strangerFriendly,
        suppressedTail = 1 == dto.suppressedTail,
        temperament = dto.temperament,
        vcaHospitals = dto.vcahospitalsUrl,
        vetStreetUrl = dto.vetstreetUrl,
        vocalisation = dto.vocalisation,
        weight = CatWeightMapper(dto.weight),
        wikipediaUrl = dto.wikipediaUrl
    )
}