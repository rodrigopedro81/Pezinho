package com.repositories.database

import com.entities.Barber
import com.entities.Client
import com.entities.User

interface FirestoreRepository {

    fun createBarberInDatabase(barber: Barber)

    fun createClientInDatabase(client: Client)

    fun getBarbers(
        onResult: (List<Barber>) -> Unit,
        onError: (Exception) -> Unit = {}
    )
}