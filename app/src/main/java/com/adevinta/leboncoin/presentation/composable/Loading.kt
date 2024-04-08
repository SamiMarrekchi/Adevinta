package com.adevinta.leboncoin.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier, contentAlignment: Alignment) {
    Box(modifier = modifier, contentAlignment = contentAlignment) {
        CircularProgressIndicator(modifier = Modifier.size(80.dp))
    }
}