package com.github.pvoid.vecka

import android.graphics.drawable.Icon
import androidx.wear.complications.ComplicationProviderService
import androidx.wear.complications.ComplicationRequest
import androidx.wear.complications.data.ComplicationData
import androidx.wear.complications.data.ComplicationText
import androidx.wear.complications.data.ComplicationType
import androidx.wear.complications.data.LongTextComplicationData
import androidx.wear.complications.data.MonochromaticImage
import androidx.wear.complications.data.PlainComplicationText
import androidx.wear.complications.data.ShortTextComplicationData
import androidx.wear.complications.data.SmallImage
import androidx.wear.complications.data.SmallImageComplicationData
import androidx.wear.complications.data.SmallImageType
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

private val LOCALE = Locale("sv", "SE")

class VeckaNuComplicationProviderService :  ComplicationProviderService() {
    private val icon by lazy<Icon> {
        Icon.createWithResource(this, R.drawable.icn_complications)
    }

    private val image by lazy<MonochromaticImage> {
        MonochromaticImage.Builder(icon).build()
    }

    override fun getPreviewData(type: ComplicationType): ComplicationData? {
        if (type == ComplicationType.LONG_TEXT) {
            return LongTextComplicationData.Builder(
                PlainComplicationText.Builder("Week 10").build(),
                PlainComplicationText.Builder(getString(R.string.complications_provider_week_number)).build()
            ).setMonochromaticImage(image)
            .build()
        }

        if (type == ComplicationType.SHORT_TEXT) {
            return ShortTextComplicationData.Builder(
                PlainComplicationText.Builder("Week 10").build(),
                PlainComplicationText.Builder(getString(R.string.complications_provider_week_number)).build()
            ).setMonochromaticImage(image)
            .build()
        }

        if (type == ComplicationType.SMALL_IMAGE) {
            SmallImageComplicationData.Builder(
                SmallImage.Builder(icon, SmallImageType.ICON).build(),
                PlainComplicationText.Builder("Week 10").build(),
            )
        }

        return null
    }

    override fun onComplicationRequest(request: ComplicationRequest, listener: ComplicationRequestListener) {
        val date = LocalDate.now()
        val week = date.get(WeekFields.of(LOCALE).weekOfYear())

        val text = PlainComplicationText.Builder("Week $week").build()

        val data: ComplicationData? = when (request.complicationType) {
            ComplicationType.SHORT_TEXT -> {
                ShortTextComplicationData.Builder(
                    text,
                    PlainComplicationText.Builder(getString(R.string.complications_provider_week_number)).build()
                ).setMonochromaticImage(image)
                .build()
            }
            ComplicationType.LONG_TEXT -> {
                LongTextComplicationData.Builder(
                    text,
                    PlainComplicationText.Builder(getString(R.string.complications_provider_week_number)).build()
                ).setMonochromaticImage(image)
                .build()
            }
            ComplicationType.SMALL_IMAGE -> {
                SmallImageComplicationData.Builder(
                    SmallImage.Builder(icon, SmallImageType.ICON).build(),
                    text,
                ).build()
            }
            else -> null
        }

        listener.onComplicationData(data)
    }
}