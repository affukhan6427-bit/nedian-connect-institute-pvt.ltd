package com.example

import com.example.data.CourseRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class CourseDataUnitTest {

  @Test
  fun testExactFeeChartDataIntegrity() {
    val adca = CourseRepository.getCourseById("adca")
    assertNotNull(adca)
    assertEquals("12 Months", adca!!.duration)
    assertEquals(800, adca.admissionFee)
    assertEquals(825, adca.monthlyFee)
    assertEquals(9900, adca.totalMonthlyFees)
    assertEquals(900, adca.discount)
    assertEquals(9000, adca.finalTotalFee)
    assertEquals("Bag & ID Card Free", adca.offer)

    val dca = CourseRepository.getCourseById("dca")
    assertNotNull(dca)
    assertEquals("6 Months", dca!!.duration)
    assertEquals(800, dca.admissionFee)
    assertEquals(825, dca.monthlyFee)
    assertEquals(4950, dca.totalMonthlyFees)
    assertEquals(450, dca.discount)
    assertEquals(4500, dca.finalTotalFee)
    assertEquals("Bag & ID Card Free", dca.offer)

    val ccc = CourseRepository.getCourseById("ccc")
    assertNotNull(ccc)
    assertEquals("3 Months", ccc!!.duration)
    assertEquals(800, ccc.admissionFee)
    assertEquals(1000, ccc.monthlyFee)
    assertEquals(3000, ccc.totalMonthlyFees)
    assertEquals(0, ccc.discount)
    assertEquals(3000, ccc.finalTotalFee)
    assertEquals("Bag & ID Card Free", ccc.offer)

    val cca = CourseRepository.getCourseById("cca")
    assertNotNull(cca)
    assertEquals("5 Months", cca!!.duration)
    assertEquals(800, cca.admissionFee)
    assertEquals(825, cca.monthlyFee)
    assertEquals(4125, cca.totalMonthlyFees)
    assertEquals(625, cca.discount)
    assertEquals(3500, cca.finalTotalFee)

    val cfa = CourseRepository.getCourseById("cfa")
    assertNotNull(cfa)
    assertEquals("3 Months", cfa!!.duration)
    assertEquals(800, cfa.admissionFee)
    assertEquals(1000, cfa.monthlyFee)
    assertEquals(3000, cfa.totalMonthlyFees)
    assertEquals(200, cfa.discount)
    assertEquals(2800, cfa.finalTotalFee)

    val dtp = CourseRepository.getCourseById("dtp")
    assertNotNull(dtp)
    assertEquals("5 Months", dtp!!.duration)
    assertEquals(800, dtp.admissionFee)
    assertEquals(825, dtp.monthlyFee)
    assertEquals(4125, dtp.totalMonthlyFees)
    assertEquals(625, dtp.discount)
    assertEquals(3500, dtp.finalTotalFee)

    val tally = CourseRepository.getCourseById("accounting_tally")
    assertNotNull(tally)
    assertEquals("3 Months", tally!!.duration)
    assertEquals(800, tally.admissionFee)
    assertEquals(1000, tally.monthlyFee)
    assertEquals(3000, tally.totalMonthlyFees)
    assertEquals(0, tally.discount)
    assertEquals(3000, tally.finalTotalFee)

    val libreOffice = CourseRepository.getCourseById("libreoffice")
    assertNotNull(libreOffice)
    assertEquals("5 Months", libreOffice!!.duration)
    assertEquals(800, libreOffice.admissionFee)
    assertEquals(825, libreOffice.monthlyFee)
    assertEquals(4125, libreOffice.totalMonthlyFees)
    assertEquals(625, libreOffice.discount)
    assertEquals(3500, libreOffice.finalTotalFee)
  }
}
