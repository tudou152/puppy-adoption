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
    val species: String,
    val gender: Gender,
    val description: String,
    @DrawableRes val picture: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readFloat(),
        parcel.readString() ?: "",
        Gender.values()[parcel.readInt()],
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(age)
        parcel.writeFloat(weight)
        parcel.writeString(species)
        parcel.writeInt(gender.ordinal)
        parcel.writeString(description)
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
    PuppyDetail(12, 9.3f, "泰迪", Gender.Male, "自养狗狗因工厂搬迁需免费赠养，很聪明通人性，会很多工作，狗狗用品齐全可以赠送，需要自提，地址深圳市龙华区观澜", R.drawable.teddy),
    PuppyDetail(12, 12.2f, "斗牛犬", Gender.Male, "一岁的弟弟，听话乖巧，不咬不叫，运动量小，爱干净会自己在外面拉屎拉尿。黏人可爱，喜欢睡觉，丑萌丑萌的。疫苗，驱虫已做，未绝育，赠送狗的用具。因为工作原因无法继续养了。希望好心人给他一个家。", R.drawable.bulldog),
    PuppyDetail(3, 6.5f, "秋田犬", Gender.Female, "一只快递过来但无人认领的小狗，看它可怜就暂养了一段时间，但父母实在不支持养，所以不得不送人，希望找个可靠爱狗的人领养。目测三个月左右大，已经买好了疫苗，并且打好一针了，狗狗很活泼，能跑能跳，也很聪明，目前经过训练已经会定点大小便，平时啥都吃，不挑。愿意领养，我会附带把狗笼、狗窝、疫苗等目前我已经买好的东西都一并赠送。", R.drawable.akitas_1),
    PuppyDetail(24, 17.3f, "中华田园犬", Gender.Male, "2岁 已绝育 乖巧听话 不吵不闹 适合陪伴老人 或者家里有小孩的有缘人。", R.drawable.chinese_country_dog),
    PuppyDetail(60, 19.0f, "哈士奇", Gender.Female, "成年哈士奇(母)，已绝育，2016年初生，约5周岁，品相优，身体条件正常，无健康问题，体重约19Kg，性格温顺，较聪颖，因今年家中房屋修改，加之本人工作一般都较忙，没有太多时间照顾，所以想找一位合适的宠主，无锡洛社、阳山周边优先考虑。", R.drawable.husky),
    PuppyDetail(3, 8.3f, "阿拉斯加", Gender.Female, "由于家里父母反对，沟通多次无果，三个月的阿拉斯加求找新主人，小偿，赠送目前使用全部用品。", R.drawable.alaska),
)
