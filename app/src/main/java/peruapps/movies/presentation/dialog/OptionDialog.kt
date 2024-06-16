package peruapps.movies.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import kotlinx.android.synthetic.main.dialog_option.*
import peruapps.movies.R

class OptionDialog(context: Context) : Dialog(context) {

    var onClickAccept: OnClickAccept? = null
    var onClickCancel: OnClickCancel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDialog()
        setClick()
    }

    private fun setDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_option)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun setClick() {
        acceptButton.setOnClickListener {
            onClickAccept?.onClickAccept(this)
        }
        cancelButton.setOnClickListener {
            onClickCancel?.onClickCancel(this)
        }
    }

    fun setTitle(title: String) {
        titleText.text = title
    }

    fun setMessage(message: String) {
        messageText.text = message
    }

    fun setPositiveButtonText(text: String) {
        acceptButton.text = text
    }

    fun setNegativeButtonText(text: String) {
        cancelButton.text = text
    }

    class Builder(val context: Context) {

        private var title = ""
        private var message = ""
        private var positiveButtonText = context.getString(R.string.ok)
        private var negativeButtonText = context.getString(R.string.cancel)
        var onClickAccept: OnClickAccept? = null
        var onClickCancel: OnClickCancel? = null

        fun setTitle(title: String) = apply {
            this.title = title
        }

        fun setMessage(message: String) = apply {
            this.message = message
        }

        fun setPositiveButtonText(text: String) = apply {
            positiveButtonText = text
        }

        fun setNegativeButtonText(text: String) = apply {
            negativeButtonText = text
        }

        inline fun setOnClickAccept(crossinline onClickDownload: (dialog: Dialog) -> Unit) =
            apply {
                this.onClickAccept = object : OnClickAccept {
                    override fun onClickAccept(dialog: Dialog) {
                        onClickDownload(dialog)
                    }
                }
            }

        inline fun setOnClickCancel(crossinline onClickCancel: (dialog: Dialog) -> Unit) =
            apply {
                this.onClickCancel = object : OnClickCancel {
                    override fun onClickCancel(dialog: Dialog) {
                        onClickCancel(dialog)
                    }
                }
            }

        fun show() {
            val dialog = OptionDialog(context)
            dialog.show()
            dialog.setTitle(title)
            dialog.setMessage(message)
            dialog.setPositiveButtonText(positiveButtonText)
            dialog.setNegativeButtonText(negativeButtonText)
            dialog.onClickAccept = onClickAccept
            dialog.onClickCancel = onClickCancel
        }
    }

    interface OnClickAccept {
        fun onClickAccept(dialog: Dialog)
    }

    interface OnClickCancel {
        fun onClickCancel(dialog: Dialog)
    }
}