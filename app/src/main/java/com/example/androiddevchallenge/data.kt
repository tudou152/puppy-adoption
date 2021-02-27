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

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.ui.graphics.vector.ImageVector

enum class Gender {
    Male, Female
}

data class PuppyDetail(
    val age: Int, // month
    val weight: Float,
    @StringRes val species: Int,
    val gender: Gender,
    @StringRes val description: Int,
    @DrawableRes val picture: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readInt(),
        Gender.values()[parcel.readInt()],
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeFloat(weight)
        parcel.writeInt(species)
        parcel.writeInt(gender.ordinal)
        parcel.writeInt(description)
        parcel.writeInt(picture)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PuppyDetail> {
        override fun createFromParcel(parcel: Parcel): PuppyDetail {
            return PuppyDetail(parcel)
        }

        override fun newArray(size: Int): Array<PuppyDetail?> {
            return arrayOfNulls(size)
        }
    }

    val genderIcon: ImageVector
        get() = when (gender) {
            Gender.Male -> Icons.Default.Male
            Gender.Female -> Icons.Default.Female
        }
}

val puppyList = listOf<PuppyDetail>(
    PuppyDetail(12, 9.3f, R.string.teddy, Gender.Male, R.string.teddy_des, R.drawable.teddy),
    PuppyDetail(12, 12.2f, R.string.bulldog, Gender.Male, R.string.bulldog_des, R.drawable.bulldog),
    PuppyDetail(3, 6.5f, R.string.akita, Gender.Female, R.string.akita_des, R.drawable.akitas_1),
    PuppyDetail(24, 17.3f, R.string.chinese_country_dog, Gender.Male, R.string.chinese_country_dog_des, R.drawable.chinese_country_dog),
    PuppyDetail(60, 19.0f, R.string.husky, Gender.Female, R.string.husky_des, R.drawable.husky),
    PuppyDetail(3, 8.3f, R.string.alaska, Gender.Female, R.string.alaska_des, R.drawable.alaska),
)
