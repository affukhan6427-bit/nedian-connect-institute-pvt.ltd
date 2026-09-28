package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Course
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AdminCoursesTab(
  courses: List<Course>,
  onAddCourseClick: () -> Unit,
  onEditCourse: (Course) -> Unit,
  onDeleteCourse: (String) -> Unit,
  onResetCourses: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val nf = NumberFormat.getNumberInstance(Locale.US)
  var courseToDelete by remember { mutableStateOf<Course?>(null) }
  var showResetConfirm by remember { mutableStateOf(false) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
      .testTag("admin_courses_tab"),
    contentPadding = PaddingValues(bottom = 80.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header & Actions Bar
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Course & Fee Customizer",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Manage syllabus, admission fees, monthly fees, discounts, and offers.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = onAddCourseClick,
              modifier = Modifier.weight(1f).testTag("add_course_button"),
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Add Course", fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
              onClick = { showResetConfirm = true },
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.Restore, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text("Reset", style = MaterialTheme.typography.labelMedium)
            }
          }
        }
      }
    }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "Active Courses (${courses.size})",
          style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Tap pencil to edit fees/details",
          style = MaterialTheme.typography.labelSmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    items(courses, key = { it.id }) { course ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("admin_course_item_${course.id}"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.weight(1f)
            ) {
              Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(38.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.width(10.dp))

              Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = course.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                  ) {
                    Text(
                      text = course.duration,
                      style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                }
                Text(
                  text = course.fullName,
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 1
                )
              }
            }

            // Action Buttons: Edit & Delete
            Row {
              IconButton(
                onClick = { onEditCourse(course) },
                modifier = Modifier.size(36.dp).testTag("edit_course_${course.id}")
              ) {
                Icon(
                  imageVector = Icons.Default.Edit,
                  contentDescription = "Edit Course",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(18.dp)
                )
              }

              IconButton(
                onClick = { courseToDelete = course },
                modifier = Modifier.size(36.dp).testTag("delete_course_${course.id}")
              ) {
                Icon(
                  imageVector = Icons.Default.Delete,
                  contentDescription = "Delete Course",
                  tint = MaterialTheme.colorScheme.error,
                  modifier = Modifier.size(18.dp)
                )
              }
            }
          }

          HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

          // Fee Structure Grid
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Column {
              Text("Admission", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
              Text(
                text = if (course.admissionFee != null) "NPR ${nf.format(course.admissionFee)}" else "N/A",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
              )
            }

            Column {
              Text("Monthly", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
              Text(
                text = if (course.monthlyFee != null) "NPR ${nf.format(course.monthlyFee)}" else "N/A",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
              )
            }

            Column {
              Text("Discount", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.tertiary)
              Text(
                text = if (course.discount != null) "NPR ${nf.format(course.discount)}" else "—",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.tertiary
              )
            }

            Column {
              Text("Final Fee", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
              Text(
                text = if (course.finalTotalFee != null) "NPR ${nf.format(course.finalTotalFee)}" else "Contact",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
              )
            }
          }

          if (!course.offer.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              shape = RoundedCornerShape(6.dp),
              color = MaterialTheme.colorScheme.secondaryContainer
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.LocalOffer,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onSecondaryContainer,
                  modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = course.offer,
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, fontSize = 10.sp),
                  color = MaterialTheme.colorScheme.onSecondaryContainer
                )
              }
            }
          }
        }
      }
    }
  }

  // Delete Confirmation Dialog
  if (courseToDelete != null) {
    AlertDialog(
      onDismissRequest = { courseToDelete = null },
      title = { Text("Delete Course?") },
      text = { Text("Are you sure you want to delete '${courseToDelete?.name}' (${courseToDelete?.fullName})? This will remove it from the fee chart and course list.") },
      confirmButton = {
        Button(
          onClick = {
            courseToDelete?.id?.let { onDeleteCourse(it) }
            Toast.makeText(context, "Course removed", Toast.LENGTH_SHORT).show()
            courseToDelete = null
          },
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
          Text("Delete")
        }
      },
      dismissButton = {
        OutlinedButton(onClick = { courseToDelete = null }) {
          Text("Cancel")
        }
      }
    )
  }

  // Reset Confirmation Dialog
  if (showResetConfirm) {
    AlertDialog(
      onDismissRequest = { showResetConfirm = false },
      title = { Text("Reset to Official Fee-Chart Courses?") },
      text = { Text("This will restore the 8 official fee-chart courses (ADCA, DCA, Basic, DTP, Tally Prime, Diploma in Hardware, Advance Excel, Graphic Design) with their standard fee structure.") },
      confirmButton = {
        Button(
          onClick = {
            onResetCourses()
            showResetConfirm = false
            Toast.makeText(context, "Courses reset to official chart", Toast.LENGTH_SHORT).show()
          }
        ) {
          Text("Reset to Chart")
        }
      },
      dismissButton = {
        OutlinedButton(onClick = { showResetConfirm = false }) {
          Text("Cancel")
        }
      }
    )
  }
}

@Composable
fun CourseEditDialog(
  course: Course?,
  onDismiss: () -> Unit,
  onSave: (Course) -> Unit
) {
  val isEditing = course != null

  var name by remember { mutableStateOf(course?.name ?: "") }
  var fullName by remember { mutableStateOf(course?.fullName ?: "") }
  var category by remember { mutableStateOf(course?.category ?: "Diploma") }
  var duration by remember { mutableStateOf(course?.duration ?: "6 Months") }
  var admissionFeeStr by remember { mutableStateOf(course?.admissionFee?.toString() ?: "800") }
  var monthlyFeeStr by remember { mutableStateOf(course?.monthlyFee?.toString() ?: "825") }
  var durationMonthsStr by remember {
    val digits = course?.duration?.filter { it.isDigit() }
    mutableStateOf(if (digits.isNullOrBlank()) "6" else digits)
  }
  var discountStr by remember { mutableStateOf(course?.discount?.toString() ?: "450") }
  var offer by remember { mutableStateOf(course?.offer ?: "Bag & ID Card Free") }
  var offerTag by remember { mutableStateOf(course?.offerTag ?: "Limited Fee Offer 2026–2027") }
  var description by remember { mutableStateOf(course?.description ?: "") }

  // Auto calculate total monthly fees and final fee
  val monthlyFeeVal = monthlyFeeStr.toIntOrNull() ?: 0
  val durationMonthsVal = durationMonthsStr.toIntOrNull() ?: 1
  val totalMonthlyCalculated = monthlyFeeVal * durationMonthsVal
  val discountVal = discountStr.toIntOrNull() ?: 0
  val finalFeeCalculated = (totalMonthlyCalculated - discountVal).coerceAtLeast(0)

  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text(
        text = if (isEditing) "Edit Course: ${course?.name}" else "Add New Course",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
    },
    text = {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedTextField(
          value = name,
          onValueChange = { name = it },
          label = { Text("Short Name (e.g. ADCA, Python)") },
          modifier = Modifier.fillMaxWidth().testTag("course_name_input"),
          singleLine = true
        )

        OutlinedTextField(
          value = fullName,
          onValueChange = { fullName = it },
          label = { Text("Full Course Name") },
          modifier = Modifier.fillMaxWidth().testTag("course_fullname_input"),
          singleLine = true
        )

        // Category Chips
        Text("Category", style = MaterialTheme.typography.labelSmall)
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          listOf("Diploma", "Certificate", "Professional", "Specialized").forEach { cat ->
            FilterChip(
              selected = category == cat,
              onClick = { category = cat },
              label = { Text(cat, style = MaterialTheme.typography.labelSmall) }
            )
          }
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration Label") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )

          OutlinedTextField(
            value = durationMonthsStr,
            onValueChange = { durationMonthsStr = it },
            label = { Text("Months Count") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          OutlinedTextField(
            value = admissionFeeStr,
            onValueChange = { admissionFeeStr = it },
            label = { Text("Admission (NPR)") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )

          OutlinedTextField(
            value = monthlyFeeStr,
            onValueChange = { monthlyFeeStr = it },
            label = { Text("Monthly (NPR)") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          OutlinedTextField(
            value = discountStr,
            onValueChange = { discountStr = it },
            label = { Text("Discount (NPR)") },
            modifier = Modifier.weight(1f),
            singleLine = true
          )

          // Auto-Calculated Final Total Box
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.weight(1f).height(56.dp)
          ) {
            Column(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
              verticalArrangement = Arrangement.Center
            ) {
              Text("Final Total", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onPrimaryContainer)
              Text("NPR $finalFeeCalculated", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.primary)
            }
          }
        }

        OutlinedTextField(
          value = offer,
          onValueChange = { offer = it },
          label = { Text("Offer Tag (e.g. Bag & ID Card Free)") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        OutlinedTextField(
          value = offerTag,
          onValueChange = { offerTag = it },
          label = { Text("Offer Badge (e.g. Limited Fee Offer)") },
          modifier = Modifier.fillMaxWidth(),
          singleLine = true
        )

        OutlinedTextField(
          value = description,
          onValueChange = { description = it },
          label = { Text("Course Description / Syllabus Overview") },
          modifier = Modifier.fillMaxWidth(),
          maxLines = 3
        )
      }
    },
    confirmButton = {
      Button(
        onClick = {
          if (name.isNotBlank()) {
            val adm = admissionFeeStr.toIntOrNull()
            val mon = monthlyFeeStr.toIntOrNull()
            val dis = discountStr.toIntOrNull()

            val updated = Course(
              id = course?.id ?: "custom_${System.currentTimeMillis()}",
              name = name.trim(),
              fullName = if (fullName.isNotBlank()) fullName.trim() else name.trim(),
              category = category,
              duration = duration.trim(),
              admissionFee = adm,
              monthlyFee = mon,
              totalMonthlyFees = totalMonthlyCalculated,
              discount = dis,
              finalTotalFee = finalFeeCalculated,
              offer = if (offer.isNotBlank()) offer.trim() else null,
              offerTag = if (offerTag.isNotBlank()) offerTag.trim() else null,
              hasConfirmedFee = true,
              description = description.trim()
            )
            onSave(updated)
          }
        },
        modifier = Modifier.testTag("save_course_btn")
      ) {
        Text(if (isEditing) "Save Changes" else "Create Course")
      }
    },
    dismissButton = {
      OutlinedButton(onClick = onDismiss) {
        Text("Cancel")
      }
    }
  )
}
