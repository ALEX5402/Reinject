package com.alex.injector.settings

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.alex.injector.R
import com.alex.injector.databinding.SettingsBinding
import com.alex.injector.objects.global
import es.dmoral.toasty.Toasty

class Settings : AppCompatActivity()
{
    lateinit var bind :SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = SettingsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = SharedPreferences.edit()
        val xposedmode = SharedPreferences.getBoolean(global.XPOSEMODE,false)
        val ptracemode = SharedPreferences.getBoolean(global.PtraceMode,false)
        val emulator = SharedPreferences.getBoolean(global.X86MODE,false)
        bind.xposedmode.isChecked = xposedmode
        bind.ptracemode.isChecked = ptracemode
        bind.emulatormode.isChecked = emulator

        if (!isDarkTheme(this))
        {
            val window: Window = this.window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = this.resources.getColor(R.color.statusbarcolour)
        }


        bind.xposedmode.setOnCheckedChangeListener{_,ischecked ->
            if (ischecked)
            {
                editor.putBoolean(global.XPOSEMODE ,bind.xposedmode.isChecked).apply()
                 bind.ptracemode.isChecked = false
                Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()
            }else{
                editor.putBoolean(global.XPOSEMODE ,bind.xposedmode.isChecked).apply()
                Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()

            }
        }
        bind.ptracemode.setOnCheckedChangeListener{_,ischecked ->
            if (ischecked)
            {
                editor.putBoolean(global.PtraceMode ,bind.ptracemode.isChecked).apply()
                bind.xposedmode.isChecked = false
                Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()
            }else{
                editor.putBoolean(global.PtraceMode ,bind.ptracemode.isChecked).apply()
                Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()


            }
        }
         bind.emulatormode.setOnCheckedChangeListener { _, ischecked ->
             if (ischecked) {
                 editor.putBoolean(global.X86MODE, bind.emulatormode.isChecked).apply()
                 Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()
             } else {
                 editor.putBoolean(global.X86MODE, bind.emulatormode.isChecked).apply()
                 Toasty.warning(this ,R.string.pleaserestartapp,Toasty.LENGTH_LONG ).show()
             }
         }


    }
    fun openurl ( url : String ) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }catch ( e : java.lang.Exception)
        {
            e.printStackTrace()
        }

    }

    fun cleardust (view : View)
    {
        Toasty.warning(this ,R.string.Clearcache,Toasty.LENGTH_LONG ).show()
    }
    fun isDarkTheme(context: Context): Boolean {
        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
    fun report(view: View)
    {

        openurl("https://github.com/ALEX5402/Reinject")

    }
}