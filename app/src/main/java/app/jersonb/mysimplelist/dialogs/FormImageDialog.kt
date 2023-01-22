package app.jersonb.mysimplelist.dialogs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import app.jersonb.mysimplelist.databinding.DialogImageFormBinding
import app.jersonb.mysimplelist.extensions.loadImage

class FormImageDialog(private val context: Context) {
    fun show(
        urlDefault: String? = null,
        onLoadImage: (urlImage: String, imageView: ImageView) -> Unit
    ) {
        DialogImageFormBinding.inflate(LayoutInflater.from(context)).apply {
            var url = ""

            urlDefault?.let {
                imageProductInput.loadImage(it)
                inputUrl.setText(it)
                url = it
            }

            buttonLoad.setOnClickListener {
                url = inputUrl.text.toString()
                imageProductInput.loadImage(url)
            }
            AlertDialog
                .Builder(context)
                .setView(root)
                .setPositiveButton("Salvar") { _, _ ->
                    Log.i("FormImageDialog.show", url)
                    onLoadImage(url, imageProductInput)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }

    }
}