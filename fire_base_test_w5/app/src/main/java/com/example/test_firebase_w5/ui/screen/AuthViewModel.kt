package com.example.test_firebase_w5.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.app.Activity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.tasks.await
import com.example.test_firebase_w5.R
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import androidx.compose.runtime.State
import com. google. android. gms. common. ConnectionResult
import com. google. android. gms. common. GoogleApiAvailability
import com. google. firebase. firestore. FieldValue

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    // State để theo dõi trạng thái đăng nhập
    private val _isSignedIn = MutableStateFlow(auth.currentUser != null)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn
    // Thêm các state mới
    private val _currentUser = mutableStateOf<FirebaseUser?>(null)
    val currentUser: State<FirebaseUser?> = _currentUser

    // Khởi tạo GoogleSignInClient
    fun getGoogleSignInClient(activity: Activity): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.getString(R.string.default_web_client_id)) // request
            .requestEmail()
            .requestProfile() // để yêu cầu thông tin profile
            .build()
        return GoogleSignIn.getClient(activity, gso)
    }

    suspend fun checkPrerequisites(activity: Activity): Boolean {
        return try {
            val apiAvailability = GoogleApiAvailability.getInstance()
            val resultCode = apiAvailability.isGooglePlayServicesAvailable(activity)
            if (resultCode != ConnectionResult.SUCCESS) {
                Log.e("Auth", "Google Play Services không khả dụng")
                false
            } else {
                true
            }
        } catch (e: Exception) {
            Log.e("Auth", "Lỗi kiểm tra prerequisites", e)
            false
        }
    }

    // Xử lý kết quả đăng nhập
    fun handleGoogleSignInResult(idToken: String) {
        viewModelScope.launch {
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                val authResult = auth.signInWithCredential(credential).await()
                _currentUser.value = authResult.user?.apply {
                    // Lưu thông tin user mới vào Firestore
                    saveUserToFirestore(this)
                }
                _isSignedIn.value = true
            } catch (e: Exception) {
                _currentUser.value = null
                _isSignedIn.value = false
                throw e // Re-throw để bắt ở tầng UI
            }
        }
    }

    private fun saveUserToFirestore(user: FirebaseUser) {
        Firebase.firestore.collection("users").document(user.uid)
            .set(mapOf(
                "name" to user.displayName,
                "email" to user.email,
                "createdAt" to FieldValue.serverTimestamp()
            ))
            .addOnFailureListener { e ->
                Log.e("Auth", "Lỗi lưu user vào Firestore", e)
            }
    }
}