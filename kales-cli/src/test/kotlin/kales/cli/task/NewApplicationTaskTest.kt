package kales.cli.task

import com.google.common.truth.Truth.assertThat
import kales.cli.task.NewApplicationTask
import org.gradle.api.plugins.buildcomparison.outcome.internal.BuildOutcome
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class NewApplicationTaskTest {
  @get:Rule val tempDir = TemporaryFolder()

  @Test fun `creates project files`() {
    val root = tempDir.root
    val appName = "com.example.testapp"
    NewApplicationTask(root, appName).run()
    val appDir = File(root, appName)
    assertThat(File(appDir, "build.gradle").exists()).isTrue()
    assertThat(File(appDir, "gradlew").exists()).isTrue()
    assertThat(File(appDir, "gradle/wrapper/gradle-wrapper.properties").exists()).isTrue()
    assertThat(File(appDir, "gradle/wrapper/gradle-wrapper.jar").exists()).isTrue()
    assertThat(File(appDir, "src/main/resources/database.yml").exists()).isTrue()
    assertThat(File(appDir, "src/main/kotlin/com/example/testapp/Main.kt").exists()).isTrue()
    assertThat(File(appDir, "src/main/kotlin/com/example/testapp/app/controllers").exists()).isTrue()
    assertThat(File(appDir, "src/main/kotlin/com/example/testapp/db/migrate").exists()).isTrue()
    assertThat(File(appDir, "src/main/kotlin/com/example/testapp/app/views").exists()).isTrue()
    assertThat(File(appDir,
        "src/main/kotlin/com/example/testapp/app/views/layouts/AppLayout.kt").exists()).isTrue()
    assertThat(File(appDir, "src/main/kotlin/com/example/testapp/app/models").exists()).isTrue()
  }

  @Test fun `new project builds correctly`() {
    val root = tempDir.root
    val appName = "com.example.testapp2"
    NewApplicationTask(root, appName).run()
    val appDir = File(root, appName)
    val result = GradleRunner.create()
        .withProjectDir(appDir)
        .withArguments("jar")
        .build()
    assertThat(result.task(":jar")?.outcome).isEqualTo(TaskOutcome.SUCCESS)
  }
}