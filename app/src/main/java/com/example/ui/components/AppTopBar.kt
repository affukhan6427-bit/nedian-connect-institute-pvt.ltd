package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.CourseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
  onCallClick: () -> Unit,
  onWhatsAppClick: () -> Unit,
  onMenuClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  TopAppBar(
    modifier = modifier.testTag("app_top_bar"),
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.surface,
      titleContentColor = MaterialTheme.colorScheme.onSurface
    ),
    navigationIcon = {
      IconButton(
        onClick = onMenuClick,
        modifier = Modifier.testTag("topbar_btn_menu")
      ) {
        Icon(
          imageVector = Icons.Default.Menu,
          contentDescription = "Open Menu",
          tint = MaterialTheme.colorScheme.onSurface
        )
      }
    },
    title = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
          shape = RoundedCornerShape(8.dp),
          color = Color.White,
          shadowElevation = 1.dp,
          modifier = Modifier.size(40.dp)
        ) {
          Box(
            modifier = Modifier.fillMaxSize().padding(2.dp),
            contentAlignment = Alignment.Center
          ) {
            Image(
              painter = painterResource(id = R.drawable.app_logo),
              contentDescription = "Nedian Connect Logo",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Fit
            )
          }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = "NEDIAN CONNECT",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.ExtraBold,
              letterSpacing = 0.5.sp,
              color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1
          )
          Text(
            text = "INSTITUTE • Chakarchauda",
            style = MaterialTheme.typography.labelSmall.copy(
              color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            maxLines = 1
          )
        }
      }
    },
    actions = {
      IconButton(
        onClick = onCallClick,
        modifier = Modifier.testTag("topbar_btn_call")
      ) {
        Icon(
          imageVector = Icons.Default.Call,
          contentDescription = "Call Institute",
          tint = MaterialTheme.colorScheme.primary
        )
      }
      IconButton(
        onClick = onWhatsAppClick,
        modifier = Modifier.testTag("topbar_btn_whatsapp")
      ) {
        Surface(
          shape = CircleShape,
          color = Color(0xFF25D366),
          modifier = Modifier.size(32.dp)
        ) {
          Box(contentAlignment = Alignment.Center) {
            Text(
              text = "WA",
              style = MaterialTheme.typography.labelSmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
              )
            )
          }
        }
      }
    }
  )
}

@Composable
fun FloatingContactButtons(
  onWhatsAppClick: () -> Unit,
  onCallClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp),
    horizontalAlignment = Alignment.End
  ) {
    // Call Floating Button
    FloatingActionButton(
      onClick = onCallClick,
      containerColor = MaterialTheme.colorScheme.surface,
      contentColor = MaterialTheme.colorScheme.primary,
      elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
      shape = CircleShape,
      modifier = Modifier
        .size(48.dp)
        .testTag("fab_call")
    ) {
      Icon(
        imageVector = Icons.Default.Phone,
        contentDescription = "Call Now +977 9705508838",
        modifier = Modifier.size(22.dp)
      )
    }

    // WhatsApp Floating Button (Prominent Brand)
    FloatingActionButton(
      onClick = onWhatsAppClick,
      containerColor = Color(0xFF25D366),
      contentColor = Color.White,
      elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp),
      shape = RoundedCornerShape(16.dp),
      modifier = Modifier.testTag("fab_whatsapp")
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "💬",
            fontSize = 13.sp
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "WhatsApp",
          style = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        )
      }
    }
  }
}
