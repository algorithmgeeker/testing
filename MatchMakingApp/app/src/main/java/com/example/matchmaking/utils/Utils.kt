package com.example.matchmaking.utils

import android.R
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

/**
 * Display Dialog
 */
fun displayAlertDialog(title: String?, message: String?, context: Context?) {
    AlertDialog.Builder(context!!)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(
            R.string.ok
        ) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
        .show()
}