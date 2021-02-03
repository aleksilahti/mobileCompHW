package com.example.mobilecomphw

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_reminders.*
import java.time.LocalDateTime

class Reminders : AppCompatActivity() {

    private lateinit var reminderAdapter: ReminderAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminders)
        reminderAdapter = ReminderAdapter(mutableListOf())

        rvReminders.adapter = reminderAdapter
        rvReminders.layoutManager = LinearLayoutManager( this)

        btnAddReminder.setOnClickListener{
            val reminderTitle = etAddReminder.text.toString()
            if(reminderTitle.isNotEmpty()){
                val reminder = Reminder(reminderTitle, false, LocalDateTime.now())
                reminderAdapter.addReminder(reminder)
                etAddReminder.text.clear()
            }
        }
    }
}