package com.quran.labs.androidquran.presenter.data

import com.quran.data.core.QuranInfo
import com.quran.labs.androidquran.model.translation.ArabicDatabaseUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JuzListPresenter @Inject constructor(
  private val quranInfo: QuranInfo,
  private val arabicDatabaseUtils: ArabicDatabaseUtils
) {

  suspend fun quarters(): List<String> {
    return withContext(Dispatchers.IO) {
      val ayahIds = quranInfo.quarters
        .map { quranInfo.getAyahId(it.first(), it.last()) }
      val results = arabicDatabaseUtils.getAyahTextForAyat(ayahIds)
      ayahIds.map { results[it] ?: "" }
    }
  }
}
