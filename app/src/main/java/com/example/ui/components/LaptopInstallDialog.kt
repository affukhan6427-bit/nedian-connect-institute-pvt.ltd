package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

const val LAPTOP_WEB_APP_URL = "https://ais-pre-fhvbn4dtpk5vpmzwyfihvn-202956864369.asia-southeast1.run.app"

@Composable
fun LaptopInstallDialog(
  onDismiss: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current
  var selectedTab by remember { mutableIntStateOf(0) } // 0: Web App (Chrome/Edge), 1: BlueStacks / APK
  var isCopied by remember { mutableStateOf(false) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Card(
      modifier = modifier
        .fillMaxWidth(0.94f)
        .padding(vertical = 20.dp)
        .testTag("dialog_laptop_install"),
      shape = RoundedCornerShape(24.dp),
      colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface
      ),
      elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(20.dp)
      ) {
        // Header Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Surface(
              shape = CircleShape,
              color = MaterialTheme.colorScheme.primaryContainer,
              modifier = Modifier.size(46.dp)
            ) {
              Box(contentAlignment = Alignment.Center) {
                Icon(
                  imageVector = Icons.Default.Computer,
                  contentDescription = "Laptop / PC",
                  tint = MaterialTheme.colorScheme.primary,
                  modifier = Modifier.size(26.dp)
                )
              }
            }
            Column {
              Text(
                text = "Laptop / PC Install Guide",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.ExtraBold,
                  color = MaterialTheme.colorScheme.onSurface
                )
              )
              Text(
                text = "लैपटॉप व कंप्यूटर में कैसे चलाएं",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(36.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Close,
              contentDescription = "Close",
              tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Web Link Copy Box
        Surface(
          shape = RoundedCornerShape(14.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
          border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp)) {
            Text(
              text = "🌐 Laptop Web App Link:",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = LAPTOP_WEB_APP_URL,
              style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 11.sp
              ),
              maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Button(
                onClick = {
                  clipboardManager.setText(AnnotatedString(LAPTOP_WEB_APP_URL))
                  isCopied = true
                  Toast.makeText(context, "Link Copied! Laptop me Chrome me open karein.", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (isCopied) Color(0xFF16A34A) else MaterialTheme.colorScheme.primary
                )
              ) {
                Icon(
                  imageVector = if (isCopied) Icons.Default.Check else Icons.Default.ContentCopy,
                  contentDescription = null,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = if (isCopied) "Link Copied!" else "Copy Link",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold
                )
              }

              OutlinedButton(
                onClick = {
                  shareLinkViaWhatsApp(context, LAPTOP_WEB_APP_URL)
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Share,
                  contentDescription = null,
                  modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Send to Laptop",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tabs: Method 1 (Direct Chrome PWA) vs Method 2 (APK / Emulator)
        TabRow(
          selectedTabIndex = selectedTab,
          containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
          contentColor = MaterialTheme.colorScheme.primary,
          modifier = Modifier.clip(RoundedCornerShape(12.dp))
        ) {
          Tab(
            selected = selectedTab == 0,
            onClick = { selectedTab = 0 },
            text = {
              Text(
                text = "Method 1: Direct Web App\n(No Emulator, Easy)",
                fontSize = 11.sp,
                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Normal,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
              )
            }
          )
          Tab(
            selected = selectedTab == 1,
            onClick = { selectedTab = 1 },
            text = {
              Text(
                text = "Method 2: APK / Emulator\n(BlueStacks / WSA)",
                fontSize = 11.sp,
                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Normal,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
              )
            }
          )
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (selectedTab == 0) {
          // Method 1: Direct Web App / Chrome PWA
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = Color(0xFFF0FDF4),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF86EFAC)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = "⭐", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Sabse Aasan Tarika: Laptop me bina kisi emulator ke direct software ban jayega!",
                  fontSize = 11.sp,
                  color = Color(0xFF166534),
                  fontWeight = FontWeight.SemiBold
                )
              }
            }

            StepCard(
              stepNumber = "1",
              title = "Laptop me Link Open Karein",
              description = "Apne Laptop / PC me Google Chrome ya Microsoft Edge browser kholein aur upar diya gaya link open karein."
            )

            StepCard(
              stepNumber = "2",
              title = "'Install' Icon par click karein",
              description = "Chrome ke address bar (URL bar) me daayein (right) side me 'Install Nedian Connect' (📥) icon dikhega. Us par click karein.\n\nYa fir Chrome ke 3-dots (⋮) par click karein -> 'Save and Share' -> 'Install as app' select karein."
            )

            StepCard(
              stepNumber = "3",
              title = "Laptop Desktop par Shortcut ban gaya!",
              description = "Ab Nedian Connect ka icon aapke Windows Desktop aur Start Menu me aa jayega. Double click karke pura app laptop screen me chalayein!"
            )
          }
        } else {
          // Method 2: APK on BlueStacks / Windows 11
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Surface(
              shape = RoundedCornerShape(10.dp),
              color = Color(0xFFEFF6FF),
              border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF93C5FD)),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = "💡", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = "Agar aap Windows 11 ya BlueStacks me pura Android APK chalana chahte hain:",
                  fontSize = 11.sp,
                  color = Color(0xFF1E40AF),
                  fontWeight = FontWeight.SemiBold
                )
              }
            }

            StepCard(
              stepNumber = "1",
              title = "BlueStacks ya LDPlayer Install Karein",
              description = "Laptop me www.bluestacks.com se free Android emulator download karke install karein (Ya Windows 11 me Amazon Appstore / WSA use karein)."
            )

            StepCard(
              stepNumber = "2",
              title = "APK File Download Karein",
              description = "AI Studio ke Settings / Export menu se app ka .apk file download karein."
            )

            StepCard(
              stepNumber = "3",
              title = "APK ko BlueStacks me Open Karein",
              description = "Downloaded .apk file ko BlueStacks window par drag & drop karein ya double click karein. App install ho jayega aur computer par mouse-keyboard ke saath chalega!"
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Action Button: Open in Browser
        Button(
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(LAPTOP_WEB_APP_URL)).apply {
              addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            try {
              context.startActivity(intent)
            } catch (e: Exception) {
              Toast.makeText(context, "Link: $LAPTOP_WEB_APP_URL", Toast.LENGTH_LONG).show()
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("dialog_btn_open_web"),
          shape = RoundedCornerShape(12.dp)
        ) {
          Icon(imageVector = Icons.Default.OpenInBrowser, contentDescription = null)
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "Open Web Version Now", fontWeight = FontWeight.Bold)
        }
      }
    }
  }
}

@Composable
private fun StepCard(
  stepNumber: String,
  title: String,
  description: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .background(
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f),
        RoundedCornerShape(12.dp)
      )
      .border(
        1.dp,
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
        RoundedCornerShape(12.dp)
      )
      .padding(12.dp),
    verticalAlignment = Alignment.Top
  ) {
    Surface(
      shape = CircleShape,
      color = MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(24.dp)
    ) {
      Box(contentAlignment = Alignment.Center) {
        Text(
          text = stepNumber,
          color = Color.White,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.width(10.dp))

    Column(modifier = Modifier.weight(1f)) {
      Text(
        text = title,
        style = MaterialTheme.typography.titleSmall.copy(
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onSurface
        )
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = description,
        style = MaterialTheme.typography.bodySmall.copy(
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          lineHeight = 16.sp
        )
      )
    }
  }
}

private fun shareLinkViaWhatsApp(context: Context, link: String) {
  val message = """
    💻 *NEDIAN CONNECT INSTITUTE - LAPTOP INSTALL LINK*
    
    Aap Nedian Connect Institute app ko apne laptop/PC me directly install kar sakte hain!
    
    👉 *Laptop Web App Link:*
    $link
    
    *How to Install on Laptop:*
    1. Upar wala link apne Laptop ke Google Chrome me open karein.
    2. Address bar me 'Install' (📥) icon par click karein ya 3-dots me jakar 'Save and share' -> 'Install as app' karein.
    3. Laptop Desktop par icon ban jayega aur app laptop me full screen chalega!
  """.trimIndent()

  val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_TEXT, message)
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  }
  try {
    context.startActivity(Intent.createChooser(intent, "Share Laptop Link via"))
  } catch (e: Exception) {
    Toast.makeText(context, "Could not open share sheet", Toast.LENGTH_SHORT).show()
  }
}
