package com.example.services

import com.example.rules.MaxLength
import com.example.rules.MinLength
import com.example.rules.Required

class Validator {

    private fun splitString(str: String): List<String> =
        str.split(":").takeIf { ":" in str } ?: listOf(str, "0")

    private fun localizeFieldName(fieldNames: Array<String>, locale: I18n.Locale): String {
        return fieldNames.map { splitString(it) }
            .firstOrNull { it[0] == locale.shortName }
            ?.get(1) ?: "fieldName"
    }

    private fun getDefaultFieldName(fieldNames: Array<String>): String {
        return fieldNames.firstOrNull()?.substringBefore(":") ?: "fieldName"
    }

    fun check(fieldNames: Array<String>, field: String, rules: Array<String>, locale: I18n.Locale): MutableList<String> {
        val array = mutableListOf<String>()
        val fieldName = localizeFieldName(fieldNames, locale)

        for (rule in rules) {
            val (key, value) = splitString(rule).let { it[0] to it[1].toInt() }

            when (key) {
                "required" -> Required(field, value, locale, fieldName, array).check()
                "minLength" -> MinLength(field, value, locale, fieldName, array).check()
                "maxLength" -> MaxLength(field, value, locale, fieldName, array).check()
            }

        }
        return array
    }

}