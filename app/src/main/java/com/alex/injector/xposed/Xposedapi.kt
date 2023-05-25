package com.alex.injector.xposed


import com.alex.injector.objects.global
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage

class Xposedapi : IXposedHookLoadPackage {


    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam)
    {
                val loadlib = Clear()
                if (lpparam.packageName == global.gamename[0])
                {
                    loadlib.loadlib("libv4.so",global.gamename[0] )

                } else if (lpparam.packageName == global.gamename[1])
                {
                    loadlib.loadlib("libv3.so",global.gamename[1])

                }else if (lpparam.packageName == global.gamename[3])
                {
                    loadlib.loadlib("libv3.so",global.gamename[3])

                }else if (lpparam.packageName == global.gamename[4])
                {
                    XposedBridge.log(global.gamename[4])
                    loadlib.loadlib("libv3.so",global.gamename[4])

                } else if (lpparam.packageName == global.gamename[5])

                 return loadlib.loadlib("libv3.so",global.gamename[5])
    }

}