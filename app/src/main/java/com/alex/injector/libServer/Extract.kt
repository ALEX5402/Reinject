package com.alex.injector.libServer

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Process
import android.util.Log
import com.alex.injector.MainActivity
import com.alex.injector.objects.global
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
class Extract (private val zipFilePath : String,
               mainActivity: MainActivity,
               private val xposedapi : Boolean,
               private val packagename : String,
               private val zipmode : Boolean): AsyncTask<String , Void ,String > ( )
{

    private val mainactivityy = WeakReference(mainActivity)
    @SuppressLint("SetWorldReadable", "SetWorldWritable", "SuspiciousIndentation", "SdCardPath")
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String? {
        if (zipmode)
        {

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
        }

        val localpath = localpath()
        val libv1 = libv1()
        val libv2 = libv2()
        val libv3 = libv3()
        val libv4 = libv4()
        val temp = System.getProperty("java.io.tmpdir")?.plus("/")
        val userid = getuuid().toString()
        val gamedatadir = "/data/user/$userid/$packagename/cache/"


        if(!xposedapi)
            {
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
                                listOf("$temp$libv1", "$temp$libv2", "$temp$libv3", "$temp$libv4").forEach {
                                    File(it).delete()
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                listOf("$temp$libv1", "$temp$libv2", "$temp$libv3", "$temp$libv4").forEach {
                    File(it).delete()
                }
            }
        } else {
            try {
                if (temp != null) {
                    if (CheckLibs(temp + libv1) || CheckLibs(temp + libv2) || CheckLibs(temp + libv3) || CheckLibs(temp + libv4) ) {
                        val commands = arrayOf(
                            "cp $temp${libv1()} $gamedatadir${libv1()}",
                            "cp $temp$libv2 $gamedatadir$libv2",
                            "cp $temp$libv3 $gamedatadir$libv3",
                            "cp $temp$libv4 $gamedatadir$libv4",
                            "chmod 777 $gamedatadir$libv1",
                            "chmod 777 $gamedatadir$libv2",
                            "chmod 777 $gamedatadir$libv3",
                            "chmod 777 $gamedatadir$libv4"
                        )
                        try {
                            for (command in commands) {
                                cmd(command).exec()
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
     return null
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (xposedapi)
        {
            mainactivityy.get()?.xposedinject()
        }
        else
        {
            mainactivityy.get()?.injection()
        }
    }
    fun CheckLibs ( path : String) : Boolean {
        val file = File(path)
        return file.exists()
    }
    private fun getuuid ( ) : Int
    {
    return Process.myUid() / 100000 }
}