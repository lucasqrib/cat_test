package com.lucasqrib.cats.data.mapper

interface Mapper<in T, out Z> {
    operator fun invoke(dto: T): Z
}