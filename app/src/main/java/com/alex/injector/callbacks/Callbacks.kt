package com.alex.injector.callbacks

import android.content.Intent
import android.os.*
import android.util.Log

import com.topjohnwu.superuser.ipc.RootService

class Callbacks : RootService(), Handler.Callback {

    override fun onBind(intent: Intent): IBinder {
        Log.e("Alexinjector", "RootService: onBind")

        val handler = Handler(Looper.getMainLooper(), this)
        return Messenger(handler).binder
    }

    override fun handleMessage(message: Message): Boolean {
        Log.e("Alexinjector", "RootService: handleMessage")

        if (message.what != 1) {
            return false
        }
        val msg = Message.obtain()
        val bundle = Bundle()
        val load = Load()
        val pkgName = message.data.getString("pkg")!!
        val libPath = message.data.getString("lib")!!
        val result = load.Inject(pkgName,libPath)
        Log.w("Handler", pkgName + libPath)
        bundle.putInt("result", result)
        msg.data = bundle
        try {
            message.replyTo.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        return false
    }

}
