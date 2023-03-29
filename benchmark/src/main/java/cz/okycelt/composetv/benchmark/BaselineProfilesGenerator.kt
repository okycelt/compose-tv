package cz.okycelt.composetv.benchmark

import androidx.benchmark.macro.ExperimentalBaselineProfilesApi
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalBaselineProfilesApi::class)
@RunWith(AndroidJUnit4ClassRunner::class)
class BaselineProfilesGenerator {
    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() = rule.collectBaselineProfile(
        packageName = "cz.okycelt.composetv"
    ) {
        startActivityAndWait()
        device.wait(Until.hasObject(By.text("Lazy Rows and Columns")), 30_000)

        // focus first item
        device.pressDPadDown()

        // enter "Lazy Rows and Columns"
        device.pressDPadDown()

        repeat(10) { device.pressDPadDown() }
        repeat(10) { device.pressDPadRight() }
        repeat(10) { device.pressDPadLeft() }
        repeat(10) { device.pressDPadUp() }
    }
}