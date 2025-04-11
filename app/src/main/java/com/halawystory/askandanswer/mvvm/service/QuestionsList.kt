package com.halawystory.askandanswer.mvvm.service

import com.halawystory.askandanswer.mvvm.model.Category
import com.halawystory.askandanswer.mvvm.model.Difficulty
import com.halawystory.askandanswer.mvvm.model.Question

object Questions{
    val questionsList = listOf(
        // 🟢 المستوى الأول (سهل) - 40 سؤالًا
        // التاريخ - سهل
        Question(1, "من هو أول رئيس للولايات المتحدة الأمريكية؟", listOf("جورج واشنطن", "أبراهام لينكولن", "توماس جيفرسون", "جون آدامز"), "جورج واشنطن", Category.HISTORY, Difficulty.EASY),
        Question(2, "ما هو أطول سور في العالم؟", listOf("سور الصين العظيم", "سور برلين", "سور هادريان", "سور باريس"), "سور الصين العظيم", Category.HISTORY, Difficulty.EASY),
        Question(3, "من هو مؤسس المملكة العربية السعودية الحديثة؟", listOf("الملك فهد", "الملك سلمان", "الملك عبدالله", "الملك عبدالعزيز"), "الملك عبدالعزيز", Category.HISTORY, Difficulty.EASY),
        Question(4, "في أي عام تأسست منظمة الأمم المتحدة؟", listOf("1945", "1950", "1960", "1970"), "1945", Category.HISTORY, Difficulty.EASY),
        Question(5, "ما هي أقدم حضارة في التاريخ؟", listOf("الحضارة المصرية", "الحضارة السومرية", "الحضارة الصينية", "الحضارة الإغريقية"), "الحضارة السومرية", Category.HISTORY, Difficulty.EASY),
        Question(6, "من بنى الأهرامات في مصر؟", listOf("الرومان", "الفراعنة", "الإغريق", "العرب"), "الفراعنة", Category.HISTORY, Difficulty.EASY),
        Question(7, "ما اسم أول إنسان هبط على سطح القمر؟", listOf("نيل أرمسترونج", "باز ألدرين", "يوري جاجارين", "جون جلين"), "نيل أرمسترونج", Category.HISTORY, Difficulty.EASY),
        Question(8, "في أي قارة تقع مصر؟", listOf("آسيا", "أفريقيا", "أوروبا", "أمريكا الشمالية"), "أفريقيا", Category.HISTORY, Difficulty.EASY),
        Question(9, "ما اسم عاصمة المملكة العربية السعودية؟", listOf("جدة", "مكة", "الرياض", "المدينة"), "الرياض", Category.HISTORY, Difficulty.EASY),
        Question(10, "من هو مخترع المصباح الكهربائي؟", listOf("توماس إديسون", "ألبرت أينشتاين", "الإخوة رايت", "جراهام بيل"), "توماس إديسون", Category.HISTORY, Difficulty.EASY),

        // التكنولوجيا - سهل
        Question(11, "ما هو نظام التشغيل الذي تستخدمه هواتف iPhone؟", listOf("Android", "Windows", "iOS", "Linux"), "iOS", Category.TECHNOLOGY, Difficulty.EASY),
        Question(12, "ما اسم الجهاز الذي يستخدم لالتقاط الصور؟", listOf("ميكروفون", "تلفاز", "كاميرا", "راديو"), "كاميرا", Category.TECHNOLOGY, Difficulty.EASY),
        Question(13, "ما هو الجهاز المستخدم للطباعة؟", listOf("الماسح الضوئي", "الطابعة", "لوحة المفاتيح", "الفأرة"), "الطابعة", Category.TECHNOLOGY, Difficulty.EASY),
        Question(14, "ما هو الاختصار لشبكة الإنترنت العالمية؟", listOf("WWW", "HTTP", "HTML", "URL"), "WWW", Category.TECHNOLOGY, Difficulty.EASY),
        Question(15, "ما هي الشركة المصنعة لهواتف Galaxy؟", listOf("آبل", "سامسونج", "هواوي", "شاومي"), "سامسونج", Category.TECHNOLOGY, Difficulty.EASY),
        Question(16, "ما اسم الجهاز المستخدم لسماع الصوت؟", listOf("سماعات", "مكبر صوت", "ميكروفون", "جميع ما سبق"), "سماعات", Category.TECHNOLOGY, Difficulty.EASY),
        Question(17, "ما هو الاسم الشائع لجهاز التحكم عن بعد للتلفاز؟", listOf("الريموت", "الماوس", "الكيبورد", "الجوال"), "الريموت", Category.TECHNOLOGY, Difficulty.EASY),
        Question(18, "ما هو جهاز تخزين البيانات المحمول الشائع؟", listOf("القرص الصلب", "فلاش USB", "القرص المرن", "الشريط المغناطيسي"), "فلاش USB", Category.TECHNOLOGY, Difficulty.EASY),
        Question(19, "أي من التالي يمكن استخدامه للاتصال بالإنترنت؟", listOf("الراوتر", "الطابعة", "الميكروفون", "السماعات"), "الراوتر", Category.TECHNOLOGY, Difficulty.EASY),
        Question(20, "ما هو المتصفح الذي طورته شركة جوجل؟", listOf("Firefox", "Chrome", "Safari", "Internet Explorer"), "Chrome", Category.TECHNOLOGY, Difficulty.EASY),

        // العلوم - سهل
        Question(21, "كم عدد الكواكب في المجموعة الشمسية؟", listOf("7", "8", "9", "10"), "8", Category.SCIENCE, Difficulty.EASY),
        Question(22, "ما هو العنصر الأساسي في تكوين الماء؟", listOf("أكسجين", "هيدروجين", "نيتروجين", "كربون"), "هيدروجين", Category.SCIENCE, Difficulty.EASY),
        Question(23, "ما الحيوان الذي يعتبر من الثدييات ويعيش في الماء؟", listOf("التمساح", "السمك", "الدلفين", "الضفدع"), "الدلفين", Category.SCIENCE, Difficulty.EASY),
        Question(24, "ما هو أكبر كوكب في المجموعة الشمسية؟", listOf("الأرض", "المشتري", "زحل", "المريخ"), "المشتري", Category.SCIENCE, Difficulty.EASY),
        Question(25, "كم عدد أرجل العنكبوت؟", listOf("6", "8", "10", "12"), "8", Category.SCIENCE, Difficulty.EASY),
        Question(26, "ما هو مصدر الطاقة الرئيسي للشمس؟", listOf("الاندماج النووي", "الانشطار النووي", "الاحتراق", "التفاعل الكيميائي"), "الاندماج النووي", Category.SCIENCE, Difficulty.EASY),
        Question(27, "ما هو المعدن الذي يوجد في الدم ويعطيه اللون الأحمر؟", listOf("الحديد", "الزنك", "النحاس", "الكالسيوم"), "الحديد", Category.SCIENCE, Difficulty.EASY),
        Question(28, "ما هو الحيوان الذي يلد ولا يبيض؟", listOf("السلحفاة", "الدجاجة", "القط", "التمساح"), "القط", Category.SCIENCE, Difficulty.EASY),
        Question(29, "ما هو الغاز الذي يتنفسه الإنسان؟", listOf("ثاني أكسيد الكربون", "النيتروجين", "الأكسجين", "الهيدروجين"), "الأكسجين", Category.SCIENCE, Difficulty.EASY),
        Question(30, "ما هو الكائن الحي الذي يصنع غذاءه بنفسه؟", listOf("الأسد", "النبات", "الإنسان", "السمكة"), "النبات", Category.SCIENCE, Difficulty.EASY),

        // الرياضيات - سهل
        Question(31, "ما هو ناتج 5 + 5؟", listOf("8", "9", "10", "11"), "10", Category.MATH, Difficulty.EASY),
        Question(32, "كم عدد الزوايا في المثلث؟", listOf("2", "3", "4", "5"), "3", Category.MATH, Difficulty.EASY),
        Question(33, "ما هو ناتج 10 - 5؟", listOf("3", "4", "5", "6"), "5", Category.MATH, Difficulty.EASY),
        Question(34, "كم يساوي 2 × 3؟", listOf("5", "6", "7", "8"), "6", Category.MATH, Difficulty.EASY),
        Question(35, "كم عدد أيام الأسبوع؟", listOf("5", "6", "7", "8"), "7", Category.MATH, Difficulty.EASY),
        Question(36, "ما هو العدد الذي يأتي بعد 9؟", listOf("8", "10", "11", "12"), "10", Category.MATH, Difficulty.EASY),
        Question(37, "كم عدد الساعات في اليوم الواحد؟", listOf("12", "18", "24", "30"), "24", Category.MATH, Difficulty.EASY),
        Question(38, "كم عدد الدقائق في الساعة الواحدة؟", listOf("30", "45", "60", "90"), "60", Category.MATH, Difficulty.EASY),
        Question(39, "كم عدد أضلاع المربع؟", listOf("3", "4", "5", "6"), "4", Category.MATH, Difficulty.EASY),
        Question(40, "ما هو ناتج 20 ÷ 4؟", listOf("4", "5", "6", "8"), "5", Category.MATH, Difficulty.EASY),

        // 🟡 المستوى الثاني (متوسط) - 40 سؤالًا
        // التاريخ - متوسط
        Question(41, "في أي سنة انتهت الحرب العالمية الأولى؟", listOf("1914", "1918", "1939", "1945"), "1918", Category.HISTORY, Difficulty.MEDIUM),
        Question(42, "من هو القائد الذي قاد ثورة 1952 في مصر؟", listOf("جمال عبد الناصر", "أنور السادات", "محمد نجيب", "حسني مبارك"), "محمد نجيب", Category.HISTORY, Difficulty.MEDIUM),
        Question(43, "في أي عام تم توحيد المملكة العربية السعودية؟", listOf("1902", "1925", "1932", "1950"), "1932", Category.HISTORY, Difficulty.MEDIUM),
        Question(44, "ما اسم المعاهدة التي أنهت الحرب العالمية الأولى؟", listOf("معاهدة فرساي", "معاهدة السلام", "معاهدة باريس", "معاهدة بروكسل"), "معاهدة فرساي", Category.HISTORY, Difficulty.MEDIUM),
        Question(45, "من هو مؤسس الإمبراطورية المغولية؟", listOf("تيمورلنك", "جنكيز خان", "كوبلاي خان", "بابر"), "جنكيز خان", Category.HISTORY, Difficulty.MEDIUM),
        Question(46, "من هو أول من وصل إلى القطب الجنوبي؟", listOf("روبرت بيري", "رونالد أموندسن", "جيمس كوك", "روبرت سكوت"), "رونالد أموندسن", Category.HISTORY, Difficulty.MEDIUM),
        Question(47, "في أي عام تأسست جامعة الدول العربية؟", listOf("1940", "1945", "1950", "1955"), "1945", Category.HISTORY, Difficulty.MEDIUM),
        Question(48, "من كان قائد المسلمين في معركة القادسية؟", listOf("خالد بن الوليد", "سعد بن أبي وقاص", "عمرو بن العاص", "طارق بن زياد"), "سعد بن أبي وقاص", Category.HISTORY, Difficulty.MEDIUM),
        Question(49, "ما هي الدولة التي احتلت فلسطين عام 1917؟", listOf("فرنسا", "ألمانيا", "بريطانيا", "إيطاليا"), "بريطانيا", Category.HISTORY, Difficulty.MEDIUM),
        Question(50, "من هو مخترع الطباعة الحديثة؟", listOf("جوتنبرج", "نيوتن", "أديسون", "بيل"), "جوتنبرج", Category.HISTORY, Difficulty.MEDIUM),

        // التكنولوجيا - متوسط
        Question(51, "ما اسم أول حاسوب إلكتروني؟", listOf("ENIAC", "IBM", "Windows", "Macintosh"), "ENIAC", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(52, "ما هي لغة البرمجة الأكثر استخدامًا لتطوير تطبيقات الويب؟", listOf("Java", "C++", "JavaScript", "Python"), "JavaScript", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(53, "ما هو الامتداد الشائع لملفات الصور؟", listOf("DOC", "MP3", "JPG", "EXE"), "JPG", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(54, "ما هي وحدة قياس سرعة الإنترنت؟", listOf("هرتز", "بت في الثانية", "بايت", "واط"), "بت في الثانية", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(55, "ما هو الاختصار لتقنية البلوتوث؟", listOf("BT", "WF", "LT", "LAN"), "BT", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(56, "ما هو البرنامج المستخدم للحماية من الفيروسات؟", listOf("Adobe Reader", "Microsoft Word", "VLC Media Player", "Norton Antivirus"), "Norton Antivirus", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(57, "من هو مؤسس شركة مايكروسوفت؟", listOf("ستيف جوبز", "بيل جيتس", "مارك زوكربيرج", "لاري بيج"), "بيل جيتس", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(58, "ما هو نوع الشبكة التي تربط أجهزة الكمبيوتر في منطقة جغرافية محدودة؟", listOf("LAN", "WAN", "MAN", "PAN"), "LAN", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(59, "ما هو الجزء المسؤول عن معالجة البيانات في الحاسوب؟", listOf("القرص الصلب", "الذاكرة", "المعالج", "البطارية"), "المعالج", Category.TECHNOLOGY, Difficulty.MEDIUM),
        Question(60, "ما هي الشركة التي طورت نظام Android؟", listOf("Apple", "Microsoft", "Google", "Samsung"), "Google", Category.TECHNOLOGY, Difficulty.MEDIUM),

        // العلوم - متوسط
        Question(61, "ما هي أكبر غدة في جسم الإنسان؟", listOf("البنكرياس", "الغدة الدرقية", "الكبد", "الغدة النخامية"), "الكبد", Category.SCIENCE, Difficulty.MEDIUM),
        Question(62, "ما هو العضو المسؤول عن تنقية الدم في جسم الإنسان؟", listOf("الكبد", "القلب", "الكلى", "الرئة"), "الكلى", Category.SCIENCE, Difficulty.MEDIUM),
        Question(63, "ما هو الغاز الأكثر وفرة في الغلاف الجوي للأرض؟", listOf("الأكسجين", "ثاني أكسيد الكربون", "النيتروجين", "الهيدروجين"), "النيتروجين", Category.SCIENCE, Difficulty.MEDIUM),
        Question(64, "ما هي وحدة قياس القوة؟", listOf("جول", "واط", "نيوتن", "أمبير"), "نيوتن", Category.SCIENCE, Difficulty.MEDIUM),
        Question(65, "ما هو العنصر الكيميائي رقم 1 في الجدول الدوري؟", listOf("الهيليوم", "الهيدروجين", "الليثيوم", "الكربون"), "الهيدروجين", Category.SCIENCE, Difficulty.MEDIUM),
        Question(66, "ما هي المادة التي تنتجها النباتات في عملية التمثيل الضوئي؟", listOf("الأكسجين", "ثاني أكسيد الكربون", "الغلوكوز", "النيتروجين"), "الغلوكوز", Category.SCIENCE, Difficulty.MEDIUM),
        Question(67, "ما هو اسم الخلايا العصبية في الدماغ؟", listOf("البكتيريا", "العضلات", "العصبونات", "الكريات الحمراء"), "العصبونات", Category.SCIENCE, Difficulty.MEDIUM),
        Question(68, "ما هي وحدة قياس شدة التيار الكهربائي؟", listOf("فولت", "أوم", "أمبير", "واط"), "أمبير", Category.SCIENCE, Difficulty.MEDIUM),
        Question(69, "ما هو الاسم العلمي للإنسان؟", listOf("هومو سابينس", "هومو إريكتوس", "هومو نياندرتال", "بانثيرا ليو"), "هومو سابينس", Category.SCIENCE, Difficulty.MEDIUM),
        Question(70, "ما هي المجموعة التي تنتمي إليها الكلاب في تصنيف الكائنات الحية؟", listOf("الزواحف", "الثدييات", "البرمائيات", "الأسماك"), "الثدييات", Category.SCIENCE, Difficulty.MEDIUM),

        // الرياضيات - متوسط
        Question(71, "ما هي النتيجة لـ 12 × 12؟", listOf("124", "144", "156", "169"), "144", Category.MATH, Difficulty.MEDIUM),
        Question(72, "ما هو الناتج لـ 25% من 200؟", listOf("25", "40", "50", "75"), "50", Category.MATH, Difficulty.MEDIUM),
        Question(73, "كم عدد أضلاع المسدس؟", listOf("5", "6", "7", "8"), "6", Category.MATH, Difficulty.MEDIUM),
        Question(74, "ما هو محيط المربع الذي طول ضلعه 7 سم؟", listOf("14 سم", "21 سم", "28 سم", "49 سم"), "28 سم", Category.MATH, Difficulty.MEDIUM),
        Question(75, "ما هو الجذر التربيعي للعدد 81؟", listOf("8", "9", "10", "12"), "9", Category.MATH, Difficulty.MEDIUM),
        Question(76, "ما هو العدد الذي إذا ضربته في نفسه يعطي 64؟", listOf("6", "8", "9", "10"), "8", Category.MATH, Difficulty.MEDIUM),
        Question(77, "كم تساوي قيمة π (باي) تقريبًا؟", listOf("3.14", "2.71", "1.62", "1.41"), "3.14", Category.MATH, Difficulty.MEDIUM),
        Question(78, "ما هو الناتج لـ (3² + 4²)؟", listOf("15", "25", "49", "50"), "25", Category.MATH, Difficulty.MEDIUM),
        Question(79, "ما هو مجموع زوايا المثلث بالدرجات؟", listOf("90°", "180°", "270°", "360°"), "180°", Category.MATH, Difficulty.MEDIUM),
        Question(80, "ما هو ناتج 0.5 × 0.5؟", listOf("0.1", "0.25", "0.5", "1"), "0.25", Category.MATH, Difficulty.MEDIUM),

        // 🟠 المستوى الثالث (صعب) - 40 سؤالًا
        // التاريخ - صعب
        Question(81, "من هو القائد الذي وحد الصين لأول مرة؟", listOf("كونفوشيوس", "شي هوانغ تي", "ماو تسي تونغ", "جنكيز خان"), "شي هوانغ تي", Category.HISTORY, Difficulty.HARD),
        Question(82, "متى انتهت الحرب الباردة رسميًا؟", listOf("1985", "1989", "1991", "1995"), "1991", Category.HISTORY, Difficulty.HARD),
        Question(83, "من هو مؤلف كتاب 'البؤساء'؟", listOf("فيكتور هوجو", "ليو تولستوي", "تشارلز ديكنز", "جين أوستن"), "فيكتور هوجو", Category.HISTORY, Difficulty.HARD),
        Question(84, "ما اسم المعركة التي هزم فيها نابليون بونابرت عام 1815؟", listOf("معركة واترلو", "معركة أوسترليتز", "معركة ألمانز", "معركة ليبزيج"), "معركة واترلو", Category.HISTORY, Difficulty.HARD),
        Question(85, "في أي عام وقعت الثورة الفرنسية؟", listOf("1776", "1789", "1804", "1848"), "1789", Category.HISTORY, Difficulty.HARD),
        Question(86, "من هو الفاتح الإسلامي الذي فتح بلاد السند (باكستان حاليًا)؟", listOf("طارق بن زياد", "محمد بن القاسم", "صلاح الدين الأيوبي", "سعد بن أبي وقاص"), "محمد بن القاسم", Category.HISTORY, Difficulty.HARD),
        Question(87, "متى تأسست الدولة العباسية؟", listOf("632 م", "661 م", "750 م", "813 م"), "750 م", Category.HISTORY, Difficulty.HARD),
        Question(88, "من هو الملك الفرعوني الذي بنى أعظم الأهرامات في الجيزة؟", listOf("خوفو", "خفرع", "منقرع", "توت عنخ آمون"), "خوفو", Category.HISTORY, Difficulty.HARD),
        Question(89, "في أي معركة انتصر صلاح الدين الأيوبي على الصليبيين واستعاد القدس؟", listOf("معركة عين جالوت", "معركة حطين", "معركة اليرموك", "معركة القادسية"), "معركة حطين", Category.HISTORY, Difficulty.HARD),
        Question(90, "من كان أول خليفة للمسلمين بعد وفاة النبي محمد ﷺ؟", listOf("عمر بن الخطاب", "أبو بكر الصديق", "عثمان بن عفان", "علي بن أبي طالب"), "أبو بكر الصديق", Category.HISTORY, Difficulty.HARD),

        // التكنولوجيا - صعب
        Question(91, "ما هو الاسم الأصلي لشركة جوجل؟", listOf("GoTo", "BackRub", "Yahoo", "Bing"), "BackRub", Category.TECHNOLOGY, Difficulty.HARD),
        Question(92, "أي من التالي ليس نظام قاعدة بيانات؟", listOf("MySQL", "MongoDB", "Oracle", "Ruby"), "Ruby", Category.TECHNOLOGY, Difficulty.HARD),
        Question(93, "ما هو البروتوكول المستخدم لتبادل الملفات عبر الإنترنت؟", listOf("HTTP", "FTP", "SMTP", "TCP"), "FTP", Category.TECHNOLOGY, Difficulty.HARD),
        Question(94, "ما هو العام الذي تم فيه إطلاق أول iPhone؟", listOf("2005", "2007", "2010", "2012"), "2007", Category.TECHNOLOGY, Difficulty.HARD),
        Question(95, "ما هي خوارزمية التشفير الشهيرة المستخدمة في حماية المعلومات؟", listOf("MP3", "PNG", "AES", "GPS"), "AES", Category.TECHNOLOGY, Difficulty.HARD),
        Question(96, "ما هي لغة البرمجة التي طورها بيورن ستروستروب؟", listOf("Java", "C++", "Python", "Ruby"), "C++", Category.TECHNOLOGY, Difficulty.HARD),
        Question(97, "ما هو المصطلح المستخدم لوصف المهاجمين الإلكترونيين الذين يستخدمون مهاراتهم لاكتشاف الثغرات الأمنية وإصلاحها؟", listOf("القراصنة السوداء", "القراصنة البيضاء", "القراصنة الرمادية", "القراصنة الزرقاء"), "القراصنة البيضاء", Category.TECHNOLOGY, Difficulty.HARD),
        Question(98, "ما هي تقنية الاتصال اللاسلكي قصيرة المدى المستخدمة في الدفع بالهاتف المحمول؟", listOf("Bluetooth", "Wi-Fi", "NFC", "GPS"), "NFC", Category.TECHNOLOGY, Difficulty.HARD),
        Question(99, "ما هو الاسم الشائع للبرمجيات الخبيثة التي تقوم بتشفير ملفات المستخدم وطلب فدية لفك التشفير؟", listOf("Worm", "Trojan", "Spyware", "Ransomware"), "Ransomware", Category.TECHNOLOGY, Difficulty.HARD),
        Question(100, "ما هي الطبقة المسؤولة عن نقل البيانات في نموذج OSI للشبكات؟", listOf("طبقة التطبيقات", "طبقة النقل", "الطبقة الفيزيائية", "طبقة الشبكة"), "طبقة النقل", Category.TECHNOLOGY, Difficulty.HARD),

        // العلوم - صعب
        Question(101, "ما اسم العالم الذي اكتشف البنسلين؟", listOf("ألكسندر فلمنج", "لويس باستير", "روبرت كوخ", "جوناس سالك"), "ألكسندر فلمنج", Category.SCIENCE, Difficulty.HARD),
        Question(102, "ما هو العنصر الكيميائي الذي رمزه 'Au'؟", listOf("الفضة", "الذهب", "النحاس", "الألومنيوم"), "الذهب", Category.SCIENCE, Difficulty.HARD),
        Question(103, "ما هو علم دراسة الأحياء المجهرية؟", listOf("علم الفيروسات", "علم الأحياء الدقيقة", "علم الخلية", "علم البكتيريا"), "علم الأحياء الدقيقة", Category.SCIENCE, Difficulty.HARD),
        Question(104, "ما هي السرعة التقريبية للضوء في الفراغ؟", listOf("300,000 كم/ثانية", "150,000 كم/ثانية", "250,000 كم/ثانية", "500,000 كم/ثانية"), "300,000 كم/ثانية", Category.SCIENCE, Difficulty.HARD),
        Question(105, "ما هي الصفيحة الدموية المسؤولة عن تجلط الدم؟", listOf("الخلايا الجذعية", "الكريات الحمراء", "الكريات البيضاء", "الصفائح الدموية"), "الصفائح الدموية", Category.SCIENCE, Difficulty.HARD),
        Question(106, "ما هو العضو المسؤول عن إنتاج الأنسولين في جسم الإنسان؟", listOf("الكبد", "البنكرياس", "الكلى", "الطحال"), "البنكرياس", Category.SCIENCE, Difficulty.HARD),
        Question(107, "ما هو الاسم العلمي للصوت الذي يفوق سرعة الصوت؟", listOf("الصوت المرتفع", "موجات فوق صوتية", "الصوت فائق السرعة", "الصوت فوق السمعي"), "الصوت فائق السرعة", Category.SCIENCE, Difficulty.HARD),
        Question(108, "ما هو اسم القانون الذي ينص على أن الطاقة لا تفنى ولا تستحدث؟", listOf("قانون نيوتن", "قانون أوم", "قانون حفظ الطاقة", "قانون بويل"), "قانون حفظ الطاقة", Category.SCIENCE, Difficulty.HARD),
        Question(109, "ما هو العنصر الأكثر وفرة في القشرة الأرضية؟", listOf("الحديد", "الأكسجين", "السيليكون", "الألومنيوم"), "الأكسجين", Category.SCIENCE, Difficulty.HARD),
        Question(110, "ما هو جهاز قياس الضغط الجوي؟", listOf("الثرمومتر", "البارومتر", "الهيدرومتر", "الأميتر"), "البارومتر", Category.SCIENCE, Difficulty.HARD),

        // الرياضيات - صعب
        Question(111, "ما هي المسافة بين نقطتين إحداثياتهما (0,0) و (3,4)؟", listOf("5", "7", "9", "12"), "5", Category.MATH, Difficulty.HARD),
        Question(112, "ما هو حاصل ضرب العددين المركبين (2+3i) و (1-i)؟", listOf("5+i", "5-i", "2+4i", "2-4i"), "5+i", Category.MATH, Difficulty.HARD),
        Question(113, "ما هو مجموع المتسلسلة الهندسية: 1 + 1/2 + 1/4 + 1/8 + ... إلى ما لا نهاية؟", listOf("1", "2", "3", "4"), "2", Category.MATH, Difficulty.HARD),
        Question(114, "ما هو حل المعادلة: x² - 5x + 6 = 0؟", listOf("2 و 3", "1 و 6", "-2 و -3", "-1 و -6"), "2 و 3", Category.MATH, Difficulty.HARD),
        Question(115, "ما هي قيمة sin(π/2)؟", listOf("0", "1/2", "1", "-1"), "1", Category.MATH, Difficulty.HARD),
        Question(116, "ما هو تعريف المشتقة في الرياضيات؟", listOf("مجموع التغيرات", "معدل التغير اللحظي", "مجموع القيم", "متوسط القيم"), "معدل التغير اللحظي", Category.MATH, Difficulty.HARD),
        Question(117, "ما هو تعريف التكامل في الرياضيات؟", listOf("إيجاد المساحة تحت المنحنى", "إيجاد النهايات", "إيجاد المشتقات", "إيجاد قيمة النهاية العظمى"), "إيجاد المساحة تحت المنحنى", Category.MATH, Difficulty.HARD),
        Question(118, "ما هي صيغة الحل العام للمعادلة التربيعية ax² + bx + c = 0؟", listOf("x = -b ± √(b² - 4ac) / 2a", "x = -b ± √(b² + 4ac) / 2a", "x = b ± √(b² - 4ac) / 2a", "x = b ± √(b² - 4ac) / a"), "x = -b ± √(b² - 4ac) / 2a", Category.MATH, Difficulty.HARD),
        Question(119, "ما هو مجموع زوايا الشكل الرباعي؟", listOf("180°", "270°", "360°", "540°"), "360°", Category.MATH, Difficulty.HARD),
        Question(120, "ما هي النسبة المثلثية التي تساوي نسبة طول الضلع المقابل للزاوية إلى طول الوتر؟", listOf("الجيب", "جيب التمام", "الظل", "الظل التمام"), "الجيب", Category.MATH, Difficulty.HARD),

        // 🔴 المستوى الرابع (خبير) - 40 سؤالًا
        // التاريخ - خبير
        Question(121, "ما هي أول حضارة استخدمت الكتابة المسمارية؟", listOf("الحضارة الفرعونية", "الحضارة السومرية", "الحضارة الإغريقية", "الحضارة الرومانية"), "الحضارة السومرية", Category.HISTORY, Difficulty.EXPERT),
        Question(122, "متى وقعت معاهدة وستفاليا التي أنهت حرب الثلاثين عامًا في أوروبا؟", listOf("1598", "1618", "1648", "1688"), "1648", Category.HISTORY, Difficulty.EXPERT),
        Question(123, "من هو المؤرخ العربي الذي ألف كتاب 'المقدمة'؟", listOf("ابن سينا", "الطبري", "ابن خلدون", "المسعودي"), "ابن خلدون", Category.HISTORY, Difficulty.EXPERT),
        Question(124, "ما هو اسم المجلس الذي حكم الدولة الأموية في الأندلس بعد سقوط الخلافة؟", listOf("مجلس العلماء", "مجلس الخلافة", "مجلس ملوك الطوائف", "مجلس الشورى"), "مجلس ملوك الطوائف", Category.HISTORY, Difficulty.EXPERT),
        Question(125, "من هو القائد البريطاني الذي هزم نابليون في معركة واترلو؟", listOf("اللورد نيلسون", "دوق ويلينغتون", "الجنرال مونتغمري", "الميجور ويلسون"), "دوق ويلينغتون", Category.HISTORY, Difficulty.EXPERT),
        Question(126, "ما اسم العهد الذي منحه الخليفة عمر بن الخطاب لأهل القدس؟", listOf("العهد العمري", "عهد الذمة", "عهد الأمان", "عهد المدينة"), "العهد العمري", Category.HISTORY, Difficulty.EXPERT),
        Question(127, "ما اسم المدينة اليونانية التي شهدت إقامة أول ألعاب أولمبية قديمة؟", listOf("أثينا", "سبارتا", "أولمبيا", "دلفي"), "أولمبيا", Category.HISTORY, Difficulty.EXPERT),
        Question(128, "من هو العالم المسلم الذي لقب بـ 'الشيخ الرئيس'؟", listOf("الخوارزمي", "ابن رشد", "ابن سينا", "الرازي"), "ابن سينا", Category.HISTORY, Difficulty.EXPERT),
        Question(129, "ما هو اسم أول دستور مكتوب في العالم الإسلامي؟", listOf("القانون العثماني", "قانون حمورابي", "صحيفة المدينة", "كتاب الخراج"), "صحيفة المدينة", Category.HISTORY, Difficulty.EXPERT),
        Question(130, "في أي عام تم اكتشاف مقبرة توت عنخ آمون؟", listOf("1912", "1922", "1932", "1942"), "1922", Category.HISTORY, Difficulty.EXPERT),

        // التكنولوجيا - خبير
        Question(131, "ما هو اسم العملة المشفرة التي ابتكرها ساتوشي ناكاموتو؟", listOf("إيثريوم", "بيتكوين", "ريبل", "لايتكوين"), "بيتكوين", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(132, "ما هو اسم لغة البرمجة التي طورها جيمس جوسلينج في شركة صن ميكروسيستمز؟", listOf("C++", "Python", "Java", "Ruby"), "Java", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(133, "ما هو مصطلح 'TCP/IP' وما يمثله في عالم التكنولوجيا؟", listOf("نظام تشغيل", "مجموعة بروتوكولات الإنترنت", "نوع من الشبكات", "لغة برمجة"), "مجموعة بروتوكولات الإنترنت", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(134, "ما هو مفهوم 'Blockchain' أو سلسلة الكتل؟", listOf("تقنية تخزين موزعة وآمنة", "نوع من البرمجيات الخبيثة", "جهاز لتخزين البيانات", "نظام حماية للشبكات"), "تقنية تخزين موزعة وآمنة", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(135, "ما هو اسم الخوارزمية المستخدمة في ترتيب البيانات بتعقيد زمني O(n log n)؟", listOf("Bubble Sort", "Selection Sort", "Quick Sort", "Insertion Sort"), "Quick Sort", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(136, "ما هو مبدأ عمل تقنية 'AJAX' في تطوير الويب؟", listOf("تحديث الصفحة بالكامل", "التحديث الجزئي للصفحة بدون إعادة تحميلها", "تشفير البيانات", "تسريع تحميل الصور"), "التحديث الجزئي للصفحة بدون إعادة تحميلها", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(137, "ما هو نظام التشغيل المفتوح المصدر المبني على نظام يونكس؟", listOf("Windows", "macOS", "Linux", "Android"), "Linux", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(138, "ما هو مفهوم 'REST API' في تطوير البرمجيات؟", listOf("نموذج برمجة تطبيقات الويب", "تقنية لتصميم واجهات المستخدم", "نظام إدارة قواعد البيانات", "نظام تشغيل للخوادم"), "نموذج برمجة تطبيقات الويب", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(139, "ما هو اسم المصطلح الذي يعني 'القدرة على تحمل الفشل' في أنظمة الكمبيوتر؟", listOf("Scalability", "Fault Tolerance", "Reliability", "Resilience"), "Fault Tolerance", Category.TECHNOLOGY, Difficulty.EXPERT),
        Question(140, "ما هو مفهوم 'Quantum Computing' أو الحوسبة الكمية؟", listOf("الحوسبة باستخدام خصائص ميكانيكا الكم", "الحوسبة عالية السرعة", "الحوسبة السحابية المتقدمة", "الحوسبة المستخدمة في علم الفلك"), "الحوسبة باستخدام خصائص ميكانيكا الكم", Category.TECHNOLOGY, Difficulty.EXPERT),

        // العلوم - خبير
        Question(141, "ما اسم العالم الذي طور نظرية النسبية؟", listOf("إسحاق نيوتن", "ألبرت أينشتاين", "نيلز بور", "ستيفن هوكينغ"), "ألبرت أينشتاين", Category.SCIENCE, Difficulty.EXPERT),
        Question(142, "ما هو اسم الجسيم الأولي الذي يعتبر ناقلاً للقوة الكهرومغناطيسية؟", listOf("البوزون", "الفوتون", "الإلكترون", "النيوترون"), "الفوتون", Category.SCIENCE, Difficulty.EXPERT),
        Question(143, "ما هو اسم النظرية التي تصف نشأة الكون؟", listOf("نظرية الانفجار العظيم", "نظرية التطور", "نظرية الحالة المستقرة", "نظرية الأوتار"), "نظرية الانفجار العظيم", Category.SCIENCE, Difficulty.EXPERT),
        Question(144, "ما اسم المركب الكيميائي الذي يشكل الحمض النووي الريبوزي منقوص الأكسجين؟", listOf("RNA", "DNA", "ATP", "ADP"), "DNA", Category.SCIENCE, Difficulty.EXPERT),
        Question(145, "ما هو علم دراسة الوظائف الحيوية للكائنات الحية؟", listOf("علم التشريح", "علم الأحياء", "علم وظائف الأعضاء", "علم الخلية"), "علم وظائف الأعضاء", Category.SCIENCE, Difficulty.EXPERT),
        Question(146, "ما هو اسم الأنزيم المستخدم في تفاعل البلمرة المتسلسل (PCR)؟", listOf("البوليميراز", "الأميليز", "الليبيز", "البروتييز"), "البوليميراز", Category.SCIENCE, Difficulty.EXPERT),
        Question(147, "ما هو اسم المصطلح الذي يصف تحول المادة من الحالة الصلبة إلى الحالة الغازية مباشرة؟", listOf("التسامي", "التبخر", "التكثيف", "الانصهار"), "التسامي", Category.SCIENCE, Difficulty.EXPERT),
        Question(148, "ما هو اسم النظرية الحديثة للتطور التي تجمع بين نظرية داروين وعلم الوراثة؟", listOf("النظرية الداروينية", "النظرية التطورية الحديثة", "النظرية الاصطفائية", "نظرية التطور بالطفرات"), "النظرية التطورية الحديثة", Category.SCIENCE, Difficulty.EXPERT),
        Question(149, "ما هو اسم القاعدة التي تنص على أنه 'لا يمكن معرفة موقع وسرعة الجسيم في نفس الوقت بدقة تامة'؟", listOf("مبدأ الشك", "قانون نيوتن الثالث", "قانون ثبات الطاقة", "قانون باسكال"), "مبدأ الشك", Category.SCIENCE, Difficulty.EXPERT),
        Question(150, "ما هو اسم المقياس المستخدم لقياس شدة الزلازل؟", listOf("مقياس بوفورت", "مقياس كلفن", "مقياس ريختر", "مقياس فهرنهايت"), "مقياس ريختر", Category.SCIENCE, Difficulty.EXPERT),

        // الرياضيات - خبير
        Question(151, "ما هي نظرية فيرمات الأخيرة في الرياضيات؟", listOf("لا توجد أعداد صحيحة موجبة x, y, z تحقق المعادلة xⁿ + yⁿ = zⁿ إذا كان n > 2", "لكل عدد أولي يوجد عدد أولي أكبر منه", "مجموع زوايا المثلث يساوي 180 درجة", "نظرية فيثاغورس في المثلث قائم الزاوية"), "لا توجد أعداد صحيحة موجبة x, y, z تحقق المعادلة xⁿ + yⁿ = zⁿ إذا كان n > 2", Category.MATH, Difficulty.EXPERT),
        Question(152, "ما هو عدد أعداد فيبوناتشي الأولية المعروفة حتى الآن؟", listOf("خمسة", "عشرة", "خمسة عشر", "عدد لا نهائي"), "خمسة", Category.MATH, Difficulty.EXPERT),
        Question(153, "ما هو التكامل لدالة sin(x)؟", listOf("-cos(x) + C", "cos(x) + C", "tan(x) + C", "cot(x) + C"), "-cos(x) + C", Category.MATH, Difficulty.EXPERT),
        Question(154, "ما هي صيغة حساب المحدد للمصفوفة ذات البعد 2×2 حيث A = [[a, b], [c, d]]؟", listOf("a+d", "a×d", "a×d - b×c", "a+b+c+d"), "a×d - b×c", Category.MATH, Difficulty.EXPERT),
        Question(155, "ما هو مفهوم 'الحد' في التفاضل والتكامل؟", listOf("أكبر قيمة للدالة", "قيمة الدالة عندما يقترب المتغير من قيمة معينة", "ميل المماس للمنحنى", "معدل تغير الدالة"), "قيمة الدالة عندما يقترب المتغير من قيمة معينة", Category.MATH, Difficulty.EXPERT),
        Question(156, "ما هو عدد الأوجه في المجسم الرباعي المنتظم؟", listOf("3", "4", "5", "6"), "4", Category.MATH, Difficulty.EXPERT),
        Question(157, "ما هو الحل العام للمعادلة التفاضلية dy/dx = y؟", listOf("y = Ce^x", "y = Cx", "y = C/x", "y = Csinx"), "y = Ce^x", Category.MATH, Difficulty.EXPERT),
        Question(158, "ما هي نظرية القيمة المتوسطة في التفاضل والتكامل؟", listOf("متوسط قيم الدالة يساوي قيمة الدالة عند نقطة معينة", "توجد نقطة في المجال تكون فيها مشتقة الدالة تساوي الميل الوسطي للدالة", "تكامل الدالة يساوي متوسط قيمها", "مجموع قيم الدالة يساوي صفراً"), "توجد نقطة في المجال تكون فيها مشتقة الدالة تساوي الميل الوسطي للدالة", Category.MATH, Difficulty.EXPERT),
        Question(159, "ما هو مفهوم 'التوبولوجيا' في الرياضيات؟", listOf("دراسة الأشكال الهندسية", "دراسة الخصائص التي لا تتغير تحت التشوهات المستمرة", "دراسة الدوال الرياضية", "دراسة الأعداد المركبة"), "دراسة الخصائص التي لا تتغير تحت التشوهات المستمرة", Category.MATH, Difficulty.EXPERT),
        Question(160, "ما هو تعريف الدالة المتصلة في نقطة ما؟", listOf("الدالة التي يمكن رسمها دون رفع القلم", "الدالة التي يكون حد الدالة عند النقطة يساوي قيمة الدالة عند تلك النقطة", "الدالة التي يمكن اشتقاقها", "الدالة التي يمكن تكاملها"), "الدالة التي يكون حد الدالة عند النقطة يساوي قيمة الدالة عند تلك النقطة", Category.MATH, Difficulty.EXPERT)
    )



}