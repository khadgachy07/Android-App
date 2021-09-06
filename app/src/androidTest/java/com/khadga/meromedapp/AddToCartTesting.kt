package com.khadga.meromedapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.khadga.meromedapp.adapter.ProductAdapter
import com.khadga.meromedapp.api.ServiceBuilder
import com.khadga.meromedapp.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class AddToCartTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun addTOCartUI()
    {

        runBlocking {
            ServiceBuilder.token = "Bearer "+UserRepository().loginUser("test1","test@1").token
        }
        onView(withId(R.id.navigation_home))
            .perform(click())

        Thread.sleep(400)

        onView(withId(R.id.rvProducts))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ProductAdapter.ProductViewHolder>(0,CustomAction.clickItemWithId(R.id.btnAddToCart)))

        Thread.sleep(2000)

        onView(withText("Added"))
            .check(matches(isDisplayed()))

    }


}