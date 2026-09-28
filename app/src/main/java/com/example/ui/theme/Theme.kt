package com.example.ui.theme

import android.os.Build
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext

// NEDIAN Brand ColorScheme (Default: Emerald Green primary, Crimson secondary, Amber gold tertiary)
private val NedianLightColorScheme = lightColorScheme(
  primary = NedianGreenPrimary,
  onPrimary = NedianGreenOnPrimary,
  primaryContainer = NedianGreenContainer,
  onPrimaryContainer = NedianGreenOnContainer,
  secondary = NedianCrimson,
  onSecondary = Color.White,
  secondaryContainer = NedianCrimsonContainer,
  onSecondaryContainer = NedianCrimsonOnContainer,
  tertiary = GoldAccent,
  onTertiary = Color.White,
  tertiaryContainer = GoldAccentContainer,
  onTertiaryContainer = GoldOnAccentContainer,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

private val NedianDarkColorScheme = darkColorScheme(
  primary = DarkNedianGreenPrimary,
  onPrimary = DarkNedianGreenOnPrimary,
  primaryContainer = DarkNedianGreenContainer,
  onPrimaryContainer = DarkNedianGreenOnContainer,
  secondary = DarkNedianCrimson,
  onSecondary = Color.White,
  secondaryContainer = DarkNedianCrimsonContainer,
  onSecondaryContainer = DarkNedianCrimsonOnContainer,
  tertiary = DarkGoldAccent,
  onTertiary = DarkGoldOnAccentContainer,
  tertiaryContainer = DarkGoldAccentContainer,
  onTertiaryContainer = DarkGoldOnAccentContainer,
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val NavyLightColorScheme = lightColorScheme(
  primary = NavyPrimary,
  onPrimary = NavyOnPrimary,
  primaryContainer = NavyPrimaryContainer,
  onPrimaryContainer = NavyOnPrimaryContainer,
  secondary = GoldAccent,
  onSecondary = Color.White,
  secondaryContainer = GoldAccentContainer,
  onSecondaryContainer = GoldOnAccentContainer,
  tertiary = EmeraldSuccess,
  onTertiary = Color.White,
  tertiaryContainer = EmeraldContainer,
  onTertiaryContainer = EmeraldOnContainer,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

private val CrimsonLightColorScheme = lightColorScheme(
  primary = CrimsonPrimary,
  onPrimary = Color.White,
  primaryContainer = CrimsonPrimaryContainer,
  onPrimaryContainer = CrimsonOnPrimaryContainer,
  secondary = NedianTeal,
  onSecondary = Color.White,
  secondaryContainer = NedianTealContainer,
  onSecondaryContainer = NedianTealOnContainer,
  tertiary = GoldAccent,
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

private val PurpleLightColorScheme = lightColorScheme(
  primary = PurplePrimary,
  onPrimary = Color.White,
  primaryContainer = PurplePrimaryContainer,
  onPrimaryContainer = PurpleOnPrimaryContainer,
  secondary = EmeraldSuccess,
  onSecondary = Color.White,
  secondaryContainer = EmeraldContainer,
  onSecondaryContainer = EmeraldOnContainer,
  tertiary = GoldAccent,
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Sunset Amber & Orange
private val SunsetAmberLightColorScheme = lightColorScheme(
  primary = SunsetAmberPrimary,
  onPrimary = Color.White,
  primaryContainer = SunsetAmberContainer,
  onPrimaryContainer = SunsetAmberOnContainer,
  secondary = SunsetOrangeSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFFEDD5),
  onSecondaryContainer = Color(0xFF7C2D12),
  tertiary = Color(0xFF0284C7),
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Cyber Tech Teal & Indigo
private val CyberTealLightColorScheme = lightColorScheme(
  primary = CyberTealPrimary,
  onPrimary = Color.White,
  primaryContainer = CyberTealContainer,
  onPrimaryContainer = CyberTealOnContainer,
  secondary = CyberIndigoSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFE0E7FF),
  onSecondaryContainer = Color(0xFF312E81),
  tertiary = Color(0xFFEA580C),
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Midnight Onyx & Gold
private val MidnightOnyxLightColorScheme = lightColorScheme(
  primary = MidnightOnyxPrimary,
  onPrimary = Color.White,
  primaryContainer = MidnightOnyxContainer,
  onPrimaryContainer = MidnightOnyxOnContainer,
  secondary = MidnightGoldSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFEF08A),
  onSecondaryContainer = Color(0xFF713F12),
  tertiary = Color(0xFF2563EB),
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Nordic Forest Pine & Sage
private val ForestPineLightColorScheme = lightColorScheme(
  primary = ForestPinePrimary,
  onPrimary = Color.White,
  primaryContainer = ForestPineContainer,
  onPrimaryContainer = ForestPineOnContainer,
  secondary = ForestSageSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFD1FAE5),
  onSecondaryContainer = Color(0xFF064E3B),
  tertiary = GoldAccent,
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Electric Sapphire Blue & Cyan
private val ElectricBlueLightColorScheme = lightColorScheme(
  primary = ElectricBluePrimary,
  onPrimary = Color.White,
  primaryContainer = ElectricBlueContainer,
  onPrimaryContainer = ElectricBlueOnContainer,
  secondary = ElectricCyanSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFCFFAFE),
  onSecondaryContainer = Color(0xFF164E63),
  tertiary = GoldAccent,
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Rose Gold & Mulberry
private val RoseMulberryLightColorScheme = lightColorScheme(
  primary = RoseMulberryPrimary,
  onPrimary = Color.White,
  primaryContainer = RoseMulberryContainer,
  onPrimaryContainer = RoseMulberryOnContainer,
  secondary = RoseCopperSecondary,
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFEF3C7),
  onSecondaryContainer = Color(0xFF78350F),
  tertiary = Color(0xFF7C3AED),
  onTertiary = Color.White,
  background = SlateBackground,
  onBackground = SlateOnBackground,
  surface = SlateSurface,
  onSurface = SlateOnSurface,
  surfaceVariant = SlateSurfaceVariant,
  onSurfaceVariant = SlateOnSurfaceVariant,
  outline = SlateOutline
)

// Dark Theme Variants
private val NavyDarkColorScheme = darkColorScheme(
  primary = Color(0xFF93C5FD),
  onPrimary = Color(0xFF00315F),
  primaryContainer = Color(0xFF0F4C81),
  onPrimaryContainer = Color(0xFFD6E4FF),
  secondary = DarkGoldAccent,
  onSecondary = Color(0xFF451A03),
  tertiary = DarkEmeraldSuccess,
  onTertiary = Color(0xFF064E3B),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val CrimsonDarkColorScheme = darkColorScheme(
  primary = Color(0xFFFCA5A5),
  onPrimary = Color(0xFF7F1D1D),
  primaryContainer = Color(0xFF991B1B),
  onPrimaryContainer = Color(0xFFFEE2E2),
  secondary = Color(0xFF2DD4BF),
  onSecondary = Color(0xFF115E59),
  tertiary = DarkGoldAccent,
  onTertiary = Color(0xFF78350F),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val PurpleDarkColorScheme = darkColorScheme(
  primary = Color(0xFFD8B4FE),
  onPrimary = Color(0xFF581C87),
  primaryContainer = Color(0xFF6B21A8),
  onPrimaryContainer = Color(0xFFF3E8FF),
  secondary = DarkEmeraldSuccess,
  onSecondary = Color(0xFF064E3B),
  tertiary = DarkGoldAccent,
  onTertiary = Color(0xFF78350F),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val SunsetAmberDarkColorScheme = darkColorScheme(
  primary = Color(0xFFFBBF24),
  onPrimary = Color(0xFF78350F),
  primaryContainer = Color(0xFFB45309),
  onPrimaryContainer = Color(0xFFFEF3C7),
  secondary = Color(0xFFFB923C),
  onSecondary = Color(0xFF7C2D12),
  tertiary = Color(0xFF38BDF8),
  onTertiary = Color(0xFF0C4A6E),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val CyberTealDarkColorScheme = darkColorScheme(
  primary = Color(0xFF2DD4BF),
  onPrimary = Color(0xFF115E59),
  primaryContainer = Color(0xFF0F766E),
  onPrimaryContainer = Color(0xFFCCFBF1),
  secondary = Color(0xFF818CF8),
  onSecondary = Color(0xFF312E81),
  tertiary = Color(0xFFFB923C),
  onTertiary = Color(0xFF7C2D12),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val MidnightOnyxDarkColorScheme = darkColorScheme(
  primary = Color(0xFFF4F4F5),
  onPrimary = Color(0xFF18181B),
  primaryContainer = Color(0xFF3F3F46),
  onPrimaryContainer = Color(0xFFF4F4F5),
  secondary = Color(0xFFFACC15),
  onSecondary = Color(0xFF713F12),
  tertiary = Color(0xFF60A5FA),
  onTertiary = Color(0xFF1E3A8A),
  background = Color(0xFF09090B),
  onBackground = Color(0xFFFAFAFA),
  surface = Color(0xFF18181B),
  onSurface = Color(0xFFF4F4F5),
  surfaceVariant = Color(0xFF27272A),
  onSurfaceVariant = Color(0xFFA1A1AA),
  outline = Color(0xFF52525B)
)

private val ForestPineDarkColorScheme = darkColorScheme(
  primary = Color(0xFF4ADE80),
  onPrimary = Color(0xFF052E16),
  primaryContainer = Color(0xFF166534),
  onPrimaryContainer = Color(0xFFDCFCE7),
  secondary = Color(0xFF34D399),
  onSecondary = Color(0xFF064E3B),
  tertiary = DarkGoldAccent,
  onTertiary = Color(0xFF78350F),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val ElectricBlueDarkColorScheme = darkColorScheme(
  primary = Color(0xFF60A5FA),
  onPrimary = Color(0xFF1E3A8A),
  primaryContainer = Color(0xFF1D4ED8),
  onPrimaryContainer = Color(0xFFDBEAFE),
  secondary = Color(0xFF22D3EE),
  onSecondary = Color(0xFF164E63),
  tertiary = DarkGoldAccent,
  onTertiary = Color(0xFF78350F),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val AnimeSakuraLightColorScheme = lightColorScheme(
  primary = Color(0xFFFF2A85), // Sakura Neon Pink
  onPrimary = Color.White,
  primaryContainer = Color(0xFFFFD1EA),
  onPrimaryContainer = Color(0xFF5A0028),
  secondary = Color(0xFF8B5CF6), // Anime Violet
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFEDE9FE),
  onSecondaryContainer = Color(0xFF2E1065),
  tertiary = Color(0xFF06B6D4), // Cyan Glow
  onTertiary = Color.White,
  background = Color(0xFFFFF1F2),
  onBackground = Color(0xFF1F1215),
  surface = Color.White,
  onSurface = Color(0xFF1F1215),
  surfaceVariant = Color(0xFFFFE4E6),
  onSurfaceVariant = Color(0xFF581C26),
  outline = Color(0xFFFDA4AF)
)

private val AnimeSakuraDarkColorScheme = darkColorScheme(
  primary = Color(0xFFFF5C9D),
  onPrimary = Color(0xFF4A001C),
  primaryContainer = Color(0xFF990044),
  onPrimaryContainer = Color(0xFFFFD1EA),
  secondary = Color(0xFFA78BFA),
  onSecondary = Color(0xFF2E1065),
  tertiary = Color(0xFF22D3EE),
  onTertiary = Color(0xFF0E7490),
  background = Color(0xFF120A10),
  onBackground = Color(0xFFFCE7F3),
  surface = Color(0xFF1E101A),
  onSurface = Color(0xFFFCE7F3),
  surfaceVariant = Color(0xFF2D1627),
  onSurfaceVariant = Color(0xFFF472B6),
  outline = Color(0xFF6B213A)
)

private val RoseMulberryDarkColorScheme = darkColorScheme(
  primary = Color(0xFFF472B6),
  onPrimary = Color(0xFF831843),
  primaryContainer = Color(0xFFBE185D),
  onPrimaryContainer = Color(0xFFFCE7F3),
  secondary = Color(0xFFFBBF24),
  onSecondary = Color(0xFF78350F),
  tertiary = Color(0xFFA78BFA),
  onTertiary = Color(0xFF4C1D95),
  background = DarkSlateBackground,
  onBackground = DarkSlateOnBackground,
  surface = DarkSlateSurface,
  onSurface = DarkSlateOnSurface,
  surfaceVariant = DarkSlateSurfaceVariant,
  onSurfaceVariant = DarkSlateOnSurfaceVariant,
  outline = DarkSlateOutline
)

private val Holographic3DLightColorScheme = lightColorScheme(
  primary = Color(0xFF4F46E5),
  onPrimary = Color.White,
  primaryContainer = Color(0xFFE0E7FF),
  onPrimaryContainer = Color(0xFF312E81),
  secondary = Color(0xFFDB2777),
  onSecondary = Color.White,
  secondaryContainer = Color(0xFFFCE7F3),
  onSecondaryContainer = Color(0xFF831843),
  tertiary = Color(0xFF0891B2),
  onTertiary = Color.White,
  background = Color(0xFFF8FAFC),
  onBackground = Color(0xFF0F172A),
  surface = Color.White,
  onSurface = Color(0xFF1E293B),
  surfaceVariant = Color(0xFFF1F5F9),
  onSurfaceVariant = Color(0xFF475569),
  outline = Color(0xFF94A3B8)
)

private val Holographic3DDarkColorScheme = darkColorScheme(
  primary = Color(0xFF818CF8),
  onPrimary = Color(0xFF1E1B4B),
  primaryContainer = Color(0xFF3730A3),
  onPrimaryContainer = Color(0xFFE0E7FF),
  secondary = Color(0xFFF472B6),
  onSecondary = Color(0xFF500724),
  secondaryContainer = Color(0xFF9D174D),
  onSecondaryContainer = Color(0xFFFCE7F3),
  tertiary = Color(0xFF22D3EE),
  onTertiary = Color(0xFF083344),
  background = Color(0xFF070B14),
  onBackground = Color(0xFFF1F5F9),
  surface = Color(0xFF0F172A),
  onSurface = Color(0xFFE2E8F0),
  surfaceVariant = Color(0xFF1E293B),
  onSurfaceVariant = Color(0xFF94A3B8),
  outline = Color(0xFF334155)
)

@Composable
fun NedianTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  themePalette: String = "ELECTRIC_BLUE",
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    darkTheme -> when (themePalette) {
      "ELECTRIC_BLUE" -> ElectricBlueDarkColorScheme
      "ROYAL_NAVY" -> NavyDarkColorScheme
      "CRIMSON_ELEGANCE" -> CrimsonDarkColorScheme
      "IMPERIAL_PURPLE" -> PurpleDarkColorScheme
      "SUNSET_AMBER" -> SunsetAmberDarkColorScheme
      "CYBER_TEAL" -> CyberTealDarkColorScheme
      "MIDNIGHT_ONYX" -> MidnightOnyxDarkColorScheme
      "FOREST_PINE" -> ForestPineDarkColorScheme
      "ROSE_MULBERRY" -> RoseMulberryDarkColorScheme
      "ANIME_SAKURA" -> AnimeSakuraDarkColorScheme
      "HOLOGRAPHIC_3D" -> Holographic3DDarkColorScheme
      else -> Holographic3DDarkColorScheme
    }
    else -> when (themePalette) {
      "ELECTRIC_BLUE" -> ElectricBlueLightColorScheme
      "ROYAL_NAVY" -> NavyLightColorScheme
      "CRIMSON_ELEGANCE" -> CrimsonLightColorScheme
      "IMPERIAL_PURPLE" -> PurpleLightColorScheme
      "SUNSET_AMBER" -> SunsetAmberLightColorScheme
      "CYBER_TEAL" -> CyberTealLightColorScheme
      "MIDNIGHT_ONYX" -> MidnightOnyxLightColorScheme
      "FOREST_PINE" -> ForestPineLightColorScheme
      "ROSE_MULBERRY" -> RoseMulberryLightColorScheme
      "ANIME_SAKURA" -> AnimeSakuraLightColorScheme
      "HOLOGRAPHIC_3D" -> Holographic3DLightColorScheme
      else -> Holographic3DLightColorScheme
    }
  }


  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}

@Composable
fun remember3DInteractiveModifier(): Modifier {
  val infiniteTransition = rememberInfiniteTransition(label = "3d_float_anim")
  val translateY by infiniteTransition.animateFloat(
    initialValue = -4f,
    targetValue = 4f,
    animationSpec = infiniteRepeatable(
      animation = tween(2200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "3d_translate"
  )
  val rotationX by infiniteTransition.animateFloat(
    initialValue = -1.5f,
    targetValue = 1.5f,
    animationSpec = infiniteRepeatable(
      animation = tween(3500, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "3d_rot_x"
  )
  return Modifier.graphicsLayer {
    cameraDistance = 14f * density
    this.translationY = translateY
    this.rotationX = rotationX
  }
}

// Keep backwards-compat alias
@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  themePalette: String = "ELECTRIC_BLUE",
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  NedianTheme(darkTheme = darkTheme, themePalette = themePalette, dynamicColor = dynamicColor, content = content)
}
