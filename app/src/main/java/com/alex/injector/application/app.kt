package com.alex.injector.application

import android.app.Application
import com.alex.injector.objects.global.Companion.shell
import com.google.android.material.color.DynamicColors
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.topjohnwu.superuser.Shell


class app : Application()

{
    override fun onCreate() {
        super.onCreate()
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        DynamicColors.applyToActivitiesIfAvailable(this)
        Shell.getShell().apply {
            shell = true
        }
    }

}