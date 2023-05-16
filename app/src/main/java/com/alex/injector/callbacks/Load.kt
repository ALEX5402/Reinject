package com.alex.injector.callbacks

class Load {

    external fun Inject(pkg: String, libPath: String): Int

        init {
            System.loadLibrary("alex")
        }
}