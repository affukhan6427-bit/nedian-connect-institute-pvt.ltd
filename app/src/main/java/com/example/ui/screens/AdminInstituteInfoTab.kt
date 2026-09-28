package com.example.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.InstituteInfo

@Composable
fun AdminInstituteInfoTab(
  info: InstituteInfo,
  onSaveInfo: (InstituteInfo) -> Unit,
  onResetInfo: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  var name by remember(info) { mutableStateOf(info.name) }
  var phone by remember(info) { mutableStateOf(info.phone) }
  var phoneRaw by remember(info) { mutableStateOf(info.phoneRaw) }
  var location by remember(info) { mutableStateOf(info.location) }
  var email by remember(info) { mutableStateOf(info.email) }
  var officeHours by remember(info) { mutableStateOf(info.officeHours) }
  var offerHeadline by remember(info) { mutableStateOf(info.offerHeadline) }
  var admissionBatchTag by remember(info) { mutableStateOf(info.admissionBatchTag) }
  var tagline by remember(info) { mutableStateOf(info.tagline) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp)
      .testTag("admin_institute_info_tab"),
    contentPadding = PaddingValues(bottom = 80.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
      ) {
        Row(
          modifier = Modifier.padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(44.dp)
          ) {
            Box(contentAlignment = Alignment.Center) {
              Icon(
                imageVector = Icons.Default.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
              )
            }
          }
          Spacer(modifier = Modifier.width(14.dp))
          Column {
            Text(
              text = "Institute Info & Branding",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Customize institute contact numbers, address, email, and headlines.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    item {
      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Institute Legal Name") },
        leadingIcon = { Icon(Icons.Default.Business, contentDescription = null) },
        modifier = Modifier.fillMaxWidth().testTag("info_name_input"),
        singleLine = true
      )
    }

    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        OutlinedTextField(
          value = phone,
          onValueChange = { phone = it },
          label = { Text("Display Phone") },
          leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
          modifier = Modifier.weight(1f).testTag("info_phone_input"),
          singleLine = true
        )

        OutlinedTextField(
          value = phoneRaw,
          onValueChange = { phoneRaw = it },
          label = { Text("Raw Dial Phone") },
          modifier = Modifier.weight(1f),
          singleLine = true
        )
      }
    }

    item {
      OutlinedTextField(
        value = location,
        onValueChange = { location = it },
        label = { Text("Campus Location & Address") },
        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
        modifier = Modifier.fillMaxWidth().testTag("info_location_input"),
        singleLine = true
      )
    }

    item {
      OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Official Email") },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
        modifier = Modifier.fillMaxWidth().testTag("info_email_input"),
        singleLine = true
      )
    }

    item {
      OutlinedTextField(
        value = officeHours,
        onValueChange = { officeHours = it },
        label = { Text("Office Working Hours") },
        leadingIcon = { Icon(Icons.Default.Schedule, contentDescription = null) },
        modifier = Modifier.fillMaxWidth().testTag("info_hours_input"),
        singleLine = true
      )
    }

    item {
      OutlinedTextField(
        value = offerHeadline,
        onValueChange = { offerHeadline = it },
        label = { Text("Admission Offer Tagline (e.g. Free Bag & ID Card)") },
        modifier = Modifier.fillMaxWidth().testTag("info_offer_input"),
        singleLine = true
      )
    }

    item {
      OutlinedTextField(
        value = admissionBatchTag,
        onValueChange = { admissionBatchTag = it },
        label = { Text("Batch Open Tagline") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )
    }

    item {
      OutlinedTextField(
        value = tagline,
        onValueChange = { tagline = it },
        label = { Text("App Tagline / Subtitle") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )
    }

    // Action Buttons
    item {
      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedButton(
          onClick = {
            onResetInfo()
            Toast.makeText(context, "Institute information reset to default", Toast.LENGTH_SHORT).show()
          },
          modifier = Modifier.weight(1f).height(48.dp),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(imageVector = Icons.Default.Restore, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Reset")
        }

        Button(
          onClick = {
            val updatedInfo = InstituteInfo(
              name = name.trim(),
              phone = phone.trim(),
              phoneRaw = phoneRaw.trim(),
              location = location.trim(),
              email = email.trim(),
              officeHours = officeHours.trim(),
              offerHeadline = offerHeadline.trim(),
              admissionBatchTag = admissionBatchTag.trim(),
              tagline = tagline.trim()
            )
            onSaveInfo(updatedInfo)
            Toast.makeText(context, "Institute details successfully saved!", Toast.LENGTH_SHORT).show()
          },
          modifier = Modifier
            .weight(2f)
            .height(48.dp)
            .testTag("save_institute_info_btn"),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text("Save Institute Info", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}
