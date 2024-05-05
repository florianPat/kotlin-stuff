package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.ktor.utils.io.charsets.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ApplikationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        val response = client.get("/")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello World!", response.bodyAsText())
    }

    @Test
    fun testNewEndpoint() = testApplication {
        application {
            module()
        }
        val response = client.get("/test1")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("html", response.contentType()?.contentSubtype)
        assertContains(response.bodyAsText(), "Hello from ktor")
    }

    @Test
    fun testContentEndpoint() = testApplication {
        application {
            module()
        }
        val response = client.get("/content/sample.html")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(ContentType.Text.Html.withCharset(Charset.defaultCharset()), response.contentType())
        assertContains(response.bodyAsText(), "This page is built with")
    }

    @Test
    fun testErrorRoute() = testApplication {
        application {
            module()
        }
        val response = client.get("/error-test")

        assertEquals("App in illegal state as Too busy", response.bodyAsText())
    }
}