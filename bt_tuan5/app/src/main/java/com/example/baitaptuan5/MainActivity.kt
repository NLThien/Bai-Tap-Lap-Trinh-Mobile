package com.example.baitaptuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.baitaptuan5.ui.theme.AppTheme
import com.example.baitaptuan5.screens.AddTaskScreen
import com.example.baitaptuan5.screens.TaskListScreen

class MainActivity : ComponentActivity() {
    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskRepository = TaskRepository(this)

        setContent {
            AppTheme {
                var isAddingTask by remember { mutableStateOf(false) }
                var tasks by remember { mutableStateOf(taskRepository.loadTasks()) }

                if (isAddingTask) {
                    AddTaskScreen(
                        onNavigateBack = { isAddingTask = false },
                        onTaskAdded = { task ->
                            val updatedTasks = tasks + task
                            tasks = updatedTasks
                            taskRepository.saveTasks(updatedTasks)
                        }
                    )
                } else {
                    TaskListScreen(
                        tasks = tasks,
                        onAddClick = { isAddingTask = true }
                    )
                }
            }
        }
    }
}