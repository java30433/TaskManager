package wat.app.taskmanager.utils

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.text.TextUtils
import wat.app.taskmanager.shizuku.ShizukuAPI


object PackageUtils {
    fun Context.killApp(packageName: String) {
        ShizukuAPI.ACTIVITY_MANAGER.forceStopPackage(packageName, 0)
    }

    fun Context.launchApp(packageName: String) {
        getAppOpenIntentByPackageName(this, packageName)?.let {
            startActivity(it)
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    fun getAppOpenIntentByPackageName(context: Context, packageName: String): Intent? {
        var mainAct: String? = null
        val pkgMag = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED or Intent.FLAG_ACTIVITY_NEW_TASK)
        val list = pkgMag.queryIntentActivities(intent, PackageManager.GET_ACTIVITIES)
        for (i in list.indices) {
            val info = list[i]
            if (info.activityInfo.packageName == packageName) {
                mainAct = info.activityInfo.name
                break
            }
        }
        if (TextUtils.isEmpty(mainAct)) {
            return null
        }
        intent.setComponent(ComponentName(packageName, mainAct!!))
        return intent
    }
}