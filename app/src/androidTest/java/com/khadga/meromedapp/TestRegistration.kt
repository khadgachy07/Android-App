package com.khadga.meromedapp

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class TestRegistration {
    @get:Rule
    val testRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testRegisterUI() {
        onView(withId(R.id.etFullName)).perform(typeText("Test Registration"))
        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.etUsername)).perform(typeText("test1"))
        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.etEmail)).perform(typeText("test1@gmail.com"))
        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.etPhone)).perform(typeText("9812453678"))
        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.etPassword)).perform(typeText("test@1"))
        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.etConfirmPass)).perform(typeText("test@1"))


        closeSoftKeyboard()
        Thread.sleep(400)

        onView(withId(R.id.btnRegister)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }
}