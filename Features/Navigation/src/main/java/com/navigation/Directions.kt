package com.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

open class Route(
    val route: String,
    val container: String
) {
    fun getFullRoute() = container.replaceArgs(Args.StartDestination.navArgument to route)
}

sealed class Destinations(val container: String) {

    object Entry :
        Destinations("entry_container/".withArgs(Args.StartDestination.navArgument)) {
        val splashScreen = Route("splash_screen", container)
    }

    object Login :
        Destinations("login_container/".withArgs(Args.StartDestination.navArgument)) {
        val loginScreen = Route("login_screen", container)
        val registerScreen = Route("register_screen", container)
    }

    object Main : Destinations("home_container/".withArgs(Args.StartDestination.navArgument)) {
        val barberListScreen = Route("barber_list_screen", container)
        val barberShopScreen = Route("barber_shop_screen", container)
        val profileScreen = Route("profile_screen", container)
    }
}

private fun String.withArgs(vararg args: NamedNavArgument): String =
    args.joinToString("", prefix = this, postfix = "") { "{$it}" }

private fun String.replaceArgs(vararg args: Pair<NamedNavArgument, String>): String =
    args.fold(this) { acc, (key, value) ->
        acc.replace("{${key.name}}", value)
    }

private fun String.extractNavArguments(): List<NamedNavArgument> {
    val navArguments = Args.values().map { it.navArgument }
    val argumentNames = split("{", "}").filterIndexed { index, _ -> index % 2 == 1 }
    return navArguments.filter { namedNavArgument ->
        namedNavArgument.name in argumentNames
    }
}

fun String.navArguments() = extractNavArguments()

inline fun <reified T> NavBackStackEntry.getArgument(arg: Args): T? {
    val arguments = arguments ?: return null
    val key = arg.navArgument.name
    return when (T::class.java) {
        String::class.java -> arguments.getString(key) as? T
        Int::class.java -> arguments.getInt(key) as? T
        Boolean::class.java -> arguments.getBoolean(key) as? T
        else -> throw IllegalArgumentException("Unsupported argument type: ${T::class.java.simpleName}")
    }
}


enum class Args(val navArgument: NamedNavArgument) {
    StartDestination(
        navArgument = navArgument("StartDestination") {
            type = NavType.StringType
            nullable = true
        }
    );
}