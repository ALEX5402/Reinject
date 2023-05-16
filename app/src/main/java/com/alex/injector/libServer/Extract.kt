package com.alex.injector.libServer

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.alex.injector.MainActivity
import com.alex.injector.objects.global
import com.alex.injector.objects.global.Companion.TAG
import com.alex.injector.objects.global.Companion.libv1
import com.alex.injector.objects.global.Companion.libv2
import com.alex.injector.objects.global.Companion.libv3
import com.alex.injector.objects.global.Companion.libv4
import com.alex.injector.objects.global.Companion.localpath
import com.topjohnwu.superuser.Shell.cmd
import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.exception.ZipException
import java.io.File
import java.lang.ref.WeakReference

@Suppress("DEPRECATION")
class Extract (private val zipFilePath : String , mainActivity: MainActivity): AsyncTask<String , Void ,String > ( )

{

    private val mainactivityy = WeakReference(mainActivity)
    var reasult : String = ""
    @SuppressLint("SetWorldReadable", "SetWorldWritable", "SuspiciousIndentation")
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String? {
        Log.i("Extract" , zipFilePath)
        val destDirectory = File(System.getProperty("java.io.tmpdir") as String)
        val zipentry = File(zipFilePath)
        val zipFile = ZipFile(zipFilePath)


        try {
            if (zipFile.isEncrypted) {
                zipFile.setPassword(global.c)
            }
            zipFile.extractAll(destDirectory.toString())

        } catch (e: ZipException) {
            e.printStackTrace()
        }

        if (zipentry.exists()) {
            zipentry.delete()
        }

        // Set permissions for all files in the system temporary directory
        val tempDir = File(System.getProperty("java.io.tmpdir") as String)
        tempDir.setExecutable(true, false)
        tempDir.setReadable(true, false)
        tempDir.setWritable(true, false)
        tempDir.walkTopDown().forEach { file ->
            file.setExecutable(true, false)
            file.setReadable(true, false)
            file.setWritable(true, false)
        }

        val localpath = localpath()
        val libv1 = libv1()
        val libv2 = libv2()
        val libv3 = libv3()
        val libv4 = libv4()
        val temp = System.getProperty("java.io.tmpdir")?.plus("/")

        try {
            if (temp != null) {
                if (CheckLibs(temp + libv1) || CheckLibs(temp + libv2) || CheckLibs(temp + libv3) || CheckLibs(temp + libv4) ) {
                    val commands = arrayOf(
                        "cp $temp${libv1()} $localpath${libv1()}",
                        "cp $temp$libv2 $localpath$libv2",
                        "cp $temp$libv3 $localpath$libv3",
                        "cp $temp$libv4 $localpath$libv4",
                        "chmod 777 $localpath$libv1",
                        "chmod 777 $localpath$libv2",
                        "chmod 777 $localpath$libv3",
                        "chmod 777 $localpath$libv4"
                    )
                    try {
                        for (command in commands) {
                            cmd(command).exec()
                        }
                        listOf("$temp$libv1", "$temp$libv2", "$temp$libv3", "$temp$libv4").forEach {
                            File(it).delete()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()

                        listOf("$temp$libv1", "$temp$libv2", "$temp$libv3", "$temp$libv4").forEach {
                            File(it).delete()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            listOf("$temp$libv1", "$temp$libv2", "$temp$libv3", "$temp$libv4").forEach {
                File(it).delete()
            }
        }
     return null
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        mainactivityy.get()?.injection()
        Log.d(TAG, reasult)

    }

    fun CheckLibs ( path : String) : Boolean {
        val file = File(path)
        return file.exists()
    }


}