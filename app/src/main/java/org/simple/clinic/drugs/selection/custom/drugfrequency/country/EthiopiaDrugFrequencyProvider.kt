package org.simple.clinic.drugs.selection.custom.drugfrequency.country

import android.content.res.Resources
import org.simple.clinic.R
import org.simple.clinic.drugs.search.DrugFrequency.BD
import org.simple.clinic.drugs.search.DrugFrequency.OD
import org.simple.clinic.drugs.search.DrugFrequency.QDS
import org.simple.clinic.drugs.search.DrugFrequency.TDS

class EthiopiaDrugFrequencyProvider : DrugFrequencyProvider {
  override fun provide(resources: Resources): List<DrugFrequencyChoiceItem> {
    return listOf(
        DrugFrequencyChoiceItem(drugFrequency = null, label = resources.getString(R.string.custom_drug_entry_sheet_frequency_none)),
        DrugFrequencyChoiceItem(drugFrequency = OD, label = resources.getString(R.string.custom_drug_entry_sheet_frequency_ethiopia_PD)),
        DrugFrequencyChoiceItem(drugFrequency = BD, label = resources.getString(R.string.custom_drug_entry_sheet_frequency_ethiopia_BID)),
        DrugFrequencyChoiceItem(drugFrequency = TDS, label = resources.getString(R.string.custom_drug_entry_sheet_frequency_ethiopia_TID)),
        DrugFrequencyChoiceItem(drugFrequency = QDS, label = resources.getString(R.string.custom_drug_entry_sheet_frequency_ethiopia_QID))
    )
  }
}