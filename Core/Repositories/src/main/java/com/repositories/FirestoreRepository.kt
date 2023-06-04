package com.repositories

import com.entities.User

interface FirestoreRepository {
    fun createUserInDatabase(user: User)
}