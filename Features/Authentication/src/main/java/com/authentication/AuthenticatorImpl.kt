package com.authentication

import com.entities.AuthResult
import com.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.repositories.Authenticator
import com.repositories.FirestoreRepository
import javax.inject.Inject

class AuthenticatorImpl @Inject constructor(
    private val databaseRepository: FirestoreRepository,
) : Authenticator {

    private val auth = FirebaseAuth.getInstance()

    override fun login(
        email: String,
        password: String,
        onResult: (AuthResult) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            onResult.invoke(AuthResult(success = true, error = null))
        }.addOnFailureListener {
            onResult.invoke(AuthResult(success = false, error = it))
        }
    }

    override fun register(user: User, onResult: (AuthResult) -> Unit) {
        register(user.email, user.password) {
            onResult.invoke(it)
            if (it.success) {
                auth.currentUser?.sendEmailVerification()
                databaseRepository.createUserInDatabase(user)
            }
        }
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
        }
    }

    override fun userIsLoggedIn(): Boolean = auth.currentUser != null
}