package com.dimyak.animationwithcompose.presentation.examples.animated_circle

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class AnimatedCircleViewModel : ViewModel() {

    private val availableColors = listOf(Color.Blue, Color.Red, Color.Yellow, Color.Cyan)

    private val fakeProfiles = List(10) { index ->
        Profile(
            id = index.toString(),
            color = availableColors.random(),
            name = "User$index"
        )
    }

    private val _user = MutableStateFlow(Profile("userId", availableColors.first(), "User"))
    val user = _user.asStateFlow()

    private val _showCircle = MutableStateFlow(false)
    val showCircle = _showCircle.asStateFlow()

    private val _showNearbyPeople = MutableStateFlow(false)
    val showNearbyPeople = _showNearbyPeople.asStateFlow()

    val nearbyPeople: StateFlow<List<Profile>> = showNearbyPeople.map {
        if (it) fakeProfiles else emptyList()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun switchShowNearbyPeople() {
        _showNearbyPeople.value = !_showNearbyPeople.value
    }

    fun switchShowCircle(switchTo: Boolean) {
        _showCircle.value = switchTo
    }

}
