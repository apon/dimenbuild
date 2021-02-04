package me.apon.builder


import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

/**
 * Created by yaopeng(https://github.com/apon) on 2020/12/2.
 */
class BuilderPlugin:Plugin<Project> {
    override fun apply(project: Project) {

        project.extensions.create("dimenBuild",BuilderParams::class.java)


        project.android().variants().all { variant ->

            val taskName = "generate${variant.name.capitalize()}DimenValues"

            val outputPath = "${project.buildDir}/generated/res"
            //将task添加到构建树
            variant.assembleProvider.dependsOn(taskName)
//            variant.preBuildProvider.dependsOn(taskName)
            // 注册task
            project.tasks.register(taskName, BuilderTask::class.java) { dimenTask ->
                dimenTask.group = "DimensionBuilder"
                //定义dimen.xml文件输出目录(如:/app/build/generated/res/release/values/)
                val outputDirectory =
                        File("$outputPath/${variant.dirName}").apply { mkdirs() }
                dimenTask.outputDir = outputDirectory
                dimenTask.designWidth = project.extensions.findByType(BuilderParams::class.java)?.designWidth?:700
                dimenTask.smallestWidth = project.extensions.findByType(BuilderParams::class.java)?.smallestWidth
                // 将dimen.xml文件目录设置为app资源目录
                variant.registerGeneratedResFolders(
                        project.files(outputDirectory).builtBy(dimenTask)
                )
            }
        }
    }
}