package com.mcmacker4.ecs

import kotlin.reflect.KClass
import kotlin.reflect.full.cast

abstract class Component

class Entity {

    private val components = arrayListOf<Component>()
    private val cache = hashMapOf<Any, Component>()

    fun <T : Component> hasComponent(kClass: KClass<T>) : Boolean {
        if (cache.containsKey(kClass))
            return true
        for (component in components) {
            if (kClass.isInstance(component))
                return true
        }
        return false
    }

    fun <T : Component> getComponent(kClass: KClass<T>) : T? {
        val cached = cache[kClass]
        if (cached != null)
            return kClass.cast(cached)
        for (component in components) {
            if (kClass.isInstance(component)) {
                cache[kClass] = component
                return kClass.cast(component)
            }
        }
        return null
    }

    fun <T : Component> addComponent(component: T) : Boolean {
        if (hasComponent(component::class))
            return false
        components.add(component)
        return true
    }
    
    fun <T : Component> removeComponent(kClass: KClass<T>) {
        val component = getComponent(kClass)
        if (component != null)
            components.remove(component)
        cache.remove(kClass)
    }

}