package com.example.userprofile

import org.hamcrest.core.Is.`is`
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ValidatorTest {

    /**
     * declare test data
     */
    private var VALID_NUMBER="08060456301"
    private var INVALID_LONG_NUMBER_080="080606044994"
    private var VALID_NUMBER_234="2348060456301"
    private var INVALID_LONG_NUMBER_234="2348080949993923"
    private var VALID_NUMBER_01="017413946"
    private var INVALID_LONG_NUMBER_01="01738388585"
    private var VALID_NAME="Kingsley"
    private var INVALID_NAME=""
    private var VALID_EMAIL="izundukingsleyemeka@gmail.com"
    private var INVALID_EMAIL=""

    var validator=Validator()

    @Before
    fun setup(){

    }

    /**
     * check that phone number can be returned
     */
    @Test
    fun getPhoneNumber_phoneNumberCanBeGotten() {
        validator.phoneNumber=VALID_NUMBER
        assertThat(validator.phoneNumber,`is`(VALID_NUMBER))
    }

    /**
     * check that phone number can be set
     */
    @Test
    fun setPhoneNumber_inputValidNumber_acceptValidNumber() {
        validator.phoneNumber=VALID_NUMBER
    }

    /**
     * check that email can be returned
     */
    @Test
    fun getEmail_emailCanBeGotten() {
        validator.email=VALID_EMAIL
        assertThat(validator.email,`is`(VALID_EMAIL))
    }

    /**
     * check that email can be set
     */
    @Test
    fun setEmail_emailCanBeSet() {
        validator.email=VALID_EMAIL
    }

    /**
     * check that name can be returned
     */
    @Test
    fun getName_nameCanBeGotten() {
        validator.name=VALID_NAME
        assertThat(validator.name,`is`(VALID_NAME))
    }

    /**
     * check that name can be set
     */

    @Test
    fun setName_nameCanBeSet() {
        validator.name=VALID_NAME
    }

    /**
     * check that only valid number is accepted
     */

    @Test
    fun validateNumber_acceptOnlyValidPhoneNumbers() {
        validator.phoneNumber=VALID_NUMBER
        assertThat(validator.validateNumber(),`is`(true))

        validator.phoneNumber= INVALID_LONG_NUMBER_080
        assertThat(validator.validateNumber(),`is`(false))

        validator.phoneNumber=INVALID_LONG_NUMBER_234
        assertThat(validator.validateNumber(),`is`(false))

        validator.phoneNumber=INVALID_LONG_NUMBER_01
        assertThat(validator.validateNumber(),`is`(false))

    }

    @Test
    fun validateEmail_acceptValidEmailOnly() {
        validator.email=VALID_EMAIL
        assertTrue(validator.email,true)

        validator.email= INVALID_EMAIL
        assertFalse(validator.email,false)

    }

    @Test
    fun validateName_acceptValidName() {
        validator.name=VALID_NAME
        assertTrue(validator.name,true)

        validator.name= INVALID_NAME
        assertFalse(validator.name,false)
    }
}