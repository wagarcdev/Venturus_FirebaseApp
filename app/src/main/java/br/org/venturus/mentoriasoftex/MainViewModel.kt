package br.org.venturus.mentoriasoftex

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.org.venturus.mentoriasoftex.utils.BUTTON_CLICKS
import br.org.venturus.mentoriasoftex.utils.CLICKS
import br.org.venturus.mentoriasoftex.utils.TOTAL_CLICKS
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainViewModel: ViewModel() {

    private val TAG = "MAIN_VIEW_MODEL"

    private val db = Firebase.firestore
    private val usersDbRef = db.collection("users")

    var totalClicks by mutableStateOf(0L)
        private set

    var buttonClicks by mutableStateOf(0L)
        private set


    fun sendClicksToFirebase() {
        buttonClicks = ++totalClicks

        val data = hashMapOf(CLICKS to buttonClicks)

        db.collection(BUTTON_CLICKS).document(TOTAL_CLICKS)
            .set(data)
            .addOnSuccessListener {
                Log.d(TAG, "Document SnapShot added, data = $data ")
                getTotalClicks()
            }
            .addOnFailureListener { error ->
                Log.w(TAG, "Error while adding the document... error = $error")

            }
    }

    fun getTotalClicks() {
        db.collection(BUTTON_CLICKS).document(TOTAL_CLICKS)
            .get()
            .addOnSuccessListener { document ->
                document?.let {
                    val currentValue = it.data?.get(CLICKS)
                    totalClicks = (currentValue as? Long) ?: 0L
                    Log.d(TAG, "TOTAL CLICKS = ${totalClicks}" )
                }
            }
            .addOnFailureListener{ exception ->
                Log.w(TAG, "Failure while getting documents, error = $exception", )
            }
    }




}