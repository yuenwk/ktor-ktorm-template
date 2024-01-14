package com.example.core.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Assertion utility class that assists in validating arguments.
 *
 *
 * Useful for identifying programmer errors early and clearly at runtime.
 *
 * Reference comes from [spring](https://github.com/spring-projects/spring-framework/blob/main/spring-core/src/main/java/org/springframework/util/Assert.java)
 */
object Assert {

    /**
     * Assert a boolean expression, throwing an `IllegalStateException`
     * if the expression evaluates to `false`.
     *
     * Call [.isTrue] if you wish to throw an `IllegalArgumentException`
     * on an assertion failure.
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalStateException if `expression` is `false`
     */
    fun state(expression: Boolean, message: String) {
        check(expression) { message }
    }

    /**
     * Assert a boolean expression, throwing an `IllegalStateException`
     * if the expression evaluates to `false`.
     *
     * Call [.isTrue] if you wish to throw an `IllegalArgumentException`
     * on an assertion failure.
     * <pre class="code">
     * Assert.state(entity.getId() == null,
     * () -&gt; "ID for entity " + entity.getName() + " must not already be initialized");
    </pre> *
     *
     * @param expression      a boolean expression
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalStateException if `expression` is `false`
     * @since 5.0
     */
    fun state(expression: Boolean, lazyMessage: () -> String) {
        check(expression) { lazyMessage() }
    }

    /**
     * Assert a boolean expression, throwing an `IllegalArgumentException`
     * if the expression evaluates to `false`.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if `expression` is `false`
     */
    fun isTrue(expression: Boolean, message: String) {
        require(expression) { message }
    }

    /**
     * Assert a boolean expression, throwing an `IllegalArgumentException`
     * if the expression evaluates to `false`.
     * <pre class="code">
     * Assert.isTrue(i &gt; 0, () -&gt; "The value '" + i + "' must be greater than zero");
    </pre> *
     *
     * @param expression      a boolean expression
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if `expression` is `false`
     * @since 5.0
     */
    fun isTrue(expression: Boolean, lazyMessage: () -> String) {
        require(expression) { lazyMessage() }
    }

    /**
     * Assert that an object is `null`.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param value   the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not `null`
     */
    fun isNull(value: Any?, message: String) {
        require(value == null) { message }
    }

    /**
     * Assert that an object is `null`.
     * <pre class="code">
     * Assert.isNull(value, () -&gt; "The value '" + value + "' must be null");
    </pre> *
     *
     * @param value           the object to check
     * @param lazyMessage     a supplier for the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not `null`
     * @since 5.0
     */
    fun isNull(value: Any?, lazyMessage: () -> String) {
        require(value == null) { lazyMessage() }
    }

    /**
     * Assert that an object is not `null`.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param value   the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is `null`
     */
    @OptIn(ExperimentalContracts::class)
    fun <T : Any> notNull(value: T?, message: String): T {
        contract {
            returns() implies (value != null)
        }
        return requireNotNull(value) { message }
    }

    /**
     * Assert that an object is not `null`.
     * <pre class="code">
     * Assert.notNull(entity.getId(),
     * () -&gt; "ID for entity " + entity.getName() + " must not be null");
    </pre> *
     *
     * @param value          the object to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the object is `null`
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun <T : Any> notNull(value: T?, lazyMessage: () -> Any): T {
        contract {
            returns() implies (value != null)
        }
        return requireNotNull(value) { lazyMessage() }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be `null` and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param value   the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text is empty
     */
    @OptIn(ExperimentalContracts::class)
    fun hasLength(value: String?, message: String): String {
        contract {
            returns() implies (value != null)
        }
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException(message)
        }
        return value
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be `null` and not the empty String.
     * <pre class="code">
     * Assert.hasLength(account.getName(),
     * () -&gt; "Name for account '" + account.getId() + "' must not be empty");
    </pre> *
     *
     * @param value           the String to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the text is empty
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun hasLength(value: String?, lazyMessage: () -> String): String {
        contract {
            returns() implies (value != null)
        }
        if (value.isNullOrEmpty()) {
            throw IllegalArgumentException(lazyMessage())
        }
        return value
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be `null` and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param value   the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     */
    @OptIn(ExperimentalContracts::class)
    fun hasText(value: String?, message: String): String {
        contract {
            returns() implies (value != null)
        }
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException(message)
        }
        return value
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be `null` and must contain at least one non-whitespace character.
     * <pre class="code">
     * Assert.hasText(account.getName(),
     * () -&gt; "Name for account '" + account.getId() + "' must not be empty");
    </pre> *
     *
     * @param value           the String to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun hasText(value: String?, lazyMessage: () -> String): String {
        contract {
            returns() implies (value != null)
        }
        if (value.isNullOrBlank()) {
            throw IllegalArgumentException(lazyMessage())
        }
        return value
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @param message      the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text contains the substring
     */
    fun doesNotContain(textToSearch: String?, substring: String, message: String) {
        require(!(!textToSearch.isNullOrEmpty() && substring.isNotEmpty() && textToSearch.contains(substring))) { message }
    }

    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">
     * Assert.doesNotContain(name, forbidden, () -&gt; "Name must not contain '" + forbidden + "'");
    </pre> *
     *
     * @param textToSearch    the text to search
     * @param substring       the substring to find within the text
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the text contains the substring
     * @since 5.0
     */
    fun doesNotContain(textToSearch: String?, substring: String, lazyMessage: () -> String) {
        require(!(!textToSearch.isNullOrEmpty() && substring.isNotEmpty() && textToSearch.contains(substring))) {
            lazyMessage
        }
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * `null` and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array is `null` or contains no elements
     */

    @OptIn(ExperimentalContracts::class)
    fun <T : Any> notEmpty(array: Array<T?>?, message: String): Array<T?> {
        contract {
            returns() implies (array != null)
        }
        if (array.isNullOrEmpty()) {
            throw IllegalArgumentException(message)
        }
        return array
    }

    /**
     * Assert that an array contains elements; that is, it must not be
     * `null` and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(array, () -&gt; "The " + arrayType + " array must contain elements");
    </pre> *
     *
     * @param array           the array to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the object array is `null` or contains no elements
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun <T : Any> notEmpty(array: Array<T?>?, lazyMessage: () -> String): Array<T?> {
        contract {
            returns() implies (array != null)
        }
        if (array.isNullOrEmpty()) {
            throw IllegalArgumentException(lazyMessage())
        }
        return array
    }

    /**
     * Assert that an array contains no `null` elements.
     *
     * Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object array contains a `null` element
     */
    @OptIn(ExperimentalContracts::class)
    fun <T : Any> noNullElements(array: Array<T?>?, message: String): Array<T?> {
        contract {
            returns() implies (array != null)
        }

        if (array.isNullOrEmpty() || array.any { it == null }) {
            throw IllegalArgumentException(message)
        }

        return array
    }

    /**
     * Assert that an array contains no `null` elements.
     *
     * Note: Does not complain if the array is empty!
     * <pre class="code">
     * Assert.noNullElements(array, () -&gt; "The " + arrayType + " array must contain non-null elements");
    </pre> *
     *
     * @param array           the array to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the object array contains a `null` element
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun <T : Any> noNullElements(array: Array<T?>?, lazyMessage: () -> String): Array<T?> {
        contract {
            returns() implies (array != null)
        }

        if (array.isNullOrEmpty() || array.any { it == null }) {
            throw IllegalArgumentException(lazyMessage())
        }

        return array
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * `null` and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the collection is `null` or
     * contains no elements
     */
    @OptIn(ExperimentalContracts::class)
    fun <T> notEmpty(collection: Collection<T>?, message: String): Collection<T> {
        contract {
            returns() implies (collection != null)
        }
        if (collection.isNullOrEmpty()) {
            throw IllegalArgumentException(message)
        }
        return collection
    }

    /**
     * Assert that a collection contains elements; that is, it must not be
     * `null` and must contain at least one element.
     * <pre class="code">
     * Assert.notEmpty(collection, () -&gt; "The " + collectionType + " collection must contain elements");
    </pre> *
     *
     * @param collection      the collection to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the collection is `null` or
     * contains no elements
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun <T> notEmpty(collection: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
        contract {
            returns() implies (collection != null)
        }
        if (collection.isNullOrEmpty()) {
            throw IllegalArgumentException(lazyMessage())
        }
        return collection
    }

    /**
     * Assert that a collection contains no `null` elements.
     *
     * Note: Does not complain if the collection is empty!
     * <pre class="code">Assert.noNullElements(collection, "Collection must contain non-null elements");</pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the collection contains a `null` element
     * @since 5.2
     */
    @OptIn(ExperimentalContracts::class)
    fun <T> noNullElements(collection: Collection<T?>?, message: String): Collection<T?> {
        contract {
            returns() implies (collection != null)
        }
        if (collection.isNullOrEmpty() || collection.any { it == null }) {
            throw IllegalArgumentException(message)
        }
        return collection
    }

    /**
     * Assert that a collection contains no `null` elements.
     *
     * Note: Does not complain if the collection is empty!
     * <pre class="code">
     * Assert.noNullElements(collection, () -&gt; "Collection " + collectionName + " must contain non-null elements");
    </pre> *
     *
     * @param collection      the collection to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the collection contains a `null` element
     * @since 5.2
     */
    @OptIn(ExperimentalContracts::class)
    fun <T> noNullElements(collection: Collection<T?>?, lazyMessage: () -> String): Collection<T?> {
        contract {
            returns() implies (collection != null)
        }
        if (collection.isNullOrEmpty() || collection.any { it == null }) {
            throw IllegalArgumentException(lazyMessage())
        }
        return collection
    }

    /**
     * Assert that a Map contains entries; that is, it must not be `null`
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     *
     * @param map     the map to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the map is `null` or contains no entries
     */
    @OptIn(ExperimentalContracts::class)
    fun <K : Any, V : Any> notEmpty(map: Map<K, V?>?, message: String): Map<K, V?> {
        contract {
            returns() implies (map != null)
        }
        if (map.isNullOrEmpty()) {
            throw IllegalArgumentException(message)
        }
        return map
    }

    /**
     * Assert that a Map contains entries; that is, it must not be `null`
     * and must contain at least one entry.
     * <pre class="code">
     * Assert.notEmpty(map, () -&gt; "The " + mapType + " map must contain entries");
    </pre> *
     *
     * @param map             the map to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the map is `null` or contains no entries
     * @since 5.0
     */
    @OptIn(ExperimentalContracts::class)
    fun <K : Any, V : Any> notEmpty(map: Map<K, V?>?, lazyMessage: () -> String): Map<K, V?> {
        contract {
            returns() implies (map != null)
        }
        if (map.isNullOrEmpty()) {
            throw IllegalArgumentException(lazyMessage())
        }
        return map
    }

}

