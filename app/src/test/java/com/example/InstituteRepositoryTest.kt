package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.InstituteNotice
import com.example.data.InstituteRepository
import com.example.data.PlacementStory
import com.example.data.StudentInquiry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class InstituteRepositoryTest {

  private lateinit var context: Context
  private lateinit var repository: InstituteRepository

  @Before
  fun setUp() {
    context = ApplicationProvider.getApplicationContext()
    // Clear preferences
    context.getSharedPreferences("nedian_connect_prefs", Context.MODE_PRIVATE).edit().clear().commit()
    repository = InstituteRepository(context)
  }

  @Test
  fun testDefaultPlacementStoriesExist() {
    val stories = repository.placementStories.value
    assertTrue("Should have default placement stories seeded", stories.isNotEmpty())
    assertTrue("Should have at least 5 default stories", stories.size >= 5)
  }

  @Test
  fun testAdminPinVerification() {
    // Default password is @FZ@LKH@N1100
    assertTrue("Default password should verify", repository.verifyPin("@FZ@LKH@N1100"))
    assertFalse("Incorrect password should fail", repository.verifyPin("0000"))

    // Change password
    val updated = repository.updatePin("NewPass@123")
    assertTrue("Updating valid password should succeed", updated)
    assertTrue("New password should verify", repository.verifyPin("NewPass@123"))
    assertFalse("Old password should fail", repository.verifyPin("@FZ@LKH@N1100"))
  }

  @Test
  fun testAddAndDeletePlacementStory() {
    val initialCount = repository.placementStories.value.size
    val newStory = PlacementStory(
      id = "test_story_1",
      studentName = "Rohan Sharma",
      courseCompleted = "ADCA (12 Months)",
      jobTitle = "IT Assistant",
      companyName = "Everest Tech Pvt Ltd",
      location = "Biratnagar",
      placementYear = "2026",
      packageOrSalary = "NPR 30,000",
      quote = "Great learning experience.",
      imageResName = "img_graduate_1",
      isFeatured = true
    )

    repository.addPlacementStory(newStory)
    val afterAdd = repository.placementStories.value
    assertEquals(initialCount + 1, afterAdd.size)
    assertNotNull(afterAdd.find { it.id == "test_story_1" })

    // Delete
    repository.deletePlacementStory("test_story_1")
    val afterDelete = repository.placementStories.value
    assertEquals(initialCount, afterDelete.size)
  }

  @Test
  fun testNoticeUpdate() {
    val updatedNotice = InstituteNotice(
      isEnabled = true,
      badge = "Urgent Notice",
      message = "Admissions closing this Friday for new morning batch.",
      urgency = "Urgent"
    )

    repository.updateNotice(updatedNotice)
    val currentNotice = repository.instituteNotice.value
    assertEquals("Urgent Notice", currentNotice.badge)
    assertEquals("Urgent", currentNotice.urgency)
    assertTrue(currentNotice.isEnabled)
  }

  @Test
  fun testInquiryTracking() {
    val inquiry = StudentInquiry(
      id = "inq_test_1",
      studentName = "Bikash Yadav",
      phoneNumber = "+977 9801234567",
      courseInterested = "Tally Prime with GST",
      status = "Pending",
      dateAdded = "Today",
      notes = "Interested in evening shift"
    )

    repository.addInquiry(inquiry)
    val inquiries = repository.studentInquiries.value
    val found = inquiries.find { it.id == "inq_test_1" }
    assertNotNull(found)

    // Update status
    repository.updateInquiryStatus("inq_test_1", "Enrolled")
    val updatedInquiry = repository.studentInquiries.value.find { it.id == "inq_test_1" }
    assertEquals("Enrolled", updatedInquiry?.status)
  }
}
