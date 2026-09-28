package com.example

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.Course
import com.example.data.CourseRepository
import com.example.data.InstituteRepository
import com.example.ui.components.AppTopBar
import com.example.ui.components.CourseDetailDialog
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.CoursesScreen
import com.example.ui.screens.FaqScreen
import com.example.ui.screens.FeeTableScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.JobApplyScreen
import com.example.ui.screens.OnlineLanguageClassesScreen
import com.example.ui.screens.PlacementsScreen
import com.example.ui.screens.WhyChooseUsScreen
import com.example.ui.theme.NedianTheme
import kotlinx.coroutines.launch

enum class Screen(val title: String, val icon: ImageVector) {
  HOME("Home", Icons.Default.Home),
  FEES("Fees Table", Icons.Default.LocalOffer),
  COURSES("Courses", Icons.Default.School),
  PLACEMENTS("Placements", Icons.Default.WorkspacePremium),
  JOB_APPLY("Careers & Jobs", Icons.Default.Work),
  ONLINE_CLASSES("English & Arabic", Icons.Default.Language),
  ABOUT("About", Icons.Default.Info),
  WHY_US("Why Us", Icons.Default.Star),
  FAQ("FAQ", Icons.AutoMirrored.Filled.Help),
  CONTACT("Contact", Icons.Default.Call),
  ADMIN("Admin Panel", Icons.Default.AdminPanelSettings)
}

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val context = LocalContext.current
      val repository = remember { InstituteRepository(context) }
      val themeSettings by repository.themeSettings.collectAsStateWithLifecycle()
      val systemDark = androidx.compose.foundation.isSystemInDarkTheme()
      val isDark = when (themeSettings.themeMode) {
        "DARK" -> true
        "LIGHT" -> false
        else -> systemDark
      }

      NedianTheme(
        darkTheme = isDark,
        themePalette = themeSettings.paletteName
      ) {
        NedianConnectApp(repository = repository)
      }
    }
  }
}

@Composable
fun NedianConnectApp(
  repository: InstituteRepository = InstituteRepository(LocalContext.current)
) {
  val context = LocalContext.current
  val scope = rememberCoroutineScope()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

  val placementStories by repository.placementStories.collectAsStateWithLifecycle()
  val instituteNotice by repository.instituteNotice.collectAsStateWithLifecycle()
  val studentInquiries by repository.studentInquiries.collectAsStateWithLifecycle()
  val courses by repository.courses.collectAsStateWithLifecycle()
  val instituteInfo by repository.instituteInfo.collectAsStateWithLifecycle()
  val themeSettings by repository.themeSettings.collectAsStateWithLifecycle()

  var currentScreen by remember { mutableStateOf(Screen.HOME) }
  var selectedCourseForDetail by remember { mutableStateOf<Course?>(null) }

  // Handle back press to navigate to Home before exiting
  BackHandler(enabled = currentScreen != Screen.HOME || drawerState.isOpen) {
    if (drawerState.isOpen) {
      scope.launch { drawerState.close() }
    } else {
      currentScreen = Screen.HOME
    }
  }

  val activePhone = instituteInfo.phoneRaw.ifBlank { instituteInfo.phone }

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      ModalDrawerSheet(
        modifier = Modifier.width(310.dp)
      ) {
        // Drawer Header with Institute Branding
        Surface(
          color = MaterialTheme.colorScheme.primary,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(20.dp)) {
            var secretTapCount by remember { mutableStateOf(0) }
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color.White,
              shadowElevation = 2.dp,
              modifier = Modifier
                .size(56.dp)
                .clickable {
                  secretTapCount++
                  if (secretTapCount >= 7) {
                    secretTapCount = 0
                    currentScreen = Screen.ADMIN
                    scope.launch { drawerState.close() }
                  }
                }
            ) {
              Box(
                modifier = Modifier.fillMaxSize().padding(3.dp),
                contentAlignment = Alignment.Center
              ) {
                Image(
                  painter = painterResource(id = R.drawable.app_logo),
                  contentDescription = "Institute Logo",
                  modifier = Modifier.fillMaxSize(),
                  contentScale = ContentScale.Fit
                )
              }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
              text = instituteInfo.name,
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                letterSpacing = 0.5.sp
              )
            )
            Text(
              text = instituteInfo.tagline,
              style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold
              )
            )
            Text(
              text = "📍 ${instituteInfo.location}",
              style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White.copy(alpha = 0.85f)
              )
            )
            Text(
              text = "📞 ${instituteInfo.phone}",
              style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White.copy(alpha = 0.85f)
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Navigation Drawer items (Public student screens)
        val studentScreens = listOf(
          Screen.HOME,
          Screen.FEES,
          Screen.COURSES,
          Screen.PLACEMENTS,
          Screen.JOB_APPLY,
          Screen.ONLINE_CLASSES,
          Screen.ABOUT,
          Screen.WHY_US,
          Screen.FAQ,
          Screen.CONTACT
        )

        studentScreens.forEach { screen ->
          NavigationDrawerItem(
            label = {
              Text(
                text = screen.title,
                fontWeight = if (currentScreen == screen) FontWeight.Bold else FontWeight.Medium
              )
            },
            icon = {
              Icon(
                imageVector = screen.icon,
                contentDescription = screen.title,
                tint = if (currentScreen == screen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
              )
            },
            selected = currentScreen == screen,
            onClick = {
              currentScreen = screen
              scope.launch { drawerState.close() }
            },
            modifier = Modifier
              .padding(NavigationDrawerItemDefaults.ItemPadding)
              .testTag("drawer_item_${screen.name.lowercase()}"),
            colors = NavigationDrawerItemDefaults.colors(
              selectedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
            )
          )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // Quick External Actions
        NavigationDrawerItem(
          label = { Text("Chat on WhatsApp", fontWeight = FontWeight.SemiBold, color = Color(0xFF15803D)) },
          icon = {
            Surface(shape = CircleShape, color = Color(0xFF25D366), modifier = Modifier.size(24.dp)) {
              Box(contentAlignment = Alignment.Center) {
                Text("WA", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
              }
            }
          },
          selected = false,
          onClick = {
            scope.launch { drawerState.close() }
            openWhatsApp(context, CourseRepository.INSTITUTE_PHONE)
          },
          modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        NavigationDrawerItem(
          label = { Text("Call Institute", fontWeight = FontWeight.SemiBold) },
          icon = {
            Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
          },
          selected = false,
          onClick = {
            scope.launch { drawerState.close() }
            dialPhone(context, CourseRepository.INSTITUTE_PHONE)
          },
          modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        NavigationDrawerItem(
          label = { Text("Get Directions", fontWeight = FontWeight.SemiBold) },
          icon = {
            Icon(imageVector = Icons.Default.Directions, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
          },
          selected = false,
          onClick = {
            scope.launch { drawerState.close() }
            openDirections(context, "Chakarchauda, Nepal")
          },
          modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
      }
    }
  ) {
    Scaffold(
      modifier = Modifier
        .fillMaxSize()
        .testTag("main_scaffold"),
      topBar = {
        AppTopBar(
          onCallClick = { dialPhone(context, activePhone) },
          onWhatsAppClick = { openWhatsApp(context, activePhone) },
          onMenuClick = {
            scope.launch {
              if (drawerState.isOpen) drawerState.close() else drawerState.open()
            }
          }
        )
      },
      bottomBar = {
        NavigationBar(
          containerColor = MaterialTheme.colorScheme.surface,
          tonalElevation = 6.dp,
          modifier = Modifier.testTag("bottom_nav_bar")
        ) {
          val bottomScreens = listOf(
            Screen.HOME,
            Screen.FEES,
            Screen.COURSES,
            Screen.ABOUT,
            Screen.CONTACT
          )

          bottomScreens.forEach { screen ->
            val isSelected = currentScreen == screen
            NavigationBarItem(
              selected = isSelected,
              onClick = { currentScreen = screen },
              icon = {
                Icon(
                  imageVector = screen.icon,
                  contentDescription = screen.title
                )
              },
              label = {
                Text(
                  text = screen.title,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 10.sp
                  )
                )
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                indicatorColor = MaterialTheme.colorScheme.primaryContainer
              ),
              modifier = Modifier.testTag("bottom_nav_${screen.name.lowercase()}")
            )
          }
        }
      }
    ) { innerPadding ->
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
      ) {
        AnimatedContent(
          targetState = currentScreen,
          transitionSpec = {
            (fadeIn(animationSpec = tween(280, easing = FastOutSlowInEasing)) +
              slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Up,
                animationSpec = tween(280, easing = FastOutSlowInEasing),
                initialOffset = { it / 14 }
              )) togetherWith (
              fadeOut(animationSpec = tween(200, easing = FastOutSlowInEasing)) +
                slideOutOfContainer(
                  towards = AnimatedContentTransitionScope.SlideDirection.Down,
                  animationSpec = tween(200, easing = FastOutSlowInEasing),
                  targetOffset = { it / 18 }
                )
            )
          },
          label = "screen_transition"
        ) { targetScreen ->
          when (targetScreen) {
            Screen.HOME -> {
            HomeScreen(
              courses = courses,
              instituteInfo = instituteInfo,
              placementStories = placementStories,
              instituteNotice = instituteNotice,
              onNavigateToCourses = { currentScreen = Screen.COURSES },
              onNavigateToFees = { currentScreen = Screen.FEES },
              onNavigateToPlacements = { currentScreen = Screen.PLACEMENTS },
              onNavigateToAbout = { currentScreen = Screen.ABOUT },
              onNavigateToWhyUs = { currentScreen = Screen.WHY_US },
              onNavigateToFaq = { currentScreen = Screen.FAQ },
              onNavigateToStudentPortal = { currentScreen = Screen.ONLINE_CLASSES },
              onNavigateToJobApply = { currentScreen = Screen.JOB_APPLY },
              onCourseInquiry = { course ->
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to enroll/inquire about ${course.name} (${course.fullName})."
                )
              },
              onViewDetails = { course ->
                selectedCourseForDetail = course
              },
              onContactClick = {
                currentScreen = Screen.CONTACT
              },
              onCallInstitute = { dialPhone(context, activePhone) },
              onWhatsAppInstitute = { openWhatsApp(context, activePhone) },
            )
          }

          Screen.FEES -> {
            FeeTableScreen(
              courses = courses,
              onApplyCourse = { course ->
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to enroll/inquire about ${course.name} (${course.fullName})."
                )
              },
              onWhatsAppInquiry = {
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to inquire about course fees."
                )
              }
            )
          }

          Screen.COURSES -> {
            CoursesScreen(
              courses = courses,
              onViewDetails = { course -> selectedCourseForDetail = course },
              onApplyNow = { course ->
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to enroll/inquire about ${course.name} (${course.fullName})."
                )
              },
              onContactForFee = { course ->
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, please share fee and duration details for ${course.name} (${course.fullName})."
                )
              }
            )
          }

          Screen.ABOUT -> {
            AboutScreen(
              onCallInstitute = { dialPhone(context, activePhone) },
              onWhatsAppInstitute = { openWhatsApp(context, activePhone) }
            )
          }

          Screen.WHY_US -> {
            WhyChooseUsScreen(
              onWhatsAppInquiry = {
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to inquire about enrolling in a course."
                )
              }
            )
          }

          Screen.FAQ -> {
            FaqScreen(
              onWhatsAppInquiry = {
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I have a question regarding courses."
                )
              }
            )
          }

          Screen.CONTACT -> {
            ContactScreen(
              onCallNow = { dialPhone(context, activePhone) },
              onWhatsApp = { openWhatsApp(context, activePhone) },
              onScheduleAppointmentWhatsApp = { appointmentMessage ->
                openWhatsApp(context, activePhone, appointmentMessage)
              },
              onGetDirections = { openDirections(context, instituteInfo.location) }
            )
          }

          Screen.PLACEMENTS -> {
            PlacementsScreen(
              stories = placementStories,
              onWhatsAppInquiry = {
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to inquire about course placements and alumni career opportunities."
                )
              }
            )
          }

          Screen.JOB_APPLY -> {
            JobApplyScreen(
              onWhatsAppInquiry = { message ->
                openWhatsApp(context, activePhone, message)
              }
            )
          }

          Screen.ONLINE_CLASSES -> {
            OnlineLanguageClassesScreen(
              onEnrollWhatsApp = { courseTitle ->
                openWhatsApp(
                  context,
                  activePhone,
                  "Hello ${instituteInfo.name}, I would like to join the online live class for $courseTitle."
                )
              }
            )
          }

          Screen.ADMIN -> {
            AdminScreen(
              repository = repository,
              placementStories = placementStories,
              instituteNotice = instituteNotice,
              studentInquiries = studentInquiries,
              courses = courses,
              instituteInfo = instituteInfo,
              themeSettings = themeSettings,
              onCallStudent = { phone -> dialPhone(context, phone) },
              onWhatsAppStudent = { phone, name ->
                openWhatsApp(
                  context,
                  phone,
                  "Hello $name, this is from ${instituteInfo.name} regarding your computer course inquiry."
                )
              }
            )
          }
        }
      }

        // Course Detail Dialog
        selectedCourseForDetail?.let { detailCourse ->
          CourseDetailDialog(
            course = detailCourse,
            onDismiss = { selectedCourseForDetail = null },
            onCallOffice = {
              selectedCourseForDetail = null
              dialPhone(context, activePhone)
            },
            onWhatsAppInquiry = { course ->
              selectedCourseForDetail = null
              openWhatsApp(
                context,
                activePhone,
                "Hello ${instituteInfo.name}, please share fee and admission details for ${course.name} (${course.fullName})."
              )
            }
          )
        }
      }
    }
  }
}

private fun openWhatsApp(context: Context, phone: String, message: String? = null) {
  val cleanPhone = phone.replace("+", "").replace(" ", "").replace("-", "")
  val uri = if (!message.isNullOrBlank()) {
    val encoded = java.net.URLEncoder.encode(message, "UTF-8")
    Uri.parse("https://api.whatsapp.com/send?phone=$cleanPhone&text=$encoded")
  } else {
    Uri.parse("https://api.whatsapp.com/send?phone=$cleanPhone")
  }
  val intent = Intent(Intent.ACTION_VIEW, uri).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  }
  try {
    context.startActivity(intent)
  } catch (_: Exception) {
    try {
      val browserIntent = Intent(Intent.ACTION_VIEW, uri).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      }
      context.startActivity(browserIntent)
    } catch (_: Exception) {
      android.widget.Toast.makeText(
        context,
        "Could not open WhatsApp. Contact: $phone",
        android.widget.Toast.LENGTH_LONG
      ).show()
    }
  }
}

private fun dialPhone(context: Context, phone: String) {
  val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone")).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  }
  try {
    context.startActivity(intent)
  } catch (_: Exception) {
    android.widget.Toast.makeText(
      context,
      "Contact Phone: $phone",
      android.widget.Toast.LENGTH_LONG
    ).show()
  }
}

private fun openDirections(context: Context, query: String) {
  val encoded = Uri.encode(query)
  val mapUri = Uri.parse("geo:0,0?q=$encoded")
  val mapIntent = Intent(Intent.ACTION_VIEW, mapUri).apply {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  }
  try {
    context.startActivity(mapIntent)
  } catch (e: Exception) {
    val webUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$encoded")
    val webIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    try {
      context.startActivity(webIntent)
    } catch (_: Exception) {
      android.widget.Toast.makeText(
        context,
        "Location: $query",
        android.widget.Toast.LENGTH_LONG
      ).show()
    }
  }
}
