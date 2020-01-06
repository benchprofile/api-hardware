package com.benchprofile.api.hardware.concept2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("node")
class NodeController(
        private val service: NodeService
) {
    @GetMapping
    fun listByLabel(@RequestParam label: String): MutableList<MutableMap<String, Any>>? {
        return service.findAllByLabel(label)
    }
}