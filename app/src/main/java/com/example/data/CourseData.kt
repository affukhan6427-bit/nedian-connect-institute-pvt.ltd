package com.example.data

import org.json.JSONObject

data class Course(
  val id: String,
  val name: String,
  val fullName: String,
  val category: String,
  val duration: String,
  val admissionFee: Int? = null,
  val monthlyFee: Int? = null,
  val totalMonthlyFees: Int? = null,
  val discount: Int? = null,
  val finalTotalFee: Int? = null,
  val offer: String? = null,
  val offerTag: String? = null,
  val hasConfirmedFee: Boolean = true,
  val description: String = ""
) {
  fun toJsonObject(): JSONObject {
    return JSONObject().apply {
      put("id", id)
      put("name", name)
      put("fullName", fullName)
      put("category", category)
      put("duration", duration)
      if (admissionFee != null) put("admissionFee", admissionFee)
      if (monthlyFee != null) put("monthlyFee", monthlyFee)
      if (totalMonthlyFees != null) put("totalMonthlyFees", totalMonthlyFees)
      if (discount != null) put("discount", discount)
      if (finalTotalFee != null) put("finalTotalFee", finalTotalFee)
      put("offer", offer ?: "")
      put("offerTag", offerTag ?: "")
      put("hasConfirmedFee", hasConfirmedFee)
      put("description", description)
    }
  }

  companion object {
    fun fromJsonObject(json: JSONObject): Course {
      val adm = if (json.has("admissionFee") && !json.isNull("admissionFee")) json.optInt("admissionFee") else null
      val mon = if (json.has("monthlyFee") && !json.isNull("monthlyFee")) json.optInt("monthlyFee") else null
      val tot = if (json.has("totalMonthlyFees") && !json.isNull("totalMonthlyFees")) json.optInt("totalMonthlyFees") else null
      val dis = if (json.has("discount") && !json.isNull("discount")) json.optInt("discount") else null
      val fin = if (json.has("finalTotalFee") && !json.isNull("finalTotalFee")) json.optInt("finalTotalFee") else null
      val off = json.optString("offer", "")
      val offTag = json.optString("offerTag", "")

      return Course(
        id = json.optString("id", System.currentTimeMillis().toString()),
        name = json.optString("name", "Course"),
        fullName = json.optString("fullName", "Course Title"),
        category = json.optString("category", "Diploma"),
        duration = json.optString("duration", "6 Months"),
        admissionFee = adm,
        monthlyFee = mon,
        totalMonthlyFees = tot,
        discount = dis,
        finalTotalFee = fin,
        offer = if (off.isBlank()) null else off,
        offerTag = if (offTag.isBlank()) null else offTag,
        hasConfirmedFee = json.optBoolean("hasConfirmedFee", true),
        description = json.optString("description", "")
      )
    }
  }
}

object CourseRepository {
  const val INSTITUTE_NAME = "NEDIAN CONNECT INSTITUTE PVT. LTD."
  const val INSTITUTE_PHONE = "+977 9705508838"
  const val INSTITUTE_PHONE_RAW = "+9779705508838"
  const val INSTITUTE_HELPLINE_PHONE = "+977 9807508838"
  const val INSTITUTE_LOCATION = "Chakarchauda, Nepal"

  // Exact 8 courses from the Fee-Chart image provided by user
  val feeChartCourses = listOf(
    Course(
      id = "adca",
      name = "ADCA",
      fullName = "Advanced Diploma in Computer Application",
      category = "Diploma",
      duration = "12 Months",
      admissionFee = 800,
      monthlyFee = 825,
      totalMonthlyFees = 9900,
      discount = 900,
      finalTotalFee = 9000,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Comprehensive 1-year advanced program covering fundamental computer operations, office automation, database management, desktop publishing, programming fundamentals, and accounting software."
    ),
    Course(
      id = "dca",
      name = "DCA",
      fullName = "Diploma in Computer Application",
      category = "Diploma",
      duration = "6 Months",
      admissionFee = 800,
      monthlyFee = 825,
      totalMonthlyFees = 4950,
      discount = 450,
      finalTotalFee = 4500,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Practical 6-month diploma covering operating systems, MS Office Suite (Word, Excel, PowerPoint), internet technologies, and basic digital productivity tools."
    ),
    Course(
      id = "ccc",
      name = "CCC",
      fullName = "Course on Computer Concepts",
      category = "Certificate",
      duration = "3 Months",
      admissionFee = 800,
      monthlyFee = 1000,
      totalMonthlyFees = 3000,
      discount = 0,
      finalTotalFee = 3000,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Foundational 3-month computer course designed to equip students with essential digital literacy, operating system navigation, web browsing, email communication, and basic documents."
    ),
    Course(
      id = "cca",
      name = "CCA",
      fullName = "Certificate in Computer Accounting",
      category = "Accounting",
      duration = "5 Months",
      admissionFee = 800,
      monthlyFee = 825,
      totalMonthlyFees = 4125,
      discount = 625,
      finalTotalFee = 3500,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Specialized computerized accounting certificate covering financial accounting principles, ledger creation, vouchers, tax concepts, and automated accounting workflows."
    ),
    Course(
      id = "cfa",
      name = "CFA",
      fullName = "Computer Financial Accounting",
      category = "Accounting",
      duration = "3 Months",
      admissionFee = 800,
      monthlyFee = 1000,
      totalMonthlyFees = 3000,
      discount = 200,
      finalTotalFee = 2800,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Intensive 3-month financial accounting training focusing on business record keeping, balance sheets, profit & loss statements, and digital bookkeeping practices."
    ),
    Course(
      id = "dtp",
      name = "DTP",
      fullName = "Diploma in Desktop Publishing",
      category = "Design",
      duration = "5 Months",
      admissionFee = 800,
      monthlyFee = 825,
      totalMonthlyFees = 4125,
      discount = 625,
      finalTotalFee = 3500,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Professional graphic and print design training including document layout, image editing, banner/brochure creation, typography, and commercial print preparation."
    ),
    Course(
      id = "accounting_tally",
      name = "Accounting",
      fullName = "Tally Prime & Tally ERP 9 with GST",
      category = "Accounting",
      duration = "3 Months",
      admissionFee = 800,
      monthlyFee = 1000,
      totalMonthlyFees = 3000,
      discount = 0,
      finalTotalFee = 3000,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "In-depth practical training on industry-standard accounting software: Tally Prime & Tally ERP 9, inventory management, taxation, GST computation, billing, and reporting."
    ),
    Course(
      id = "libreoffice",
      name = "LibreOffice",
      fullName = "LibreOffice Suite (Writer, Calc, Impress)",
      category = "Office",
      duration = "5 Months",
      admissionFee = 800,
      monthlyFee = 825,
      totalMonthlyFees = 4125,
      discount = 625,
      finalTotalFee = 3500,
      offer = "Bag & ID Card Free",
      offerTag = "Limited Fee Offer 2026–2027",
      hasConfirmedFee = true,
      description = "Complete open-source office suite mastery including LibreOffice Writer for documents, Calc for spreadsheets, and Impress for multimedia presentations."
    )
  )

  // Other Courses as explicitly specified: do NOT invent fees or duration
  val otherCourses = listOf(
    Course(
      id = "basic_computer",
      name = "Basic Computer",
      fullName = "Basic Computer Course",
      category = "Foundation",
      duration = "Contact Institute",
      hasConfirmedFee = false,
      offer = null,
      offerTag = "Contact Institute for Fee",
      description = "Fundamentals of personal computers, mouse/keyboard skills, Windows navigation, folder management, and introductory digital tools."
    ),
    Course(
      id = "advanced_computer",
      name = "Advanced Computer",
      fullName = "Advanced Computer Training",
      category = "Advanced",
      duration = "Contact Institute",
      hasConfirmedFee = false,
      offer = null,
      offerTag = "Contact Institute for Fee",
      description = "Higher level computer proficiency, advanced productivity applications, troubleshooting, and digital systems management."
    ),
    Course(
      id = "english_speaking",
      name = "English Speaking",
      fullName = "English Speaking & Communication Skills",
      category = "Languages",
      duration = "Contact Institute",
      hasConfirmedFee = false,
      offer = null,
      offerTag = "Contact Institute for Fee",
      description = "Spoken English fluency, everyday conversation practice, pronunciation, vocabulary enrichment, and confidence building for students and job seekers."
    ),
    Course(
      id = "arabic_speaking",
      name = "Arabic Speaking",
      fullName = "Arabic Speaking Course",
      category = "Languages",
      duration = "Contact Institute",
      hasConfirmedFee = false,
      offer = null,
      offerTag = "Contact Institute for Fee",
      description = "Practical Arabic conversation skills, foundational grammar, colloquial dialect understanding, and essential vocabulary for career or travel opportunities."
    ),
    Course(
      id = "english_to_arabic_translation",
      name = "English to Arabic Translation",
      fullName = "English to Arabic Translation Course",
      category = "Languages",
      duration = "Contact Institute",
      hasConfirmedFee = false,
      offer = null,
      offerTag = "Contact Institute for Fee",
      description = "Bilingual translation training between English and Arabic, specialized commercial, legal, and educational text translation techniques."
    )
  )

  val allCourses = feeChartCourses + otherCourses

  fun getCourseById(id: String): Course? = allCourses.find { it.id.equals(id, ignoreCase = true) }

  val categories = listOf("All", "Diploma", "Accounting", "Certificate", "Design", "Office", "Languages")
}
