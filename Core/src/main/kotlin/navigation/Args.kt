package navigation

/**
 * This object represents the default Arguments constants and 'easy to implement' methods to replace them
 */
object Args {
    const val START_DESTINATION_ARG = "{startDestination}"

    fun String.replaceStartDestination(route: String) = replace(START_DESTINATION_ARG, route)
}