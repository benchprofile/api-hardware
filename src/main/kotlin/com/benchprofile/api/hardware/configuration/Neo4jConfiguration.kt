package com.benchprofile.api.hardware.configuration

import org.neo4j.driver.AuthToken
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Neo4jConfiguration {
    @Bean
    fun neo4jDriver(): Driver {
        val authToken: AuthToken = AuthTokens.basic("neo4j", "benchprofile")
        return GraphDatabase.driver("bolt://localhost:7687", authToken)
    }
}