package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalOffer
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Course
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CourseCard(
  course: Course,
  onViewDetails: (Course) -> Unit,
  onApplyNow: (Course) -> Unit,
  onContactForFee: (Course) -> Unit,
  modifier: Modifier = Modifier
) {
  val nf = NumberFormat.getNumberInstance(Locale.US)
  var isModulesExpanded by remember { mutableStateOf(false) }

  // Infinite animations for Course Cards
  val infiniteTransition = rememberInfiniteTransition(label = "course_card_anim")

  // Shimmer offset for animated top border gradient
  val shimmerOffset by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 600f,
    animationSpec = infiniteRepeatable(
      animation = tween(3000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "shimmer_offset"
  )

  // Subtle breathing pulse for gift badge & icons
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.06f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale"
  )

  // Rotating arrow state for modules expansion
  val arrowRotation by animateFloatAsState(
    targetValue = if (isModulesExpanded) 180f else 0f,
    animationSpec = tween(280, easing = FastOutSlowInEasing),
    label = "arrow_rot"
  )

  // 3D Tilt animation for premium interactive feel
  val cardInfiniteTransition = rememberInfiniteTransition(label = "course_card_3d")
  val tiltX by cardInfiniteTransition.animateFloat(
    initialValue = -1.5f,
    targetValue = 1.5f,
    animationSpec = infiniteRepeatable(
      animation = tween(4000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "tilt_x"
  )
  val tiltY by cardInfiniteTransition.animateFloat(
    initialValue = -1.5f,
    targetValue = 1.5f,
    animationSpec = infiniteRepeatable(
      animation = tween(5000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "tilt_y"
  )

  // Dynamic category icon
  val categoryIcon = when {
    course.category.contains("Accounting", ignoreCase = true) || course.name.contains("Tally", ignoreCase = true) -> Icons.Default.Calculate
    course.category.contains("Programming", ignoreCase = true) || course.name.contains("Python", ignoreCase = true) -> Icons.Default.Code
    course.category.contains("Design", ignoreCase = true) -> Icons.Default.Brush
    else -> Icons.Default.School
  }

  Card(
    modifier = modifier
      .fillMaxWidth()
      .graphicsLayer {
        cameraDistance = 14f * density
        rotationX = tiltX
        rotationY = tiltY
      }
      .animateContentSize(animationSpec = tween(300, easing = FastOutSlowInEasing))
      .testTag("course_card_${course.id}"),
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.surface
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
    )
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      // Animated Top Gradient Accent Stripe
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(4.dp)
          .background(
            Brush.horizontalGradient(
              colors = listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.tertiary,
                MaterialTheme.colorScheme.primary
              ),
              startX = shimmerOffset,
              endX = shimmerOffset + 600f
            )
          )
      )

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(18.dp)
      ) {
        // Top row: Category tag with Animated Icon & Duration badge
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.85f)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = categoryIcon,
                contentDescription = null,
                modifier = Modifier
                  .size(15.dp)
                  .scale(pulseScale),
                tint = MaterialTheme.colorScheme.primary
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = course.category,
                style = MaterialTheme.typography.labelMedium.copy(
                  color = MaterialTheme.colorScheme.onPrimaryContainer,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(MaterialTheme.colorScheme.surfaceVariant)
              .padding(horizontal = 10.dp, vertical = 5.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Schedule,
              contentDescription = "Duration",
              modifier = Modifier.size(14.dp),
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = course.duration,
              style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Course Title & Full Name
        Text(
          text = course.name,
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
          )
        )
        Text(
          text = course.fullName,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
          ),
          modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Fee Information Box
        if (course.hasConfirmedFee) {
          // Limited fee offer tag with animated sparkle
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f),
            modifier = Modifier.padding(bottom = 10.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                  .size(13.dp)
                  .scale(pulseScale)
              )
              Spacer(modifier = Modifier.width(5.dp))
              Text(
                text = course.offerTag ?: "Limited Fee Offer 2026–2027",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.secondary
                )
              )
            }
          }

          // Fee Breakdown Grid
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = "Admission Fee:",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                  text = course.admissionFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
                  style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              Spacer(modifier = Modifier.height(4.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = "Monthly Fee:",
                  style = MaterialTheme.typography.bodySmall,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                  text = course.monthlyFee?.let { "NPR ${nf.format(it)} / mo" } ?: "Contact",
                  style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              course.totalMonthlyFees?.let { totalMonthly ->
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Text(
                    text = "Total Monthly Fees:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                  Text(
                    text = "NPR ${nf.format(totalMonthly)}",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontWeight = FontWeight.Normal,
                      textDecoration = if ((course.discount ?: 0) > 0) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }

              if ((course.discount ?: 0) > 0) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "Fee Less / Discount:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                  )
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                  ) {
                    Text(
                      text = "- NPR ${course.discount?.let { nf.format(it) }} (SAVE)",
                      style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                      color = MaterialTheme.colorScheme.onTertiaryContainer,
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(8.dp))
              HorizontalDivider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
              )
              Spacer(modifier = Modifier.height(8.dp))

              // Prominently Highlighted Final Total Fee
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = "FINAL TOTAL FEE",
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.Bold,
                      letterSpacing = 0.8.sp,
                      color = MaterialTheme.colorScheme.primary
                    )
                  )
                }
                Text(
                  text = course.finalTotalFee?.let { "NPR ${nf.format(it)}" } ?: "Contact",
                  style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                  )
                )
              }
            }
          }

          // Animated Offer badge: Bag & ID Card Free
          course.offer?.let { offerText ->
            Spacer(modifier = Modifier.height(10.dp))
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.6f))
                .border(
                  1.dp,
                  MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f),
                  RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 10.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.CardGiftcard,
                contentDescription = "Special Offer",
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier
                  .size(16.dp)
                  .scale(pulseScale)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = offerText,
                style = MaterialTheme.typography.bodySmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onTertiaryContainer
                )
              )
            }
          }
        } else {
          // Other Courses without confirmed fees
          Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(
              modifier = Modifier.padding(16.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                  .size(24.dp)
                  .scale(pulseScale)
              )
              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = "Fee & Duration: Contact Institute",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = "Contact NEDIAN CONNECT INSTITUTE for customized duration and fee structure.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Expandable Course Highlights / Syllabus Toggle with Animation
        Surface(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { isModulesExpanded = !isModulesExpanded },
          shape = RoundedCornerShape(8.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.AutoAwesome,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (isModulesExpanded) "Hide Course Highlights" else "Show Syllabus & Lab Highlights",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.primary
                )
              )
            }

            Icon(
              imageVector = Icons.Default.KeyboardArrowDown,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier
                .size(18.dp)
                .rotate(arrowRotation)
            )
          }
        }

        AnimatedVisibility(
          visible = isModulesExpanded,
          enter = fadeIn(tween(260)) + expandVertically(tween(260)),
          exit = fadeOut(tween(200)) + shrinkVertically(tween(200))
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 8.dp, bottom = 4.dp)
          ) {
            if (course.description.isNotBlank()) {
              Text(
                text = course.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 8.dp)
              )
            }

            // Key Course Highlights
            val highlights = listOf(
              "100% Practical Computer Lab Training",
              "Personalized 1-on-1 Teacher Guidance",
              "Govt. & Industry Recognized Certificate",
              "Free Study Materials & Exam Preparation"
            )

            highlights.forEach { highlight ->
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 2.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = Color(0xFF10B981),
                  modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = highlight,
                  style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Action Buttons: [View Details] and [Apply Now] / [Contact for Fee]
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = { onViewDetails(course) },
            modifier = Modifier
              .weight(1f)
              .testTag("btn_details_${course.id}"),
            shape = RoundedCornerShape(10.dp)
          ) {
            Text(text = "View Details", style = MaterialTheme.typography.labelLarge)
          }

          if (course.hasConfirmedFee) {
            Button(
              onClick = { onApplyNow(course) },
              modifier = Modifier
                .weight(1f)
                .testTag("btn_apply_${course.id}"),
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
              )
            ) {
              Text(
                text = "Inquire / Enroll",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
              )
            }
          } else {
            Button(
              onClick = { onContactForFee(course) },
              modifier = Modifier
                .weight(1f)
                .testTag("btn_contact_${course.id}"),
              shape = RoundedCornerShape(10.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
              )
            ) {
              Text(
                text = "Contact for Fee",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
              )
            }
          }
        }
      }
    }
  }
}
