package com.lucasqrib.cats.data.mapper

import com.lucasqrib.cats.data.dto.WeightNetworkDTO
import com.lucasqrib.cats.domain.model.CatWeight

object CatWeightMapper : Mapper<WeightNetworkDTO, CatWeight> {
    override fun invoke(dto: WeightNetworkDTO): CatWeight = CatWeight(
        imperial = dto.imperial,
        metric = dto.metric
    )
}