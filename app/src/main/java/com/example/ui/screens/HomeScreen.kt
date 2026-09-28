package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import com.example.data.PlacementImageView
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.filled.Work
import com.example.R
import com.example.data.Course
import com.example.data.CourseRepository
import com.example.data.InstituteInfo
import com.example.data.InstituteNotice
import com.example.data.PlacementStory
import com.example.ui.components.CourseCard

@Composable
fun HomeScreen(
  courses: List<Course> = CourseRepository.feeChartCourses,
  instituteInfo: InstituteInfo? = null,
  placementStories: List<PlacementStory> = emptyList(),
  instituteNotice: InstituteNotice? = null,
  onNavigateToCourses: () -> Unit,
  onNavigateToFees: () -> Unit,
  onNavigateToPlacements: () -> Unit,
  onNavigateToAbout: (() -> Unit)? = null,
  onNavigateToWhyUs: (() -> Unit)? = null,
  onNavigateToFaq: (() -> Unit)? = null,
  onNavigateToStudentPortal: (() -> Unit)? = null,
  onNavigateToJobApply: (() -> Unit)? = null,
  onCourseInquiry: (Course) -> Unit,
  onViewDetails: (Course) -> Unit,
  onContactClick: () -> Unit,
  onCallInstitute: () -> Unit,
  onWhatsAppInstitute: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "home_infinite_anim")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.04f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale"
  )
  val broadcastAlpha by infiniteTransition.animateFloat(
    initialValue = 0.55f,
    targetValue = 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "broadcast_alpha"
  )

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("home_screen"),
    contentPadding = PaddingValues(bottom = 90.dp)
  ) {
    // Institute Notice Banner (if enabled by Admin)
    if (instituteNotice != null && instituteNotice.isEnabled) {
      item {
        AnimatedVisibility(
          visible = true,
          enter = fadeIn(animationSpec = tween(300)) + expandVertically(animationSpec = tween(300)),
          exit = fadeOut(animationSpec = tween(200)) + shrinkVertically(animationSpec = tween(200))
        ) {
          Surface(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 8.dp)
              .clickable { onWhatsAppInstitute() }
              .testTag("home_notice_banner"),
            shape = RoundedCornerShape(12.dp),
            color = when (instituteNotice.urgency) {
              "Urgent" -> Color(0xFFFEF2F2)
              "Important" -> Color(0xFFFEF3C7)
              else -> Color(0xFFF0FDF4)
            },
            border = androidx.compose.foundation.BorderStroke(
              1.dp,
              when (instituteNotice.urgency) {
                "Urgent" -> Color(0xFFF87171)
                "Important" -> Color(0xFFFBBF24)
                else -> Color(0xFF34D399)
              }
            )
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.Campaign,
                contentDescription = null,
                tint = when (instituteNotice.urgency) {
                  "Urgent" -> Color(0xFFDC2626)
                  "Important" -> Color(0xFFD97706)
                  else -> Color(0xFF059669)
                },
                modifier = Modifier
                  .size(24.dp)
                  .alpha(broadcastAlpha)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Column(modifier = Modifier.weight(1f)) {
                Surface(
                  color = when (instituteNotice.urgency) {
                    "Urgent" -> Color(0xFFDC2626)
                    "Important" -> Color(0xFFD97706)
                    else -> Color(0xFF059669)
                  },
                  shape = RoundedCornerShape(4.dp)
                ) {
                  Text(
                    text = instituteNotice.badge,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                  )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = instituteNotice.message,
                  style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                  color = Color(0xFF1E293B)
                )
              }
            }
          }
        }
      }
    }
    // Hero Banner with Image
    item {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(240.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_institute_hero),
          contentDescription = "Nedian Connect Computer Training Lab",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Dark gradient overlay for text readability
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Black.copy(alpha = 0.3f),
                  Color.Black.copy(alpha = 0.75f)
                )
              )
            )
        )

        // Three.js Style Interactive 3D Hero Tech Mesh Animation
        ThreeDHeroCanvas(modifier = Modifier.fillMaxSize())

        // Hero Text & Badges
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
          verticalArrangement = Arrangement.Bottom
        ) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.scale(pulseScale)
          ) {
            Text(
              text = "Official Computer Training Institute",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
              ),
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
            )
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = instituteInfo?.name ?: "NEDIAN CONNECT\nINSTITUTE PVT. LTD.",
            style = MaterialTheme.typography.headlineSmall.copy(
              fontWeight = FontWeight.ExtraBold,
              color = Color.White,
              lineHeight = 28.sp
            )
          )

          Spacer(modifier = Modifier.height(6.dp))

          Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.clickable { onContactClick() }
            ) {
              Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFFFBBF24),
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = instituteInfo?.location ?: CourseRepository.INSTITUTE_LOCATION,
                style = MaterialTheme.typography.bodyMedium.copy(
                  color = Color.White.copy(alpha = 0.95f),
                  fontWeight = FontWeight.Medium
                )
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.clickable { onCallInstitute() }
            ) {
              Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null,
                tint = Color(0xFF34D399),
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = instituteInfo?.phone ?: CourseRepository.INSTITUTE_PHONE,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = Color.White.copy(alpha = 0.9f)
                )
              )
            }
          }
        }
      }
    }

    // Special Offer Alert Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp)
          .clickable { onNavigateToWhyUs?.invoke() ?: onNavigateToFees() }
          .testTag("card_special_offer"),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)
        )
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(44.dp)
              .scale(pulseScale)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.tertiary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.CardGiftcard,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(24.dp)
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = instituteInfo?.admissionBatchTag ?: "Limited Fee Offer 2026–2027",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
              )
            )
            Text(
              text = instituteInfo?.offerHeadline ?: "Bag & ID Card Free!",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
              )
            )
            Text(
              text = "Tap to see full benefits & fee discounts →",
              style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.9f),
                fontWeight = FontWeight.SemiBold
              )
            )
          }
        }
      }
    }

    // Quick Action Buttons
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Button(
          onClick = { onWhatsAppInstitute() },
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("home_btn_whatsapp"),
          shape = RoundedCornerShape(12.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
          )
        ) {
          Text("Inquire / WhatsApp", fontWeight = FontWeight.Bold)
        }

        OutlinedButton(
          onClick = onNavigateToFees,
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("home_btn_view_fees"),
          shape = RoundedCornerShape(12.dp)
        ) {
          Text("View Fee Chart", fontWeight = FontWeight.SemiBold)
        }
      }
    }

    // Quick Explore Shortcuts Grid (Direct 1-tap access to all app features)
    item {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp)
          .testTag("home_quick_nav_grid")
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Quick Explore",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground
            )
          )
          Text(
            text = "Tap to open",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Row 1: Courses & Fees
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          HomeShortcutCard(
            title = "All Courses",
            subtitle = "${courses.size} Programs",
            icon = Icons.Default.School,
            iconColor = Color(0xFF3B82F6),
            backgroundColor = Color(0xFFEFF6FF),
            onClick = onNavigateToCourses,
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_courses"
          )

          HomeShortcutCard(
            title = "Fee Structure",
            subtitle = "NPR 800 Admission",
            icon = Icons.Default.Payments,
            iconColor = Color(0xFF10B981),
            backgroundColor = Color(0xFFECFDF5),
            onClick = onNavigateToFees,
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_fees"
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 2: Placements & Why Us
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          HomeShortcutCard(
            title = "Placements",
            subtitle = "${placementStories.size} Alumni Placed",
            icon = Icons.Default.WorkspacePremium,
            iconColor = Color(0xFF8B5CF6),
            backgroundColor = Color(0xFFF5F3FF),
            onClick = onNavigateToPlacements,
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_placements"
          )

          HomeShortcutCard(
            title = "Why Us & Offers",
            subtitle = "Free Bag & ID Card",
            icon = Icons.Default.Star,
            iconColor = Color(0xFFF59E0B),
            backgroundColor = Color(0xFFFFFBEB),
            onClick = { onNavigateToWhyUs?.invoke() ?: onNavigateToFees() },
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_why_us"
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 3: Student Portal (ID Card & Quiz) & Contact / Branch
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          HomeShortcutCard(
            title = "English & Arabic",
            subtitle = "Online Live Classes",
            icon = Icons.Default.Language,
            iconColor = Color(0xFF0D9488),
            backgroundColor = Color(0xFFF0FDFA),
            onClick = { onNavigateToStudentPortal?.invoke() ?: onNavigateToCourses() },
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_online_classes"
          )

          HomeShortcutCard(
            title = "Contact Office",
            subtitle = "Chakarchauda, Nepal",
            icon = Icons.Default.LocationOn,
            iconColor = Color(0xFF06B6D4),
            backgroundColor = Color(0xFFECFEFF),
            onClick = onContactClick,
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_contact"
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Row 4: Careers & Job Apply
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          HomeShortcutCard(
            title = "Careers & Jobs",
            subtitle = "Apply Now",
            icon = Icons.Default.Work,
            iconColor = Color(0xFF6366F1),
            backgroundColor = Color(0xFFEEF2FF),
            onClick = { onNavigateToJobApply?.invoke() },
            modifier = Modifier.weight(1f),
            testTag = "home_shortcut_careers"
          )

          Box(modifier = Modifier.weight(1f))
        }
      }
    }

    // Prominent Career / Job Apply Banner Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 6.dp)
          .clickable { onNavigateToJobApply?.invoke() }
          .testTag("home_card_job_apply"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(48.dp)
              .scale(pulseScale)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Work,
              contentDescription = null,
              tint = Color.White,
              modifier = Modifier.size(24.dp)
            )
          }

          Spacer(modifier = Modifier.width(14.dp))

          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "Join Our Team • Job Apply",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onPrimaryContainer
                )
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.secondary
              ) {
                Text(
                  text = "Hiring 2026",
                  style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, fontWeight = FontWeight.Bold),
                  color = Color.White,
                  modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )
              }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Apply for Instructor, Accountant & Office roles instantly via WhatsApp.",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
            )
          }

          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
          )
        }
      }
    }




    // Featured Courses Section Header
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Featured Courses",
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.onBackground
            )
          )
          Text(
            text = "Most popular computer courses in Chakarchauda",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Text(
          text = "See All",
          style = MaterialTheme.typography.labelLarge.copy(
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
          ),
          modifier = Modifier
            .clickable { onNavigateToCourses() }
            .padding(4.dp)
            .testTag("home_btn_see_all_courses")
        )
      }
    }

    // Top 3 Featured Courses
    items(courses.take(3), key = { it.id }) { course ->
      Box(
        modifier = Modifier
          .animateItem()
          .padding(horizontal = 16.dp, vertical = 6.dp)
      ) {
        CourseCard(
          course = course,
          onViewDetails = onViewDetails,
          onApplyNow = { onCourseInquiry(it) },
          onContactForFee = { onCourseInquiry(it) }
        )
      }
    }

    // Placement Success Stories Showcase Section
    if (placementStories.isNotEmpty()) {
      item {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.WorkspacePremium,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Placement Success Stories",
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onBackground
                )
              )
            }
            Text(
              text = "Where our graduates are now working across Nepal",
              style = MaterialTheme.typography.bodySmall,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Text(
            text = "View All (${placementStories.size})",
            style = MaterialTheme.typography.labelMedium.copy(
              color = MaterialTheme.colorScheme.primary,
              fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
              .clickable { onNavigateToPlacements() }
              .padding(4.dp)
              .testTag("home_btn_view_all_placements")
          )
        }
      }

      // Horizontal Row of Placement Story Cards
      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(14.dp),
          modifier = Modifier.testTag("home_placements_carousel")
        ) {
          items(placementStories.take(6), key = { it.id }) { story ->
            Card(
              modifier = Modifier
                .width(280.dp)
                .clickable { onNavigateToPlacements() }
                .testTag("home_placement_card_${story.id}"),
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
              elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
              Column(modifier = Modifier.padding(14.dp)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(54.dp)
                      .clip(RoundedCornerShape(12.dp))
                      .border(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                  ) {
                    PlacementImageView(
                      imageResName = story.imageResName,
                      contentDescription = "Photo of ${story.studentName}",
                      modifier = Modifier.fillMaxSize(),
                      contentScale = ContentScale.Crop
                    )
                  }

                  Spacer(modifier = Modifier.width(10.dp))

                  Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Text(
                        text = story.studentName,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1
                      )
                      Spacer(modifier = Modifier.width(4.dp))
                      Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                      )
                    }

                    Text(
                      text = story.jobTitle,
                      style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                      ),
                      maxLines = 1
                    )

                    Text(
                      text = story.companyName,
                      style = MaterialTheme.typography.labelSmall,
                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                      maxLines = 1
                    )
                  }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                  ) {
                    Text(
                      text = story.courseCompleted,
                      style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                      color = MaterialTheme.colorScheme.onPrimaryContainer,
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }

                  if (!story.packageOrSalary.isNullOrBlank()) {
                    Surface(
                      shape = RoundedCornerShape(4.dp),
                      color = Color(0xFFD1FAE5)
                    ) {
                      Text(
                        text = story.packageOrSalary,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                        color = Color(0xFF065F46),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                      )
                    }
                  }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Text(
                    text = "\"${story.quote}\"",
                    style = MaterialTheme.typography.bodySmall.copy(
                      fontStyle = FontStyle.Italic,
                      lineHeight = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    modifier = Modifier.padding(8.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    // Why Choose Us Highlight Box
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp)
          .clickable { onNavigateToWhyUs?.invoke() ?: onNavigateToFees() }
          .testTag("home_why_choose_us_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = androidx.compose.foundation.BorderStroke(
          1.dp,
          MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Why Choose Nedian Connect?",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
              )
            )
            Text(
              text = "Learn More →",
              style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
              )
            )
          }
          Spacer(modifier = Modifier.height(10.dp))

          val highlights = listOf(
            "Free Bag & ID Card with all major courses",
            "Affordable monthly fees starting from NPR 825/mo",
            "Discount offers up to NPR 900 on full fee payments",
            "Morning, Day, and Evening flexible class batches",
            "Industry-standard software: Tally Prime with GST & LibreOffice",
            "Conveniently located at Chakarchauda, Nepal"
          )

          highlights.forEach { highlight ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = highlight,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        }
      }
    }

    // Direct Contact Card
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Text(
            text = "Need Help Choosing a Course?",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Talk directly with our faculty for guidance on duration, syllabus, and seat availability.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Spacer(modifier = Modifier.height(12.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = onCallInstitute,
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp)
            ) {
              Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Call Now")
            }

            OutlinedButton(
              onClick = onWhatsAppInstitute,
              modifier = Modifier.weight(1f),
              shape = RoundedCornerShape(10.dp)
            ) {
              Text("WhatsApp")
            }
          }
        }
      }
    }
  }
}

@Composable
private fun ThreeDHeroCanvas(modifier: Modifier = Modifier) {
  val infiniteTransition = rememberInfiniteTransition(label = "3d_hero_anim")
  val rotationAngle by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = 360f,
    animationSpec = infiniteRepeatable(
      animation = tween(12000, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "hero_rot"
  )
  val pulseRadius by infiniteTransition.animateFloat(
    initialValue = 20f,
    targetValue = 45f,
    animationSpec = infiniteRepeatable(
      animation = tween(2000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "hero_pulse"
  )

  Canvas(modifier = modifier) {
    val centerX = size.width * 0.8f
    val centerY = size.height * 0.5f
    val baseRadius = 60.dp.toPx()

    val numNodes = 8
    val rad = Math.toRadians(rotationAngle.toDouble())

    for (i in 0 until numNodes) {
      val angle = rad + (i * 2.0 * Math.PI / numNodes)
      val x = centerX + (baseRadius * kotlin.math.cos(angle)).toFloat()
      val y = centerY + (baseRadius * 0.6f * kotlin.math.sin(angle)).toFloat()
      
      drawLine(
        color = Color(0xFF38BDF8).copy(alpha = 0.4f),
        start = androidx.compose.ui.geometry.Offset(centerX, centerY),
        end = androidx.compose.ui.geometry.Offset(x, y),
        strokeWidth = 1.5f
      )

      drawCircle(
        color = if (i % 2 == 0) Color(0xFF6366F1) else Color(0xFFEC4899),
        radius = 6.dp.toPx(),
        center = androidx.compose.ui.geometry.Offset(x, y)
      )
    }

    drawCircle(
      color = Color(0xFF06B6D4).copy(alpha = 0.3f),
      radius = pulseRadius * 1.5f,
      center = androidx.compose.ui.geometry.Offset(centerX, centerY)
    )
    drawCircle(
      color = Color(0xFF818CF8),
      radius = pulseRadius * 0.7f,
      center = androidx.compose.ui.geometry.Offset(centerX, centerY)
    )
  }
}

@Composable
private fun HomeShortcutCard(
  title: String,
  subtitle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  iconColor: Color,
  backgroundColor: Color,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  testTag: String = ""
) {
  Card(
    modifier = modifier
      .clickable(onClick = onClick)
      .testTag(testTag),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
    )
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Surface(
        shape = RoundedCornerShape(10.dp),
        color = backgroundColor,
        modifier = Modifier.size(42.dp)
      ) {
        Box(contentAlignment = Alignment.Center) {
          Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconColor,
            modifier = Modifier.size(22.dp)
          )
        }
      }

      Spacer(modifier = Modifier.width(10.dp))

      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp
          ),
          color = MaterialTheme.colorScheme.onSurface,
          maxLines = 1
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          ),
          maxLines = 1
        )
      }
    }
  }
}
