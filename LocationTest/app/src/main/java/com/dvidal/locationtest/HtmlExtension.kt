package com.dvidal.locationtest

import android.os.Build
import android.text.Html
import android.text.Spanned

/**
 * @author diegovidal on 22/11/18.
 */

fun String.formattedHtmlText(): Spanned {

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
     else
        Html.fromHtml(this)
}