package com.alex.injector;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.NonNull;


import com.alex.injector.callbacks.Callbacks;

public class handlerclass implements Handler.Callback {
    private  InternalClass messageConnection;

    public  InternalClass mSGConnection = messageConnection;
    private Messenger remoteMessenger;
    private final Messenger replyMessenger = new Messenger(new Handler(Looper.getMainLooper(), this));

    public final Context context;
    private String packagename=""; // don't do this parameater to null
    private String libpath= "";

// this class was created by alex5402 to prevent injection failed and leave the main activity flexible
    public handlerclass(Context context,String packagename,String libpath) {
        this.context = context;
        this.packagename = packagename;
        this.libpath = libpath;
    }

    public boolean handleMessage(@NonNull Message message) {
        int result = message.getData().getInt("result");
        if (result == -1) {
            Log.i("handleMessage", "MSGConnection: handleMessage successful" + result);
        } else
        if (result == 0) {
            Log.i("handleMessage", "MSGConnection: handleMessage using 0" +     result);
        } else {
            Log.i("handleMessage", "MSGConnection: handleMessage using 1"   +   result);
        }
        return false;
    }

public void Connect()
{
    try {
        Callbacks.bind(new Intent(context, Callbacks.class), new InternalClass());
    }catch (Exception e)
    {
        e.printStackTrace();
    }

}
public void disconnect()
 {
try {
    Callbacks.unbind(mSGConnection);
} catch ( Exception e)
{
    e.printStackTrace();
}
    }

    public class InternalClass implements ServiceConnection {
        InternalClass() {
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            remoteMessenger = new Messenger(iBinder);
            messageConnection = this;
            Message message = Message.obtain((Handler) null, 1);
            message.getData().putString("pkg", packagename);
            message.getData().putString("lib", libpath);
            message.replyTo = replyMessenger;

            try {
                remoteMessenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            remoteMessenger = null;
            messageConnection = null;
        }
    }

}
