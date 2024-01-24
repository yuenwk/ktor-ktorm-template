package com.example.core

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun hasLength(value: String?, ex: () -> RuntimeException): String {
    contract {
        returns() implies (value != null)
    }
    if (value.isNullOrEmpty()) throw ex()
    return value
}

@OptIn(ExperimentalContracts::class)
fun requireHasLength(value: String?, lazyMessage: () -> String): String {
    contract {
        returns() implies (value != null)
    }
    return hasLength(value) { IllegalArgumentException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun checkHasLength(value: String?, lazyMessage: () -> String): String {
    contract {
        returns() implies (value != null)
    }
    return hasLength(value) { IllegalStateException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun hasText(value: String?, ex: () -> RuntimeException): String {
    contract {
        returns() implies (value != null)
    }
    if (value.isNullOrBlank()) throw ex()
    return value
}

@OptIn(ExperimentalContracts::class)
fun requireHasText(value: String?, lazyMessage: () -> String): String {
    contract {
        returns() implies (value != null)
    }
    return hasText(value) { IllegalArgumentException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun checkHasText(value: String?, lazyMessage: () -> String): String {
    contract {
        returns() implies (value != null)
    }
    return hasText(value) { IllegalStateException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> notEmpty(value: Array<T?>?, ex: () -> RuntimeException): Array<T?> {
    contract {
        returns() implies (value != null)
    }
    if (value.isNullOrEmpty()) throw ex()
    return value
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> requireNotEmpty(value: Array<T?>?, lazyMessage: () -> String): Array<T?> {
    contract {
        returns() implies (value != null)
    }

    return notEmpty(value) { IllegalArgumentException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <T : Any> checkNotEmpty(value: Array<T?>?, lazyMessage: () -> String): Array<T?> {
    contract {
        returns() implies (value != null)
    }
    return notEmpty(value) { IllegalStateException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <K : Any, V : Any> notEmpty(value: Map<K, V?>?, ex: () -> RuntimeException): Map<K, V?> {
    contract {
        returns() implies (value != null)
    }
    if (value.isNullOrEmpty()) throw ex()
    return value
}

@OptIn(ExperimentalContracts::class)
fun <K : Any, V : Any> requireNotEmpty(value: Map<K, V?>?, lazyMessage: () -> String): Map<K, V?> {
    contract {
        returns() implies (value != null)
    }
    return notEmpty(value) { IllegalArgumentException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <K : Any, V : Any> checkNotEmpty(value: Map<K, V?>?, lazyMessage: () -> String): Map<K, V?> {
    contract {
        returns() implies (value != null)
    }
    return notEmpty(value) { IllegalStateException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <T> notEmpty(value: Collection<T?>?, ex: () -> RuntimeException): Collection<T?> {
    contract {
        returns() implies (value != null)
    }
    if (value.isNullOrEmpty()) throw ex()
    return value
}

@OptIn(ExperimentalContracts::class)
fun <T> requireNotEmpty(value: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
    contract {
        returns() implies (value != null)
    }
    return notEmpty(value) { IllegalArgumentException(lazyMessage()) }
}

@OptIn(ExperimentalContracts::class)
fun <T> checkNotEmpty(value: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
    contract {
        returns() implies (value != null)
    }

    return notEmpty(value) { IllegalStateException(lazyMessage()) }
}

/**
 * Throws an [ex] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T> noNullElements(collection: Collection<T?>?, ex: () -> RuntimeException): Collection<T?> {
    contract {
        returns() implies (collection != null)
    }
    if (collection.isNullOrEmpty() || collection.any { it == null }) throw ex()
    return collection
}

/**
 * Throws an [IllegalArgumentException] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T> requireNoNullElements(value: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
    contract {
        returns() implies (value != null)
    }

    return noNullElements(value) { IllegalArgumentException(lazyMessage()) }
}

/**
 * Throws an [IllegalStateException] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T> checkNoNullElements(value: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
    contract {
        returns() implies (value != null)
    }
    return noNullElements(value) { IllegalStateException(lazyMessage()) }
}

/**
 * Throws an [ex] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T : Any> noNullElements(value: Array<T?>?, ex: () -> RuntimeException): Array<T?> {
    contract {
        returns() implies (value != null)
    }

    if (value.isNullOrEmpty() || value.any { it == null }) throw ex()

    return value
}

/**
 * Throws an [IllegalArgumentException] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T : Any> requireNoNullElements(value: Array<T?>?, lazyMessage: () -> String): Array<T?> {
    contract {
        returns() implies (value != null)
    }

    return noNullElements(value) { IllegalArgumentException(lazyMessage()) }
}

/**
 * Throws an [IllegalStateException] if the that an array contains no `null` elements.
 */
@OptIn(ExperimentalContracts::class)
fun <T : Any> checkNoNullElements(value: Array<T?>?, lazyMessage: () -> String): Array<T?> {
    contract {
        returns() implies (value != null)
    }

    return noNullElements(value) { IllegalStateException(lazyMessage()) }
}

