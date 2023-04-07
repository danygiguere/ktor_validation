package com.example.rules

import com.example.services.I18n

class MaxLength(val field: String, val value: Int, val locale: I18n.Locale, val fieldName: String, var array: MutableList<String>) {

    fun check(): MutableList<String> {
        if(field.length > value) {
            if(locale === I18n.Locale.ENGLISH) {
                array.add("The $fieldName field must contain less than $value characters")
            } else {
                array.add("Le champ $fieldName doit contenir moins de $value caractères")
            }
        }
        return array
    }

}