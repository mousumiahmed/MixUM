package com.mixbyte.mixum.utils;

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage




object FirebaseUtil {

    // Firebase Authentication
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    // Firestore Database
    val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    // Firebase Storage (for images)
    val storage: FirebaseStorage by lazy {
        FirebaseStorage.getInstance()
    }

    // Current logged-in user UID
    val currentUserId: String?
        get() = auth.currentUser?.uid

    /**
     * Check if user is logged in
     */
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    /**
     * Sign out current user
     */
    fun signOut() {
        auth.signOut()
    }
}

