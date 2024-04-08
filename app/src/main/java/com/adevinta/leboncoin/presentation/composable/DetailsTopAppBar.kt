package com.adevinta.leboncoin.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.adevinta.leboncoin.R

@Composable
fun DetailsTopAppBar(onBack: () -> Unit) {
    TopAppBar(title = { Text(text = stringResource(R.string.details)) }, navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(id = R.string.menu_back))
        }
    }, modifier = Modifier.fillMaxWidth())
}