package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JobApplyScreen(
  onWhatsAppInquiry: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val clipboardManager: ClipboardManager = LocalClipboardManager.current

  // Form Fields State
  var fullName by remember { mutableStateOf("") }
  var fatherName by remember { mutableStateOf("") }
  var dob by remember { mutableStateOf("") }
  var gender by remember { mutableStateOf("Male") }
  var mobileNumber by remember { mutableStateOf("") }
  var whatsappNumber by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var currentAddress by remember { mutableStateOf("") }

  // Job Info
  var selectedPosition by remember { mutableStateOf("Computer Instructor") }
  var customPosition by remember { mutableStateOf("") }
  var department by remember { mutableStateOf("Academic & Training") }
  var experience by remember { mutableStateOf("1 Year") }
  var qualification by remember { mutableStateOf("Bachelor / Plus 2") }
  var expectedSalary by remember { mutableStateOf("") }
  var availableToJoin by remember { mutableStateOf("Immediately") }
  var previousOrganization by remember { mutableStateOf("") }

  // Skills & About
  var computerSkills by remember { mutableStateOf("") }
  var otherSkills by remember { mutableStateOf("") }
  var aboutYourself by remember { mutableStateOf("") }

  // Declaration
  var declarationChecked by remember { mutableStateOf(false) }

  // UI state
  var showSuccessDialog by remember { mutableStateOf(false) }
  var generatedMessage by remember { mutableStateOf("") }
  var isSubmitting by remember { mutableStateOf(false) }

  // Position Dropdown expanded
  var positionDropdownExpanded by remember { mutableStateOf(false) }
  val positions = listOf(
    "Computer Instructor",
    "Accountant",
    "Office Assistant",
    "Receptionist",
    "Computer Operator",
    "Marketing Staff",
    "IT Support",
    "Other"
  )

  val infiniteTransition = rememberInfiniteTransition(label = "job_banner_anim")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.03f,
    animationSpec = infiniteRepeatable(
      animation = tween(1400, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "job_pulse"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("job_apply_screen"),
    contentPadding = PaddingValues(16.dp, bottom = 90.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Career Header Banner Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("job_career_hero_card"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.horizontalGradient(
                colors = listOf(
                  MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                  MaterialTheme.colorScheme.tertiary.copy(alpha = 0.75f)
                )
              )
            )
            .padding(20.dp)
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically
            ) {
              Surface(
                shape = CircleShape,
                color = Color.White,
                modifier = Modifier
                  .size(46.dp)
                  .scale(pulseScale)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Icon(
                    imageVector = Icons.Default.Work,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                  )
                }
              }
              Spacer(modifier = Modifier.width(14.dp))
              Column {
                Text(
                  text = "Join Our Team",
                  style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                  )
                )
                Text(
                  text = "Nedian Connect Institute • Career Portal",
                  style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White.copy(alpha = 0.9f)
                  )
                )
              }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
              text = "Want to build your career with Kapilvastu's premier practical IT institute? Apply for available teaching, technical, and administrative job opportunities by submitting your details below.",
              style = MaterialTheme.typography.bodyMedium.copy(
                color = Color.White.copy(alpha = 0.95f),
                lineHeight = 20.sp
              )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color.White.copy(alpha = 0.2f)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(
                  imageVector = Icons.Default.LocationOn,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "Location: Chakarchauda, Nepal | Instant WhatsApp Application",
                  style = MaterialTheme.typography.labelSmall.copy(color = Color.White, fontWeight = FontWeight.Bold)
                )
              }
            }
          }
        }
      }
    }

    // 2. Personal Information Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Person,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Personal Information",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.primary
            )
          }

          HorizontalDivider()

          OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name *") },
            modifier = Modifier.fillMaxWidth().testTag("job_input_fullname"),
            singleLine = true
          )

          OutlinedTextField(
            value = fatherName,
            onValueChange = { fatherName = it },
            label = { Text("Father's Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = dob,
              onValueChange = { dob = it },
              label = { Text("Date of Birth (DD/MM/YYYY)") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )

            // Gender selector
            Column(modifier = Modifier.weight(1f)) {
              Text("Gender", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
              Spacer(modifier = Modifier.height(4.dp))
              Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf("Male", "Female").forEach { g ->
                  FilterChip(
                    selected = gender == g,
                    onClick = { gender = g },
                    label = { Text(g, style = MaterialTheme.typography.labelSmall) }
                  )
                }
              }
            }
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = mobileNumber,
              onValueChange = { mobileNumber = it },
              label = { Text("Mobile Number *") },
              modifier = Modifier.weight(1f).testTag("job_input_mobile"),
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
              singleLine = true
            )

            OutlinedTextField(
              value = whatsappNumber,
              onValueChange = { whatsappNumber = it },
              label = { Text("WhatsApp Number") },
              modifier = Modifier.weight(1f),
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
              singleLine = true
            )
          }

          OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
          )

          OutlinedTextField(
            value = currentAddress,
            onValueChange = { currentAddress = it },
            label = { Text("Current Address * (e.g. Kapilvastu, Nepal)") },
            modifier = Modifier.fillMaxWidth().testTag("job_input_address"),
            singleLine = true
          )
        }
      }
    }

    // 3. Job Information Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Work,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.secondary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Job Information & Position",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.secondary
            )
          }

          HorizontalDivider()

          // Position Dropdown
          Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
              value = if (selectedPosition == "Other" && customPosition.isNotBlank()) "Other: $customPosition" else selectedPosition,
              onValueChange = {},
              readOnly = true,
              label = { Text("Position Applying For *") },
              modifier = Modifier
                .fillMaxWidth()
                .clickable { positionDropdownExpanded = true }
                .testTag("job_dropdown_position"),
              trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
              }
            )
            Box(
              modifier = Modifier
                .matchParentSize()
                .clickable { positionDropdownExpanded = true }
            )

            DropdownMenu(
              expanded = positionDropdownExpanded,
              onDismissRequest = { positionDropdownExpanded = false },
              modifier = Modifier.fillMaxWidth(0.85f)
            ) {
              positions.forEach { pos ->
                DropdownMenuItem(
                  text = { Text(pos) },
                  onClick = {
                    selectedPosition = pos
                    positionDropdownExpanded = false
                  }
                )
              }
            }
          }

          if (selectedPosition == "Other") {
            OutlinedTextField(
              value = customPosition,
              onValueChange = { customPosition = it },
              label = { Text("Specify Custom Position Title *") },
              modifier = Modifier.fillMaxWidth(),
              singleLine = true
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = department,
              onValueChange = { department = it },
              label = { Text("Preferred Department") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )

            OutlinedTextField(
              value = experience,
              onValueChange = { experience = it },
              label = { Text("Experience (e.g. 2 Years)") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = qualification,
              onValueChange = { qualification = it },
              label = { Text("Education / Qualification") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )

            OutlinedTextField(
              value = expectedSalary,
              onValueChange = { expectedSalary = it },
              label = { Text("Expected Salary (NPR)") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = availableToJoin,
              onValueChange = { availableToJoin = it },
              label = { Text("Available to Join") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )

            OutlinedTextField(
              value = previousOrganization,
              onValueChange = { previousOrganization = it },
              label = { Text("Previous Organization") },
              modifier = Modifier.weight(1f),
              singleLine = true
            )
          }
        }
      }
    }

    // 4. Skills & About Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Assignment,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.tertiary,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Skills & Introduction",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.tertiary
            )
          }

          HorizontalDivider()

          OutlinedTextField(
            value = computerSkills,
            onValueChange = { computerSkills = it },
            label = { Text("Computer Skills (e.g. MS Office, Tally, DTP, Coding)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )

          OutlinedTextField(
            value = otherSkills,
            onValueChange = { otherSkills = it },
            label = { Text("Other Skills (e.g. Communication, Teaching, Typing)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )

          OutlinedTextField(
            value = aboutYourself,
            onValueChange = { aboutYourself = it },
            label = { Text("Short Introduction / About Yourself *") },
            modifier = Modifier.fillMaxWidth().testTag("job_input_about"),
            maxLines = 4
          )
        }
      }
    }

    // 5. Document Upload & CV Info Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Text(
            text = "📄 Resume / CV & Document Upload",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
          )
          Text(
            text = "Note: WhatsApp text applications submit all professional details instantly. You may attach your PDF/Word CV file directly when our office opens your WhatsApp chat, or bring a printed copy during your interview at Chakarchauda.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 18.sp
          )
        }
      }
    }

    // 6. Declaration & Submit Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { declarationChecked = !declarationChecked },
            verticalAlignment = Alignment.CenterVertically
          ) {
            Checkbox(
              checked = declarationChecked,
              onCheckedChange = { declarationChecked = it },
              modifier = Modifier.testTag("job_checkbox_declaration")
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "I confirm that the information provided above is correct and true to the best of my knowledge. *",
              style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.weight(1f)
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            OutlinedButton(
              onClick = {
                fullName = ""
                fatherName = ""
                dob = ""
                gender = "Male"
                mobileNumber = ""
                whatsappNumber = ""
                email = ""
                currentAddress = ""
                selectedPosition = "Computer Instructor"
                customPosition = ""
                department = "Academic & Training"
                experience = "1 Year"
                qualification = "Bachelor / Plus 2"
                expectedSalary = ""
                availableToJoin = "Immediately"
                previousOrganization = ""
                computerSkills = ""
                otherSkills = ""
                aboutYourself = ""
                declarationChecked = false
                Toast.makeText(context, "Form cleared", Toast.LENGTH_SHORT).show()
              },
              modifier = Modifier.weight(1f).height(50.dp),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text("Clear Form")
            }

            Button(
              onClick = {
                // Validation
                if (fullName.isBlank()) {
                  Toast.makeText(context, "Please enter your Full Name", Toast.LENGTH_SHORT).show()
                  return@Button
                }
                if (mobileNumber.isBlank()) {
                  Toast.makeText(context, "Please enter your Mobile Number", Toast.LENGTH_SHORT).show()
                  return@Button
                }
                if (currentAddress.isBlank()) {
                  Toast.makeText(context, "Please enter your Current Address", Toast.LENGTH_SHORT).show()
                  return@Button
                }
                if (aboutYourself.isBlank()) {
                  Toast.makeText(context, "Please write a short introduction about yourself", Toast.LENGTH_SHORT).show()
                  return@Button
                }
                if (!declarationChecked) {
                  Toast.makeText(context, "Please check the declaration before submitting", Toast.LENGTH_SHORT).show()
                  return@Button
                }

                val finalPos = if (selectedPosition == "Other" && customPosition.isNotBlank()) customPosition else selectedPosition

                // Format WhatsApp Message
                val msg = buildString {
                  appendLine("JOB APPLICATION")
                  appendLine("NEDIAN CONNECT INSTITUTE")
                  appendLine("----------------------------------------")
                  appendLine("Full Name: $fullName")
                  if (fatherName.isNotBlank()) appendLine("Father's Name: $fatherName")
                  if (dob.isNotBlank()) appendLine("DOB: $dob")
                  appendLine("Gender: $gender")
                  appendLine("Mobile: $mobileNumber")
                  if (whatsappNumber.isNotBlank()) appendLine("WhatsApp: $whatsappNumber")
                  if (email.isNotBlank()) appendLine("Email: $email")
                  appendLine("Address: $currentAddress")
                  appendLine("----------------------------------------")
                  appendLine("Position Applied: $finalPos")
                  if (department.isNotBlank()) appendLine("Department: $department")
                  if (experience.isNotBlank()) appendLine("Experience: $experience")
                  if (qualification.isNotBlank()) appendLine("Qualification: $qualification")
                  if (expectedSalary.isNotBlank()) appendLine("Expected Salary: $expectedSalary")
                  if (availableToJoin.isNotBlank()) appendLine("Available From: $availableToJoin")
                  if (previousOrganization.isNotBlank()) appendLine("Previous Org: $previousOrganization")
                  appendLine("----------------------------------------")
                  if (computerSkills.isNotBlank()) appendLine("Computer Skills: $computerSkills")
                  if (otherSkills.isNotBlank()) appendLine("Other Skills: $otherSkills")
                  appendLine("----------------------------------------")
                  appendLine("About Applicant:")
                  appendLine(aboutYourself)
                  appendLine("----------------------------------------")
                  appendLine("Declaration: Correct & Verified ✓")
                }

                generatedMessage = msg
                showSuccessDialog = true
              },
              modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .testTag("job_submit_button"),
              shape = RoundedCornerShape(12.dp),
              colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
              Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Submit Application", fontWeight = FontWeight.Bold)
            }
          }
        }
      }
    }
  }

  // Success / Confirmation Dialog
  if (showSuccessDialog) {
    AlertDialog(
      onDismissRequest = { showSuccessDialog = false },
      title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF10B981),
            modifier = Modifier.size(28.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text("Application Ready! ✓", fontWeight = FontWeight.ExtraBold)
        }
      },
      text = {
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = "Your job application has been prepared successfully and formatted for Nedian Connect Institute.",
            style = MaterialTheme.typography.bodySmall
          )

          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier
              .fillMaxWidth()
              .height(140.dp)
          ) {
            LazyColumn(modifier = Modifier.padding(10.dp)) {
              item {
                Text(
                  text = generatedMessage,
                  style = MaterialTheme.typography.labelSmall.copy(fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace, fontSize = 11.sp),
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }

          Text(
            text = "Continue to WhatsApp to send this application directly to +977 9705508838.",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
          )
        }
      },
      confirmButton = {
        Button(
          onClick = {
            showSuccessDialog = false
            onWhatsAppInquiry(generatedMessage)
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366))
        ) {
          Text("Continue to WhatsApp", color = Color.White, fontWeight = FontWeight.Bold)
        }
      },
      dismissButton = {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
          OutlinedButton(
            onClick = {
              clipboardManager.setText(AnnotatedString(generatedMessage))
              Toast.makeText(context, "Application copied to clipboard!", Toast.LENGTH_SHORT).show()
            }
          ) {
            Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Copy")
          }

          OutlinedButton(
            onClick = { showSuccessDialog = false }
          ) {
            Text("Edit")
          }
        }
      }
    )
  }
}
