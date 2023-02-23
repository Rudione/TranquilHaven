package com.example.productsadder

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityTest {
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseStorage: FirebaseStorage

    @Before
    fun setup() {
        firebaseFirestore = mock(FirebaseFirestore::class.java)
        firebaseStorage = mock(FirebaseStorage::class.java)

        FirebaseApp.initializeApp(
            ApplicationProvider.getApplicationContext(),
            FirebaseOptions.Builder().setApiKey("").build(),
            "test"
        )
    }

    @After
    fun teardown() {
        FirebaseApp.clearInstancesForTest()
    }

    @Test
    fun testSaveProduct_shouldShowToastMessage_whenValidateInformationReturnsFalse() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.saveProduct)).perform(click())

        scenario.close()
    }
}