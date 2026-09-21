package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.ReelDatabase
import com.example.data.model.ReelIdea
import com.example.data.model.ReelIdeaResponse
import com.example.data.repository.ReelRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface GenerationState {
    data object Idle : GenerationState
    data object Loading : GenerationState
    data class Success(val response: ReelIdeaResponse) : GenerationState
    data class Error(val message: String) : GenerationState
}

class ReelGuruViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ReelRepository

    init {
        val db = ReelDatabase.getDatabase(application)
        repository = ReelRepository(db.reelDao())
    }

    val savedReels: StateFlow<List<ReelIdea>> = repository.allSavedReels
        .map { list -> list.map { it.toReelIdea() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _niche = MutableStateFlow("Fitness")
    val niche: StateFlow<String> = _niche.asStateFlow()

    private val _targetAudience = MutableStateFlow("")
    val targetAudience: StateFlow<String> = _targetAudience.asStateFlow()

    private val _language = MutableStateFlow("Hinglish")
    val language: StateFlow<String> = _language.asStateFlow()

    private val _followerStage = MutableStateFlow("Growing (10k-100k)")
    val followerStage: StateFlow<String> = _followerStage.asStateFlow()

    private val _pastReelsContext = MutableStateFlow("")
    val pastReelsContext: StateFlow<String> = _pastReelsContext.asStateFlow()

    private val _isAdvancedOpen = MutableStateFlow(false)
    val isAdvancedOpen: StateFlow<Boolean> = _isAdvancedOpen.asStateFlow()

    private val _selectedTab = MutableStateFlow(0) // 0: Generator, 1: Shoot List, 2: Hook Vault
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    private val _generationState = MutableStateFlow<GenerationState>(GenerationState.Idle)
    val generationState: StateFlow<GenerationState> = _generationState.asStateFlow()

    // Teleprompter / Shoot mode state
    private val _activePrompterIdea = MutableStateFlow<ReelIdea?>(null)
    val activePrompterIdea: StateFlow<ReelIdea?> = _activePrompterIdea.asStateFlow()

    private val _prompterRunning = MutableStateFlow(false)
    val prompterRunning: StateFlow<Boolean> = _prompterRunning.asStateFlow()

    private val _prompterSecond = MutableStateFlow(0)
    val prompterSecond: StateFlow<Int> = _prompterSecond.asStateFlow()

    private var timerJob: Job? = null

    init {
        // Initial generation for pleasant instant experience
        generateIdeas()
    }

    fun setNiche(value: String) {
        _niche.value = value
    }

    fun setTargetAudience(value: String) {
        _targetAudience.value = value
    }

    fun setLanguage(value: String) {
        _language.value = value
    }

    fun setFollowerStage(value: String) {
        _followerStage.value = value
    }

    fun setPastReelsContext(value: String) {
        _pastReelsContext.value = value
    }

    fun toggleAdvancedOptions() {
        _isAdvancedOpen.value = !_isAdvancedOpen.value
    }

    fun selectTab(index: Int) {
        _selectedTab.value = index
    }

    fun generateIdeas() {
        val currentNiche = _niche.value.trim().ifBlank { "Fitness" }
        viewModelScope.launch {
            _generationState.value = GenerationState.Loading
            try {
                val result = repository.generateReelIdeas(
                    niche = currentNiche,
                    targetAudience = _targetAudience.value,
                    language = _language.value,
                    followerCount = _followerStage.value,
                    pastReelsContext = _pastReelsContext.value
                )
                result.onSuccess { response ->
                    _generationState.value = GenerationState.Success(response)
                }.onFailure { error ->
                    _generationState.value = GenerationState.Error(error.message ?: "Failed to generate ideas")
                }
            } catch (e: Exception) {
                _generationState.value = GenerationState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    fun toggleSaveIdea(idea: ReelIdea) {
        viewModelScope.launch {
            val isAlreadySaved = savedReels.value.any { it.id == idea.id || (it.ideaTitle == idea.ideaTitle && it.hookLine == idea.hookLine) }
            if (isAlreadySaved) {
                val savedItem = savedReels.value.firstOrNull { it.id == idea.id || it.ideaTitle == idea.ideaTitle }
                if (savedItem != null) {
                    repository.removeReel(savedItem.id)
                }
            } else {
                repository.saveReel(_niche.value, idea.copy(isSaved = true))
            }
        }
    }

    fun deleteSavedIdea(id: String) {
        viewModelScope.launch {
            repository.removeReel(id)
        }
    }

    // Shoot Teleprompter controls
    fun openPrompter(idea: ReelIdea) {
        _activePrompterIdea.value = idea
        resetPrompterTimer()
    }

    fun closePrompter() {
        pausePrompterTimer()
        _activePrompterIdea.value = null
    }

    fun togglePrompterTimer() {
        if (_prompterRunning.value) {
            pausePrompterTimer()
        } else {
            startPrompterTimer()
        }
    }

    fun resetPrompterTimer() {
        pausePrompterTimer()
        _prompterSecond.value = 0
    }

    private fun startPrompterTimer() {
        _prompterRunning.value = true
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_prompterRunning.value && _prompterSecond.value < 30) {
                delay(1000)
                _prompterSecond.value += 1
            }
            if (_prompterSecond.value >= 30) {
                _prompterRunning.value = false
            }
        }
    }

    private fun pausePrompterTimer() {
        _prompterRunning.value = false
        timerJob?.cancel()
        timerJob = null
    }
}
