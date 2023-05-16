package com.alex.injector

import android.os.Build
import android.os.Handler
import androidx.lifecycle.ViewModel
import java.io.File

class MainViewModel : ViewModel(){

    var check: Boolean = true
    var versionmanager: Boolean = false
    var finalzippath : String = ""
    var savestate : Boolean = false
    var connect : Boolean = false
    var bitmode : Boolean = false
    var liburl : String = ""
    var versioncheck : Boolean = true
    var phreasedata :Boolean = false
    var libpath : String = ""
    var packagename : String = ""
    var files : Boolean = false
    var deletefiles : Boolean = false

    fun Splash() {
         @Suppress("DEPRECATION")
         Handler().postDelayed({
             check = false
         }, 2000)
     }
    fun isEmulator(): Boolean {
        return  Build.FINGERPRINT.contains("generic") ||
                Build.FINGERPRINT.contains("unknown") ||
                Build.MODEL.contains("google_sdk") ||
                Build.MODEL.contains("Emulator") ||
                Build.MODEL.contains("Android SDK built for x86") ||
                Build.MANUFACTURER.contains("Genymotion") ||
                (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) ||
                "google_sdk" == Build.PRODUCT
    }

     fun isDeviceRooted(): Boolean {
         // Check if the build tags contain "test-keys" or "release-keys", which are commonly used on rooted devices
         val buildTags = Build.TAGS
         if (buildTags != null && (buildTags.contains("test-keys") || buildTags.contains("release-keys"))) {
             return true
         }
         // Check if the system apps have been modified or if the su binary is present in the system
         val paths = arrayOf(
             "/system/app/Superuser.apk",
             "/sbin/su",
             "/system/bin/su",
             "/system/xbin/su",
             "/data/local/xbin/su",
             "/data/local/bin/su",
             "/system/sd/xbin/su",
             "/system/bin/failsafe/su",
             "/data/local/su",
             "/data/local/",
             "/data/local/bin/",
             "/data/local/xbin/",
             "/sbin/",
             "/system/bin/",
             "/system/bin/.ext/",
             "/system/bin/failsafe/",
             "/system/sd/xbin/",
             "/su/xbin/",
             "/su/bin/",
             "/magisk/.core/bin/",
             "/system/usr/we-need-root/",
             "/system/xbin/"
         )
         for (path in paths) {
             if (File(path).exists()) {
                 return true
             }
         }
         return false
     }

 }