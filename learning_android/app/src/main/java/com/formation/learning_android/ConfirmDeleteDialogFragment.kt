package com.formation.learning_android


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog

import androidx.fragment.app.DialogFragment



class ConfirmDeleteDialogFragment: DialogFragment() {

    interface ConfirmDeleteListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    private val TAG = ConfirmDeleteDialogFragment::class.java.simpleName

    var listener: ConfirmDeleteListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)

        builder.setMessage("Supprimer tout le contenu du téléphone ?")
                .setPositiveButton("Oh oui!!", object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.i(TAG, "Youpi ! On va tout casser")
                        listener?.onDialogPositiveClick()
                    }
                })
                .setNegativeButton("Euh... Non", DialogInterface.OnClickListener{dialog, which ->
                    Log.i(TAG, "Ok. Ca sera pour une prochaine fois")
                    listener?.onDialogNegativeClick()
                })

        return builder.create()
    }
}