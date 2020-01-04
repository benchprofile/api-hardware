package com.benchprofile.api.hardware.concept1

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("unit")
class UnitController(private val repository: UnitRepository) {
    @GetMapping
    fun list(): List<Map<String, Any>> {
        return repository.findAll().map { toModel(it) }.toList()
    }

    @PostMapping
    fun create(@RequestBody model: Map<String, String>): Map<String, Any> {
        val unit = requireNotNull(model["unit"])
        val type: Type = Type.valueOf(requireNotNull(model["type"]))
        val created: Unit = Unit(unit, type)
        return toModel(repository.save(created))
    }

    private fun toModel(unit: Unit): Map<String, Any> {
        return mapOf(
                Pair("id", unit.id!!),
                Pair("unit", unit.unit),
                Pair("type", unit.type)
        )
    }
}
