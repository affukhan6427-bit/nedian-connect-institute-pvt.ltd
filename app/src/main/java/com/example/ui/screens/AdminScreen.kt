package com.example.ui.screens

import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.Course
import com.example.data.InstituteInfo
import com.example.data.InstituteNotice
import com.example.data.InstituteRepository
import com.example.data.PlacementStory
import com.example.data.PlacementImageView
import com.example.data.StudentInquiry
import com.example.data.ThemeSettings

@Composable
fun AdminScreen(
  repository: InstituteRepository,
  placementStories: List<PlacementStory>,
  instituteNotice: InstituteNotice,
  studentInquiries: List<StudentInquiry>,
  courses: List<Course>,
  instituteInfo: InstituteInfo,
  themeSettings: ThemeSettings,
  onCallStudent: (String) -> Unit,
  onWhatsAppStudent: (String, String) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var isAuthenticated by remember { mutableStateOf(false) }
  var pinInput by remember { mutableStateOf("") }
  var pinError by remember { mutableStateOf<String?>(null) }
  var isPinVisible by remember { mutableStateOf(false) }
  var selectedTabIndex by remember { mutableIntStateOf(0) }

  // Dialog states
  var editingStory by remember { mutableStateOf<PlacementStory?>(null) }
  var isAddStoryDialogOpen by remember { mutableStateOf(false) }
  var editingCourse by remember { mutableStateOf<Course?>(null) }
  var isAddCourseDialogOpen by remember { mutableStateOf(false) }
  var isAddInquiryDialogOpen by remember { mutableStateOf(false) }
  var showResetConfirmDialog by remember { mutableStateOf(false) }

  if (!isAuthenticated) {
    // Password Authentication Gate
    Box(
      modifier = modifier
        .fillMaxSize()
        .padding(24.dp)
        .testTag("admin_login_screen"),
      contentAlignment = Alignment.Center
    ) {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Column(
          modifier = Modifier.padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.size(64.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.AdminPanelSettings,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(36.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "Administrator Portal",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
          )

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "NEDIAN CONNECT INSTITUTE PVT. LTD.",
            style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 0.5.sp),
            color = MaterialTheme.colorScheme.primary
          )

          Text(
            text = "Enter your secure password to manage placements, notices, and inquiries.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )

          Spacer(modifier = Modifier.height(20.dp))

          OutlinedTextField(
            value = pinInput,
            onValueChange = {
              if (it.length <= 30) {
                pinInput = it
                pinError = null
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("admin_pin_input"),
            label = { Text("Admin Password") },
            placeholder = { Text("Enter secure password") },
            visualTransformation = if (isPinVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            isError = pinError != null,
            trailingIcon = {
              IconButton(onClick = { isPinVisible = !isPinVisible }) {
                Icon(
                  imageVector = if (isPinVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                  contentDescription = if (isPinVisible) "Hide Password" else "Show Password"
                )
              }
            },
            shape = RoundedCornerShape(12.dp)
          )

          if (pinError != null) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = pinError ?: "",
              color = MaterialTheme.colorScheme.error,
              style = MaterialTheme.typography.bodySmall
            )
          }

          Spacer(modifier = Modifier.height(20.dp))

          Button(
            onClick = {
              if (repository.verifyPin(pinInput)) {
                isAuthenticated = true
                pinInput = ""
                pinError = null
              } else {
                pinError = "Incorrect password. Access denied."
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("admin_login_submit_btn"),
            shape = RoundedCornerShape(12.dp)
          ) {
            Icon(imageVector = Icons.Default.LockOpen, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Unlock Admin Portal", fontWeight = FontWeight.Bold)
          }
        }
      }
    }
    return
  }

  // Authenticated Admin Dashboard
  Column(
    modifier = modifier
      .fillMaxSize()
      .testTag("admin_dashboard_screen")
  ) {
    // Admin Header Status Bar
    Surface(
      color = MaterialTheme.colorScheme.surfaceVariant,
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.AdminPanelSettings,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "NEDIAN Admin Active",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        OutlinedButton(
          onClick = { isAuthenticated = false },
          contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
          shape = RoundedCornerShape(8.dp)
        ) {
          Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Lock", style = MaterialTheme.typography.labelMedium)
        }
      }
    }

    // Scrollable Tabs
    val tabs = listOf("Courses & Fees", "Institute Info", "Notice Banner", "Placements", "Inquiries / Leads", "Settings")
    ScrollableTabRow(
      selectedTabIndex = selectedTabIndex,
      edgePadding = 16.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      tabs.forEachIndexed { index, title ->
        Tab(
          selected = selectedTabIndex == index,
          onClick = { selectedTabIndex = index },
          text = {
            Text(
              text = when (index) {
                0 -> "📚 Courses (${courses.size})"
                1 -> "🏛️ Institute Info"
                2 -> if (instituteNotice.isEnabled) "📢 Notice (ON)" else "📢 Notice (OFF)"
                3 -> "🎓 Placements (${placementStories.size})"
                4 -> "📋 Leads (${studentInquiries.size})"
                else -> "⚙️ Settings"
              },
              fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
            )
          }
        )
      }
    }

    // Tab Content with Animated Transitions
    AnimatedContent(
      targetState = selectedTabIndex,
      transitionSpec = {
        if (targetState > initialState) {
          (slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(260, easing = FastOutSlowInEasing)) + fadeIn(tween(260))) togetherWith
            (slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(200, easing = FastOutSlowInEasing)) + fadeOut(tween(200)))
        } else {
          (slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(260, easing = FastOutSlowInEasing)) + fadeIn(tween(260))) togetherWith
            (slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(200, easing = FastOutSlowInEasing)) + fadeOut(tween(200)))
        }
      },
      label = "admin_tab_transition"
    ) { tabIndex ->
      when (tabIndex) {
        0 -> AdminCoursesTab(
          courses = courses,
          onAddCourseClick = { isAddCourseDialogOpen = true },
          onEditCourse = { editingCourse = it },
          onDeleteCourse = { id -> repository.deleteCourse(id) },
          onResetCourses = { repository.resetCourses() }
        )

        1 -> AdminInstituteInfoTab(
          info = instituteInfo,
          onSaveInfo = { newInfo -> repository.updateInstituteInfo(newInfo) },
          onResetInfo = { repository.resetInstituteInfo() }
        )

        2 -> AdminNoticeTab(
          notice = instituteNotice,
          onSaveNotice = { updatedNotice ->
            repository.updateNotice(updatedNotice)
            Toast.makeText(context, "Notice banner updated successfully", Toast.LENGTH_SHORT).show()
          }
        )

        3 -> AdminPlacementsTab(
          stories = placementStories,
          onAddStoryClick = { isAddStoryDialogOpen = true },
          onEditStory = { editingStory = it },
          onDeleteStory = { storyId ->
            repository.deletePlacementStory(storyId)
            Toast.makeText(context, "Placement story deleted", Toast.LENGTH_SHORT).show()
          },
          onToggleFeatured = { storyId ->
            repository.toggleFeatured(storyId)
          }
        )

        4 -> AdminInquiriesTab(
          inquiries = studentInquiries,
          onAddInquiryClick = { isAddInquiryDialogOpen = true },
          onCallStudent = onCallStudent,
          onWhatsAppStudent = onWhatsAppStudent,
          onUpdateStatus = { id, status ->
            repository.updateInquiryStatus(id, status)
            Toast.makeText(context, "Inquiry marked as $status", Toast.LENGTH_SHORT).show()
          },
          onDeleteInquiry = { id ->
            repository.deleteInquiry(id)
            Toast.makeText(context, "Inquiry deleted", Toast.LENGTH_SHORT).show()
          }
        )

        5 -> AdminSettingsTab(
          currentPin = repository.adminPin.value,
          onChangePin = { newPassword ->
            if (repository.updatePin(newPassword)) {
              Toast.makeText(context, "Password successfully updated", Toast.LENGTH_SHORT).show()
              true
            } else {
              Toast.makeText(context, "Password must be at least 4 characters", Toast.LENGTH_SHORT).show()
              false
            }
          },
          onResetDefaultsClick = { showResetConfirmDialog = true }
        )
      }
    }
  }

  // Add / Edit Course Dialog
  if (isAddCourseDialogOpen || editingCourse != null) {
    CourseEditDialog(
      course = editingCourse,
      onDismiss = {
        isAddCourseDialogOpen = false
        editingCourse = null
      },
      onSave = { savedCourse ->
        if (editingCourse != null) {
          repository.updateCourse(savedCourse)
          Toast.makeText(context, "Course updated", Toast.LENGTH_SHORT).show()
        } else {
          repository.addCourse(savedCourse)
          Toast.makeText(context, "New course added", Toast.LENGTH_SHORT).show()
        }
        isAddCourseDialogOpen = false
        editingCourse = null
      }
    )
  }

  // Add / Edit Placement Dialog
  if (isAddStoryDialogOpen || editingStory != null) {
    StoryEditDialog(
      story = editingStory,
      onDismiss = {
        isAddStoryDialogOpen = false
        editingStory = null
      },
      onSave = { savedStory ->
        if (editingStory != null) {
          repository.updatePlacementStory(savedStory)
          Toast.makeText(context, "Placement updated", Toast.LENGTH_SHORT).show()
        } else {
          repository.addPlacementStory(savedStory)
          Toast.makeText(context, "New placement added", Toast.LENGTH_SHORT).show()
        }
        isAddStoryDialogOpen = false
        editingStory = null
      }
    )
  }

  // Add Inquiry Dialog
  if (isAddInquiryDialogOpen) {
    AddInquiryDialog(
      onDismiss = { isAddInquiryDialogOpen = false },
      onSave = { newInquiry ->
        repository.addInquiry(newInquiry)
        Toast.makeText(context, "Student inquiry logged", Toast.LENGTH_SHORT).show()
        isAddInquiryDialogOpen = false
      }
    )
  }

  // Reset Confirmation Dialog
  if (showResetConfirmDialog) {
    AlertDialog(
      onDismissRequest = { showResetConfirmDialog = false },
      title = { Text("Reset to Default Sample Data?") },
      text = { Text("This will restore default placement stories, reset the announcement notice, and restore sample walk-in leads.") },
      confirmButton = {
        Button(
          onClick = {
            repository.resetToDefaults()
            showResetConfirmDialog = false
            Toast.makeText(context, "Reset complete", Toast.LENGTH_SHORT).show()
          },
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
          Text("Reset")
        }
      },
      dismissButton = {
        OutlinedButton(onClick = { showResetConfirmDialog = false }) {
          Text("Cancel")
        }
      }
    )
  }
}

// -------------------------------------------------------------
// TAB 1: PLACEMENTS MANAGER
// -------------------------------------------------------------
@Composable
private fun AdminPlacementsTab(
  stories: List<PlacementStory>,
  onAddStoryClick: () -> Unit,
  onEditStory: (PlacementStory) -> Unit,
  onDeleteStory: (String) -> Unit,
  onToggleFeatured: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Manage Placement Stories",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "Total ${stories.size} graduates published",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Button(
          onClick = onAddStoryClick,
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier.testTag("admin_btn_add_placement")
        ) {
          Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Add Story")
        }
      }
    }

    items(stories, key = { it.id }) { story ->
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
          ) {
            Box(
              modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(10.dp))
            ) {
              PlacementImageView(
                imageResName = story.imageResName,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
              )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = story.studentName,
                  style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                if (story.isFeatured) {
                  Spacer(modifier = Modifier.width(6.dp))
                  Surface(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = RoundedCornerShape(4.dp)
                  ) {
                    Text(
                      text = "Featured",
                      style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                      color = MaterialTheme.colorScheme.onTertiaryContainer,
                      modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                  }
                }
              }

              Text(
                text = "${story.jobTitle} • ${story.companyName}",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
              )

              Text(
                text = "${story.courseCompleted} (${story.placementYear}) • ${story.location}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Actions
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
          ) {
            TextButton(onClick = { onToggleFeatured(story.id) }) {
              Icon(
                imageVector = if (story.isFeatured) Icons.Default.Star else Icons.Default.Check,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(if (story.isFeatured) "Unfeature" else "Feature")
            }

            IconButton(onClick = { onEditStory(story) }) {
              Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = MaterialTheme.colorScheme.primary)
            }

            IconButton(onClick = { onDeleteStory(story.id) }) {
              Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
          }
        }
      }
    }
  }
}

// -------------------------------------------------------------
// TAB 2: NOTICE BANNER MANAGER
// -------------------------------------------------------------
@Composable
private fun AdminNoticeTab(
  notice: InstituteNotice,
  onSaveNotice: (InstituteNotice) -> Unit
) {
  var isEnabled by remember(notice) { mutableStateOf(notice.isEnabled) }
  var badgeText by remember(notice) { mutableStateOf(notice.badge) }
  var messageText by remember(notice) { mutableStateOf(notice.message) }
  var urgency by remember(notice) { mutableStateOf(notice.urgency) }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Text(
        text = "Notice & Announcement Banner",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
      Text(
        text = "Controls the highlighted alert strip visible to all students on the Home Screen.",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
    }

    // Toggle Switch
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text("Show Banner on Home Screen", fontWeight = FontWeight.Bold)
            Text(
              text = if (isEnabled) "Banner is currently LIVE" else "Banner is currently HIDDEN",
              style = MaterialTheme.typography.bodySmall,
              color = if (isEnabled) Color(0xFF059669) else MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
          Switch(
            checked = isEnabled,
            onCheckedChange = { isEnabled = it }
          )
        }
      }
    }

    // Edit Fields
    item {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
          value = badgeText,
          onValueChange = { badgeText = it },
          label = { Text("Badge Label") },
          placeholder = { Text("e.g. Admissions 2026–2027, New Batch Alert") },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp),
          singleLine = true
        )

        OutlinedTextField(
          value = messageText,
          onValueChange = { messageText = it },
          label = { Text("Announcement Message") },
          placeholder = { Text("Enter announcement details...") },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(10.dp),
          minLines = 3
        )

        Text("Urgency Level", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          listOf("Normal", "Important", "Urgent").forEach { level ->
            FilterChip(
              selected = urgency == level,
              onClick = { urgency = level },
              label = { Text(level) }
            )
          }
        }
      }
    }

    // Live Preview
    item {
      Text("Preview on Home Screen", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
      Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = when (urgency) {
          "Urgent" -> Color(0xFFFEF2F2)
          "Important" -> Color(0xFFFEF3C7)
          else -> Color(0xFFF0FDF4)
        },
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          when (urgency) {
            "Urgent" -> Color(0xFFF87171)
            "Important" -> Color(0xFFFBBF24)
            else -> Color(0xFF34D399)
          }
        )
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.Campaign,
            contentDescription = null,
            tint = when (urgency) {
              "Urgent" -> Color(0xFFDC2626)
              "Important" -> Color(0xFFD97706)
              else -> Color(0xFF059669)
            },
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Surface(
              color = when (urgency) {
                "Urgent" -> Color(0xFFDC2626)
                "Important" -> Color(0xFFD97706)
                else -> Color(0xFF059669)
              },
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = badgeText.ifBlank { "Notice" },
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = messageText.ifBlank { "Announcement text preview..." },
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = Color(0xFF1E293B)
            )
          }
        }
      }
    }

    item {
      Button(
        onClick = {
          onSaveNotice(
            InstituteNotice(
              isEnabled = isEnabled,
              badge = badgeText.trim(),
              message = messageText.trim(),
              urgency = urgency
            )
          )
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(48.dp),
        shape = RoundedCornerShape(10.dp)
      ) {
        Text("Save & Publish Notice Banner", fontWeight = FontWeight.Bold)
      }
    }
  }
}

// -------------------------------------------------------------
// TAB 3: STUDENT INQUIRIES / LEADS TRACKER
// -------------------------------------------------------------
@Composable
private fun AdminInquiriesTab(
  inquiries: List<StudentInquiry>,
  onAddInquiryClick: () -> Unit,
  onCallStudent: (String) -> Unit,
  onWhatsAppStudent: (String, String) -> Unit,
  onUpdateStatus: (String, String) -> Unit,
  onDeleteInquiry: (String) -> Unit
) {
  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Student Inquiries & Leads",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "Track walk-in inquiries, calls, and follow-ups",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Button(
          onClick = onAddInquiryClick,
          shape = RoundedCornerShape(10.dp)
        ) {
          Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Log Lead")
        }
      }
    }

    if (inquiries.isEmpty()) {
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
          contentAlignment = Alignment.Center
        ) {
          Text("No inquiries recorded yet. Click 'Log Lead' to record a student inquiry.")
        }
      }
    } else {
      items(inquiries, key = { it.id }) { inq ->
        Card(
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = inq.studentName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
              )

              Surface(
                shape = RoundedCornerShape(6.dp),
                color = when (inq.status) {
                  "Enrolled" -> Color(0xFFD1FAE5)
                  "Followed Up" -> Color(0xFFDBEAFE)
                  "Closed" -> Color(0xFFF3F4F6)
                  else -> Color(0xFFFEF3C7) // Pending
                }
              ) {
                Text(
                  text = inq.status,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = when (inq.status) {
                    "Enrolled" -> Color(0xFF065F46)
                    "Followed Up" -> Color(0xFF1E40AF)
                    "Closed" -> Color(0xFF374151)
                    else -> Color(0xFF92400E)
                  },
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "Course: ${inq.courseInterested}",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
              color = MaterialTheme.colorScheme.primary
            )

            Text(
              text = "Phone: ${inq.phoneNumber} • Logged: ${inq.dateAdded}",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (inq.notes.isNotBlank()) {
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = "Notes: ${inq.notes}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action Buttons: Call, WhatsApp, Status Switch, Delete
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Button(
                  onClick = { onCallStudent(inq.phoneNumber) },
                  contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                  shape = RoundedCornerShape(8.dp)
                ) {
                  Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(4.dp))
                  Text("Call", style = MaterialTheme.typography.labelSmall)
                }

                Button(
                  onClick = { onWhatsAppStudent(inq.phoneNumber, inq.studentName) },
                  contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                  shape = RoundedCornerShape(8.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981))
                ) {
                  Text("WhatsApp", style = MaterialTheme.typography.labelSmall, color = Color.White)
                }
              }

              Row(verticalAlignment = Alignment.CenterVertically) {
                // Change status menu
                var menuExpanded by remember { mutableStateOf(false) }
                Box {
                  OutlinedButton(
                    onClick = { menuExpanded = true },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp)
                  ) {
                    Text("Status", style = MaterialTheme.typography.labelSmall)
                  }
                  DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                  ) {
                    listOf("Pending", "Followed Up", "Enrolled", "Closed").forEach { statusOption ->
                      DropdownMenuItem(
                        text = { Text(statusOption) },
                        onClick = {
                          onUpdateStatus(inq.id, statusOption)
                          menuExpanded = false
                        }
                      )
                    }
                  }
                }

                IconButton(onClick = { onDeleteInquiry(inq.id) }) {
                  Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
                }
              }
            }
          }
        }
      }
    }
  }
}

// -------------------------------------------------------------
// TAB 4: SETTINGS
// -------------------------------------------------------------
@Composable
private fun AdminSettingsTab(
  currentPin: String,
  onChangePin: (String) -> Boolean,
  onResetDefaultsClick: () -> Unit
) {
  var newPassword by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var passwordMessage by remember { mutableStateOf<String?>(null) }

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    item {
      Text(
        text = "Admin Security & Data Controls",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
    }

    // Change Password Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
          Text("Change Administrator Password", fontWeight = FontWeight.Bold)

          OutlinedTextField(
            value = newPassword,
            onValueChange = { if (it.length <= 30) newPassword = it },
            label = { Text("New Password (minimum 4 characters)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
          )

          OutlinedTextField(
            value = confirmPassword,
            onValueChange = { if (it.length <= 30) confirmPassword = it },
            label = { Text("Confirm New Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
          )

          if (passwordMessage != null) {
            Text(
              text = passwordMessage ?: "",
              color = MaterialTheme.colorScheme.primary,
              style = MaterialTheme.typography.bodySmall
            )
          }

          Button(
            onClick = {
              if (newPassword != confirmPassword) {
                passwordMessage = "Passwords do not match"
              } else if (newPassword.length < 4) {
                passwordMessage = "Password must be at least 4 characters"
              } else {
                val success = onChangePin(newPassword)
                if (success) {
                  passwordMessage = "Password successfully updated!"
                  newPassword = ""
                  confirmPassword = ""
                }
              }
            },
            shape = RoundedCornerShape(10.dp)
          ) {
            Text("Update Password")
          }
        }
      }
    }

    // Institute Info Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text("Institute Information", fontWeight = FontWeight.Bold)
          Text("Name: NEDIAN CONNECT INSTITUTE PVT. LTD.", style = MaterialTheme.typography.bodySmall)
          Text("Location: Chakarchauda, Nepal", style = MaterialTheme.typography.bodySmall)
          Text("Official Contact: +977 9705508838", style = MaterialTheme.typography.bodySmall)
          Text("Standard Admission Fee: NPR 800 (Free Bag & ID Card)", style = MaterialTheme.typography.bodySmall)
        }
      }
    }

    // Reset Defaults Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f))
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Reset Application Data",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.error
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Restores default placement success stories, default announcement banner, and clears customized leads.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Spacer(modifier = Modifier.height(10.dp))
          OutlinedButton(
            onClick = onResetDefaultsClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
          ) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Reset to Initial Seed Data")
          }
        }
      }
    }
  }
}

// -------------------------------------------------------------
// DIALOGS: ADD / EDIT PLACEMENT STORY
// -------------------------------------------------------------
@Composable
private fun StoryEditDialog(
  story: PlacementStory?,
  onDismiss: () -> Unit,
  onSave: (PlacementStory) -> Unit
) {
  var studentName by remember { mutableStateOf(story?.studentName ?: "") }
  var courseCompleted by remember { mutableStateOf(story?.courseCompleted ?: "ADCA (12 Months)") }
  var jobTitle by remember { mutableStateOf(story?.jobTitle ?: "") }
  var companyName by remember { mutableStateOf(story?.companyName ?: "") }
  var location by remember { mutableStateOf(story?.location ?: "Nepal") }
  var placementYear by remember { mutableStateOf(story?.placementYear ?: "2025") }
  var salary by remember { mutableStateOf(story?.packageOrSalary ?: "") }
  var quote by remember { mutableStateOf(story?.quote ?: "") }
  var selectedImageResName by remember { mutableStateOf(story?.imageResName ?: "img_graduate_1") }
  var isFeatured by remember { mutableStateOf(story?.isFeatured ?: true) }

  val context = androidx.compose.ui.platform.LocalContext.current
  val galleryLauncher = androidx.activity.compose.rememberLauncherForActivityResult(
    contract = androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia()
  ) { uri ->
    uri?.let {
      try {
        context.contentResolver.takePersistableUriPermission(
          it,
          android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
      } catch (e: Exception) {}
      selectedImageResName = it.toString()
    }
  }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text(if (story == null) "Add Placement Success Story" else "Edit Placement Story") },
    text = {
      LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        item {
          OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Graduate Full Name *") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
        }

        item {
          OutlinedTextField(
            value = courseCompleted,
            onValueChange = { courseCompleted = it },
            label = { Text("Course Completed *") },
            placeholder = { Text("e.g. ADCA (12 Months)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
        }

        item {
          OutlinedTextField(
            value = jobTitle,
            onValueChange = { jobTitle = it },
            label = { Text("Job Title / Designation *") },
            placeholder = { Text("e.g. Junior Accountant, Computer Operator") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
        }

        item {
          OutlinedTextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Hiring Company / Workplace *") },
            placeholder = { Text("e.g. Sunrise Co-operative, Gaupalika") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
        }

        item {
          Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
              value = location,
              onValueChange = { location = it },
              label = { Text("Location") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )
            OutlinedTextField(
              value = placementYear,
              onValueChange = { placementYear = it },
              label = { Text("Year") },
              modifier = Modifier.width(90.dp),
              singleLine = true
            )
          }
        }

        item {
          OutlinedTextField(
            value = salary,
            onValueChange = { salary = it },
            label = { Text("Salary / Package (Optional)") },
            placeholder = { Text("e.g. NPR 25,000 / month") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )
        }

        item {
          OutlinedTextField(
            value = quote,
            onValueChange = { quote = it },
            label = { Text("Graduate Testimonial / Experience *") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
          )
        }

        item {
          Text("Graduate Photo (Gallery / Default)", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
          Spacer(modifier = Modifier.height(6.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            ) {
              PlacementImageView(
                imageResName = selectedImageResName,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
              )
            }

            Button(
              onClick = {
                galleryLauncher.launch(
                  PickVisualMediaRequest(
                    androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                  )
                )
              },
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.PhotoLibrary, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Choose from Gallery")
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text("Or select default avatar:", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
          Spacer(modifier = Modifier.height(4.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            listOf(
              "img_graduate_1" to R.drawable.img_graduate_1,
              "img_graduate_2" to R.drawable.img_graduate_2,
              "img_graduate_3" to R.drawable.img_graduate_3
            ).forEach { (resName, resId) ->
              val isSelected = selectedImageResName == resName
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(RoundedCornerShape(8.dp))
                  .border(
                    width = if (isSelected) 3.dp else 1.dp,
                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                  )
                  .clickable { selectedImageResName = resName }
              ) {
                Image(
                  painter = painterResource(id = resId),
                  contentDescription = null,
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Crop
                )
              }
            }
          }
        }
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (studentName.isNotBlank() && jobTitle.isNotBlank() && companyName.isNotBlank()) {
            onSave(
              PlacementStory(
                id = story?.id ?: System.currentTimeMillis().toString(),
                studentName = studentName.trim(),
                courseCompleted = courseCompleted.trim(),
                jobTitle = jobTitle.trim(),
                companyName = companyName.trim(),
                location = location.trim(),
                placementYear = placementYear.trim(),
                packageOrSalary = salary.trim().ifBlank { null },
                quote = quote.trim().ifBlank { "Learning at NEDIAN CONNECT helped me build a great career." },
                imageResName = selectedImageResName,
                isFeatured = isFeatured
              )
            )
          }
        }
      ) {
        Text("Save Story")
      }
    },
    dismissButton = {
      OutlinedButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}

// -------------------------------------------------------------
// DIALOGS: ADD INQUIRY / LEAD
// -------------------------------------------------------------
@Composable
private fun AddInquiryDialog(
  onDismiss: () -> Unit,
  onSave: (StudentInquiry) -> Unit
) {
  var studentName by remember { mutableStateOf("") }
  var phoneNumber by remember { mutableStateOf("") }
  var courseInterested by remember { mutableStateOf("ADCA (12 Months)") }
  var notes by remember { mutableStateOf("") }

  AlertDialog(
    onDismissRequest = onDismiss,
    title = { Text("Log Walk-in / Phone Inquiry") },
    text = {
      Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(
          value = studentName,
          onValueChange = { studentName = it },
          label = { Text("Student Name *") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        OutlinedTextField(
          value = phoneNumber,
          onValueChange = { phoneNumber = it },
          label = { Text("Phone / WhatsApp *") },
          placeholder = { Text("+977 98XXXXXXXX") },
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        OutlinedTextField(
          value = courseInterested,
          onValueChange = { courseInterested = it },
          label = { Text("Course Interested In") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        OutlinedTextField(
          value = notes,
          onValueChange = { notes = it },
          label = { Text("Notes / Follow-up Details") },
          modifier = Modifier.fillMaxWidth(),
          minLines = 2
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (studentName.isNotBlank() && phoneNumber.isNotBlank()) {
            onSave(
              StudentInquiry(
                id = System.currentTimeMillis().toString(),
                studentName = studentName.trim(),
                phoneNumber = phoneNumber.trim(),
                courseInterested = courseInterested.trim(),
                status = "Pending",
                dateAdded = "Today",
                notes = notes.trim()
              )
            )
          }
        }
      ) {
        Text("Save Inquiry")
      }
    },
    dismissButton = {
      OutlinedButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}
