package me.apon.builder

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * Created by yaopeng(https://github.com/apon) on 2020/12/4.
 */
open class BuilderTask:DefaultTask() {


    @Input
    var designWidth: Int = 700
    @Input
    var smallestWidth: Array<Int>? = null

    @OutputDirectory
    lateinit var outputDir: File

    @TaskAction
    fun makeResources() {


        smallestWidth?.forEach {
            val dimenMap = mutableMapOf<String,String>()
            var outputFile = File(outputDir, "values-sw${it}dp/dimen.xml")
            if (it==smallestWidth?.min()){
                outputFile = File(outputDir, "values/sw${it}dp-dimen.xml")
            }
            outputFile.parentFile.mkdirs()
//            println("outputFile:${outputFile.absoluteFile}")
            dimenMap["base_swdp"] = "${it}dp"
            for(i in 0..designWidth){
                var dpValue = i / designWidth.toDouble() * it
                dimenMap["_${i}swdp"] = "${dpValue.round(2)}dp"
            }

            dimenMap.entries.joinToString("") { (dimenName, dimen) ->
                "\n    <dimen name=\"$dimenName\">$dimen</dimen>"
            }.also { xml ->
                outputFile.writeXlmWithTags(xml)
            }
        }


    }
}