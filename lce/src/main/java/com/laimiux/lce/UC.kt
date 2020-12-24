package com.laimiux.lce

/**
 * UC stands for Unit / Content. A type that represents either loading state of type [Unit]
 * or content state of type [C].
 */
interface UC<out C> {
    companion object {
        fun <T> content(value: T) = Type.Content(value)
        fun loading() = Type.UnitLoading
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean

    fun loadingOrNull(): Any?
    fun contentOrNull(): C?

    fun asLceType(): Type<Any?, C, Nothing>
}

inline fun <ContentT, T> UC<ContentT>.fold(
    crossinline onLoading: (Any?) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return asLceType().fold(
        onLoading = onLoading,
        onError = Utils.HANDLE_NOTHING,
        onContent = onContent
    )
}
