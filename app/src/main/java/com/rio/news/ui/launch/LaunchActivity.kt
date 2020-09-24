package com.rio.news.ui.launch

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.Window
import android.view.WindowManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.rio.news.R
import com.rio.news.ui.MainActivity
import com.rio.news.utils.toast

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    override fun onResume() {
        permission()
        super.onResume()
    }

    private fun permission() {
        Dexter.withActivity(this)
            .withPermissions(Manifest.permission.INTERNET).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (!report.areAllPermissionsGranted()) {
                        toast("This Application Need Permission")
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName"))
                        intent.addCategory(Intent.CATEGORY_DEFAULT)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    } else {
                        validation()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    private fun validation() {
        val handler = Handler()
        handler.postDelayed({
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}