package com.benchprofile.api.hardware.concept1

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("hardware")
class HardwareController(
        private val repository: HardwareRepository,
        private val unitRepository: UnitRepository
) {
    @GetMapping
    fun list(): Set<Map<String, Any>> {
        return repository.findAll().map { toModel(it) }.toSet()
    }

    @PostMapping
    fun create(@RequestBody model: Map<String, Any>): Map<String, Any> {
        val name: String = requireNotNull(model["name"]) as String
        val hardware: Hardware = Hardware(name)

        val properties: List<Map<String, Any>> = model["properties"] as List<Map<String, Any>>? ?: emptyList()
        for (property in properties) {
            val pUnit = property["unit"] as String
            val unit = requireNotNull(unitRepository.findByUnit(pUnit))
            val value = property["value"] as String

            hardware.properties.add(Property(hardware, value, unit))
        }

        return toModel(repository.save(hardware))
    }

    private fun toModel(hardware: Hardware): Map<String, Any> {
        val model = mutableMapOf<String, Any>()
        model["id"] = hardware.id!!
        model["name"] = hardware.name
        model["properties"] = hardware.properties.map {
            mapOf(
                    Pair("id", it.id),
                    Pair("rawValue", it.value),
                    Pair("value", it.computedValue()),
                    Pair("unitId", it.unit.id),
                    Pair("unit", it.unit.unit),
                    Pair("type", it.unit.type)
            )
        }.toList()
        return model
    }
}
