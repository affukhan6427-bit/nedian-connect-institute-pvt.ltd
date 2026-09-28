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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WhyChooseUsScreen(
  onWhatsAppInquiry: () -> Unit,
  modifier: Modifier = Modifier
) {
  val benefits = listOf(
    BenefitItem(
      title = "Free Bag & Student ID Card",
      description = "Every student enrolled in our Diploma and Certificate programs receives an official institute bag and student ID card completely free of charge.",
      icon = Icons.Default.CardGiftcard
    ),
    BenefitItem(
      title = "Transparent & Verified Fee Structure",
      description = "Fixed admission fee of NPR 800 with affordable monthly installments starting from NPR 825/month. Discounts up to NPR 900 for upfront full fee payments.",
      icon = Icons.Default.AccountBalanceWallet
    ),
    BenefitItem(
      title = "Practical Training Lab Focus",
      description = "Dedicated lab time for each student to practice hands-on coding, typing, accounting entries, and digital publishing tools.",
      icon = Icons.Default.School
    ),
    BenefitItem(
      title = "Flexible Daily Batch Shifts",
      description = "Choose your preferred class schedule among Morning, Day, and Evening batches to comfortably balance school, college, or work.",
      icon = Icons.Default.Schedule
    ),
    BenefitItem(
      title = "Industry-Relevant Syllabus",
      description = "Master in-demand computer skills including Tally Prime & ERP 9 with GST, LibreOffice Suite, DTP, ADCA, DCA, and spoken language skills.",
      icon = Icons.Default.School
    ),
    BenefitItem(
      title = "Instant WhatsApp Support & Admission",
      description = "Submit your application online and directly receive admissions support and verification through our WhatsApp helpline at +977 9705508838.",
      icon = Icons.Default.Verified
    )
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("why_choose_us_screen"),
    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 90.dp)
  ) {
    item {
      Column(modifier = Modifier.fillMaxWidth()) {
        Surface(
          shape = RoundedCornerShape(6.dp),
          color = MaterialTheme.colorScheme.secondaryContainer,
          modifier = Modifier.padding(bottom = 6.dp)
        ) {
          Text(
            text = "Excellence in Computer Education",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = "Why Choose Nedian Connect?",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
          )
        )
        Text(
          text = "Discover how NEDIAN CONNECT INSTITUTE PVT. LTD. empowers students in Chakarchauda with real computer skills.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }

    items(benefits.size) { index ->
      val benefit = benefits[index]
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
        )
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.Top
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = benefit.icon,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(24.dp)
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = benefit.title,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = benefit.description,
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 18.sp
            )
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(16.dp))
      Button(
        onClick = onWhatsAppInquiry,
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp),
        shape = RoundedCornerShape(12.dp)
      ) {
        Text("Inquire on WhatsApp Now", fontWeight = FontWeight.Bold)
      }
    }
  }
}

private data class BenefitItem(
  val title: String,
  val description: String,
  val icon: ImageVector
)
