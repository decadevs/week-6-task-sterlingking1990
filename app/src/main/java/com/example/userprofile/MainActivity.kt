package com.example.userprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    /**
     * declare the views in this activity
     */
    private lateinit var sexSelected: String
    private lateinit var etName:EditText
    lateinit var etPhone:EditText
    lateinit var etEmail:EditText
    lateinit var spSex:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * reference the views using their Resource ID's
         */
        etName=findViewById(R.id.etName)
        etPhone=findViewById(R.id.etPhone)
        etEmail =findViewById(R.id.etEmail)
        spSex=findViewById(R.id.spSex)


        /**
         * Populate the spinner view with data during run time
         */
        var adapter=ArrayAdapter.createFromResource(this,R.array.sex,android.R.layout.simple_spinner_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spSex.adapter=adapter

        /**
         * Use KTX to check which item was selected
         */
        spSex.onItemSelectedListener=this


        /**
         * When the btnDisplayProfile is clicked, check for validity run the displayInfo method
         */
        btnDisplayInfo.setOnClickListener {
            val name=etName.text.toString()
            val phone=etPhone.text.toString()
            val email=etEmail.text.toString()
            val personProfile= UserData(name,phone,email,sexSelected)
            //initialise validator
            val validator=Validator()
            //check that the fields are valid and display info
            validator.phoneNumber=phone
            validator.name=name
            validator.email=email
            val isValidPhoneNumber=validator.validateNumber()
            val isValidName=validator.validateName()
            val isValidEmail=validator.validateEmail()
            displayInfo(personProfile,isValidPhoneNumber,isValidName,isValidEmail)

        }

    }

    /**
     * Display info method, taking the data to display and wether the phone number to check for validity
     * Displays info if the phone number is valid else return
     */

    private fun displayInfo(profile: UserData, isValidPhoneNumber: Boolean, isValidName: Boolean, isValidEmail: Boolean) {
        if (!isValidPhoneNumber) {
            Toast.makeText(this, R.string.invalid_phone_number, Toast.LENGTH_LONG).show()
        } else if (!isValidName) {
            Toast.makeText(this, R.string.no_name_entered, Toast.LENGTH_LONG).show()
        } else if (!isValidEmail) {
            Toast.makeText(this, R.string.no_email_entered, Toast.LENGTH_LONG).show()
        }
        else if(!isValidEmail && !isValidName && !isValidPhoneNumber){
            Toast.makeText(this, R.string.all_field_empty, Toast.LENGTH_LONG).show()
        }
        else {
            Intent(this, ProfileInfo::class.java).also {
                it.putExtra("USER_INFO", profile)
                startActivity(it)
            }
        }
    }


    /**
     * get the item that was clicked and set sexSelected to that item
     */

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        val sex: String = parent?.getItemAtPosition(position).toString()
        sexSelected=sex
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}