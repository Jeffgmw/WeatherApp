package com.example.weatherapp.utils.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.weatherapp.utils.extensions.alertDialog
import com.example.weatherapp.utils.extensions.toast

fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(
    message,
    duration
)

inline fun androidx.fragment.app.Fragment.alertDialog(
    body: AlertDialog.Builder.() -> AlertDialog.Builder
) = activity?.alertDialog(body)
