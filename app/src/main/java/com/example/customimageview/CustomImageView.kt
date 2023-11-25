package com.example.customimageview

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.card.MaterialCardView
import java.util.Random

class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var cardBg: MaterialCardView
    private lateinit var tvWord: TextView
    private lateinit var image: ImageView
    private lateinit var root: ConstraintLayout
    private var textColorApplied = false

    init {
        initCustomImageView(context)
    }

    private fun initCustomImageView(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.custom_image_view, this, true)
        cardBg = findViewById(R.id.card_bg)
        tvWord = findViewById(R.id.tv_word)
        image = findViewById(R.id.image)
        root = findViewById(R.id.root)
    }

    fun getRootViewClick(): View {
        return root
    }

    fun setCustomImageData(uri: Uri? = null, cardBgColor: Int? = null, text: String? = null) {
        if (uri != null) {
            cardBg.visibility = View.VISIBLE
            image.visibility = View.VISIBLE
            tvWord.visibility = GONE
            setImageData(uri)
        } else if (cardBgColor != null && text != null) {
            cardBg.visibility = View.VISIBLE
            image.visibility = GONE
            tvWord.visibility = View.VISIBLE
            setCardBackgroundColor(cardBgColor)
            setLetter(text)
        } else {
            return
        }
    }

    private fun setImageData(uri: Uri) {
        image.setImageURI(uri)
    }

    private fun setCardBackgroundColor(color: Int) {
        cardBg.setCardBackgroundColor(color)
    }

    private fun setLetter(text: String) {
        if (text.length == 1 && text.isNotEmpty()) {
            tvWord.text = text
            if (!textColorApplied) {
                setRandomColor()
                textColorApplied = true
            }
        } else if (text.isNotEmpty()) {
            tvWord.text = text[0].toString()
            if (!textColorApplied) {
                setRandomColor()
                textColorApplied = true
            }
        } else {
            textColorApplied = false
            return
        }
    }

    private fun setRandomColor() {
        val random = Random()
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        tvWord.setTextColor(color)
    }

}