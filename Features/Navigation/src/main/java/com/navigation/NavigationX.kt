package com.navigation

import androidx.navigation.NavController
import java.io.Serializable

fun NavController.navigateWithObject(
    route: String,
    objectToPass: Serializable
) {
    currentBackStackEntry?.savedStateHandle?.set(objectToPass::class.java.simpleName, objectToPass)
    navigate(route)
}

inline fun <reified T : Serializable> NavController.recoverObject(key: String = T::class.java.simpleName) =
    previousBackStackEntry?.savedStateHandle?.get<T>(key)