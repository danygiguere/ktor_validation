package com.example.services

class Validator {

    companion object {

        fun splitString(str: String): List<String> =
            str.split(":").takeIf { ":" in str } ?: listOf(str, "0")

        fun check(fieldNames: Array<String>, field: String, rules: Array<String>, locale: String): MutableList<String> {
            val array = mutableListOf<String>()
            var fieldName = "fieldName"

            for (name in fieldNames) {
                val fieldNameKeyValue = splitString(name)
                if(fieldNameKeyValue[0] == locale) {
                    fieldName = fieldNameKeyValue[1]
                }
            }

            for (rule in rules) {
                val ruleKeyValue = splitString(rule)
                val key = ruleKeyValue[0]
                val value = ruleKeyValue[1].toInt()

                when (key) {
                    "required" -> {
                        if (field.isEmpty()) {
                            if (locale === "en") {
                                array.add("The $fieldName field is required")
                            } else {
                                array.add("Le champ $fieldName est requis")
                            }
                        }
                    }
                    "minLength" -> {
                        if(field.length < value) {
                            if(locale === "en") {
                                array.add("The $fieldName field must contain at least $value characters")
                            } else {
                                array.add("Le champ $fieldName doit contenir au moins $value caractères")
                            }
                        }
                    }
                    "maxLength" -> {
                        if(field.length > value) {
                            if(locale === "en") {
                                array.add("The $fieldName field must contain less than $value characters")
                            } else {
                                array.add("Le champ $fieldName doit contenir moins de $value caractères")
                            }
                        }
                    }
                }

            }
            return array
        }

    }

}