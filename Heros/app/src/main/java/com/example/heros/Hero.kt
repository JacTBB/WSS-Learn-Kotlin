package com.example.heros

import android.graphics.drawable.Drawable

class Hero(name: String, imageDrawable: Drawable) {
    var heroName = name
    var heroImage = imageDrawable

    override fun toString(): String {
        return "name : $heroName\n"
    }
}