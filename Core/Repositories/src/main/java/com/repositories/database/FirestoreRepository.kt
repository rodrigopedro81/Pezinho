package com.repositories.database

import com.entities.Barber
import com.entities.BarberShop
import com.entities.Client

interface FirestoreRepository {

    fun createBarberInDatabase(barber: Barber)

    fun createClientInDatabase(client: Client)

    fun getBarberShops(
        onResult: (List<BarberShop>) -> Unit,
        onError: (Exception) -> Unit = {}
    )
}