package com.pezinho

import android.widget.Toast
import androidx.activity.ComponentActivity

fun ComponentActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}