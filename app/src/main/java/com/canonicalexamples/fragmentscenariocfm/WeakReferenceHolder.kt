package com.canonicalexamples.realprogrammers.helper

import java.lang.ref.WeakReference
import kotlin.reflect.KProperty

/**
 * 20181122. Initial version created by jorge
 * for a Canonical Examples training.
 *
 * Copyright 2018 Jorge D. Ortiz Fuentes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class WeakReferenceHolder<T> {
    private var propertyRef: WeakReference<T>? = null

    operator fun getValue(t: Any, property: KProperty<*>): T? = propertyRef?.get()

    operator fun setValue(t: Any, property: KProperty<*>, newValue: T?) {
        propertyRef = if (newValue != null) {
            WeakReference(newValue)
        } else {
            null
        }
    }
}
