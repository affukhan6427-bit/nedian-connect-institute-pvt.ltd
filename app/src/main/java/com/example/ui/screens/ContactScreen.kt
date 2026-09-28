package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CourseRepository

enum class ShiftTiming(val label: String, val timeRange: String, val subtitle: String) {
  MORNING("Morning Shift", "07:00 AM - 11:00 AM", "Ideal for early learners & students"),
  DAY("Day Shift", "11:30 AM - 03:30 PM", "Regular intensive practical batches"),
  EVENING("Evening Shift", "04:00 PM - 07:30 PM", "Perfect for working individuals & professionals")
}

@Composable
fun ContactScreen(
  onCallNow: () -> Unit,
  onWhatsApp: () -> Unit,
  onScheduleAppointmentWhatsApp: ((message: String) -> Unit)? = null,
  onGetDirections: () -> Unit,
  modifier: Modifier = Modifier
) {
  var visitorName by remember { mutableStateOf("") }
  var visitorPhone by remember { mutableStateOf("") }
  var courseOfInterest by remember { mutableStateOf("") }
  var selectedShift by remember { mutableStateOf(ShiftTiming.MORNING) }
  var preferredDate by remember { mutableStateOf("Today / Tomorrow") }
  var additionalNote by remember { mutableStateOf("") }
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("contact_screen"),
    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp)
  ) {
    item {
      Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = MaterialTheme.colorScheme.primaryContainer,
          modifier = Modifier.padding(bottom = 6.dp)
        ) {
          Text(
            text = "Get in Touch",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = "Contact Us",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
          )
        )
        Text(
          text = "Have questions about courses, fees, or class schedules? Reach out directly to NEDIAN CONNECT INSTITUTE PVT. LTD.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }

    // Institute Contact Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
        )
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Text(
            text = CourseRepository.INSTITUTE_NAME,
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.ExtraBold,
              color = MaterialTheme.colorScheme.primary
            )
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Phone detail
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "Primary Phone & WhatsApp",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = CourseRepository.INSTITUTE_PHONE,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.ExtraBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = "Helpline: ${CourseRepository.INSTITUTE_HELPLINE_PHONE}",
                style = MaterialTheme.typography.bodyMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Location detail
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "Institute Location",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = CourseRepository.INSTITUTE_LOCATION,
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Class timings
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Schedule,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text(
                text = "Batch Shifts Available",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
              Text(
                text = "Morning • Day • Evening",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
            }
          }

          HorizontalDivider(
            modifier = Modifier.padding(vertical = 16.dp),
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
          )

          // 3 Action Buttons: Call Now, WhatsApp, Get Directions
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(
              onClick = onCallNow,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("contact_btn_call_now"),
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
              )
            ) {
              Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Call Now (+977 9705508838)", fontWeight = FontWeight.Bold)
            }

            Button(
              onClick = onWhatsApp,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("contact_btn_whatsapp"),
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF25D366)
              )
            ) {
              Text("💬  Chat on WhatsApp", fontWeight = FontWeight.Bold, color = Color.White)
            }

            OutlinedButton(
              onClick = onGetDirections,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("contact_btn_get_directions"),
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Get Directions (Chakarchauda, Nepal)", fontWeight = FontWeight.SemiBold)
            }
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }

    // Schedule Appointment Feature
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("card_schedule_appointment"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
          ) {
            Box(
              modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.padding(bottom = 4.dp)
              ) {
                Text(
                  text = "Direct Counseling & Visit",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                  ),
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                )
              }
              Text(
                text = "Schedule an Appointment",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.ExtraBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Select your preferred shift timing and counseling details. We will pre-fill a professional inquiry ready to send directly to our WhatsApp counselor.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Spacer(modifier = Modifier.height(16.dp))

          // 1. Choose Preferred Shift Time
          Text(
            text = "1. Select Preferred Shift Time",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.primary
            )
          )
          Spacer(modifier = Modifier.height(8.dp))

          ShiftTiming.entries.forEach { shift ->
            val isSelected = selectedShift == shift
            Surface(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .clickable { selectedShift = shift }
                .testTag("shift_option_${shift.name.lowercase()}"),
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
              } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
              },
              border = BorderStroke(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
              )
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.AccessTime,
                  contentDescription = null,
                  tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = shift.label,
                      style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                      )
                    )
                    Text(
                      text = shift.timeRange,
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                      )
                    )
                  }
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = shift.subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // 2. Candidate & Course Details (Inputs)
          Text(
            text = "2. Candidate & Inquiry Details",
            style = MaterialTheme.typography.titleSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.primary
            )
          )
          Spacer(modifier = Modifier.height(10.dp))

          OutlinedTextField(
            value = visitorName,
            onValueChange = { visitorName = it },
            label = { Text("Your Full Name") },
            placeholder = { Text("e.g., Afzal Khan") },
            leadingIcon = {
              Icon(Icons.Default.Person, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("input_appointment_name"),
            shape = RoundedCornerShape(10.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            value = visitorPhone,
            onValueChange = { visitorPhone = it },
            label = { Text("Contact Phone / Mobile") },
            placeholder = { Text("e.g., +977 98XXXXXXXX") },
            leadingIcon = {
              Icon(Icons.Default.Phone, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("input_appointment_phone"),
            shape = RoundedCornerShape(10.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            value = courseOfInterest,
            onValueChange = { courseOfInterest = it },
            label = { Text("Course of Interest (Optional)") },
            placeholder = { Text("e.g., Tally, Web Development, PGDCA...") },
            leadingIcon = {
              Icon(Icons.Default.School, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("input_appointment_course"),
            shape = RoundedCornerShape(10.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            value = preferredDate,
            onValueChange = { preferredDate = it },
            label = { Text("Preferred Date / Day") },
            placeholder = { Text("e.g., Today afternoon, Tomorrow 10 AM") },
            leadingIcon = {
              Icon(Icons.Default.EventNote, contentDescription = null)
            },
            singleLine = true,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("input_appointment_date"),
            shape = RoundedCornerShape(10.dp)
          )

          Spacer(modifier = Modifier.height(8.dp))

          OutlinedTextField(
            value = additionalNote,
            onValueChange = { additionalNote = it },
            label = { Text("Any specific question or notes") },
            placeholder = { Text("e.g., Want fee discount info or demo class") },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("input_appointment_note"),
            shape = RoundedCornerShape(10.dp),
            maxLines = 3
          )

          Spacer(modifier = Modifier.height(18.dp))

          // Preview Template Box
          val templatePreview = buildString {
            append("📅 *APPOINTMENT / COUNSELING INQUIRY*\n")
            append("Institute: ${CourseRepository.INSTITUTE_NAME}\n\n")
            append("👤 *Name:* ${visitorName.ifBlank { "Candidate" }}\n")
            if (visitorPhone.isNotBlank()) append("📞 *Phone:* $visitorPhone\n")
            append("⏰ *Preferred Shift:* ${selectedShift.label} (${selectedShift.timeRange})\n")
            append("🗓️ *Preferred Date:* ${preferredDate.ifBlank { "Immediate / This Week" }}\n")
            if (courseOfInterest.isNotBlank()) append("🎓 *Interested Course:* $courseOfInterest\n")
            if (additionalNote.isNotBlank()) append("📝 *Note:* $additionalNote\n\n")
            append("Please confirm appointment schedule for counseling & campus visit at Chakarchauda.")
          }

          Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
              ) {
                Text(
                  text = "Pre-filled WhatsApp Template Preview",
                  style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                  color = MaterialTheme.colorScheme.primary
                )
                Text(
                  text = "Auto-formatted",
                  style = MaterialTheme.typography.labelSmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = templatePreview,
                style = MaterialTheme.typography.bodySmall.copy(
                  fontSize = 11.5.sp,
                  lineHeight = 16.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Action Button: Send to WhatsApp
          Button(
            onClick = {
              if (onScheduleAppointmentWhatsApp != null) {
                onScheduleAppointmentWhatsApp(templatePreview)
              } else {
                onWhatsApp()
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .height(50.dp)
              .testTag("btn_send_appointment_whatsapp"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF25D366)
            )
          ) {
            Text(
              text = "💬  Book Appointment via WhatsApp",
              fontWeight = FontWeight.ExtraBold,
              fontSize = 15.sp,
              color = Color.White
            )
          }
        }
      }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }

    // Note regarding unlisted courses or missing information
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Official Institute Inquiry Note",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "For courses such as Basic Computer, Advanced Computer, English Speaking, Arabic Speaking, and English to Arabic Translation: Contact Institute for Fee & Duration schedules.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  }
}
