package com.alex.injector.xposed

import android.os.Process
import de.robv.android.xposed.XposedBridge
import java.io.File

class Clear {
// don't do anything if you dont know what are you doing
    fun loadlib (libname: String , pkg : String)
    {
        val userid = getuuid().toString()
        val tempdir = "/data/user/$userid/$pkg/cache/"
        try {
            if (checkfile(tempdir+libname))
            {
                System.load(tempdir+libname)
                XposedBridge.log(tempdir+libname)
                Thread.sleep(1000) // dont remove this delay
                delete(tempdir+libname)
            }else{
                XposedBridge.log("Lib Not Found")
            }
        }catch (err : Exception)
        {
            XposedBridge.log(err)
            err.printStackTrace()
        }
    }
    private fun getuuid ( ) : Int
    {
        return Process.myUid() / 100000
    }

    fun checkfile (path : String) : Boolean {
        val file = File(path)
        return file.exists()
    }
    fun delete(path: String)
    {
        val file = File(path)
        if (file.exists())
        {
            file.delete()
        }
    }
}