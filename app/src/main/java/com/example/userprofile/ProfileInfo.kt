package com.example.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile_info.*

class ProfileInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_info)

        var userDataDeserialized=intent.getSerializableExtra("USER_INFO") as UserData?
        Toast.makeText(this,"${userDataDeserialized?.name}",Toast.LENGTH_SHORT).show()

        tvDisplayName.text=userDataDeserialized?.name
        tvDisplayPhone.text=userDataDeserialized?.phoneNumber.toString()
        tvDisplayEmail.text=userDataDeserialized?.email
        tvSex.text=userDataDeserialized?.sex

    }
}