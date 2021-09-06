package com.khadga.meromedapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.khadga.meromedapp.adapter.CartAdapter
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class DeleteCartTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun addTOCartUI()
    {

        runBlocking {
            ServiceBuilder.token = "Bearer "+ UserRepository().loginUser("test1","test@1").token
        }
        Espresso.onView(ViewMatchers.withId(R.id.cartFragment))
            .perform(ViewActions.click())

        Thread.sleep(400)

        Espresso.onView(ViewMatchers.withId(R.id.rvCart))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CartAdapter.CartViewHolder>(0,CustomAction.clickItemWithId(R.id.ibDelete)))

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withText("Deleted"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }


}