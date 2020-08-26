package com.example.userprofile

class Validator {

    lateinit var phoneNumber:String
    lateinit var email:String
    lateinit var name:String


    fun validateNumber():Boolean{
        var case01=phoneNumber.startsWith("01")
        var case070=phoneNumber.startsWith("070")
        var case080=phoneNumber.startsWith("080")
        var case090=phoneNumber.startsWith("090")
        var case234=phoneNumber.startsWith("234")

        var caseFor01=phoneNumber.length==9 && case01

        var caseFor080And070And090=(phoneNumber.length==11 && case070) ||
                (phoneNumber.length==11 && case080) ||
                (phoneNumber.length==11 && case090)

        var caseFor234=phoneNumber.length==13 && case234

        return (caseFor01 || caseFor080And070And090 || caseFor234)
    }

    fun validateEmail()= email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validateName()=name.isNotEmpty()

}