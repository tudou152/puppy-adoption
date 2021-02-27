/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PuppyDetailScreen(puppyDetail: PuppyDetail, onBackPress: () -> Unit) {
    val scrollState = rememberScrollState()
    Scaffold(topBar = { DetailAppBar(onNavIconPressed = onBackPress) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = puppyDetail.picture),
                contentDescription = "puppy picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = puppyDetail.species),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.puppy_card_description, puppyDetail.age, puppyDetail.weight) + " ·",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Icon(
                        modifier = Modifier.padding(start = 2.dp),
                        imageVector = puppyDetail.genderIcon,
                        contentDescription = "gender",
                        tint = genderColor(puppyDetail.gender)
                    )
                }
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(text = stringResource(id = puppyDetail.description), style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.puppy_detail)) },
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.95f),
        elevation = 3.dp, // No shadow needed
        contentColor = MaterialTheme.colors.onSurface,
        navigationIcon = {
            IconButton(onClick = onNavIconPressed) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewPuppyDetailScreen() {
    PuppyDetailScreen(puppyDetail = puppyList.first(), onBackPress = { /*TODO*/ })
}
