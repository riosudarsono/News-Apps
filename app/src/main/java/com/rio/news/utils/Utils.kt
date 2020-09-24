package com.rio.news.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rio.news.R
import java.text.ParseException
import java.text.SimpleDateFormat

object Utils {

    fun setGlide(context: Context, urlImage: String?, imageView: ImageView) {
        if (!urlImage.isNullOrEmpty()) {
            Glide.with(context)
                .asBitmap()
                .load(urlImage)
                .disallowHardwareConfig()
                .centerCrop().fitCenter()
                .dontTransform().dontAnimate()
                .thumbnail(0.3f)
                .placeholder(R.drawable.ic_image_grey)
                .error(R.drawable.ic_image_grey)
                .timeout(40 * 60 * 1000)
                .into(imageView)
        }
    }

    fun setGlideCircle(context: Context, urlImage: String?, imageView: ImageView) {
        if (!urlImage.isNullOrEmpty()) {
            Glide.with(context)
                .asBitmap()
                .load(urlImage)
                .disallowHardwareConfig()
                .centerCrop().fitCenter()
                .dontTransform().dontAnimate()
                .thumbnail(0.3f)
                .placeholder(R.drawable.ic_image_grey)
                .error(R.drawable.ic_image_grey)
                .apply(RequestOptions.circleCropTransform())
                .timeout(40 * 60 * 1000)
                .into(imageView)
        }
    }

    fun openLink(mLink: String, context: Context) {
        var link = mLink.replace("www.", "").replace(" ", "").replace("\n", "")
        if (!link.startsWith("http://") && !link.startsWith("https://")) {
            link = "http://$link"
        }
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        if (i.resolveActivity(context.packageManager) != null) {
            context.startActivity(i)
        }
    }

    fun convertDate(dateString: String?): String? {
        val formatIn = "yyyy-MM-dd'T'HH:mm:ss"
        val formatOut = "E, dd MMM yyyy, HH:mm"
        return if (!dateString.isNullOrEmpty()) {
            try {
                val sdf = SimpleDateFormat(formatIn)
                val date = sdf.parse(dateString)
                val fmt = SimpleDateFormat(formatOut)
                fmt.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                ""
            }
        } else {
            ""
        }
    }

}