package com.vdc.generativeai.Navigation

sealed class Screens (
    var route: String
){
    object Text2Text : Screens("Text-2-Text")
    object ImageText2Text : Screens("Image/Text-2-Text")
}