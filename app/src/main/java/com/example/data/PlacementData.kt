package com.example.data

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

data class PlacementStory(
  val id: String,
  val studentName: String,
  val courseCompleted: String,
  val jobTitle: String,
  val companyName: String,
  val location: String,
  val placementYear: String,
  val packageOrSalary: String? = null,
  val quote: String,
  val imageResName: String = "img_graduate_1", // "img_graduate_1", "img_graduate_2", "img_graduate_3"
  val isFeatured: Boolean = true
) {
  fun getImageDrawableRes(): Int {
    return when (imageResName) {
      "img_graduate_1" -> R.drawable.img_graduate_1
      "img_graduate_2" -> R.drawable.img_graduate_2
      "img_graduate_3" -> R.drawable.img_graduate_3
      else -> R.drawable.img_graduate_1
    }
  }

  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("id", id)
      put("studentName", studentName)
      put("courseCompleted", courseCompleted)
      put("jobTitle", jobTitle)
      put("companyName", companyName)
      put("location", location)
      put("placementYear", placementYear)
      put("packageOrSalary", packageOrSalary ?: "")
      put("quote", quote)
      put("imageResName", imageResName)
      put("isFeatured", isFeatured)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): PlacementStory {
      val salary = json.optString("packageOrSalary", "")
      return PlacementStory(
        id = json.optString("id", System.currentTimeMillis().toString()),
        studentName = json.optString("studentName", "Graduate"),
        courseCompleted = json.optString("courseCompleted", "ADCA"),
        jobTitle = json.optString("jobTitle", "Professional"),
        companyName = json.optString("companyName", "Commercial Enterprise"),
        location = json.optString("location", "Nepal"),
        placementYear = json.optString("placementYear", "2025"),
        packageOrSalary = if (salary.isBlank()) null else salary,
        quote = json.optString("quote", ""),
        imageResName = json.optString("imageResName", "img_graduate_1"),
        isFeatured = json.optBoolean("isFeatured", true)
      )
    }
  }
}

data class InstituteNotice(
  val isEnabled: Boolean = true,
  val badge: String = "Admissions 2026–2027",
  val message: String = "Admissions open for new morning & evening batches! Free Bag & ID Card with every enrollment.",
  val urgency: String = "Normal" // "Normal", "Important", "Urgent"
) {
  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("isEnabled", isEnabled)
      put("badge", badge)
      put("message", message)
      put("urgency", urgency)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): InstituteNotice {
      return InstituteNotice(
        isEnabled = json.optBoolean("isEnabled", true),
        badge = json.optString("badge", "Admissions 2026–2027"),
        message = json.optString("message", "Admissions open for new morning & evening batches! Free Bag & ID Card with every enrollment."),
        urgency = json.optString("urgency", "Normal")
      )
    }
  }
}

data class StudentInquiry(
  val id: String,
  val studentName: String,
  val phoneNumber: String,
  val courseInterested: String,
  val status: String = "Pending", // "Pending", "Followed Up", "Enrolled", "Closed"
  val dateAdded: String,
  val notes: String = ""
) {
  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("id", id)
      put("studentName", studentName)
      put("phoneNumber", phoneNumber)
      put("courseInterested", courseInterested)
      put("status", status)
      put("dateAdded", dateAdded)
      put("notes", notes)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): StudentInquiry {
      return StudentInquiry(
        id = json.optString("id", System.currentTimeMillis().toString()),
        studentName = json.optString("studentName", "Student"),
        phoneNumber = json.optString("phoneNumber", ""),
        courseInterested = json.optString("courseInterested", "ADCA"),
        status = json.optString("status", "Pending"),
        dateAdded = json.optString("dateAdded", "Today"),
        notes = json.optString("notes", "")
      )
    }
  }
}

data class InstituteInfo(
  val name: String = "NEDIAN CONNECT INSTITUTE PVT. LTD.",
  val phone: String = "+977 9705508838",
  val phoneRaw: String = "+9779705508838",
  val location: String = "Mayadevi R.M. - 4, Chakarchauda, Kapilvastu, Nepal",
  val email: String = "info@nedianconnect.edu.np",
  val officeHours: String = "Sun – Fri: 6:00 AM – 6:00 PM (Saturday Closed)",
  val offerHeadline: String = "Bag & ID Card Free on Every Admission!",
  val admissionBatchTag: String = "Admissions Open for New Morning & Evening Batches 2026–2027",
  val tagline: String = "AI, Skills, Media • Kapilvastu's Premier Practical IT Institute"
) {
  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("name", name)
      put("phone", phone)
      put("phoneRaw", phoneRaw)
      put("location", location)
      put("email", email)
      put("officeHours", officeHours)
      put("offerHeadline", offerHeadline)
      put("admissionBatchTag", admissionBatchTag)
      put("tagline", tagline)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): InstituteInfo {
      return InstituteInfo(
        name = json.optString("name", "NEDIAN CONNECT INSTITUTE PVT. LTD."),
        phone = json.optString("phone", "+977 9705508838"),
        phoneRaw = json.optString("phoneRaw", "+9779705508838"),
        location = json.optString("location", "Mayadevi R.M. - 4, Chakarchauda, Kapilvastu, Nepal"),
        email = json.optString("email", "info@nedianconnect.edu.np"),
        officeHours = json.optString("officeHours", "Sun – Fri: 6:00 AM – 6:00 PM (Saturday Closed)"),
        offerHeadline = json.optString("offerHeadline", "Bag & ID Card Free on Every Admission!"),
        admissionBatchTag = json.optString("admissionBatchTag", "Admissions Open for New Morning & Evening Batches 2026–2027"),
        tagline = json.optString("tagline", "AI, Skills, Media • Kapilvastu's Premier Practical IT Institute")
      )
    }
  }
}

data class ThemeSettings(
  val paletteName: String = "ELECTRIC_BLUE", // ELECTRIC_BLUE, ROYAL_NAVY, CYBER_TEAL, SUNSET_AMBER
  val themeMode: String = "AUTO" // AUTO, LIGHT, DARK
) {
  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("paletteName", paletteName)
      put("themeMode", themeMode)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): ThemeSettings {
      return ThemeSettings(
        paletteName = json.optString("paletteName", "ELECTRIC_BLUE"),
        themeMode = json.optString("themeMode", "AUTO")
      )
    }
  }
}

class InstituteRepository(context: Context) {
  private val prefs: SharedPreferences =
    context.applicationContext.getSharedPreferences("nedian_connect_prefs", Context.MODE_PRIVATE)

  private val _placementStories = MutableStateFlow<List<PlacementStory>>(emptyList())
  val placementStories: StateFlow<List<PlacementStory>> = _placementStories.asStateFlow()

  private val _instituteNotice = MutableStateFlow(InstituteNotice())
  val instituteNotice: StateFlow<InstituteNotice> = _instituteNotice.asStateFlow()

  private val _studentInquiries = MutableStateFlow<List<StudentInquiry>>(emptyList())
  val studentInquiries: StateFlow<List<StudentInquiry>> = _studentInquiries.asStateFlow()

  private val _courses = MutableStateFlow<List<Course>>(emptyList())
  val courses: StateFlow<List<Course>> = _courses.asStateFlow()

  private val _instituteInfo = MutableStateFlow(InstituteInfo())
  val instituteInfo: StateFlow<InstituteInfo> = _instituteInfo.asStateFlow()

  private val _themeSettings = MutableStateFlow(ThemeSettings())
  val themeSettings: StateFlow<ThemeSettings> = _themeSettings.asStateFlow()

  private val _adminPin = MutableStateFlow(DEFAULT_PIN)
  val adminPin: StateFlow<String> = _adminPin.asStateFlow()

  init {
    loadData()
  }

  private fun loadData() {
    // 1. Load Admin PIN
    val savedPin = prefs.getString(KEY_ADMIN_PIN, DEFAULT_PIN) ?: DEFAULT_PIN
    _adminPin.value = savedPin

    // 2. Load Notice
    val noticeJsonStr = prefs.getString(KEY_NOTICE, null)
    if (noticeJsonStr != null) {
      try {
        _instituteNotice.value = InstituteNotice.fromJsonObject(JSONObject(noticeJsonStr))
      } catch (e: Exception) {
        _instituteNotice.value = defaultNotice()
      }
    } else {
      _instituteNotice.value = defaultNotice()
    }

    // 3. Load Placements
    val placementsJsonStr = prefs.getString(KEY_PLACEMENTS, null)
    if (placementsJsonStr != null) {
      try {
        val array = JSONArray(placementsJsonStr)
        val list = mutableListOf<PlacementStory>()
        for (i in 0 until array.length()) {
          list.add(PlacementStory.fromJsonObject(array.getJSONObject(i)))
        }
        _placementStories.value = if (list.isNotEmpty()) list else defaultPlacements()
      } catch (e: Exception) {
        _placementStories.value = defaultPlacements()
      }
    } else {
      _placementStories.value = defaultPlacements()
      savePlacementsInternal(_placementStories.value)
    }

    // 4. Load Inquiries
    val inquiriesJsonStr = prefs.getString(KEY_INQUIRIES, null)
    if (inquiriesJsonStr != null) {
      try {
        val array = JSONArray(inquiriesJsonStr)
        val list = mutableListOf<StudentInquiry>()
        for (i in 0 until array.length()) {
          list.add(StudentInquiry.fromJsonObject(array.getJSONObject(i)))
        }
        _studentInquiries.value = list
      } catch (e: Exception) {
        _studentInquiries.value = defaultInquiries()
      }
    } else {
      _studentInquiries.value = defaultInquiries()
      saveInquiriesInternal(_studentInquiries.value)
    }

    // 5. Load Courses
    val coursesJsonStr = prefs.getString(KEY_COURSES, null)
    if (coursesJsonStr != null) {
      try {
        val array = JSONArray(coursesJsonStr)
        val list = mutableListOf<Course>()
        for (i in 0 until array.length()) {
          list.add(Course.fromJsonObject(array.getJSONObject(i)))
        }
        _courses.value = if (list.isNotEmpty()) list else CourseRepository.allCourses
      } catch (e: Exception) {
        _courses.value = CourseRepository.allCourses
      }
    } else {
      _courses.value = CourseRepository.allCourses
      saveCoursesInternal(_courses.value)
    }

    // 6. Load Institute Info
    val infoJsonStr = prefs.getString(KEY_INFO, null)
    if (infoJsonStr != null) {
      try {
        _instituteInfo.value = InstituteInfo.fromJsonObject(JSONObject(infoJsonStr))
      } catch (e: Exception) {
        _instituteInfo.value = InstituteInfo()
      }
    } else {
      _instituteInfo.value = InstituteInfo()
    }

    // 7. Load Theme Settings
    val themeJsonStr = prefs.getString(KEY_THEME, null)
    if (themeJsonStr != null) {
      try {
        _themeSettings.value = ThemeSettings.fromJsonObject(JSONObject(themeJsonStr))
      } catch (e: Exception) {
        _themeSettings.value = ThemeSettings()
      }
    } else {
      _themeSettings.value = ThemeSettings()
    }
  }

  // --- PLACEMENT STORY ACTIONS ---
  fun addPlacementStory(story: PlacementStory) {
    val updated = listOf(story) + _placementStories.value
    _placementStories.value = updated
    savePlacementsInternal(updated)
  }

  fun updatePlacementStory(story: PlacementStory) {
    val updated = _placementStories.value.map { if (it.id == story.id) story else it }
    _placementStories.value = updated
    savePlacementsInternal(updated)
  }

  fun deletePlacementStory(id: String) {
    val updated = _placementStories.value.filterNot { it.id == id }
    _placementStories.value = updated
    savePlacementsInternal(updated)
  }

  fun toggleFeatured(id: String) {
    val updated = _placementStories.value.map {
      if (it.id == id) it.copy(isFeatured = !it.isFeatured) else it
    }
    _placementStories.value = updated
    savePlacementsInternal(updated)
  }

  private fun savePlacementsInternal(list: List<PlacementStory>) {
    val array = JSONArray()
    list.forEach { array.put(it.toJsonObject()) }
    prefs.edit().putString(KEY_PLACEMENTS, array.toString()).apply()
  }

  // --- NOTICE ACTIONS ---
  fun updateNotice(notice: InstituteNotice) {
    _instituteNotice.value = notice
    prefs.edit().putString(KEY_NOTICE, notice.toJsonObject().toString()).apply()
  }

  // --- INQUIRY ACTIONS ---
  fun addInquiry(inquiry: StudentInquiry) {
    val updated = listOf(inquiry) + _studentInquiries.value
    _studentInquiries.value = updated
    saveInquiriesInternal(updated)
  }

  fun updateInquiryStatus(id: String, newStatus: String) {
    val updated = _studentInquiries.value.map {
      if (it.id == id) it.copy(status = newStatus) else it
    }
    _studentInquiries.value = updated
    saveInquiriesInternal(updated)
  }

  fun deleteInquiry(id: String) {
    val updated = _studentInquiries.value.filterNot { it.id == id }
    _studentInquiries.value = updated
    saveInquiriesInternal(updated)
  }

  private fun saveInquiriesInternal(list: List<StudentInquiry>) {
    val array = JSONArray()
    list.forEach { array.put(it.toJsonObject()) }
    prefs.edit().putString(KEY_INQUIRIES, array.toString()).apply()
  }

  // --- COURSES ACTIONS ---
  fun addCourse(course: Course) {
    val updated = _courses.value + listOf(course)
    _courses.value = updated
    saveCoursesInternal(updated)
  }

  fun updateCourse(course: Course) {
    val updated = _courses.value.map { if (it.id == course.id) course else it }
    _courses.value = updated
    saveCoursesInternal(updated)
  }

  fun deleteCourse(id: String) {
    val updated = _courses.value.filterNot { it.id == id }
    _courses.value = updated
    saveCoursesInternal(updated)
  }

  fun resetCourses() {
    _courses.value = CourseRepository.allCourses
    saveCoursesInternal(_courses.value)
  }

  private fun saveCoursesInternal(list: List<Course>) {
    val array = JSONArray()
    list.forEach { array.put(it.toJsonObject()) }
    prefs.edit().putString(KEY_COURSES, array.toString()).apply()
  }

  // --- INSTITUTE INFO ACTIONS ---
  fun updateInstituteInfo(info: InstituteInfo) {
    _instituteInfo.value = info
    prefs.edit().putString(KEY_INFO, info.toJsonObject().toString()).apply()
  }

  fun resetInstituteInfo() {
    _instituteInfo.value = InstituteInfo()
    prefs.edit().remove(KEY_INFO).apply()
  }

  // --- THEME SETTINGS ACTIONS ---
  fun updateThemeSettings(settings: ThemeSettings) {
    _themeSettings.value = settings
    prefs.edit().putString(KEY_THEME, settings.toJsonObject().toString()).apply()
  }

  // --- ADMIN PIN ---
  fun verifyPin(pin: String): Boolean {
    return pin == _adminPin.value
  }

  fun updatePin(newPin: String): Boolean {
    if (newPin.length >= 4) {
      _adminPin.value = newPin
      prefs.edit().putString(KEY_ADMIN_PIN, newPin).apply()
      return true
    }
    return false
  }

  fun resetToDefaults() {
    _placementStories.value = defaultPlacements()
    _instituteNotice.value = defaultNotice()
    _studentInquiries.value = defaultInquiries()
    _courses.value = CourseRepository.allCourses
    _instituteInfo.value = InstituteInfo()
    _themeSettings.value = ThemeSettings()
    _adminPin.value = DEFAULT_PIN
    prefs.edit().clear().apply()
    savePlacementsInternal(_placementStories.value)
    saveInquiriesInternal(_studentInquiries.value)
    saveCoursesInternal(_courses.value)
    updateNotice(_instituteNotice.value)
  }

  companion object {
    const val DEFAULT_PIN = "@FZ@LKH@N1100"
    private const val KEY_ADMIN_PIN = "admin_pin"
    private const val KEY_NOTICE = "institute_notice"
    private const val KEY_PLACEMENTS = "placement_stories"
    private const val KEY_INQUIRIES = "student_inquiries"
    private const val KEY_COURSES = "institute_courses"
    private const val KEY_INFO = "institute_info"
    private const val KEY_THEME = "institute_theme"

    private fun defaultNotice(): InstituteNotice {
      return InstituteNotice(
        isEnabled = true,
        badge = "Admissions 2026–2027",
        message = "Admissions open for new morning & evening batches! Free Bag & ID Card with every enrollment.",
        urgency = "Important"
      )
    }

    private fun defaultPlacements(): List<PlacementStory> {
      return listOf(
        PlacementStory(
          id = "ps_1",
          studentName = "Ramesh Chaudhary",
          courseCompleted = "ADCA (12 Months)",
          jobTitle = "Junior Accountant & MIS Operator",
          companyName = "Sunrise Multipurpose Co-operative Ltd.",
          location = "Biratnagar, Nepal",
          placementYear = "2025",
          packageOrSalary = "NPR 28,000 / month",
          quote = "The 1-year ADCA course gave me complete mastery over advanced Excel, database entries, and billing. I was hired by Sunrise Co-operative immediately after course completion!",
          imageResName = "img_graduate_1",
          isFeatured = true
        ),
        PlacementStory(
          id = "ps_2",
          studentName = "Sunita Yadav",
          courseCompleted = "Accounting – Tally Prime with GST (3 Months)",
          jobTitle = "Billing & Inventory Incharge",
          companyName = "Shree Krishna Trading & Distribution",
          location = "Chakarchauda, Nepal",
          placementYear = "2025",
          packageOrSalary = "NPR 24,000 / month",
          quote = "Practical inventory voucher entry, GST calculation, and ledger reconciliation taught at Nedian Connect gave me the exact skills local business enterprises need.",
          imageResName = "img_graduate_2",
          isFeatured = true
        ),
        PlacementStory(
          id = "ps_3",
          studentName = "Mohammad Arman",
          courseCompleted = "DTP – Diploma in Desktop Publishing (5 Months)",
          jobTitle = "Graphic & Print Media Designer",
          companyName = "Creative Vision Print & Offset Hub",
          location = "Janakpur, Nepal",
          placementYear = "2024",
          packageOrSalary = "NPR 25,000 / month",
          quote = "From flex banner designs to commercial typography and page layouts, Nedian Connect's DTP course turned my artistic interest into a stable creative career.",
          imageResName = "img_graduate_3",
          isFeatured = true
        ),
        PlacementStory(
          id = "ps_4",
          studentName = "Puja Kumari Shah",
          courseCompleted = "DCA – Diploma in Computer Application (6 Months)",
          jobTitle = "Computer Operator & Office Assistant",
          companyName = "Rural Municipality (Gaupalika) Ward Office",
          location = "Sunsari, Nepal",
          placementYear = "2025",
          packageOrSalary = "NPR 26,500 / month",
          quote = "Learning fast Nepali Unicode typing and official documentation formats helped me clear the public operator exam. The guidance from the institute faculty was invaluable.",
          imageResName = "img_graduate_2",
          isFeatured = true
        ),
        PlacementStory(
          id = "ps_5",
          studentName = "Bikash Mandal",
          courseCompleted = "ADCA – Advanced Diploma in Computer Application (12 Months)",
          jobTitle = "Computer Teacher & Lab Instructor",
          companyName = "Gyanodaya Secondary School",
          location = "Morang, Nepal",
          placementYear = "2024",
          packageOrSalary = "NPR 27,000 / month",
          quote = "The hardware, networking, and software teaching methodology in ADCA provided me total confidence to manage high school computer labs. Proud to inspire new students!",
          imageResName = "img_graduate_1",
          isFeatured = false
        ),
        PlacementStory(
          id = "ps_6",
          studentName = "Anjali Thapa",
          courseCompleted = "CCA – Certificate in Computer Accounting (5 Months)",
          jobTitle = "Accounts Assistant",
          companyName = "Prime Commercial Logistics",
          location = "Biratnagar, Nepal",
          placementYear = "2026",
          packageOrSalary = "NPR 22,000 / month",
          quote = "Hands-on financial accounting and digital spreadsheets made me job-ready in just 5 months. The flexible morning batches allowed me to balance my higher secondary college studies.",
          imageResName = "img_graduate_2",
          isFeatured = false
        )
      )
    }

    private fun defaultInquiries(): List<StudentInquiry> {
      return listOf(
        StudentInquiry(
          id = "inq_1",
          studentName = "Rajesh Sah",
          phoneNumber = "+977 9812345678",
          courseInterested = "ADCA (12 Months)",
          status = "Followed Up",
          dateAdded = "Yesterday",
          notes = "Interested in morning batch (7:00 AM). Inquired about fee discount."
        ),
        StudentInquiry(
          id = "inq_2",
          studentName = "Priyanka Mishra",
          phoneNumber = "+977 9823456789",
          courseInterested = "Tally Prime & GST (3 Months)",
          status = "Enrolled",
          dateAdded = "2 days ago",
          notes = "Enrolled for evening batch. Free bag and ID card issued."
        ),
        StudentInquiry(
          id = "inq_3",
          studentName = "Deepak Kumar",
          phoneNumber = "+977 9801234567",
          courseInterested = "DTP (5 Months)",
          status = "Pending",
          dateAdded = "Today",
          notes = "Walked in with a friend. Requested demo class schedule."
        )
      )
    }
  }
}

@Composable
fun PlacementImageView(
  imageResName: String,
  contentDescription: String?,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop
) {
  if (imageResName.startsWith("content://") || imageResName.startsWith("file://")) {
    coil.compose.AsyncImage(
      model = android.net.Uri.parse(imageResName),
      contentDescription = contentDescription,
      modifier = modifier,
      contentScale = contentScale
    )
  } else {
    val resId = when (imageResName) {
      "img_graduate_2" -> R.drawable.img_graduate_2
      "img_graduate_3" -> R.drawable.img_graduate_3
      else -> R.drawable.img_graduate_1
    }
    androidx.compose.foundation.Image(
      painter = androidx.compose.ui.res.painterResource(id = resId),
      contentDescription = contentDescription,
      modifier = modifier,
      contentScale = contentScale
    )
  }
}
