package com.example.baitaptuan5

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TaskRepository(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("tasks", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveTasks(tasks: List<Task>) {
        val json = gson.toJson(tasks)
        sharedPreferences.edit().putString("tasks_key", json).apply()
    }

    fun loadTasks(): List<Task> {
        val json = sharedPreferences.getString("tasks_key", null)
        return if (json != null) {
            val type = object : TypeToken<List<Task>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
} 