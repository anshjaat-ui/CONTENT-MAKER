package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.VideoCameraBack
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.ReelGuruViewModel
import com.example.ui.components.ShootTeleprompterDialog
import com.example.ui.screens.GeneratorScreen
import com.example.ui.screens.HookVaultScreen
import com.example.ui.screens.SavedScriptsScreen
import com.example.ui.theme.CoralPink
import com.example.ui.theme.DarkBg
import com.example.ui.theme.DarkStroke
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.GoldHook
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.SunsetOrange
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

class MainActivity : ComponentActivity() {

    private val viewModel: ReelGuruViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(darkTheme = true) {
                ReelGuruApp(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReelGuruApp(
    viewModel: ReelGuruViewModel,
    modifier: Modifier = Modifier
) {
    val selectedTab by viewModel.selectedTab.collectAsStateWithLifecycle()
    val niche by viewModel.niche.collectAsStateWithLifecycle()
    val targetAudience by viewModel.targetAudience.collectAsStateWithLifecycle()
    val language by viewModel.language.collectAsStateWithLifecycle()
    val followerStage by viewModel.followerStage.collectAsStateWithLifecycle()
    val pastReelsContext by viewModel.pastReelsContext.collectAsStateWithLifecycle()
    val isAdvancedOpen by viewModel.isAdvancedOpen.collectAsStateWithLifecycle()
    val generationState by viewModel.generationState.collectAsStateWithLifecycle()
    val savedReels by viewModel.savedReels.collectAsStateWithLifecycle()

    val activePrompterIdea by viewModel.activePrompterIdea.collectAsStateWithLifecycle()
    val prompterRunning by viewModel.prompterRunning.collectAsStateWithLifecycle()
    val prompterSecond by viewModel.prompterSecond.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Reel Guru",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                        color = TextPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBg
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                tonalElevation = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("bottom_nav_bar")
            ) {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { viewModel.selectTab(0) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Generator"
                        )
                    },
                    label = { Text("Generator", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = CoralPink,
                        indicatorColor = CoralPink,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    ),
                    modifier = Modifier.testTag("nav_generator")
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { viewModel.selectTab(1) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (savedReels.isNotEmpty()) {
                                    Badge(
                                        containerColor = CoralPink,
                                        contentColor = Color.White
                                    ) {
                                        Text("${savedReels.size}")
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selectedTab == 1) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Shoot List"
                            )
                        }
                    },
                    label = { Text("Shoot List", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = CoralPink,
                        indicatorColor = CoralPink,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    ),
                    modifier = Modifier.testTag("nav_shoot_list")
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { viewModel.selectTab(2) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = "Hook Vault"
                        )
                    },
                    label = { Text("Hook Vault", fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = CoralPink,
                        indicatorColor = CoralPink,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    ),
                    modifier = Modifier.testTag("nav_hook_vault")
                )
            }
        },
        containerColor = DarkBg
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> {
                    GeneratorScreen(
                        niche = niche,
                        targetAudience = targetAudience,
                        language = language,
                        followerStage = followerStage,
                        pastReelsContext = pastReelsContext,
                        isAdvancedOpen = isAdvancedOpen,
                        generationState = generationState,
                        savedReels = savedReels,
                        onNicheChange = viewModel::setNiche,
                        onTargetAudienceChange = viewModel::setTargetAudience,
                        onLanguageChange = viewModel::setLanguage,
                        onFollowerStageChange = viewModel::setFollowerStage,
                        onPastReelsContextChange = viewModel::setPastReelsContext,
                        onToggleAdvanced = viewModel::toggleAdvancedOptions,
                        onGenerate = viewModel::generateIdeas,
                        onToggleSave = viewModel::toggleSaveIdea,
                        onOpenShootPrompter = viewModel::openPrompter
                    )
                }

                1 -> {
                    SavedScriptsScreen(
                        savedReels = savedReels,
                        onToggleSave = viewModel::toggleSaveIdea,
                        onOpenShootPrompter = viewModel::openPrompter,
                        onGoToGenerator = { viewModel.selectTab(0) }
                    )
                }

                2 -> {
                    HookVaultScreen(
                        onUseFormulaInGenerator = { formula ->
                            viewModel.setPastReelsContext("Use hook formula: $formula")
                            viewModel.selectTab(0)
                        }
                    )
                }
            }
        }
    }

    // Interactive Full-Screen Shoot Teleprompter Dialog
    activePrompterIdea?.let { idea ->
        ShootTeleprompterDialog(
            idea = idea,
            isRunning = prompterRunning,
            currentSecond = prompterSecond,
            onToggleTimer = viewModel::togglePrompterTimer,
            onResetTimer = viewModel::resetPrompterTimer,
            onDismiss = viewModel::closePrompter
        )
    }
}
