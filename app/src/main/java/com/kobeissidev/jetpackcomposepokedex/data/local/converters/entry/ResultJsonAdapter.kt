// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN")

package com.kobeissidev.jetpackcomposepokedex.data.local.converters.entry

import com.kobeissidev.jetpackcomposepokedex.data.model.entry.Result
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.internal.Util
import kotlin.String

public class ResultJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Result>() {
  private val options: JsonReader.Options = JsonReader.Options.of("name", "url")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "name")

  public override fun toString(): String = buildString(28) {
      append("GeneratedJsonAdapter(").append("Result").append(')') }

  public override fun fromJson(reader: JsonReader): Result {
    var name: String? = null
    var url: String? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> name = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("name", "name",
            reader)
        1 -> url = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("url", "url", reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Result(
        name = name ?: throw Util.missingProperty("name", "name", reader),
        url = url ?: throw Util.missingProperty("url", "url", reader)
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Result?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("name")
    stringAdapter.toJson(writer, value_.name)
    writer.name("url")
    stringAdapter.toJson(writer, value_.url)
    writer.endObject()
  }
}