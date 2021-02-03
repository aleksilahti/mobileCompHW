package com.example.mobilecomphw

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_reminder.view.*

class ReminderAdapter(
    private val reminders: MutableList<Reminder>
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {
    class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        return ReminderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_reminder,
                parent,
                false
            )
        )
    }

    fun addReminder(reminder: Reminder) {
        reminders.add(reminder)
        notifyItemInserted(reminders.size - 1)
    }

    fun removeCheckedReminders() {
        reminders.removeAll {reminder ->
            reminder.isChecked
        }
    }

    override fun getItemCount(): Int {
        return reminders.size
    }

    private fun toggleStrikeThrough(tvReminderTitle: TextView, isChecked: Boolean) {
        if (isChecked){
            tvReminderTitle.paintFlags = tvReminderTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvReminderTitle.paintFlags = tvReminderTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val currentReminder = reminders[position]
        holder.itemView.apply {
            tvReminderTitle.text = currentReminder.title
            cbReminder.isChecked = currentReminder.isChecked
            toggleStrikeThrough(tvReminderTitle, currentReminder.isChecked)
            cbReminder.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvReminderTitle, isChecked)
                currentReminder.isChecked = !currentReminder.isChecked
            }
        }
    }
}