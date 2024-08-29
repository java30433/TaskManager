package wat.app.taskmanager.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import wat.app.taskmanager.BuildConfig
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Update {
    fun check(activity: Activity) {
        Thread {
            try {
                val connection = URL("https://pastebin.com/raw/FjTceQ7F")
                    .openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                    val reader =
                        BufferedReader(InputStreamReader(connection.inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while ((reader.readLine().also { line = it }) != null) {
                        response.append(line)
                    }
                    reader.close()
                    val info = response.toString().split("@")
                    val newVersion = info[0].toInt()
                    if (newVersion > BuildConfig.VERSION_CODE) {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                activity,
                                info[1].replace("\\n", "\n"),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                connection.disconnect()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}