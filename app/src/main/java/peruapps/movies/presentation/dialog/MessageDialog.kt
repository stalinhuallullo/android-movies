package peruapps.movies.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialog_message.*
import peruapps.movies.R

class MessageDialog(context: Context) : Dialog(context) {

    var onClickAccept: OnClickAccept? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDialog()
        setClick()
    }

    private fun setDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_message)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setCancelable(false)
    }

    private fun setClick() {
        acceptButton.setOnClickListener {
            onClickAccept?.onClickAccept(this)
        }
    }

    fun setTitle(title: String) {
        titleText.text = title
    }

    fun setMessage(message: String?) {
        messageText.text = message
    }

    fun setPositiveButtonText(text: String) {
        acceptButton.text = text
    }

    class Builder(val context: Context) {

        private var title = ""
        private var message: String? = ""
        private var positiveButtonText = context.getString(R.string.ok)
        var onClickAccept: OnClickAccept? = null

        fun setTitle(title: String) = apply {
            this.title = title
        }

        fun setMessage(message: String?) = apply {
            this.message = message
        }

        fun setPositiveButtonText(text: String) = apply {
            positiveButtonText = text
        }


        inline fun setOnClickAccept(crossinline onClickDownload: (dialog: Dialog) -> Unit) =
            apply {
                this.onClickAccept = object : OnClickAccept {
                    override fun onClickAccept(dialog: Dialog) {
                        onClickDownload(dialog)
                    }
                }
            }

        fun show() {
            val dialog = MessageDialog(context)
            dialog.show()
            dialog.setTitle(title)
            dialog.setMessage(message)
            dialog.setPositiveButtonText(positiveButtonText)
            dialog.onClickAccept = onClickAccept
        }
    }

    interface OnClickAccept {
        fun onClickAccept(dialog: Dialog)
    }
}