package com.benchprofile.api.hardware.concept2

import org.neo4j.driver.Driver
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import java.util.*

@Service
class NodeService(private val driver: Driver) {
    fun findAllByLabel(label: String): MutableList<MutableMap<String, Any>>? {
        return driver.session().run("MATCH (n) WHERE {label} IN labels(n) RETURN (n)", mapOf(
                Pair("label", label)
        )).list { it.get("n").asNode().asMap() }
    }

    fun save(node: Node): Node {
        val session = driver.session()
        session.run("""
                    MERGE (n:${Label.valueOf(node.label)} { id: {id}, createdAt: {createdAt} })
                    RETURN n
                    """.trimIndent(),
                mapOf(
                        Pair("id", node.id),
                        Pair("createdAt", node.createdAt)
                )
        )
        return node
    }

    fun save(node: Node, createdByUserId: UUID): Node {
        val session = driver.session()
        session.run("""
MATCH (u:User { id: {createdByUserId} })
MERGE (n:${Label.valueOf(node.label)} { id: {id}, createdAt: {createdAt} })-[r:CREATED_BY { id: {rId}, createdAt: {rCreatedAt} }]->(u)
""".trimIndent(),
                mapOf(
                        Pair("createdByUserId", createdByUserId),
                        Pair("id", node.id),
                        Pair("createdAt", node.createdAt),
                        Pair("rId", UUID.randomUUID()),
                        Pair("rCreatedAt", OffsetDateTime.now())
                )
        )
        return node
    }
}
