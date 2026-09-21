package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.model.ReelIdea
import com.example.ui.theme.CoralPink
import com.example.ui.theme.DarkBg
import com.example.ui.theme.DarkStroke
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.EmeraldEasy
import com.example.ui.theme.GoldHook
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ShootTeleprompterDialog(
    idea: ReelIdea,
    isRunning: Boolean,
    currentSecond: Int,
    onToggleTimer: () -> Unit,
    onResetTimer: () -> Unit,
    onDismiss: () -> Unit
) {
    var fontSizeMultiplier by remember { mutableFloatStateOf(1.0f) }
    val scrollState = rememberScrollState()

    // Determine current active section
    val activePhase = when {
        currentSecond in 0..3 -> 0 // 0-3s Hook
        currentSecond in 4..15 -> 1 // 3-15s Build-up
        currentSecond in 16..25 -> 2 // 15-25s Payoff
        else -> 3 // 25-30s CTA
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .testTag("shoot_teleprompter_dialog"),
            color = DarkBg
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Top Bar: Title, Font Sizing, Close
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(CoralPink),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Videocam,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "SHOOT TELEPROMPTER",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = CoralPink,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = idea.ideaTitle,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                fontSizeMultiplier = if (fontSizeMultiplier >= 1.4f) 0.9f else fontSizeMultiplier + 0.2f
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.FormatSize,
                                contentDescription = "Font size",
                                tint = TextSecondary
                            )
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier.testTag("close_prompter")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = TextPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Progress Bar & Timer display
                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkStroke)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val phaseName = when (activePhase) {
                                0 -> "0-3s: Hook & Pattern Interrupt"
                                1 -> "3-15s: Build-up / Story"
                                2 -> "15-25s: Payoff / Value"
                                else -> "25-30s: Natural CTA"
                            }
                            Text(
                                text = phaseName,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (activePhase) {
                                    0 -> GoldHook
                                    1 -> CoralPink
                                    2 -> ElectricViolet
                                    else -> EmeraldEasy
                                }
                            )

                            Text(
                                text = String.format("%02d : %02d / 00:30", currentSecond / 60, currentSecond % 60),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Monospace,
                                color = TextPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        val progress = currentSecond / 30f
                        LinearProgressIndicator(
                            progress = { progress.coerceIn(0f, 1f) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(CircleShape),
                            color = CoralPink,
                            trackColor = DarkSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Controls Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FilledIconButton(
                                onClick = onToggleTimer,
                                modifier = Modifier
                                    .size(54.dp)
                                    .testTag("prompter_play_pause"),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = if (isRunning) Color(0xFFEF4444) else EmeraldEasy
                                )
                            ) {
                                Icon(
                                    imageVector = if (isRunning) Icons.Default.Pause else Icons.Default.PlayArrow,
                                    contentDescription = if (isRunning) "Pause" else "Play",
                                    tint = Color.White,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            IconButton(
                                onClick = onResetTimer,
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(DarkSurfaceVariant, CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Reset",
                                    tint = TextSecondary
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Scrollable Teleprompter Cards (Large, Easy to read on camera)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    PrompterSectionCard(
                        sectionTitle = "0-3s: HOOK (STOP THE SCROLL)",
                        spokenText = idea.script.sec0_3.ifBlank { idea.hookLine },
                        isActive = activePhase == 0,
                        accentColor = GoldHook,
                        fontSizeMultiplier = fontSizeMultiplier
                    )

                    PrompterSectionCard(
                        sectionTitle = "3-15s: BUILD-UP & STORY",
                        spokenText = idea.script.sec3_15,
                        isActive = activePhase == 1,
                        accentColor = CoralPink,
                        fontSizeMultiplier = fontSizeMultiplier
                    )

                    PrompterSectionCard(
                        sectionTitle = "15-25s: PAYOFF & PUNCHLINE",
                        spokenText = idea.script.sec15_25,
                        isActive = activePhase == 2,
                        accentColor = ElectricViolet,
                        fontSizeMultiplier = fontSizeMultiplier
                    )

                    PrompterSectionCard(
                        sectionTitle = "25-30s: NATURAL CTA",
                        spokenText = idea.script.sec25_30,
                        isActive = activePhase == 3,
                        accentColor = EmeraldEasy,
                        fontSizeMultiplier = fontSizeMultiplier
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
private fun PrompterSectionCard(
    sectionTitle: String,
    spokenText: String,
    isActive: Boolean,
    accentColor: Color,
    fontSizeMultiplier: Float
) {
    val borderColor by animateColorAsState(
        targetValue = if (isActive) accentColor else DarkStroke,
        label = "border"
    )
    val bgColor by animateColorAsState(
        targetValue = if (isActive) DarkSurfaceVariant else DarkSurface,
        label = "bg"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isActive) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = sectionTitle,
                    fontSize = (11 * fontSizeMultiplier).sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isActive) accentColor else TextMuted,
                    letterSpacing = 0.8.sp
                )

                if (isActive) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(accentColor)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "NOW SHOOTING",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = spokenText,
                fontSize = (17 * fontSizeMultiplier).sp,
                fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                color = if (isActive) TextPrimary else TextSecondary,
                lineHeight = (24 * fontSizeMultiplier).sp
            )
        }
    }
}
