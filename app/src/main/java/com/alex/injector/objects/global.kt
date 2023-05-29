package com.alex.injector.objects

import org.json.JSONArray

class global {

     companion object {
//        startup updatelog prefs key
         var changes : String = "changes8"
         val arrayString1 = "[ \"Some Internal Code Improved\",\n" +
//                 "             \"New Stable Release\",\n" +
//                 "             \"Improved Status bar Colour\",\n" +
//                 "             \"Fixed 32 bit \",\n" +
//                 "             \"Supports Lesposed\",\n" +
//                 "             \"Supports Xposed Old Sdk\",\n" +
//                 "             \"Support Emulators With Xposed Installed Only 32 bit\",\n" +
//                 "             \"New Setting Tab\",\n" +
                 "             \"fixed the Lib Copy Ishu\"]"


         val updatesinfo = JSONArray(arrayString1)

         var shell : Boolean = false
         var TAG : String = "ALEX"
         const val IMAGEBUTTO_NKEY = "imagebutton"
         const val XPOSEMODE = "XPOSEMODE"
         const val PtraceMode = "PtraceMode"
         const val X86MODE = "X86MODE"


         const val GLOBAL_CHECKED_STATE_KEY = "globalCheckedState"
         const val GLOBAL2_CHECKED_STATE_KEY = "global2CheckedState"
         const val GLOBAL6_CHECKED_STATE_KEY = "global6CheckedState"
         const val GLOBAL5_CHECKED_STATE_KEY = "global5CheckedState"
         const val GLOBAL3_CHECKED_STATE_KEY = "globbalcheckstate3"
         const val SETUP_FILES_VISIBILITY_KEY = "setupFilesVisibility"
         const val LOADING_VIEW_VISIBILITY_KEY = "loadingViewVisibility"
         const val ANIMATION_STATE_KEY = "animationState"
         const val CARD_VISIBILITY_KEY = "cardVisibility"
         const val GLOBAL7_VISIBILITY_KEY = "global7Visibility"
         const val GLOBAL2_VISIBILITY_KEY = "global2Visibility"
         const val GLOBAL8_VISIBILITY_KEY = "global8Visibility"
         const val GLOBAL6_VISIBILITY_KEY = "global6Visibility"
         const val GLOBAL5_VISIBILITY_KEY = "global5Visibility"
         const val GLOBAL_VISIBILITY_KEY = "globalVisibility"
         const val HACK_BUTTON_VISIBILITY_KEY = "hackButtonVisibility"
         const val SETUP_BUTTON_VISIBILITY_KEY = "setupButtonVisibility"
         const val PROGRESS_BAR = "VrogressbarVisibility"
         const val SETUP_FILES = "SetupFiles"
         var Password : String? = null
         var c : CharArray? = Password?.toCharArray()
         external fun  localpath() : String
         external fun libv1() : String
         external fun libv2() : String
         external fun libv3() : String
         external fun libv4() : String
         external fun jsondata() : String
         var serverstatus : String = ""

         val gamename = arrayOf(
              "com.pubg.imobile", //0
             "com.pubg.krmobile", //1
             "aw2g4esh4gse5h4r863s4h5se4h", // 2
             "com.pubg.vngmobile",// 3
             "com.tencent.ig"  , // 4
         "com.tencent.tmgp.pubgmhd")  // 5



     }


 }