package com.database

import com.entities.Barber
import com.entities.BarberShop
import com.entities.Client
import com.entities.User
import com.entities.UserType
import com.google.firebase.firestore.FirebaseFirestore
import com.repositories.database.FirestoreRepository

class FirestoreRepositoryImpl : FirestoreRepository {

    private val databaseInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun createBarberInDatabase(barber: Barber) {
//        databaseInstance.run {
//            collection(USERS_COLLECTION)
//                .document(barber.email)
//                .set(User.getUser(barber))
//            collection(BARBERS_COLLECTION)
//                .document(barber.email)
//                .set(barber)
//        }
    }

    override fun createClientInDatabase(client: Client) {
//        databaseInstance.collection(USERS_COLLECTION)
//            .document(user.email)
//            .set(user)
    }

    override fun getBarberShops(
        onResult: (List<BarberShop>) -> Unit,
        onError: (Exception) -> Unit
    ) {
//        databaseInstance.collection(BARBERS_COLLECTION).get()
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    val documents = it.result.documents.filterNotNull()
//                    if (documents.isEmpty()) return@addOnCompleteListener
//                    val barbers = documents.mapNotNull { document ->
//                        document.toObject(Barber::class.java)
//                    }
//                    onResult.invoke(barbers)
//                } else {
//                    onError.invoke(it.exception ?: Exception("Unknown error"))
//                }
//            }
        onResult.invoke(getMockedBarberShops())
    }

    private fun getMockedBarberShops() = listOf(
        BarberShop(
            title = "Barbearia 1",
            address = "",
            snippet = "Pra quem sabe o que quer!",
            latitude = 0.0,
            longitude = 0.0,
            barbers = listOf(),
            services = listOf()
        ),
        BarberShop(
            title = "Barbearia 2",
            address = "",
            snippet = "A melhor barbearia do engenho!",
            latitude = 0.0,
            longitude = 0.0,
            barbers = listOf(),
            services = listOf()
        ),
        BarberShop(
            title = "Barbearia 3",
            address = "",
            snippet = "Especializados em cortes masculinos",
            latitude = 0.0,
            longitude = 0.0,
            barbers = listOf(),
            services = listOf()
        ),
    )

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val BARBERS_COLLECTION = "barbers"
        private const val CLIENTS_COLLECTION = "clients"
    }
}