package br.org.venturus.mentoriasoftex.presentation.screens.graph_auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.org.venturus.mentoriasoftex.domain.model.UserProfile
import br.org.venturus.mentoriasoftex.google.GoogleUserModel
import br.org.venturus.mentoriasoftex.presentation.navigation.SoftexGraph
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class AuthViewModel: ViewModel() {

    private val TAG = "MAIN_VIEW_MODEL"

    private val db = Firebase.firestore
    val usersDbRef = db.collection("users")

    val document = "totalClicks"
    val collection = "buttonClicks"
    val field = "clicks"

    var isLogged: MutableState<Boolean> = mutableStateOf(false)
        private set

    var loadingState = MutableLiveData(false)
        private set

    var userState = MutableLiveData<GoogleUserModel>()
        private  set


    var simpleUSer = MutableLiveData<UserProfile?>(null)
        private set


    fun hideLoading() {
        loadingState.value = false
    }

    fun signInUser(
        password: MutableState<String>,
        username: MutableState<String>,
        context: Context,
        navHostController: NavHostController
    ) {
        viewModelScope.launch {
            val comingPassword = async {  getUserPassword(username = username.value) }.await()
            if (comingPassword == password.value) {
                simpleUSer.value = UserProfile(
                    email = username.value
                )
                navHostController.navigate(SoftexGraph.APP)
            } else {
                Toast.makeText(context, "Senha incorreta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun getUserPassword(username: String): String? {

        var userPass: String? = null
        var user: UserProfile?

        viewModelScope.launch {

            val job = viewModelScope.launch {
                try {
                    val querySnapShot = usersDbRef
                        .whereEqualTo("nickname", username)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                Log.d("getUserPassword", "${document.id} => ${document.data}")
                            }
                        }
                        .addOnFailureListener { e ->
                            Log.w("getUserPassword", "Error getting documents: $e", e)
                        }.await()

                    user = querySnapShot.documents[0].toObject<UserProfile>()
                    userPass = user?.password.toString()
                } catch (e: Exception) {
                    Log.w("getUserPassword", "Error : $e ", e)
                }

            }
            job.join()

        }.join()

        return userPass

    }

    fun fetchSignInUser(email: String?, name: String?) {
        loadingState.value = true

        userState.value = GoogleUserModel(
            email = email,
            name = name
        )

        isLogged.value = true
        loadingState.value = false
    }



}