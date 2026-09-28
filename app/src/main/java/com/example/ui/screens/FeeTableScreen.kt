package com.example.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Course
import com.example.data.CourseRepository
import java.text.NumberFormat
import java.util.Locale

@Composable
fun FeeTableScreen(
  courses: List<Course> = CourseRepository.feeChartCourses,
  onApplyCourse: (Course) -> Unit,
  onWhatsAppInquiry: () -> Unit,
  modifier: Modifier = Modifier
) {
  val nf = NumberFormat.getNumberInstance(Locale.US)

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("fee_table_screen"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Header section
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Icon(
            imageVector = Icons.Default.LocalOffer,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(14.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Limited Fee Offer 2026–2027",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.secondary
            )
          )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
          text = "Course Fees & Duration",
          style = MaterialTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.primary
          )
        )
        Text(
          text = "Official verified fee chart of NEDIAN CONNECT INSTITUTE PVT. LTD. All fees shown in NPR.",
          style = MaterialTheme.typography.bodyMedium,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }

    // Special Offer Highlight Banner
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 4.dp)
          .clickable { onWhatsAppInquiry() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f)
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
        )
      ) {
        Row(
          modifier = Modifier.padding(12.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Icon(
            imageVector = Icons.Default.CardGiftcard,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(10.dp))
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "Offer: Bag & ID Card Free!",
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
              )
            )
            Text(
              text = "Complimentary student kit provided with enrollment. Tap to claim on WhatsApp →",
              style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.85f),
                fontWeight = FontWeight.Medium
              )
            )
          }
        }
      }
    }

    // Horizontal Scrollable Comparison Table
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp, bottom = 8.dp)
      ) {
        Text(
          text = "Interactive Fee Comparison Table",
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
          ),
          modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        Text(
          text = "Swipe horizontally to compare fee components side-by-side:",
          style = MaterialTheme.typography.bodySmall,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        // Scrollable Table Container
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
          ),
          border = androidx.compose.foundation.BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
          )
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .horizontalScroll(rememberScrollState())
              .padding(12.dp)
          ) {
            Column {
              // Table Header Row
              Row(
                modifier = Modifier
                  .background(
                    MaterialTheme.colorScheme.primaryContainer,
                    RoundedCornerShape(8.dp)
                  )
                  .padding(vertical = 10.dp, horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                TableCell("Course", width = 140.dp, isHeader = true)
                TableCell("Duration", width = 85.dp, isHeader = true)
                TableCell("Admission", width = 85.dp, isHeader = true)
                TableCell("Monthly", width = 80.dp, isHeader = true)
                TableCell("Total Mo.", width = 85.dp, isHeader = true)
                TableCell("Discount", width = 80.dp, isHeader = true)
                TableCell("Final Total", width = 95.dp, isHeader = true)
                TableCell("Offer", width = 110.dp, isHeader = true)
              }

              // Table Body Rows
              courses.forEachIndexed { index, course ->
                val rowBg = if (index % 2 == 0) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
                Row(
                  modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(rowBg)
                    .clickable { onApplyCourse(course) }
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  TableCell(course.name, width = 140.dp, isBold = true, subtitle = course.fullName)
                  TableCell(course.duration, width = 85.dp)
                  TableCell(course.admissionFee?.let { "NPR ${nf.format(it)}" } ?: "Contact", width = 85.dp)
                  TableCell(course.monthlyFee?.let { "NPR ${nf.format(it)}" } ?: "Contact", width = 80.dp)
                  TableCell(course.totalMonthlyFees?.let { "NPR ${nf.format(it)}" } ?: "Contact", width = 85.dp)
                  TableCell(
                    if ((course.discount ?: 0) > 0) "- NPR ${course.discount?.let { nf.format(it) }}" else "NPR 0",
                    width = 80.dp,
                    color = if ((course.discount ?: 0) > 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
                  )
                  TableCell(
                    course.finalTotalFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
                    width = 95.dp,
                    isBold = true,
                    color = MaterialTheme.colorScheme.primary
                  )
                  TableCell(course.offer ?: "—", width = 110.dp, isBold = true, color = MaterialTheme.colorScheme.tertiary)
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
              }
            }
          }
        }
      }
    }

    // Individual Course Breakdown Cards
    item {
      Text(
        text = "Detailed Fee Breakdowns",
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 4.dp)
      )
    }

    itemsIndexed(courses, key = { _, course -> course.id }) { index, course ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .animateItem()
          .animateContentSize(animationSpec = tween(300, easing = FastOutSlowInEasing))
          .padding(horizontal = 16.dp, vertical = 6.dp)
          .testTag("fee_card_${course.id}"),
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
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "${index + 1}. ${course.name}",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
              Text(
                text = course.fullName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = MaterialTheme.colorScheme.secondaryContainer
            ) {
              Text(
                text = course.duration,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSecondaryContainer
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Key Fee Values in 2-column grid
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            FeeMetricBox(
              label = "Admission Fee",
              value = course.admissionFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
              modifier = Modifier.weight(1f)
            )
            FeeMetricBox(
              label = "Monthly Fee",
              value = course.monthlyFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
              modifier = Modifier.weight(1f)
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            FeeMetricBox(
              label = "Total Monthly Fees",
              value = course.totalMonthlyFees?.let { "NPR ${nf.format(it)}" } ?: "Contact",
              modifier = Modifier.weight(1f)
            )
            FeeMetricBox(
              label = "Fee Less / Discount",
              value = if ((course.discount ?: 0) > 0) "NPR ${course.discount?.let { nf.format(it) }}" else "NPR 0",
              valueColor = if ((course.discount ?: 0) > 0) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.weight(1f)
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Final Total Highlight Bar
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f))
              .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "FINAL TOTAL FEE",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
              Text(
                text = course.offer ?: "Bag & ID Card Free",
                style = MaterialTheme.typography.bodySmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.tertiary
                )
              )
            }

            Text(
              text = course.finalTotalFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
              style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary
              )
            )
          }

          Spacer(modifier = Modifier.height(12.dp))

          Button(
            onClick = { onApplyCourse(course) },
            modifier = Modifier
              .fillMaxWidth()
              .height(44.dp)
              .testTag("btn_fee_apply_${course.id}"),
            shape = RoundedCornerShape(10.dp)
          ) {
            Text("Inquire / Enroll for ${course.name}", fontWeight = FontWeight.Bold)
          }
        }
      }
    }

    // Other Courses section
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 16.dp)
      ) {
        Text(
          text = "Other Courses (Contact Institute)",
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
          )
        )
        Text(
          text = "Fee & Duration: Contact Institute",
          style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold
          )
        )

        Spacer(modifier = Modifier.height(10.dp))

        CourseRepository.otherCourses.forEach { otherCourse ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 4.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
              containerColor = MaterialTheme.colorScheme.surface
            ),
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = otherCourse.name,
                  style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                  text = "Fee & Duration: Contact Institute",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }

              Button(
                onClick = onWhatsAppInquiry,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = MaterialTheme.colorScheme.secondary
                )
              ) {
                Text("Contact for Fee", style = MaterialTheme.typography.labelSmall)
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun TableCell(
  text: String,
  width: androidx.compose.ui.unit.Dp,
  isHeader: Boolean = false,
  isBold: Boolean = false,
  subtitle: String? = null,
  color: Color = Color.Unspecified
) {
  Column(
    modifier = Modifier
      .width(width)
      .padding(horizontal = 6.dp)
  ) {
    Text(
      text = text,
      style = if (isHeader) MaterialTheme.typography.labelMedium.copy(
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer
      ) else MaterialTheme.typography.bodySmall.copy(
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal,
        color = if (color != Color.Unspecified) color else MaterialTheme.colorScheme.onSurface
      )
    )
    if (subtitle != null) {
      Text(
        text = subtitle,
        style = MaterialTheme.typography.labelSmall.copy(
          fontSize = 10.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        maxLines = 1
      )
    }
  }
}

@Composable
private fun FeeMetricBox(
  label: String,
  value: String,
  modifier: Modifier = Modifier,
  valueColor: Color = Color.Unspecified
) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
    modifier = modifier
  ) {
    Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
      Text(
        text = label,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
      )
      Text(
        text = value,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontWeight = FontWeight.Bold,
          color = if (valueColor != Color.Unspecified) valueColor else MaterialTheme.colorScheme.onSurface
        )
      )
    }
  }
}
