package com.formation.learning_android

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class FileListDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity?.layoutInflater

        builder.setView(inflater?.inflate(R.layout.dialog_file_list, null))
                .setPositiveButton("Supprimer", null)
                .setNegativeButton("Annuler", null)
                .setTitle("Contenu supprimé : ")

        return builder.create()
    }
}