package com.database

import com.entities.Barber
import com.entities.Client
import com.entities.User
import com.entities.UserType
import com.google.firebase.firestore.FirebaseFirestore
import com.repositories.database.FirestoreRepository

class FirestoreRepositoryImpl : FirestoreRepository {

    private val databaseInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun createBarberInDatabase(barber: Barber) {
        databaseInstance.run {
            collection(USERS_COLLECTION)
                .document(barber.email)
                .set(User.getUser(barber))
            collection(BARBERS_COLLECTION)
                .document(barber.email)
                .set(barber)
        }
    }

    override fun createClientInDatabase(client: Client) {
//        databaseInstance.collection(USERS_COLLECTION)
//            .document(user.email)
//            .set(user)
    }

    override fun getBarbers(
        onResult: (List<Barber>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        databaseInstance.collection(BARBERS_COLLECTION).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val documents = it.result.documents.filterNotNull()
                    if (documents.isEmpty()) return@addOnCompleteListener
                    val barbers = documents.mapNotNull { document ->
                        document.toObject(Barber::class.java)
                    }
                    onResult.invoke(barbers)
                } else {
                    onError.invoke(it.exception ?: Exception("Unknown error"))
                }
            }
    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val BARBERS_COLLECTION = "barbers"
        private const val CLIENTS_COLLECTION = "clients"
    }
}