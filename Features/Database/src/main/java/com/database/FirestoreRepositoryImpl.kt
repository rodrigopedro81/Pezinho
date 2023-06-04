package com.database

import com.entities.User
import com.entities.UserType
import com.google.firebase.firestore.FirebaseFirestore
import com.repositories.FirestoreRepository

class FirestoreRepositoryImpl : FirestoreRepository {

    private val databaseInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun createUserInDatabase(user: User) {
        databaseInstance.collection(getUserCollection(user))
            .document(user.email)
            .set(user)
    }

    fun getUser() {

    }

    fun getBarbers() {

    }

    private fun getUserCollection(user: User) =
        if (user.userType == UserType.CLIENT) CLIENTS_COLLECTION else BARBERS_COLLECTION

    companion object {
        private const val CLIENTS_COLLECTION = "clients"
        private const val BARBERS_COLLECTION = "barbers"
    }
}