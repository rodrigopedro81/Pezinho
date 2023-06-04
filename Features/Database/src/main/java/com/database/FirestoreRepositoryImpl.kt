package com.database

import com.entities.User
import com.google.firebase.firestore.FirebaseFirestore
import com.repositories.FirestoreRepository

class FirestoreRepositoryImpl: FirestoreRepository {

    private val databaseInstance: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun createUserInDatabase(user: User) {
        databaseInstance.collection(USERS_COLLECTION)
            .document(user.email)
            .set(user)
    }

    companion object {
        private const val USERS_COLLECTION = "users"
    }
}