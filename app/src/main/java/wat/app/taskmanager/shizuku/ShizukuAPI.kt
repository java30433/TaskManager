package wat.app.taskmanager.shizuku

import android.app.IActivityManager
import android.content.Context
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.SystemServiceHelper

object ShizukuAPI {
    val ACTIVITY_MANAGER: IActivityManager by lazy {
        IActivityManager.Stub.asInterface(ShizukuBinderWrapper(
            SystemServiceHelper.getSystemService(Context.ACTIVITY_SERVICE)
        ))
    }
}