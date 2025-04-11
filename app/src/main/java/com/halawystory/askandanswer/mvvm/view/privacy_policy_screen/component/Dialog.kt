package com.halawystory.askandanswer.mvvm.view.component

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.halawystory.askandanswer.R
import com.halawystory.askandanswer.ui.theme.ibmBold
import com.halawystory.askandanswer.ui.theme.orange

import kotlinx.coroutines.*

@Composable
fun PrivacyPolicyDialog(context: Context , onAccept: (Boolean) -> Unit) {
    var isChecked by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() } // لإزالة التأثير

    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = { /* يمنع المستخدم من إغلاق الـ Dialog بدون الموافقة */ },
        title = {
            Text(
                style = TextStyle(
                    fontFamily = ibmBold
                ),
                text = "السياسة والخصوصية",
                fontSize = 20.sp,
                color = orange,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // نص السياسة والخصوصية داخل Scroll
                Column(
                    modifier = Modifier
                        .height(250.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    Text(
                        style = TextStyle(
                            fontFamily = ibmBold
                        ),
                         text = """
                         سياسة الخصوصية – لعبة "سؤال وجواب"
                        نحن نحترم خصوصيتك ونسعى لتوفير تجربة لعب ممتعة وآمنة في لعبة "سؤال وجواب". هذه السياسة توضح كيفية تعاملنا مع بياناتك وكيفية استخدام الإعلانات داخل اللعبة.
                        
                        1. الإعلانات:
                        تتضمن اللعبة إعلانات من طرف ثالث وهي Unity Ads فقط، وتُستخدم بنظام الإعلانات المُكافأة (Rewarded Ads).
                        تُعرض هذه الإعلانات فقط في حالات محددة، مثل:
                        
                        عندما يخسر اللاعب في مرحلة ما ويرغب في استكمال التحدي مجانًا.
                        
                        بهذا الشكل، يمكن للاعبين مواصلة اللعب دون دفع أي مقابل، من خلال مشاهدة إعلان واحد فقط في كل مرة يُطلب فيها الاستمرار بعد الخسارة.
                        
                        2. المراحل داخل اللعبة:
                        تتكون اللعبة من أربع مستويات تدريجية:
                        
                        مبتدئ
                        
                        متوسط
                        
                        صعب
                        
                        خبير
                        
                        كل مستوى يحتوي على 40 سؤالًا، ويُطلق على كل مستوى اسم "تحدي" داخل التطبيق. بمجرد إكمال مستوى معين، ينتقل اللاعب تلقائيًا إلى المستوى التالي.
                        
                        3. جمع البيانات واستخدام Android ID:
                        قد نستخدم معرف Android (Android ID) لتحسين تجربة الإعلانات وتخصيصها بناءً على سلوك وتفضيلات كل مستخدم. لا يتم استخدام هذه البيانات لأغراض غير مشروعة أو لمشاركتها مع أطراف أخرى غير موثوقة.
                        
                        4. الموافقة على سياسة الخصوصية:
                        عند تحميلك للعبة، فإنك توافق تلقائيًا على سياسة الخصوصية الموضحة أعلاه.
                        إذا لم تكن موافقًا على هذه الشروط، يُرجى عدم استخدام اللعبة أو حذفها من جهازك.
                        
                        5. التحديثات المستقبلية:
                        اللعبة ما زالت في مرحلة التطوير الأولى، وقد نقوم بإجراء تحديثات دورية لتحسين الأداء أو تغيير بعض البنود في سياسة الخصوصية. سيتم إشعار المستخدمين بذلك داخل التطبيق.
                        
                        6. التواصل والدعم:
                        إذا واجهت أي مشكلة أو لديك استفسار بخصوص الخصوصية أو استخدام اللعبة، لا تتردد في التواصل معنا عبر البريد الإلكتروني:
                        📧 ahmedabdelraheem190@gmail.com
                        """.trimIndent(),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ نقل الـ Checkbox خارج الـ Scroll
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null // ✅ إزالة أي تأثير عند اللمس

                        ) {
                            MediaPlayer.create(context , R.raw.pop).start()
                            isChecked = !isChecked
                        } // الضغط على أي مكان يحدد الـ Checkbox
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = orange, // لون الخلفية عند التفعيل
                            uncheckedColor = Color.Gray, // لون الخلفية عند عدم التفعيل
                            checkmarkColor = Color.White // لون العلامة ✓
                        )
                    )
                    Text(
                        style = TextStyle(
                        fontFamily = ibmBold
                    ),
                        text = "هل توافق على السياسة والخصوصية؟", modifier = Modifier.padding(start = 0.dp))
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    isLoading = true
                    CoroutineScope(Dispatchers.Main).launch {
                        MediaPlayer.create(context , R.raw.opendoor).start()

                        delay(1000) // محاكاة حفظ الموافقة
                        onAccept(true)
                    }
                },
                enabled = isChecked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isChecked) orange else Color.Gray, // خلفية الزر
                ),
                modifier = Modifier.fillMaxWidth().height(50.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Text(
                        text = "أوافق على السياسة والخصوصية",
                        color = if (isChecked) Color.White else Color.White,
                        style = TextStyle(
                            fontFamily = ibmBold
                        )
                    )
                }
            }
        }
    )
}
