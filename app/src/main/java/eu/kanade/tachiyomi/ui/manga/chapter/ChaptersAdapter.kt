package eu.kanade.tachiyomi.ui.manga.chapter

import android.content.Context
import androidx.core.content.ContextCompat
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.data.preference.PreferencesHelper
import eu.kanade.tachiyomi.ui.manga.MangaController
import eu.kanade.tachiyomi.util.system.getResourceColor
import uy.kohesive.injekt.injectLazy
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class ChaptersAdapter(
    controller: MangaController,
    context: Context
) : FlexibleAdapter<ChapterItem>(null, controller, true) {

    val preferences: PreferencesHelper by injectLazy()

    var items: List<ChapterItem> = emptyList()

    val readColor = ContextCompat.getColor(context, R.color.material_on_surface_disabled)
    val unreadColor = context.getResourceColor(R.attr.colorOnSurface)

    val bookmarkedColor = context.getResourceColor(R.attr.colorAccent)

    val decimalFormat = DecimalFormat(
        "#.###",
        DecimalFormatSymbols()
            .apply { decimalSeparator = '.' }
    )

    val dateFormat: DateFormat = preferences.dateFormat()

    override fun updateDataSet(items: List<ChapterItem>?) {
        this.items = items ?: emptyList()
        super.updateDataSet(items)
    }

    fun indexOf(item: ChapterItem): Int {
        return items.indexOf(item)
    }
}
