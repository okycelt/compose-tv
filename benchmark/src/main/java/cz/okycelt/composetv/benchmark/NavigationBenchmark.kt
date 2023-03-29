package cz.okycelt.composetv.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun navigateLazyRowAndColumns() = benchmarkRule.measureRepeated(
        packageName = "cz.okycelt.composetv",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.text("Lazy Rows and Columns")), 30_000)

            device.pressDPadDown()
        }
    ) {
        repeat(10) { device.pressDPadDown() }
        repeat(10) { device.pressDPadRight() }
        repeat(10) { device.pressDPadUp() }
        repeat(10) { device.pressDPadLeft() }
    }

    @Test
    fun navigateImmersiveList() = benchmarkRule.measureRepeated(
        packageName = "cz.okycelt.composetv",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        setupBlock = {
            pressHome()
            startActivityAndWait()
            device.wait(Until.hasObject(By.text("Lazy Rows and Columns")), 30_000)

            device.pressDPadRight()
            device.pressDPadRight()
            device.pressDPadRight()
            device.wait(Until.hasObject(By.res("ImmersiveListContent")), 30_000)
            device.pressDPadDown()
        }
    ) {
        repeat(3) { device.pressDPadDown() }
        repeat(2) { device.pressDPadRight() }
        repeat(2) { device.pressDPadLeft() }
        repeat(3) { device.pressDPadUp() }
    }
}