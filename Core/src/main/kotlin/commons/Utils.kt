package commons

fun shouldSearchCompletions(search: String): Boolean =
    with(search.withoutBlank) {
        when {
            length < 5 -> false
            lowercase().contains("rua") && length < 7 -> false
            lowercase().contains("avenida") && length < 10 -> false
            else -> true
        }
    }

val String.withoutBlank: String
    get() = replace(" ", "")

// TODO () -> Criar checagens

fun checkName(newName: String): Boolean = true

fun checkEmail(newEmail: String): Boolean = true

fun checkPassword(newPassword: String): Boolean = true

fun checkPhone(newPhone: String): Boolean = true

fun checkAddress(address: String): Boolean = true