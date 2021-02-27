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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.genderFeMaleColor
import com.example.androiddevchallenge.ui.theme.genderMaleColor

@ExperimentalFoundationApi
@Composable
fun PuppyListScreen(
    modifier: Modifier = Modifier,
    puppyList: List<PuppyDetail>,
    onPuppySelected: (PuppyDetail) -> Unit
) {
    Scaffold(topBar = { OverViewAppBar() }) {
        val columnCount = 2
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp),
            cells = GridCells.Fixed(columnCount)
        ) {
            itemsIndexed(puppyList) { _, item ->
                PuppyDetailItem(
                    onClick = { onPuppySelected(item) },
                    puppyDetail = item,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun genderColor(gender: Gender): Color {
    return if (gender == Gender.Male) genderMaleColor else genderFeMaleColor
}

@Composable
fun OverViewAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(id = R.string.puppy_adoption_list)) },
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.95f),
        elevation = 3.dp, // No shadow needed
        contentColor = MaterialTheme.colors.onSurface,
        navigationIcon = {
            IconButton(onClick = onNavIconPressed) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun PuppyDetailItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    puppyDetail: PuppyDetail
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick() }
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                bitmap = ImageBitmap.imageResource(id = puppyDetail.picture),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = puppyDetail.species),
                        color = MaterialTheme.colors.onSurface,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier.size(12.dp),
                        imageVector = puppyDetail.genderIcon,
                        contentDescription = "gender",
                        tint = genderColor(puppyDetail.gender)
                    )
                }
                Text(
                    text = stringResource(id = R.string.puppy_card_description, puppyDetail.age, puppyDetail.weight),
                    style = MaterialTheme.typography.overline
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPuppyDetailItem() {
    PuppyDetailItem(
        modifier = Modifier
            .width(150.dp)
            .wrapContentHeight(),
        puppyDetail = puppyList.first(),
        onClick = {}
    )
}
