package me.apon.builder

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DomainObjectSet
import org.gradle.api.GradleException
import org.gradle.api.Project
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Created by yaopeng(https://github.com/apon) on 2020/12/3.
 */
fun Project.android(): BaseExtension {
    val android = project.extensions.findByType(BaseExtension::class.java)
    if (android != null) {
        return android
    } else {
        throw GradleException("Project $name is not an Android project")
    }
}

fun BaseExtension.variants(): DomainObjectSet<out BaseVariant> {
    return when (this) {
        is AppExtension -> {
            applicationVariants
        }

        else -> throw GradleException("Unsupported BaseExtension type!")
    }
}

fun File.writeXlmWithTags(body: String) {
    ("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<resources>" +
            "$body\n" +
            "</resources>")
            .also { resXml ->
                try {
                    createNewFile()
                    writeText(resXml)
                } catch (e: Exception) {
                    throw GradleException(e.message+this.absoluteFile)
                }
            }
}

fun Double.round(decimals: Int):Double{
    return BigDecimal(this).setScale(decimals, RoundingMode.HALF_EVEN).toDouble()
}

