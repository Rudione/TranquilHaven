package com.rudione.tranquilhaven.ui.authorization.dialog

import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rudione.tranquilhaven.R

fun Fragment.setupBottomSheetDialog(
    onSendClick: (String) -> Unit
) {
    val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_password_dialog, null)

    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edEmail = view.findViewById<EditText>((R.id.reset_password_et))
    val buttonSend = view.findViewById<Button>(R.id.reset_password_bt_send)
    val buttonCancel = view.findViewById<Button>(R.id.reset_password_bt_cancel)

    buttonSend.setOnClickListener {
        val email = edEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    buttonCancel.setOnClickListener {
        dialog.dismiss()
    }
}