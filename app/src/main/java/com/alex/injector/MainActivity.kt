package com.alex.injector

import android.annotation.SuppressLint
import android.content.*
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.Uri
import android.os.*
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.alex.injector.crashmanager.CrashLoL
import com.alex.injector.data.UpdateInfo
import com.alex.injector.data.mainlink
import com.alex.injector.data.realtimedatabase
import com.alex.injector.databinding.ActivityMainBinding
import com.alex.injector.libServer.Extract
import com.alex.injector.libServer.PointerAngel
import com.alex.injector.objects.global
import com.alex.injector.objects.global.Companion.ANIMATION_STATE_KEY
import com.alex.injector.objects.global.Companion.CARD_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL2_CHECKED_STATE_KEY
import com.alex.injector.objects.global.Companion.GLOBAL2_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL3_CHECKED_STATE_KEY
import com.alex.injector.objects.global.Companion.GLOBAL5_CHECKED_STATE_KEY
import com.alex.injector.objects.global.Companion.GLOBAL5_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL6_CHECKED_STATE_KEY
import com.alex.injector.objects.global.Companion.GLOBAL6_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL7_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL8_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.GLOBAL_CHECKED_STATE_KEY
import com.alex.injector.objects.global.Companion.GLOBAL_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.HACK_BUTTON_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.IMAGEBUTTO_NKEY
import com.alex.injector.objects.global.Companion.LOADING_VIEW_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.PROGRESS_BAR
import com.alex.injector.objects.global.Companion.Password
import com.alex.injector.objects.global.Companion.PtraceMode
import com.alex.injector.objects.global.Companion.SETUP_BUTTON_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.SETUP_FILES
import com.alex.injector.objects.global.Companion.SETUP_FILES_VISIBILITY_KEY
import com.alex.injector.objects.global.Companion.TAG
import com.alex.injector.objects.global.Companion.XPOSEMODE
import com.alex.injector.objects.global.Companion.changes
import com.alex.injector.objects.global.Companion.gamename
import com.alex.injector.objects.global.Companion.shell
import com.alex.injector.objects.global.Companion.updatesinfo
import com.alex.injector.settings.Settings
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.topjohnwu.superuser.Shell
import es.dmoral.toasty.Toasty
import org.json.JSONArray
import org.json.JSONException
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(){
    init {
        System.loadLibrary("alex2")
    }
    var logss: MutableList<String> = mutableListOf()
    lateinit var binding: ActivityMainBinding
    lateinit var modelroot : MainViewModel
    private lateinit var prefs : SharedPreferences
    external fun passwoeddd() : String?

        @SuppressLint("ObsoleteSdkInt", "SetTextI18n")
        override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)
            Shell.getShell().apply {
                shell = true
            }
        modelroot = ViewModelProvider(this).get(MainViewModel::class.java)
        @Suppress("DEPRECATION")
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        modelroot.Splash()
        Password = this.passwoeddd()
        // android 12 splash api
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                modelroot.check
            }
        }
          //  status bar fix
            if (!isDarkTheme(this))
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val window: Window = this.window
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = this.resources.getColor(R.color.statusbarcolour)
                }
            }
            val localpath = global.localpath()
            val libv1 = global.libv1()
            val libv2 = global.libv2()
            val libv3 = global.libv3()
            val libv4 = global.libv4()
            val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            val xposedmode = SharedPreferences.getBoolean(XPOSEMODE,false)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            setupCrashLogging()
            dafaultSetup()
        if (!modelroot.launchmode) {
            if (shell){
                Snackbar.make(binding.root , "Shell Initialized",Snackbar.LENGTH_SHORT).show()
            }
            clearCacheFiles(this)
            val handler = Handler()
            handler.postDelayed({
                updateapi(this)
                importent_news(this)
            },1000)
        }
            thread {
                if (!modelroot.deletefiles)
                {
                    val libspathh = arrayOf (
                        "rm $localpath$libv1",
                        "rm $localpath$libv2",
                        "rm $localpath$libv3",
                        "rm $localpath$libv4")
                    try {
                        for (command in libspathh) {
                            Shell.cmd(command).exec()
                        }
                        modelroot.deletefiles = true
                    }
                    catch (E : Exception)
                    {
                        E.printStackTrace()
                        Log.e(TAG,E.toString())
                    }

                }
            }
            thread {
                phreasedata()
                if (savedInstanceState != null) {
                    binding.imageFilterButton.visibility = savedInstanceState.getInt(IMAGEBUTTO_NKEY)
                    binding.card.visibility = savedInstanceState.getInt(CARD_VISIBILITY_KEY)
                    binding.global2.visibility = savedInstanceState.getInt(GLOBAL2_VISIBILITY_KEY)
                    binding.global8.visibility = savedInstanceState.getInt(GLOBAL8_VISIBILITY_KEY)
                    binding.global6.visibility = savedInstanceState.getInt(GLOBAL6_VISIBILITY_KEY)
                    binding.global5.visibility = savedInstanceState.getInt(GLOBAL5_VISIBILITY_KEY)
                    binding.global.visibility = savedInstanceState.getInt(GLOBAL_VISIBILITY_KEY)
                    binding.global7.visibility = savedInstanceState.getInt(GLOBAL7_VISIBILITY_KEY)
                    binding.materialCardView.visibility = savedInstanceState.getInt(HACK_BUTTON_VISIBILITY_KEY)
                    binding.materialCardView3.visibility = savedInstanceState.getInt(SETUP_BUTTON_VISIBILITY_KEY)
                    binding.progressBar.visibility = savedInstanceState.getInt(PROGRESS_BAR)
                    binding.setupfiles.visibility = savedInstanceState.getInt(SETUP_FILES_VISIBILITY_KEY)
                    binding.loadingview.visibility = savedInstanceState.getInt(LOADING_VIEW_VISIBILITY_KEY)
                    binding.global.isChecked = savedInstanceState.getBoolean(GLOBAL_CHECKED_STATE_KEY)
                    binding.global2.isChecked = savedInstanceState.getBoolean(GLOBAL2_CHECKED_STATE_KEY)
                    binding.global6.isChecked = savedInstanceState.getBoolean(GLOBAL6_CHECKED_STATE_KEY)
                    binding.global5.isChecked = savedInstanceState.getBoolean(GLOBAL5_CHECKED_STATE_KEY)
                    binding.global5.isChecked = savedInstanceState.getBoolean(GLOBAL3_CHECKED_STATE_KEY)
                }

            }
            modelroot.launchmode = true
            Changes("Whats new in this Update", updatesinfo)
            binding.imageFilterButton.setOnClickListener {
                val intent = Intent(this,Settings::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,R.anim.activity_out)
            }
            binding.global.setOnCheckedChangeListener { _  , isChecked ->
                if (isChecked) {
                    if (xposedmode)
                    {
                        modelroot.libpath = localpath+libv3
                        modelroot.libname = libv3
                    }else{
                        modelroot.libpath = localpath+libv1
                        modelroot.libname = libv1
                    }
                    modelroot.packagename = gamename[5]
                    modelroot.versionmanager = true
                    binding.global2.isChecked = false
                    binding.global8.isChecked = false
                    binding.global6.isChecked = false
                    binding.global5.isChecked = false
                } else {
                    modelroot.versionmanager = false
                }
            }

            binding.global2.setOnCheckedChangeListener { _  , isChecked ->
                if (isChecked) {
                    if (xposedmode)
                    {
                        modelroot.libpath = localpath+libv3
                        modelroot.libname = libv3
                    }else{
                        modelroot.libpath = localpath+libv1
                        modelroot.libname = libv1
                    }
                    modelroot.packagename = gamename[4]
                    modelroot.versionmanager = true
                    binding.global.isChecked = false
                    binding.global8.isChecked = false
                    binding.global6.isChecked = false
                    binding.global5.isChecked = false
                } else {
                    modelroot.versionmanager= false
                }
            }
            binding.global8.setOnCheckedChangeListener { _  , isChecked -> // bgmi
                if (isChecked) {
                    if (xposedmode)
                    {
                        modelroot.libpath = localpath+libv4
                        modelroot.libname = libv4
                    }else{
                        modelroot.libpath = localpath+libv2
                        modelroot.libname = libv2
                    }
                    modelroot.packagename = gamename[0]
                    modelroot.versionmanager= true
                    binding.global.isChecked = false
                    binding.global2.isChecked = false
                    binding.global6.isChecked = false
                    binding.global5.isChecked = false
                } else {
                    modelroot.versionmanager= false
                }

            }
            binding.global6.setOnCheckedChangeListener { _  , isChecked ->
                if (isChecked) {
                    if (xposedmode)
                    {
                        modelroot.libpath = localpath+libv3
                        modelroot.libname = libv3
                    }else{
                        modelroot.libpath = localpath+libv1
                        modelroot.libname = libv1
                    }
                    modelroot.packagename = gamename[1]
                    Log.i(TAG , gamename[1])
                    modelroot.versionmanager= true
                    binding.global.isChecked = false
                    binding.global2.isChecked = false
                    binding.global8.isChecked = false
                    binding.global5.isChecked = false
                } else {
                    modelroot.versionmanager= false
                }

            }
            binding.global5.setOnCheckedChangeListener { _  , isChecked ->
                if (isChecked) {
                    if (xposedmode)
                    {
                        modelroot.libpath = localpath+libv3
                        modelroot.libname = libv3
                    }else{
                        modelroot.libpath = localpath+libv1
                        modelroot.libname = libv1
                    }
                    modelroot.packagename = gamename[3]
                    modelroot.versionmanager= true
                    binding.global.isChecked = false
                    binding.global2.isChecked = false
                    binding.global8.isChecked = false
                    binding.global6.isChecked = false
                } else {
                    modelroot.versionmanager= false
                }

            }
        }

    fun telegram ( view: View )
    {
        openurl("https://t.me/ALEX5402")

    }
     fun Changes (title:String, body: JSONArray)
     {
         val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
         val editor = SharedPreferences.edit()
         val updatechanges = MaterialAlertDialogBuilder(this)
         if (!prefs.getBoolean(changes , false))
         {
             val stringBuilder = StringBuilder()
             for (i in 0 until body.length()) {
                 val value = body.getString(i)
                 stringBuilder.append(value)
                 if (i < body.length() - 1) {
                     stringBuilder.append("\n")
                 }
             }
             val changes = stringBuilder.toString()
             updatechanges.setTitle(title)
             updatechanges.setMessage(changes)
             updatechanges.setPositiveButton("OK") { _ , _ ->
                 prefs.edit().putBoolean(global.changes,true).apply()
                 editor.putBoolean(PtraceMode,true).apply()
             }
             updatechanges.setCancelable(false)
             updatechanges.show()
         }
     }

     @SuppressLint("RestrictedApi")
     private fun dafaultSetup() {
         val utils = com.alex.injector.utils.Utils()
         if (!modelroot.files){
            if (utils.isRootGiven)
            {
                defaultview()
            }else {
                closesystem(R.string.devise_not_rooted.toString())
            }
         }
         else
         {
             checkfiles()
         }
     }
    fun closesystem ( message: String  )
    {
        notrooted()
        Toasty.error(this,message,Toasty.LENGTH_LONG).show()
        Toasty.error(this,message,Toasty.LENGTH_LONG).show()
        Thread.sleep(2000)
        System.exit(1)
    }

    // get internet but offline
    private fun isInternetConnected(): Boolean
    {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


  // this is the first default view to show
    fun checkfiles ( ) {
        if (modelroot.files){
            binding.loadingview.visibility = View.GONE
            binding.animation.stopAnim()
            binding.materialCardView3.visibility = View.GONE
            binding.card.visibility = View.VISIBLE
            binding.global.visibility = View.VISIBLE
            binding.global2.visibility = View.VISIBLE
            binding.global8.visibility = View.VISIBLE
            binding.global6.visibility = View.VISIBLE
            binding.global5.visibility = View.VISIBLE
            binding.global7.visibility = View.VISIBLE
            binding.materialCardView.visibility = View.VISIBLE
        }
    }
    fun isDarkTheme(context: Context): Boolean {
        val currentNightMode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == Configuration.UI_MODE_NIGHT_YES
    }
    fun toast ( meassage : String , duration: Int)
    {
        val toast = Toast.makeText(this, meassage, Toast.LENGTH_SHORT)
        toast.duration = duration
        toast.show()
    }
    /// set the default view for container
    fun defaultview ( )
    {

        val localpath = global.localpath()
        val libv1 = global.libv1()
        val libv2 = global.libv2()
        val libv3 = global.libv3()
        // clear
        if (!modelroot.deletefiles)
        {
            val libspathh = arrayOf ( "rm $localpath$libv1" , "rm $localpath$libv2","rm $localpath$libv3")
            try {
                for (command in libspathh) {
                    Shell.cmd(command).exec()
                }
            }catch (e: Exception)
            {
                e.printStackTrace()
            }
        }
      binding.card.visibility = View.GONE
        binding.global2.visibility = View.GONE
        binding.global8.visibility = View.GONE
        binding.global6.visibility = View.GONE
        binding.global5.visibility = View.GONE
        binding.global.visibility = View.GONE
        binding.global7.visibility = View.GONE
        binding.materialCardView.visibility = View.GONE  // hack button
        binding.materialCardView3.visibility = View.VISIBLE  // setup button
    }

fun notrooted ( )
{
    binding.card.visibility = View.GONE
    binding.global2.visibility = View.GONE
    binding.global8.visibility = View.GONE
    binding.global6.visibility = View.GONE
    binding.global5.visibility = View.GONE
    binding.global.visibility = View.GONE
    binding.global7.visibility = View.GONE
    binding.materialCardView.visibility = View.GONE  // hack button
    binding.materialCardView3.visibility = View.VISIBLE  // setup button
    binding.materialCardView3.isEnabled = false  // setup button
}
    fun setupfies(view: View) {
        val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val xposedmode = SharedPreferences.getBoolean(XPOSEMODE,false)
        val ptracemode = SharedPreferences.getBoolean(PtraceMode,false)
        val startdownload = PointerAngel(this)
        val versionmanager = modelroot.versioncheck
        val checint = isInternetConnected()
        val database = modelroot.phreasedata
        val notice = global.serverstatus
        if(checint)
        {
            if (versionmanager)
            {
                if (!database) {
                    phreasedata()
                }
                if (notice.equals("close"))
                   return importentNotice(this)

                if (!(xposedmode or ptracemode))
                    return Toasty.error(this,R.string.Perfectsettings,Toasty.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.VISIBLE
                    binding.setupfiles.visibility = View.GONE
                    binding.materialCardView3.isEnabled = false
                    modelroot.files = true
                    modelroot.savestate = true
                    startdownload.execute(modelroot.liburl)

            } else
            {
                Toast.makeText(this,R.string.please_update,Toast.LENGTH_LONG).show()
            }

        }
        else
        {
            Snackbar.make(view,R.string.Device_disconnected, Snackbar.LENGTH_LONG)
                .show()
        }

    }

    fun alldone( zippath : String  ) {
        val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val xposedmode = SharedPreferences.getBoolean(XPOSEMODE,false)
        val ptracemode = SharedPreferences.getBoolean(PtraceMode,false)

        if(xposedmode)
        {
            binding.gamelocal.setText(R.string.smallbit)
            binding.global7.setText(R.string.smallbits)
            binding.global7.isChecked = xposedmode
        }
        if (ptracemode)
        {
            binding.gamelocal.setText(R.string.bigbit)
            binding.global7.setText(R.string.bigbits)
            binding.global7.isChecked = ptracemode

        }
        modelroot.finalzippath = zippath
        binding.loadingview.visibility = View.GONE
        binding.animation.stopAnim()
        binding.materialCardView3.visibility = View.GONE
        binding.global.visibility = View.VISIBLE
        binding.card.visibility = View.VISIBLE
        binding.global2.visibility = View.VISIBLE
        binding.global8.visibility = View.VISIBLE
        binding.global6.visibility = View.VISIBLE
        binding.global5.visibility = View.VISIBLE
        binding.global7.visibility = View.VISIBLE
        binding.materialCardView.visibility = View.VISIBLE  // hack button
    }

    fun somethingwrong ( )
    {
        val localpath = global.localpath()
        val libv1 = global.libv1()
        val libv2 = global.libv2()
        val libv3 = global.libv3()
        // clear
        if (!modelroot.deletefiles)
        {
            val libspathh = arrayOf ( "rm $localpath$libv1" , "rm $localpath$libv2","rm $localpath$libv3")
            try {
                for (command in libspathh) {
                    Shell.cmd(command).exec()
                }
            }catch (e: Exception)
            {
                e.printStackTrace()
            }
        }

        binding.card.visibility = View.GONE
        binding.global2.visibility = View.GONE
        binding.global8.visibility = View.GONE
        binding.global6.visibility = View.GONE
        binding.global5.visibility = View.GONE
        binding.global.visibility = View.GONE
        binding.global7.visibility = View.GONE
        binding.materialCardView.visibility = View.GONE  // hack button
        binding.materialCardView3.visibility = View.VISIBLE
        binding.materialCardView3.isEnabled = true // setup button
        binding.progressBar.visibility = View.GONE
        binding.setupfiles.visibility = View.VISIBLE
        Toasty.error(this , R.string.somethingWrong,Toasty.LENGTH_SHORT).show()
        defaultview()
    }

     fun alldone2( ) {
         binding.loadingview.visibility = View.GONE
         binding.animation.stopAnim()
         binding.materialCardView.isEnabled = true
         binding.global. isEnabled = true
         binding.global2.isEnabled = true
         binding.global8.isEnabled = true
         binding.global6.isEnabled = true
         binding.global5.isEnabled = true
         binding.floatingActionButton.isEnabled = true
         binding.materialCardView.visibility = View.VISIBLE  // hack button
     }

    fun hackbutton(view: View)
    {
        val versioncheck = modelroot.versionmanager
        val SharedPreferences = this.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val xposedmode = SharedPreferences.getBoolean(XPOSEMODE,false)
        val ptracemode = SharedPreferences.getBoolean(PtraceMode,false)
        val zippath = modelroot.finalzippath
       if(!versioncheck )
          return Toasty.error(this, R.string.gameselection , Toasty.LENGTH_LONG ) .show()
        binding.loadingview.visibility = View.VISIBLE
        binding.animation.startAnim()
        binding.materialCardView.isEnabled = false
        binding.global. isEnabled = false
        binding.global2.isEnabled = false
        binding.global8.isEnabled = false
        binding.global6.isEnabled = false
        binding.global5.isEnabled = false
        binding.floatingActionButton.isEnabled = false



        if (xposedmode)
        {
            try {
                if (isfileExist(zippath))
                {
                    val extract = Extract(zippath , this,true,modelroot.packagename,true,modelroot.libname)
                    extract.execute("i love you")
                }else {
                    val extract2 = Extract(zippath , this,true,modelroot.packagename,false,modelroot.libname)
                    extract2.execute("i love you")
                }


            }catch (e:Exception)
            {
                e.printStackTrace()
            }
        }else
        {
            Toast.makeText(this,R.string.isptracemode,Toast.LENGTH_LONG).show()

        }
        Log.i(TAG ,modelroot.packagename)

        if (ptracemode){
            launchPackage(this,modelroot.packagename)

            if (isfileExist(zippath))
            {
                val extract = Extract(zippath , this,false,modelroot.packagename,true,modelroot.libname)
                extract.execute("i love you")
            } else {
                val extract = Extract(zippath , this,false,modelroot.packagename,false,modelroot.libname)
                extract.execute("i love you")

            }
        }
        else
        {
            Toast.makeText(this,R.string.Xposedmode,Toast.LENGTH_LONG).show()
        }

    }
    fun xposedinject ( )
    {
        val handler = Handler()
        handler.postDelayed({
            launchPackage(this,modelroot.packagename)
            alldone2()
        },2000)

    }

     fun injection() {
         val pkg = modelroot.packagename
         val libpath = modelroot.libpath
         val handler = Handler()
        handler.postDelayed(
             {
                 val injector = handlerclass(this,pkg,libpath)
                 injector.Connect()
             },1500)
         Toast.makeText(this , R.string.injecting ,Toast.LENGTH_LONG).show()
         handler.postDelayed({
             alldone2()
         },2000)
     }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setTitle("Alert")
        dialog.setCancelable(false)
        dialog.setMessage(R.string.wannacloseme)
        dialog.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed()
        }
        dialog.setNegativeButton("No") {_,_ ->
        }
           dialog.show()
    }
    fun importentNotice ( context: Context)
    {
        val requestQueue : RequestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET , global.jsondata() , null, {responce ->
            try {
                val server = realtimedatabase(
                    responce.getString("opentime"),
                    responce.getString("servermeassage"),
                    responce.getString("notice_title"),
                    responce.getString("notice_body"),
                    responce.getString("noticemode")
                )
                shownotice(server)
            }catch (E: Exception)
            {
                E.printStackTrace()
            }
        }, {
            toast("something is wrong", 1000)
            }


        )
        requestQueue.add(jsonObjectRequest)
    }
    fun importent_news ( context: Context)
    {
        val requestQueue : RequestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET , global.jsondata() , null, {responce ->
            try {
                val server = realtimedatabase(
                    responce.getString("opentime"),
                    responce.getString("servermeassage"),
                    responce.getString("notice_title"),
                    responce.getString("notice_body"),
                    responce.getString("noticemode")
                )
                news(server)
            }catch (E: Exception)
            {
                E.printStackTrace()
            }
        }, {
            toast("something is wrong", 1000)
        }


        )
        requestQueue.add(jsonObjectRequest)
    }

    fun news ( realtimedatabase: realtimedatabase)
    {
        val newsbool = realtimedatabase.noticemode.toBoolean()
        val alertDialog = MaterialAlertDialogBuilder(this)
        .setTitle(realtimedatabase.notice_title)
        .setMessage(realtimedatabase.notice_body)
        .setPositiveButton("ok") {_,haha -> }
        if (newsbool)
        {
            alertDialog.show()
        }
    }


    fun shownotice ( realtimedatabase: realtimedatabase)
    {
        val aleartdialog = MaterialAlertDialogBuilder(this)
        aleartdialog.setTitle(realtimedatabase.servermeassage)
        aleartdialog.setMessage(realtimedatabase.opentime)
        aleartdialog.setPositiveButton("ok") { _,_->

        }
        aleartdialog.show()

    }


// launch package
    fun launchPackage(context: Context, packageName: String) {
        val packageManager = context.packageManager

        // Check if the package is installed
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            // Package exists, so launch it
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            // Package not found
            Toast.makeText(context, "Package $packageName not found in your device please install that", Toast.LENGTH_SHORT).show()
        }
    }
    
    fun setupCrashLogging() {
        val defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, exception ->
            try {
                val logs = collectLogs(exception)
                saveLogsToFile(logs)
                val crashlol = Intent(this, CrashLoL::class.java).apply {
                    putExtra("logs",logss.toString())
                }
                startActivity(crashlol)
            } catch (e: Exception) {
                Log.e("CrashLogging", "Failed to collect logs", e)
            } finally {
                // Call the default uncaught exception handler to handle the crash
                defaultExceptionHandler?.uncaughtException(thread, exception)
            }
        }
    }

    fun collectLogs(exception: Throwable): List<String> {
        logss = mutableListOf<String>()
        // Add the stack trace of the exception to the logs
        val writer = StringWriter()
        exception.printStackTrace(PrintWriter(writer))
        logss.add(writer.toString())

        // Add the device and app information to the logs
        logss.add("Device manufacturer: ${Build.MANUFACTURER}")
        logss.add("Device model: ${Build.MODEL}")
        logss.add("Android version: ${Build.VERSION.RELEASE}")
        logss.add("App version: ${BuildConfig.VERSION_NAME}")

        // Add any additional custom logs here
        return logss
    }
    fun logname(): String {
        val vowels = listOf("a", "e", "i", "o", "u")
        val consonants = listOf("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z")
        var name = ""
        val nameLength = (4..8).random()
        var useVowel = true
        for (i in 1..nameLength) {
            if (useVowel) {
                name += vowels.random()
                useVowel = false
            } else {
                name += consonants.random()
                useVowel = true
            }
        }
        return name.replaceFirstChar {
            if (it.isLowerCase())
                it.titlecase(Locale.getDefault())
            else it.toString()
        }
    }

    fun saveLogsToFile(logs: List<String>) {
        val downloadDir = this.filesDir
        val fileName = logname() + "Injectorlogs.txt"
        val file = File(downloadDir, fileName)
        val sdf = SimpleDateFormat(" yyyy-MM-dd HH:mm:ss" , Locale.getDefault())
        val timestamp = sdf.format(Date())
        FileWriter(file).use { writer ->
            writer.append("Crash logs ($timestamp):\n\n")
            logs.forEach { log ->
                writer.append(log)
                writer.append("\n\n")
            }
        }
    }

    fun OnSupport(view: View) {
        openurl("https://github.com/ALEX5402")
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

     override fun onSaveInstanceState(outState: Bundle) {
         super.onSaveInstanceState(outState)
         // Save the visibility state of the views
         outState.putInt(IMAGEBUTTO_NKEY,binding.imageFilterButton.visibility)
         outState.putInt(CARD_VISIBILITY_KEY, binding.card.visibility)
         outState.putInt(GLOBAL2_VISIBILITY_KEY, binding.global2.visibility)
         outState.putInt(GLOBAL8_VISIBILITY_KEY, binding.global8.visibility)
         outState.putInt(GLOBAL6_VISIBILITY_KEY, binding.global6.visibility)
         outState.putInt(GLOBAL5_VISIBILITY_KEY, binding.global5.visibility)
         outState.putInt(GLOBAL_VISIBILITY_KEY, binding.global.visibility)
         outState.putInt(GLOBAL7_VISIBILITY_KEY, binding.global7.visibility)
         outState.putInt(HACK_BUTTON_VISIBILITY_KEY, binding.materialCardView.visibility)
         outState.putInt(SETUP_BUTTON_VISIBILITY_KEY, binding.materialCardView3.visibility)
         outState.putInt(PROGRESS_BAR,binding.progressBar.visibility )
         outState.putInt(SETUP_FILES,binding.setupfiles.visibility )
         outState.putInt(SETUP_FILES_VISIBILITY_KEY, binding.setupfiles.visibility)
         outState.putInt(LOADING_VIEW_VISIBILITY_KEY, binding.loadingview.visibility ?: View.GONE)
         outState.putBoolean(ANIMATION_STATE_KEY, binding.animation.isActivated ?: false)
         outState.putBoolean(GLOBAL_CHECKED_STATE_KEY, binding.global.isChecked)
         outState.putBoolean(GLOBAL2_CHECKED_STATE_KEY, binding.global2.isChecked)
         outState.putBoolean(GLOBAL6_CHECKED_STATE_KEY, binding.global6.isChecked)
         outState.putBoolean(GLOBAL5_CHECKED_STATE_KEY, binding.global5.isChecked)
         outState.putBoolean(GLOBAL3_CHECKED_STATE_KEY, binding.global5.isChecked)

     }
     override fun onRestoreInstanceState(savedInstanceState: Bundle) {
         super.onRestoreInstanceState(savedInstanceState)

         // Restore the visibility state of the views
         binding.imageFilterButton.visibility = savedInstanceState.getInt(IMAGEBUTTO_NKEY)
         binding.card.visibility = savedInstanceState.getInt(CARD_VISIBILITY_KEY)
         binding.global2.visibility = savedInstanceState.getInt(GLOBAL2_VISIBILITY_KEY)
         binding.global8.visibility = savedInstanceState.getInt(GLOBAL8_VISIBILITY_KEY)
         binding.global6.visibility = savedInstanceState.getInt(GLOBAL6_VISIBILITY_KEY)
         binding.global5.visibility = savedInstanceState.getInt(GLOBAL5_VISIBILITY_KEY)
         binding.global.visibility = savedInstanceState.getInt(GLOBAL_VISIBILITY_KEY)
         binding.global7.visibility = savedInstanceState.getInt(GLOBAL7_VISIBILITY_KEY)
         binding.materialCardView.visibility = savedInstanceState.getInt(HACK_BUTTON_VISIBILITY_KEY)
         binding.materialCardView3.visibility = savedInstanceState.getInt(SETUP_BUTTON_VISIBILITY_KEY)
         binding.setupfiles.visibility = savedInstanceState.getInt(SETUP_FILES)
         binding.progressBar.visibility = savedInstanceState.getInt(PROGRESS_BAR)
         binding.setupfiles.visibility = savedInstanceState.getInt(SETUP_FILES_VISIBILITY_KEY)
         binding.loadingview.visibility = savedInstanceState.getInt(LOADING_VIEW_VISIBILITY_KEY)
         binding.global.isChecked = savedInstanceState.getBoolean(GLOBAL_CHECKED_STATE_KEY)
         binding.global2.isChecked = savedInstanceState.getBoolean(GLOBAL2_CHECKED_STATE_KEY)
         binding.global6.isChecked = savedInstanceState.getBoolean(GLOBAL6_CHECKED_STATE_KEY)
         binding.global5.isChecked = savedInstanceState.getBoolean(GLOBAL5_CHECKED_STATE_KEY)
         binding.global5.isChecked = savedInstanceState.getBoolean(GLOBAL3_CHECKED_STATE_KEY)
         if (savedInstanceState.getBoolean(ANIMATION_STATE_KEY)) {
             binding.animation.startAnim()
         } else {
             binding.animation.stopAnim()
         }

     }
     fun isNewVersionAvailable(currentVersion: String, newVersion: String): Boolean {
        val current = currentVersion.split(".")
        val newVer = newVersion.split(".")

        for (i in current.indices) {
            val curr = current[i].toInt()
            val ver = newVer[i].toInt()
            if (ver > curr) {
                return true
            } else if (ver < curr) {
                return false
            }
        }
        return false
    }

    override fun onPause() {
        super.onPause()
        updateapi(this)
    }
    @SuppressLint("SuspiciousIndentation")
    private fun updateapi (context: Context)
    {
        val requestQueue : RequestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET , global.jsondata() , null,
            {responce ->
                try {
                  val database = UpdateInfo(
                      responce.getString("version"),
                      responce.getString("releaseDate"),
                      responce.getString("downloadUrl"),
                      responce.getJSONArray("changes"),
                      responce.getString("serverstatus")
                          )
                    global.serverstatus = database.serverstatus

                    val currentversion = context.packageManager.getPackageInfo(context.packageName,0).versionName
                    if(isNewVersionAvailable(currentversion,database.version))
                    {
                        modelroot.versioncheck = false
                        updatedialog(database)
                    }

                }
                catch ( e : JSONException)
                {
                    e.printStackTrace()
                }
            },
            {
                toast(R.string.somethingWrong.toString(), 2000)
            }
        )
       requestQueue.add(jsonObjectRequest)
    }

    fun updatedialog (updateInfo: UpdateInfo)
    {
            val stringBuilder = StringBuilder()
            for (i in 0 until updateInfo.changes.length()) {
                val value = updateInfo.changes.getString(i)
                stringBuilder.append(value)
                if (i < updateInfo.changes.length() - 1) {
                    stringBuilder.append("\n")
                }
            }
            val changes = stringBuilder.toString()

        val dialog = MaterialAlertDialogBuilder(this)
        dialog.setTitle("New update available")
        dialog.setMessage("Release Date: ${updateInfo.releaseDate} \n\n$changes")
        dialog.setPositiveButton("Download") { _, _->
            openurl(updateInfo.downloadUrl)
            dialog.setOnDismissListener {
                toast("Please click ok and download it manually from browser",2000)
            }
        }
        dialog.setNegativeButton("Later") { _,_ ->
            val view = binding.root
            Snackbar.make(view,R.string.please_update,Snackbar.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        phreasedata()
        updateapi(this )
    }
    fun phreasedata(){
        val requestQueue: RequestQueue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, global.jsondata(), null,
            { response ->
                try {
                  val updateInfo = mainlink (response.getString("libs"))
                    modelroot.liburl = updateInfo.libs
                    modelroot.phreasedata = true
                } catch (e: JSONException) {
                    modelroot.phreasedata = false
                    e.printStackTrace()
                }
            },
           {
                Toast.makeText(this, R.string.servererr, Toast.LENGTH_SHORT).show()
               modelroot.phreasedata = false
           }
        )
        requestQueue.add(jsonObjectRequest)

    }
    fun clearCacheFiles(context: Context) {
        val cacheDir = context.cacheDir // Get the cache directory
        if (cacheDir.exists()) {
            val files = cacheDir.listFiles() // Get all files in the cache directory
            if (files != null) {
                for (file in files) {
                    file.delete() // Delete each file
                }
                Log.w("Cache" , "All files are cleared")
            }
        }
    }
    fun isfileExist ( path : String) : Boolean {
        val file = File(path)
        return file.exists()
    }
     override fun onDestroy() {
        super.onDestroy()
         val injector = handlerclass(this ,modelroot.packagename,modelroot.libpath)
          injector.disconnect()
    }

}
