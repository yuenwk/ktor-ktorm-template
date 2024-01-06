/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.core.util

import org.jetbrains.annotations.Contract

/**
 * Assertion utility class that assists in validating arguments.
 *
 *
 * Useful for identifying programmer errors early and clearly at runtime.
 *
 *
 * For example, if the contract of a public method states it does not
 * allow `null` arguments, `Assert` can be used to validate that
 * contract. Doing this clearly indicates a contract violation when it
 * occurs and protects the class's invariants.
 *
 *
 * Typically used to validate method arguments rather than configuration
 * properties, to check for cases that are usually programmer errors rather
 * than configuration errors. In contrast to configuration initialization
 * code, there is usually no point in falling back to defaults in such methods.
 *
 *
 * This class is similar to JUnit's assertion library. If an argument value is
 * deemed invalid, an [IllegalArgumentException] is thrown (typically).
 * For example:
 *
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
 *
 *
 * Mainly for internal use within the framework; for a more comprehensive suite
 * of assertion utilities consider `org.apache.commons.lang3.Validate` from
 * [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/),
 * Google Guava's
 * [Preconditions](https://github.com/google/guava/wiki/PreconditionsExplained),
 * or similar third-party libraries.
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Colin Sampaleanu
 * @author Rob Harrop
 * @since 1.1.2
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
        check(expression) { lazyMessage }
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
        require(expression) { lazyMessage }
    }

    /**
     * Assert that an object is `null`.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is not `null`
     */
    fun isNull(`object`: Any?, message: String) {
        require(`object` == null) { message }
    }

    /**
     * Assert that an object is `null`.
     * <pre class="code">
     * Assert.isNull(value, () -&gt; "The value '" + value + "' must be null");
    </pre> *
     *
     * @param object          the object to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the object is not `null`
     * @since 5.0
     */
    fun isNull(`object`: Any?, lazyMessage: () -> String) {
        require(`object` == null) { lazyMessage }
    }

    /**
     * Assert that an object is not `null`.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the object is `null`
     */
    fun notNull(`object`: Any?, message: String) {
        requireNotNull(`object`) { message }
    }

    /**
     * Assert that an object is not `null`.
     * <pre class="code">
     * Assert.notNull(entity.getId(),
     * () -&gt; "ID for entity " + entity.getName() + " must not be null");
    </pre> *
     *
     * @param object          the object to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the object is `null`
     * @since 5.0
     */
    fun notNull(`object`: Any?, lazyMessage: () -> String) {
        requireNotNull(`object`) { lazyMessage }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be `null` and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text is empty
     */
    fun hasLength(text: String?, message: String) {
        require(!text.isNullOrEmpty()) { message }
    }

    /**
     * Assert that the given String is not empty; that is,
     * it must not be `null` and not the empty String.
     * <pre class="code">
     * Assert.hasLength(account.getName(),
     * () -&gt; "Name for account '" + account.getId() + "' must not be empty");
    </pre> *
     *
     * @param text            the String to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the text is empty
     * @since 5.0
     */
    fun hasLength(text: String?, lazyMessage: () -> String) {
        require(!text.isNullOrEmpty()) { lazyMessage }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be `null` and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     *
     * @param text    the String to check
     * @param message the exception message to use if the assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     */
    fun hasText(text: String?, message: String) {
        require(!text.isNullOrBlank()) { message }
    }

    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be `null` and must contain at least one non-whitespace character.
     * <pre class="code">
     * Assert.hasText(account.getName(),
     * () -&gt; "Name for account '" + account.getId() + "' must not be empty");
    </pre> *
     *
     * @param text            the String to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails
     * @throws IllegalArgumentException if the text does not contain valid text content
     * @since 5.0
     */
    fun hasText(text: String?, lazyMessage: () -> String) {
        require(!text.isNullOrBlank()) { lazyMessage }
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
    fun notEmpty(array: Array<Any?>?, message: String) {
        require(!array.isNullOrEmpty()) { message }
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
    fun notEmpty(array: Array<Any?>?, lazyMessage: () -> String) {
        require(!array.isNullOrEmpty()) { lazyMessage }
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
    fun noNullElements(array: Array<Any?>?, message: String) {
        if (array != null) {
            for (element in array) {
                requireNotNull(element) { message }
            }
        }
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
    fun noNullElements(array: Array<Any?>?, lazyMessage: () -> String) {
        if (array != null) {
            for (element in array) {
                requireNotNull(element) { lazyMessage }
            }
        }
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
    fun notEmpty(collection: Collection<*>?, message: String) {
        require(!(collection == null || collection.isEmpty())) { message }
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
    fun notEmpty(collection: Collection<*>?, lazyMessage: () -> String) {
        require(!(collection == null || collection.isEmpty())) {
            lazyMessage
        }
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
    fun noNullElements(collection: Collection<*>?, message: String) {
        if (collection != null) {
            for (element in collection) {
                requireNotNull(element) { message }
            }
        }
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
    fun noNullElements(collection: Collection<*>?, lazyMessage: () -> String) {
        if (collection != null) {
            for (element in collection) {
                requireNotNull(element) { lazyMessage }
            }
        }
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
    fun notEmpty(map: Map<*, *>?, message: String) {
        require(!(map == null || map.isEmpty())) { message }
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
    fun notEmpty(map: Map<*, *>?, lazyMessage: () -> String) {
        require(!(map == null || map.isEmpty())) { lazyMessage }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo, "Foo expected");</pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to provide further context.
     * If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     * will be appended. If it ends in a space, the name of the offending object's
     * type will be appended. In any other case, a ":" with a space and the name
     * of the offending object's type will be appended.
     * @throws IllegalArgumentException if the object is not an instance of type
     */
    fun isInstanceOf(type: Class<*>, obj: Any?, message: String) {
        notNull(type, "Type to check against must not be null")
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, message)
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo, () -&gt; "Processing " + Foo.class.getSimpleName() + ":");
    </pre> *
     *
     * @param type            the type to check against
     * @param obj             the object to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails. See [.isInstanceOf] for details.
     * @throws IllegalArgumentException if the object is not an instance of type
     * @since 5.0
     */
    fun isInstanceOf(type: Class<*>, obj: Any?, lazyMessage: () -> String) {
        notNull(type, "Type to check against must not be null")
        if (!type.isInstance(obj)) {
            instanceCheckFailed(type, obj, lazyMessage())
        }
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
     *
     * @param type the type to check against
     * @param obj  the object to check
     * @throws IllegalArgumentException if the object is not an instance of type
     */
    fun isInstanceOf(type: Class<*>, obj: Any?) {
        isInstanceOf(type, obj, "")
    }

    /**
     * Assert that `superType.isAssignableFrom(subType)` is `true`.
     * <pre class="code">Assert.isAssignable(Number.class, myClass, "Number expected");</pre>
     *
     * @param superType the supertype to check against
     * @param subType   the subtype to check
     * @param message   a message which will be prepended to provide further context.
     * If it is empty or ends in ":" or ";" or "," or ".", a full exception message
     * will be appended. If it ends in a space, the name of the offending subtype
     * will be appended. In any other case, a ":" with a space and the name of the
     * offending subtype will be appended.
     * @throws IllegalArgumentException if the classes are not assignable
     */
    fun isAssignable(superType: Class<*>, subType: Class<*>?, message: String) {
        notNull(superType, "Supertype to check against must not be null")
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, message)
        }
    }

    /**
     * Assert that `superType.isAssignableFrom(subType)` is `true`.
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass, () -&gt; "Processing " + myAttributeName + ":");
    </pre> *
     *
     * @param superType       the supertype to check against
     * @param subType         the subtype to check
     * @param lazyMessage     a supplier for the exception message to use if the
     * assertion fails. See [.isAssignable] for details.
     * @throws IllegalArgumentException if the classes are not assignable
     * @since 5.0
     */
    fun isAssignable(superType: Class<*>, subType: Class<*>?, lazyMessage: () -> String) {
        notNull(superType, "Supertype to check against must not be null")
        if (subType == null || !superType.isAssignableFrom(subType)) {
            assignableCheckFailed(superType, subType, lazyMessage())
        }
    }

    /**
     * Assert that `superType.isAssignableFrom(subType)` is `true`.
     * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
     *
     * @param superType the supertype to check
     * @param subType   the subtype to check
     * @throws IllegalArgumentException if the classes are not assignable
     */
    fun isAssignable(superType: Class<*>, subType: Class<*>) {
        isAssignable(superType, subType, "")
    }

    private fun instanceCheckFailed(type: Class<*>, obj: Any?, msg: String?) {
        val className = (if (obj != null) obj.javaClass.name else "null")
        var (result, defaultMessage) = booleanPair(msg, className)

        if (defaultMessage) {
            result += ("Object of class [$className] must be an instance of $type")
        }
        throw IllegalArgumentException(result)
    }

    private fun assignableCheckFailed(superType: Class<*>, subType: Class<*>?, msg: String?) {
        var (result, defaultMessage) = booleanPair(msg, subType)
        if (defaultMessage) {
            result += (subType.toString() + " is not assignable to " + superType)
        }
        throw IllegalArgumentException(result)
    }

    private fun booleanPair(msg: String?, typeName: Any?): Pair<String, Boolean> {
        var result = ""
        var defaultMessage = true
        if (!msg.isNullOrEmpty()) {
            if (endsWithSeparator(msg)) {
                result = "$msg "
            } else {
                result = messageWithTypeName(msg, typeName)
                defaultMessage = false
            }
        }
        return Pair(result, defaultMessage)
    }

    private fun endsWithSeparator(msg: String): Boolean {
        return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."))
    }

    private fun messageWithTypeName(msg: String, typeName: Any?): String {
        return msg + (if (msg.endsWith(" ")) "" else ": ") + typeName
    }

}