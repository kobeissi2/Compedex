package com.kobeissidev.jetpackcomposepokedex.data.model.pokemon

import android.os.Parcelable
import com.kpstv.bindings.AutoGenerateConverter
import com.kpstv.bindings.ConverterType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@AutoGenerateConverter(using = ConverterType.MOSHI)
@JsonClass(generateAdapter = true)
@Parcelize
data class OmegaRubyAlphaSapphire(
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_female") val frontFemale: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null,
    @Json(name = "front_shiny_female") val frontShinyFemale: String? = null,
) : Parcelable