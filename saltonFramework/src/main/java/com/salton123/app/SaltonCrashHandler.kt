//package com.salton123.app
//
//import android.app.Application
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import android.os.Environment
//import com.salton123.app.crash.CrashPanelAty.FLAG_INFO
//import com.salton123.app.crash.CrashService
//import com.salton123.io.FlushWriter
//import com.salton123.log.XLog
//import java.io.File
//import java.io.PrintWriter
//import java.io.StringWriter
//import java.text.DateFormat
//import java.text.SimpleDateFormat
//import java.util.*
//
//
///**
// * User: newSalton@outlook.com
// * Date: 2018/9/23 下午10:16
// * ModifyTime: 下午10:16
// * Description:
// */
//object SaltonCrashHandler : Thread.UncaughtExceptionHandler {
//
//    /**
//     * 系统默认的异常处理
//     */
//    private var mDefaultHandler: Thread.UncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
//
//    init {
//        // 设置该 CrashHandler 为程序的默认处理器
//        Thread.setDefaultUncaughtExceptionHandler(this)
//    }
//
//    var isShowCrashPanel: Boolean = false
//
//    override fun uncaughtException(t: Thread?, e: Throwable?) {
//        if (!handleException(e)) {
//            mDefaultHandler.uncaughtException(t, e)
//        }
//    }
//
//    private fun handleException(ex: Throwable?): Boolean {
//        getWriter(ex!!)
//        return false
//    }
//
//    private fun getWriter(ex: Throwable) {
//        var path = File(Environment.getExternalStorageDirectory(), "crash").path
//        path = path + File.separator + BaseApplication.getInstance<Application>().packageName
//        val crashPath = path + File.separator + createFile()
//        var flush = FlushWriter(path + File.separator + "crash_buf",
//            8192,
//            crashPath
//            , false
//        )
//        var stringBuilder = StringBuilder()
//        flush.changeLogPath(crashPath)
//        stringBuilder.append(collectDeviceInfo(BaseApplication.getInstance()) + "\n")
//            .append(printCause(ex))
//            .append(printStackTrace(ex))
//            .append(ex.message + "\n")
//        flush.write(stringBuilder.toString())
//        flush.flushAsync()
//        flush.release()
//        if (isShowCrashPanel) {
//            var intent = Intent(BaseApplication.getInstance(), CrashService::class.java)
//            intent.putExtra(FLAG_INFO, stringBuilder.toString())
//            BaseApplication.getInstance<Application>().startService(intent)
//        }
//    }
//
//    private fun printCause(ex: Throwable): String? {
//        var stringBuilder = StringBuilder()
//        val sw = StringWriter()
//        val pw = PrintWriter(sw)
//        var cause = ex.cause
//        while (cause != null) {
//            //异常链
//            cause.printStackTrace(pw)
//            pw.flush()
//            stringBuilder.append(sw.toString()).append("\n")
//            cause = cause.cause
//        }
//        return stringBuilder.toString()
//    }
//
//    internal fun printStackTrace(ex: Throwable): String {
//        var stringBuilder = StringBuilder()
//        val sw = StringWriter()
//        val pw = PrintWriter(sw)
//        ex.printStackTrace(pw)
//        pw.flush()
//        stringBuilder.append(sw.toString()).append("\n")
//        return stringBuilder.toString()
//    }
//
//    private fun createFile(): String {
//        var formatter: DateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA)
//        val time = formatter.format(Date())
//        return "crash_$time.txt"
//    }
//
//    /**
//     * 收集设备参数信息
//     *
//     * @param ctx
//     */
//    fun collectDeviceInfo(ctx: Context): String {
//        val sb = StringBuffer()
//        try {
//            val pm = ctx.packageManager
//            val pi = pm.getPackageInfo(ctx.packageName,
//                PackageManager.GET_ACTIVITIES)
//
//            if (pi != null) {
//                val versionName = if (pi.versionName == null)
//                    "null"
//                else
//                    pi.versionName
//                val versionCode = pi.versionCode.toString() + ""
//                sb.append("versionName:$versionName\n")
//                sb.append("versionCode:$versionCode\n")
//            }
//        } catch (e: PackageManager.NameNotFoundException) {
//            XLog.e(this@SaltonCrashHandler, "an error occured when collect package info$e")
//        }
//
//        val fields = Build::class.java.declaredFields
//        for (field in fields) {
//            try {
//                field.isAccessible = true
//                sb.append("${field.name}:${field.get(null)}\n")
//            } catch (e: Exception) {
//            }
//        }
//        return sb.toString()
//    }
//}