package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmberMedium
import com.example.ui.theme.CoralPink
import com.example.ui.theme.DarkStroke
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.EmeraldEasy
import com.example.ui.theme.GoldHook
import com.example.ui.theme.SunsetOrange
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

data class HookPattern(
    val title: String,
    val patternFormula: String,
    val whyItWorks: String,
    val hinglishExample: String,
    val englishExample: String,
    val accentColor: Color,
    val icon: ImageVector
)

@Composable
fun HookVaultScreen(
    onUseFormulaInGenerator: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val hookPatterns = listOf(
        HookPattern(
            title = "1. Pattern Interrupt",
            patternFormula = "Stop doing X, isse pata chalta hai tum [negative trait]...",
            whyItWorks = "Disrupts mindless scrolling by accusing the viewer of a mistake, triggering immediate defense mechanism.",
            hinglishExample = "\"Stop drinking protein shake like this! Isse tumhara 40% nutrition waste ho raha hai.\"",
            englishExample = "\"Stop doing your morning routine like this! It's secretly draining your daily focus.\"",
            accentColor = CoralPink,
            icon = Icons.Default.ElectricBolt
        ),
        HookPattern(
            title = "2. Curiosity Gap",
            patternFormula = "90% log ye galti karte hain aur pata bhi nahi chalta...",
            whyItWorks = "Brain hates unresolved questions (Zeigarnik Effect) and is compelled to watch till payoff to find out if they are guilty.",
            hinglishExample = "\"90% log SIP invest karte waqt ye 1 galti karte hain aur unhe pata bhi nahi chalta!\"",
            englishExample = "\"90% of people make this 1 mistake with their iPhone battery without even realizing.\"",
            accentColor = GoldHook,
            icon = Icons.Default.Psychology
        ),
        HookPattern(
            title = "3. Bold Claim",
            patternFormula = "Ye ek cheez chhod di to [massive result] mil gaya...",
            whyItWorks = "Promising asymmetric reward for minimal effort creates high dopamine anticipation.",
            hinglishExample = "\"Ye 1 habit maine chhod di aur meri productivity literally 3x ho gayi!\"",
            englishExample = "\"Once I cut this single food out of my diet, my brain fog vanished in 72 hours.\"",
            accentColor = SunsetOrange,
            icon = Icons.Default.TrendingUp
        ),
        HookPattern(
            title = "4. Relatable Callout",
            patternFormula = "Agar tum bhi [specific behavior] karte ho to ye dekho...",
            whyItWorks = "Tribal validation: Viewers immediately identify themselves and share with friends in DMs.",
            hinglishExample = "\"Agar tum bhi shaam ko bina wajah Swiggy cart bhar ke empty karte ho, to ye suno...\"",
            englishExample = "\"If you also spend 30 minutes scrolling Netflix instead of picking a movie, watch this.\"",
            accentColor = ElectricViolet,
            icon = Icons.Default.AutoAwesome
        ),
        HookPattern(
            title = "5. Direct Value Promise",
            patternFormula = "3 minute mein sikho [specific high-income / high-value skill]...",
            whyItWorks = "Sets a clear, micro time commitment with a high-leverage payoff promise.",
            hinglishExample = "\"3 minute mein seekho kaise kisi bhi normal photo ko cinematic banayein!\"",
            englishExample = "\"Learn the 3-step prompt framework that replaced 10 hours of manual writing in 3 minutes.\"",
            accentColor = EmeraldEasy,
            icon = Icons.Default.ElectricBolt
        )
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("hook_vault_screen")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(6.dp))
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF26122E), Color(0xFF16152B))
                        )
                    )
                    .border(1.dp, DarkStroke, RoundedCornerShape(18.dp))
                    .padding(18.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(GoldHook),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Psychology,
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Proven Hook Vault",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "The first 1-3 seconds decide 80% of reel retention. Never start with 'Hey guys'. Use these 5 battle-tested psychological triggers.",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        items(hookPatterns) { pattern ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkStroke)
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(pattern.accentColor.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = pattern.icon,
                                    contentDescription = null,
                                    tint = pattern.accentColor,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = pattern.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = TextPrimary
                            )
                        }

                        IconButton(
                            onClick = {
                                copyText(context, "Formula", pattern.patternFormula)
                            },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy formula",
                                tint = TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Formula Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(DarkSurfaceVariant)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = pattern.patternFormula,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = pattern.accentColor
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Why it works: ${pattern.whyItWorks}",
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Examples:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "• Hinglish: ${pattern.hinglishExample}",
                        fontSize = 12.sp,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "• English: ${pattern.englishExample}",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = {
                            onUseFormulaInGenerator(pattern.patternFormula)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(36.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Use In Generator", fontSize = 12.sp)
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

private fun copyText(context: Context, label: String, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(label, text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "$label copied! 📋", Toast.LENGTH_SHORT).show()
}
