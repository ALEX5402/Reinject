package com.alex.injector

import android.os.Handler
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    var check: Boolean = true
    var versionmanager: Boolean = false
    var finalzippath : String = ""
    var savestate : Boolean = false
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

 }