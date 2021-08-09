package com.kobeissidev.jetpackcomposepokedex.data.model.move

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Name(
    @Json(name = "language")
    val language: LanguageXX = LanguageXX(),
    @Json(name = "name")
    val name: String = ""
) : Parcelable