package cz.okycelt.composetv.benchmark

import androidx.benchmark.macro.CompilationMode
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
    fun navigateLazyRowAndColumnsCompilationNone() = navigateLazyRowAndColumns(CompilationMode.None())

    @Test
    fun navigateLazyRowAndColumnsCompilationPartial() = navigateLazyRowAndColumns(CompilationMode.Partial())

    private fun navigateLazyRowAndColumns(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "cz.okycelt.composetv",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = compilationMode,
        setupBlock = {
            pressHome()
            startActivityAndWait()

            val selector = By.text("Lazy Rows and Columns")
            device.wait(Until.hasObject(selector), 5_000)

            val barItem = device.findObject(selector)
            device.pressDPadDown() // focus first item
            barItem.wait(Until.focused(true), 5_000)

            device.pressDPadDown()
            barItem.wait(Until.focused(false), 5_000)
        }
    ) {
        repeat(10) { device.pressDPadDown() }
        repeat(10) { device.pressDPadRight() }
        repeat(10) { device.pressDPadLeft() }
        repeat(10) { device.pressDPadUp() }
    }

    @Test
    fun navigateImmersiveListCompilationNone() = navigateImmersiveList(CompilationMode.None())

    @Test
    fun navigateImmersiveListCompilationPartial() = navigateImmersiveList(CompilationMode.Partial())

    private fun navigateImmersiveList(compilationMode: CompilationMode) = benchmarkRule.measureRepeated(
        packageName = "cz.okycelt.composetv",
        metrics = listOf(FrameTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
        compilationMode = compilationMode,
        setupBlock = {
            pressHome()
            startActivityAndWait()

            val selector = By.text("Immersive List")
            device.wait(Until.hasObject(selector), 5_000)

            val barItem = device.findObject(selector)
            device.pressDPadRight() // focus first item
            device.pressDPadRight()
            device.pressDPadRight()
            barItem.wait(Until.focused(true), 5_000)

            device.pressDPadDown()
            barItem.wait(Until.focused(false), 5_000)
        }
    ) {
        repeat(3) { device.pressDPadDown() }
        repeat(2) { device.pressDPadRight() }
        repeat(2) { device.pressDPadLeft() }
        repeat(3) { device.pressDPadUp() }
    }
}