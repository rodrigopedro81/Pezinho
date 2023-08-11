package com.database

import com.entities.AvailableService
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

    fun getMockedBarberShops() = listOf(
        getMockedBarber(),
        getMockedBarber(),
        getMockedBarber(),
    )

    fun getMockedBarber() = BarberShop(
        icon = "https://scontent-gig4-2.xx.fbcdn.net/v/t39.30808-6/346982757_1587514885072337_8575966607121764005_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=be3454&_nc_ohc=KSvcqs_vpwcAX_XFk62&_nc_ht=scontent-gig4-2.xx&oh=00_AfBgjV9jw8-uAXDFJk7Yc8rVL0RrtN9kSmg22gzuotQ0jA&oe=64DB3057",
        title = "Peres Barber",
        address = "Coronel Pimenta",
        isOpen = true,
        services = listOf(
            AvailableService(
                id = 1,
                title = "Corte de cabelo",
                price = "R$ 30",
                description = "Corte de cabelo",
                duration = "45 minutos",
            ),
            AvailableService(
                id = 2,
                title = "Barba",
                price = "R$ 15",
                description = "Barba",
                duration = "10 minutos",
            ),
        ),
    )

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val BARBERS_COLLECTION = "barbers"
        private const val CLIENTS_COLLECTION = "clients"
    }
}