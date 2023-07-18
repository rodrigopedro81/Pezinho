package com.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.io.Serializable

fun String.withArgs(vararg strings: String): String =
    strings.joinToString("", prefix = this, postfix = "") { "{$it}" }

fun String.replaceArgs(vararg args: Pair<String, String>): String =
    args.fold(this) { acc, (key, value) ->
        acc.replace("{${key}}", value)
    }

fun NavController.navigateWithObject(
    route: String,
    objectToPass: Serializable
) {
    currentBackStackEntry?.savedStateHandle?.set(objectToPass::class.java.simpleName, objectToPass)
    navigate(route)
}

inline fun <reified T : Serializable> NavController.retrieveObject() =
    previousBackStackEntry?.savedStateHandle?.get<T>(T::class.java.simpleName)