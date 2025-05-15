package com.spruceid.mobilesdkexample

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import com.spruceid.mobile.sdk.CredentialsViewModel
import com.spruceid.mobile.sdk.CredentialPack
import com.spruceid.mobile.sdk.KeyManager
import com.spruceid.mobile.sdk.PresentmentState
import com.spruceid.mobile.sdk.jsonEncodedDetailsAll
import com.spruceid.mobile.sdk.rs.Credential
import com.spruceid.mobile.sdk.rs.generateTestMdl
import com.spruceid.mobile.sdk.rs.Mdoc
import com.spruceid.mobile.sdk.rs.Uuid


class TestMdoc {

    private val viewModel = CredentialsViewModel()

    fun generateMdoc(): String {
        try {
            val keyManager = KeyManager()
            val keyAlias = "testMdl"
            if (!keyManager.keyExists(keyAlias)) {
                keyManager.generateSigningKey(keyAlias)
            }

            val mdl = generateTestMdl(keyManager, keyAlias)

            val doc = mdl.jsonEncodedDetailsAll()
            return doc.toString()
        } catch (_: Exception) {
            return "{}"
        }
    }

    fun saveMdoc(value: String): Int {
        try {
            val credentialPack = CredentialPack()
            val credentials = credentialPack.addMdoc(mdoc = Mdoc.fromStringifiedDocument(value,  keyAlias = Uuid()))
            return credentials.size
        } catch (_: Exception) {
            return 0
        }
    }

    @Composable
    fun presentMdoc() {
        val credentialViewModel = CredentialsViewModel(Application())
        val session by credentialViewModel.session.collectAsState()
        val currentState by credentialViewModel.currState.collectAsState()

        when (currentState) {
            // Implement your UI based on the PresentmentState
            PresentmentState.UNINITIALIZED -> TODO()
            PresentmentState.ERROR -> TODO()
            PresentmentState.ENGAGING_QR_CODE -> TODO()
            PresentmentState.SELECT_NAMESPACES -> TODO()
            PresentmentState.SUCCESS -> TODO()
        }

    }
}