package com.example.disney_characters.ui.customView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.disney_characters.R
import com.example.disney_characters.databinding.ViewBannerBinding

class ViewBanner(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: ViewBannerBinding =
        ViewBannerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ViewBanner, 0, 0).run {
            setBannerMessage(getString(R.styleable.ViewBanner_bannerMessage))
            setBannerTextColor(getColor(R.styleable.ViewBanner_bannerTextColor, Color.BLACK))
            setBannerBackground(getDrawable(R.styleable.ViewBanner_bannerBackground))
            setBannerStartIcon(getDrawable(R.styleable.ViewBanner_bannerIcStart))
            setBannerCloseIcon(getDrawable(R.styleable.ViewBanner_bannerIcClose))
        }
    }

    fun setBannerMessage(message: String?) {
        binding.bannerMessage.text = message
    }

    fun setBannerTextColor(textColor: Int) {
        binding.bannerMessage.setTextColor(textColor)
    }

    fun setBannerStartIcon(icon: Drawable?) {
        binding.bannerStartIcon.setImageDrawable(icon)
    }

    fun setBannerCloseIcon(icon: Drawable?) {
        binding.bannerCloseIcon.setImageDrawable(icon)
    }


    fun setBannerBackground(background: Drawable?) {
        binding.bannerContainer.background = background
    }

    fun closeBanner(onClick: () -> Unit) {
        binding.bannerCloseIcon.setOnClickListener {
            onClick.invoke()
        }
    }

    fun getBannerState(state: BannerState) {
        when (state) {
            is BannerState.Success -> setBannerState(
                state.message,
                ContextCompat.getColor(context, R.color.text_green),
                ContextCompat.getDrawable(context, R.drawable.ic_success),
                ContextCompat.getDrawable(context, R.drawable.bg_success)
            )

            is BannerState.Error -> setBannerState(
                state.message,
                ContextCompat.getColor(context, R.color.text_red),
                ContextCompat.getDrawable(context, R.drawable.ic_error),
                ContextCompat.getDrawable(context, R.drawable.bg_error)
            )

            is BannerState.Info -> setBannerState(
                state.message,
                ContextCompat.getColor(context, R.color.text_blue),
                ContextCompat.getDrawable(context, R.drawable.ic_info),
                ContextCompat.getDrawable(context, R.drawable.bg_info)
            )

            is BannerState.Warning -> setBannerState(
                state.message,
                ContextCompat.getColor(context, R.color.text_yellow),
                ContextCompat.getDrawable(context, R.drawable.ic_warning),
                ContextCompat.getDrawable(context, R.drawable.bg_warning)
            )
        }
    }

    private fun setBannerState(
        message: String,
        textColor: Int,
        icon: Drawable?,
        background: Drawable?
    ) {
        setBannerMessage(message)
        setBannerTextColor(textColor)
        setBannerStartIcon(icon)
        setBannerBackground(background)
    }

    sealed class BannerState() {

        data class Success(val message: String) : BannerState()
        data class Info(val message: String) : BannerState()
        data class Warning(val message: String) : BannerState()
        data class Error(val message: String) : BannerState()
    }
}