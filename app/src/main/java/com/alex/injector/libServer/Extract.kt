package com.alex.injector.libServer

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Process
import com.alex.injector.MainActivity
import com.alex.injector.objects.global
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
               private val zipmode : Boolean,
               private val libname : String): AsyncTask<String , Void ,String > ( )
{

    private val mainactivityy = WeakReference(mainActivity)
    @SuppressLint("SetWorldReadable", "SetWorldWritable", "SuspiciousIndentation", "SdCardPath")
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String? {
        val localpath = localpath()
        val temp = System.getProperty("java.io.tmpdir")?.plus("/")
        val userid = getuuid().toString()
        val gamedatadir = "/data/user/$userid/$packagename/cache/"
        if (zipmode)
        {

          //  Log.i("Extract" , zipFilePath)
            val destDirectory = File(System.getProperty("java.io.tmpdir") as String)
            val zipentry = zipFilePath.let {
                File(it)
            }
            val zipFile = zipFilePath.let {
                ZipFile(it)
            }

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
             tempDir.walkTopDown().forEach { file ->
                file.setExecutable(true,false)
                file.setReadable(true,false)
                file.setWritable(true,false)
            }
        }
        if(!xposedapi)
            {
            try {
                if (temp != null) {
                    if (CheckLibs(temp + libname)) {
                        listOf("cp $temp$libname $localpath$libname", "chmod 777 $localpath$libname")
                            .forEach {
                                cmd(it).exec()
                            }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                if (temp != null) {
                    if (CheckLibs(temp + libname) ) {
                      listOf("cp $temp$libname $gamedatadir$libname","chmod 777 $gamedatadir$libname")
                          .forEach {
                              cmd(it).exec()
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