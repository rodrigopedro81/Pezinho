package com.authentication

import com.entities.AuthResult
import com.entities.Barber
import com.entities.Client
import com.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.repositories.authentication.AuthenticationRepository
import com.repositories.database.FirestoreRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val databaseRepository: FirestoreRepository,
) : AuthenticationRepository {

    private val auth = FirebaseAuth.getInstance()

    override fun login(
        email: String,
        password: String,
        onResult: (AuthResult) -> Unit
    ) {
//        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
//            onResult.invoke(AuthResult(success = true, error = null))
//        }.addOnFailureListener {
//            onResult.invoke(AuthResult(success = false, error = it))
//        }
        onResult.invoke(AuthResult(success = true, error = null))
    }

    override fun registerBarber(barber: Barber, password: String, onResult: (AuthResult) -> Unit) {
        register(barber.email, password) {
            onResult.invoke(it)
            databaseRepository.createBarberInDatabase(barber)
        }
    }

    override fun registerClient(client: Client, password: String, onResult: (AuthResult) -> Unit) {
//        register(client.email, password) {
//            onResult.invoke(it)
//            databaseRepository.createClientInDatabase(client)
//        }
    }

    private fun register(
        email: String,
        password: String,
        onResult: (AuthResult) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            onResult.invoke(AuthResult(success = true, error = null))
        }.addOnFailureListener {
            onResult.invoke(AuthResult(success = false, error = it))
        }.continueWithTask {
            auth.currentUser?.sendEmailVerification()
        }
    }

    override fun userIsLoggedIn(): Boolean = auth.currentUser != null
}