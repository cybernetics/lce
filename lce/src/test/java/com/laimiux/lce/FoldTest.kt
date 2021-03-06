package com.laimiux.lce

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class FoldTest {

    @Test fun `fold LCE loading`() {
        val value = LCE.loading(0)
        val result = fold(value)
        assertThat(result).isEqualTo("loading: 0")
    }

    @Test fun `fold LCE content`() {
        val value = LCE.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    @Test fun `fold LCE error`() {
        val value = LCE.error(0)
        val result = fold(value)
        assertThat(result).isEqualTo("error: 0")
    }

    @Test fun `fold UCE loading`() {
        val value = UCE.loading()
        val result = fold(value)
        assertThat(result).isEqualTo("loading")
    }

    @Test fun `fold UCE content`() {
        val value = UCE.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    @Test fun `fold UCE error`() {
        val value = UCE.error(0)
        val result = fold(value)
        assertThat(result).isEqualTo("error: 0")
    }

    @Test
    fun `fold UCT loading`() {
        val value = UCT.loading()
        val result = fold(value)
        assertThat(result).isEqualTo("loading")
    }

    @Test
    fun `fold UCT content`() {
        val value = UCT.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    @Test
    fun `fold UCT error`() {
        val value = UCT.error(Throwable("exception"))
        val result = fold(value)
        assertThat(result).isEqualTo("error: java.lang.Throwable: exception")
    }

    @Test fun `fold CE content`() {
        val value = CE.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    @Test fun `fold CE error`() {
        val value = CE.error(0)
        val result = fold(value)
        assertThat(result).isEqualTo("error: 0")
    }

    @Test fun `fold CT content`() {
        val value = CT.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    @Test fun `fold CT error`() {
        val value = CT.error(TestException(0))
        val result = fold(value)
        assertThat(result).isEqualTo("error: 0")
    }

    @Test fun `fold UC loading`() {
        val value = UC.loading()
        val result = fold(value)
        assertThat(result).isEqualTo("loading")
    }

    @Test fun `fold UC content`() {
        val value = UC.content(0)
        val result = fold(value)
        assertThat(result).isEqualTo("content: 0")
    }

    private fun fold(value: UCT<Int>): String {
        return value.fold(
            onLoading = { "loading" },
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
    }

    private fun fold(value: LCE<Any, Int, Any>): String {
        return value.fold(
            onLoading = { "loading: $it" },
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
    }

    private fun fold(value: UCE<Int, Any>): String {
        return value.fold(
            onLoading = { "loading" },
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
    }

    private fun fold(value: CE<Int, Any>): String {
        return value.fold(
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
    }

    private fun fold(value: CT<Int>): String {
        return value.fold(
            onError = { "error: $it" },
            onContent = { "content: $it" }
        )
    }

    private fun fold(value: UC<Int>): String {
        return value.fold(
            onLoading = { "loading" },
            onContent = { "content: $it" }
        )
    }

}