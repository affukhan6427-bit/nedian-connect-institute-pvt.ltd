package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CourseRepository

data class FaqItem(val question: String, val answer: String)

@Composable
fun FaqScreen(
  onWhatsAppInquiry: () -> Unit,
  modifier: Modifier = Modifier
) {
  val faqs = remember {
    listOf(
      FaqItem(
        question = "What are the fees and durations for computer courses?",
        answer = "Our courses range from 3 Months to 12 Months. For example, ADCA (12 Months) has a Final Total Fee of NPR 9,000 (after NPR 900 discount). DCA (6 Months) is NPR 4,500. CCC (3 Months) is NPR 3,000. All listed courses have an Admission Fee of NPR 800 and affordable monthly fees from NPR 825/month."
      ),
      FaqItem(
        question = "Is the Bag & ID Card really free?",
        answer = "Yes! Under our 'Limited Fee Offer 2026–2027', all students enrolled in ADCA, DCA, CCC, CCA, CFA, DTP, Accounting (Tally Prime), and LibreOffice receive an official Institute Bag and Student ID Card completely free."
      ),
      FaqItem(
        question = "What class timings and batches are available?",
        answer = "NEDIAN CONNECT INSTITUTE offers flexible shifts: Morning, Day, and Evening batches to suit students and working individuals."
      ),
      FaqItem(
        question = "How do I enroll in a course?",
        answer = "You can enroll by chatting with us on WhatsApp or calling our office directly at +977 9705508838. You can also visit our institute campus at Chakarchauda, Nepal for instant in-person enrollment."
      ),
      FaqItem(
        question = "How do I get fee details for Basic/Advanced Computer or Spoken Languages?",
        answer = "For courses like Basic Computer, Advanced Computer, English Speaking, Arabic Speaking, and English to Arabic Translation, please contact the institute directly at +977 9705508838 or via WhatsApp. Duration and fees will be provided based on your current level."
      ),
      FaqItem(
        question = "Where is NEDIAN CONNECT INSTITUTE located?",
        answer = "The institute is conveniently located at Chakarchauda, Nepal. You can reach us by phone or WhatsApp at +977 9705508838 for directions."
      )
    )
  }

  // Expanded state map
  val expandedState = remember {
    mutableStateMapOf<Int, Boolean>().apply {
      put(0, true) // Open first FAQ by default
      put(1, true)
    }
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("faq_screen"),
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
            text = "Help & Information",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }

        Text(
          text = "Frequently Asked Questions",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
          )
        )
        Text(
          text = "Find answers regarding admission procedures, fee chart breakdown, and batch schedules.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    item { Spacer(modifier = Modifier.height(16.dp)) }

    items(faqs.size) { index ->
      val faq = faqs[index]
      val isExpanded = expandedState[index] ?: false
      val chevronRotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(280, easing = FastOutSlowInEasing),
        label = "faq_chevron_rot"
      )

      Card(
        modifier = Modifier
          .fillMaxWidth()
          .animateItem()
          .animateContentSize(animationSpec = tween(280, easing = FastOutSlowInEasing))
          .padding(vertical = 5.dp)
          .clickable { expandedState[index] = !isExpanded },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = faq.question,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
              ),
              modifier = Modifier.weight(1f)
            )

            Icon(
              imageVector = Icons.Default.KeyboardArrowDown,
              contentDescription = if (isExpanded) "Collapse" else "Expand",
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier
                .size(24.dp)
                .rotate(chevronRotation)
            )
          }

          AnimatedVisibility(visible = isExpanded) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = faq.answer,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 20.sp
              )
            }
          }
        }
      }
    }

    item {
      Spacer(modifier = Modifier.height(20.dp))
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
      ) {
        Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "Still Have Questions?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Chat with our counselors on WhatsApp or call our office at +977 9705508838.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
          )
          Spacer(modifier = Modifier.height(12.dp))
          Button(
            onClick = onWhatsAppInquiry,
            shape = RoundedCornerShape(10.dp)
          ) {
            Text("Ask on WhatsApp")
          }
        }
      }
    }
  }
}
