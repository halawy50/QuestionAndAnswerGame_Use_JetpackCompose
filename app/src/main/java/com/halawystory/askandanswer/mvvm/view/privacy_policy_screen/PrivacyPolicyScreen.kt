package com.halawystory.askandanswer.mvvm.view.privacy_policy_screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.halawystory.askandanswer.MainActivity
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.mvvm.view.component.PrivacyPolicyDialog
import java.util.Locale

class PrivacyPolicyScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale("en") // تعيين اللغة الإنجليزية

        val sharedPreferences = this@PrivacyPolicyScreen.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // تهيئة SharedPreferences باستخدام السياق الصحيح

        // جعل شريط الحالة شفاف
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isStatusBarContrastEnforced = false
            window.isNavigationBarContrastEnforced = false
        }

        // تحسين مظهر شريط الحالة
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false // استخدم true للخلفيات الفاتحة

        val windowInsetsController = ViewCompat.getWindowInsetsController(window.decorView)
        windowInsetsController?.hide(WindowInsetsCompat.Type.navigationBars())
        windowInsetsController?.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "Background Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                PrivacyPolicyDialog( this@PrivacyPolicyScreen,
                    onAccept = { isAccepted ->
                        if (isAccepted) {
                            // حفظ السياسة في التفضيلات
                            sharedPreferences.edit().putBoolean("privacyPolicy", true).apply()

                            // التحقق من قبول السياسة
                            val isPolicyAccepted = sharedPreferences.getBoolean("privacyPolicy", false)
                            if (isPolicyAccepted) {
                                // الانتقال إلى MainActivity بعد قبول السياسة
                                startActivity(Intent(this@PrivacyPolicyScreen, MainActivity::class.java))
                                finish() // إنهاء PrivacyPolicyScreen
                            }
                        }
                    }
                )
            }
        }
    }

    private fun setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        // التحديث باستخدام Context.createConfigurationContext (أفضل طريقة لتعيين اللغة)
        val context = createConfigurationContext(config)
        resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = this@PrivacyPolicyScreen.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // التحقق من قبول السياسة
        val isPolicyAccepted = sharedPreferences.getBoolean("privacyPolicy", false)
        if (isPolicyAccepted) {
            // الانتقال إلى MainActivity بعد قبول السياسة
            startActivity(Intent(this@PrivacyPolicyScreen, MainActivity::class.java))
            finish() // إنهاء PrivacyPolicyScreen
        }
    }
}
