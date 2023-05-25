package com.alex.injector.libServer

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import com.alex.injector.MainActivity
import com.alex.injector.objects.global.Companion.TAG
import com.topjohnwu.superuser.Shell.*
import java.io.*
import java.lang.ref.WeakReference
import java.net.URL

@Suppress("DEPRECATION")
class PointerAngel(mainActivity: MainActivity ) : AsyncTask <String, Void, String>(){

    lateinit var outputfile : File
    private val mainactivityy = WeakReference(mainActivity)
    var verify : Boolean = false
    @SuppressLint("SetWorldReadable", "SetWorldWritable")
    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String?): String? {
        val downloadlink = params[ 0 ]
        val tempDir = System.getProperty("java.io.tmpdir")
        val timestamp = System.currentTimeMillis()
        outputfile = File(tempDir, "$timestamp.zip")

        val url = URL(downloadlink)
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null

        try {
            inputStream = url.openStream()
            outputStream = FileOutputStream(outputfile)

            val buff = ByteArray(1024)
            var byteread = inputStream.read(buff)

            while (byteread != -1) {
                outputStream.write(buff, 0, byteread)
                byteread = inputStream.read(buff)
            }
            verify = true
            val tempDir = File(System.getProperty("java.io.tmpdir") as String)
            tempDir.setExecutable(true, false)
            tempDir.setReadable(true, false)
            tempDir.setWritable(true, false)
            tempDir.walkTopDown().forEach { file ->
                file.setExecutable(true, false)
                file.setReadable(true, false)
                file.setWritable(true, false)
            return outputfile.absolutePath
            }
        }catch (E : java.lang.Exception)
        {
            E.printStackTrace()
            verify = false
        }
        finally {
            inputStream?.close()
            outputStream?.close()
        }
        return null
    }
    @Deprecated("Deprecated in Java")
    override fun onPostExecute(result: String?) {

        if (verify)
        {
            result?.let {
                mainactivityy.get()?.alldone(it)
            }
            result?.let { Log.d(TAG, it) }
            Log.i( TAG , "done")
        }else {
             mainactivityy.get()?.somethingwrong()
        }

    }



}
