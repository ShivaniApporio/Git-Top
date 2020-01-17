package com.example.top_github.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.top_github.R
import org.junit.Rule


class MainActivityEspressoTest {
    @Rule
    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    fun perform() {
//      var recyclerView=mActivityRule.activity.findViewById<RecyclerView>(R.id.rv_list)
//       var itemCount  = recyclerView.adapter?.itemCount

    }
}

