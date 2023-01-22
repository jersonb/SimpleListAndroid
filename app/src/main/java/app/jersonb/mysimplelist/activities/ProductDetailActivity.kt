package app.jersonb.mysimplelist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.jersonb.mysimplelist.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Detalhes do produto"
        setContentView(binding.root)
    }
}