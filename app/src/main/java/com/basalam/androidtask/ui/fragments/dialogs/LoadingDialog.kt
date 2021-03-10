package com.basalam.androidtask.ui.fragments.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.basalam.androidtask.R

class LoadingDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(requireContext()) // initialize alert dialog
        // inflate dialog view
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.loading_progress_dialog,null,false)
        dialog.setView(view) // set above view into dialog
        return dialog.create() // crate
    }
}