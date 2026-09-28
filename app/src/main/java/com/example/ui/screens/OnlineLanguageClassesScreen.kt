package com.example.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnlineLanguageClassesScreen(
  onEnrollWhatsApp: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(MaterialTheme.colorScheme.background),
    contentPadding = PaddingValues(bottom = 100.dp, start = 16.dp, end = 16.dp, top = 16.dp)
  ) {
    // Hero Header Banner
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              brush = Brush.linearGradient(
                colors = listOf(
                  Color(0xFF1E3A8A), // Deep Blue
                  Color(0xFF0D9488), // Teal
                  Color(0xFF047857)  // Emerald
                )
              )
            )
            .padding(24.dp)
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
          ) {
            Surface(
              shape = CircleShape,
              color = Color.White.copy(alpha = 0.2f),
              modifier = Modifier.size(64.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Icon(
                  imageVector = Icons.Default.Language,
                  contentDescription = null,
                  tint = Color.White,
                  modifier = Modifier.size(36.dp)
                )
              }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = "Online English & Arabic Classes",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 0.5.sp
              ),
              color = Color.White,
              textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = "Master Spoken English, Grammar & Professional Arabic from expert instructors. Live interactive classes with 100% spoken fluency guarantee!",
              style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
              color = Color.White.copy(alpha = 0.9f),
              textAlign = TextAlign.Center
            )
          }
        }
      }
    }

    // Class 1: Online Spoken English & Grammar
    item {
      LanguageClassCard(
        title = "Online Spoken English & Grammar Masterclass",
        targetAudience = "For Students, Job Seekers & Professionals",
        duration = "3 Months / 6 Months Batches",
        fee = "NPR 2,500 (Full Course)",
        features = listOf(
          "Daily Live Spoken Practice & Conversation Drills",
          "Grammar, Vocabulary & Pronunciation Correction",
          "Interview Preparation & Professional Resume Writing",
          "Flexible Morning & Evening Batch Timings"
        ),
        badgeText = "Most Popular",
        badgeColor = Color(0xFF2563EB),
        onEnroll = {
          onEnrollWhatsApp("Hello Nedian Connect Institute, I would like to enroll in the Online Spoken English & Grammar Masterclass.")
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    // Class 2: Online Arabic Language & Quranic Grammar
    item {
      LanguageClassCard(
        title = "Online Arabic Language & Spoken Course",
        targetAudience = "For Beginners, Students & Islamic Studies",
        duration = "3 Months / 6 Months Batches",
        fee = "NPR 2,500 (Full Course)",
        features = listOf(
          "Learn Arabic Alphabet, Reading, Writing & Spoken Fluency",
          "Quranic Vocabulary & Classical Grammar Basics",
          "Step-by-Step Audio & Video Interactive Sessions",
          "Dedicated Mentor Support & Doubt Clearing Classes"
        ),
        badgeText = "Special Certificate",
        badgeColor = Color(0xFF059669),
        onEnroll = {
          onEnrollWhatsApp("Hello Nedian Connect Institute, I would like to enroll in the Online Arabic Language & Spoken Course.")
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
    }

    // Why Choose Our Online Classes
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          Text(
            text = "Why Join Our Online Language Classes?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.primary
          )
          Spacer(modifier = Modifier.height(12.dp))
          FeatureRow(text = "Live interactive Zoom / WhatsApp online sessions with personal attention.")
          FeatureRow(text = "Affordable fee structure with certification upon successful completion.")
          FeatureRow(text = "Recorded video lectures available for revision anytime.")
          FeatureRow(text = "Special focus on building confidence for interviews and public speaking.")
        }
      }
    }
  }
}

@Composable
fun LanguageClassCard(
  title: String,
  targetAudience: String,
  duration: String,
  fee: String,
  features: List<String>,
  badgeText: String,
  badgeColor: Color,
  onEnroll: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = badgeColor
        ) {
          Text(
            text = badgeText,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
        Text(
          text = fee,
          style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
          color = MaterialTheme.colorScheme.primary
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
        color = MaterialTheme.colorScheme.onSurface
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "👥 $targetAudience | ⏱️ $duration",
        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )

      Spacer(modifier = Modifier.height(14.dp))

      features.forEach { feature ->
        Row(
          modifier = Modifier.padding(vertical = 4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF10B981),
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = feature,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      Button(
        onClick = onEnroll,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(12.dp)
      ) {
        Icon(Icons.Default.Call, contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Enroll via WhatsApp / Call Now", fontWeight = FontWeight.ExtraBold)
      }
    }
  }
}

@Composable
fun FeatureRow(text: String) {
  Row(
    modifier = Modifier.padding(vertical = 6.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      imageVector = Icons.Default.Verified,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(20.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = text,
      style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
      color = MaterialTheme.colorScheme.onSurface
    )
  }
}
