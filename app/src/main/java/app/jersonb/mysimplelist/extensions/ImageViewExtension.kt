package app.jersonb.mysimplelist.extensions

import android.widget.ImageView
import app.jersonb.mysimplelist.R
import coil.load

fun ImageView.loadImage(url: String?) {
    load(url) {
        error(R.drawable.default_image)
        placeholder(R.drawable.default_image)
    }
}

fun ImageView.loadImage(imageView: ImageView){
    load(imageView.drawable)
}