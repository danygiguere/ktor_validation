package com.example.rules

import com.example.services.I18n

class Required(val field: String, val value: Int, val locale: I18n.Locale, val fieldName: String, var array: MutableList<String>) {

    fun check(): MutableList<String> {
        if(field.isEmpty()) {
            if (locale === I18n.Locale.ENGLISH) {
                array.add("The $fieldName field is required")
            } else {
                array.add("Le champ $fieldName est requis")
            }
        }
        return array
    }

}