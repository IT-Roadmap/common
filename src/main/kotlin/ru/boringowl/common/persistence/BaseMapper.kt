package ru.boringowl.common.persistence

interface BaseMapper<Model, Entity, ID> {
    fun Model.toEntity(): Entity
    fun Entity.toModel(): Model
}
