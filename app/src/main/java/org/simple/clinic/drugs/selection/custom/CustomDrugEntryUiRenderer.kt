package org.simple.clinic.drugs.selection.custom

import org.simple.clinic.drugs.search.DrugFrequency
import org.simple.clinic.drugs.selection.custom.drugfrequency.country.DrugFrequencyChoiceItem
import org.simple.clinic.mobius.ViewRenderer
import org.simple.clinic.util.nullIfBlank

class CustomDrugEntryUiRenderer(
    private val ui: CustomDrugEntryUi,
    private val dosagePlaceholder: String
) : ViewRenderer<CustomDrugEntryModel> {
  override fun render(model: CustomDrugEntryModel) {
    initialSetup(model.openAs)

    if (model.drugFrequencyChoiceItems != null)
      setSheetTitle(model.drugName, model.dosage, model.frequency, model.drugFrequencyChoiceItems)

    showDefaultDosagePlaceholder(model.dosage, model.dosageHasFocus)
  }

  private fun setSheetTitle(
      drugName: String?,
      dosage: String?,
      frequency: DrugFrequency?,
      drugFrequencyChoiceItems: List<DrugFrequencyChoiceItem>
  ) {
    val index = getIndexOfDrugFrequencyChoiceItem(drugFrequencyChoiceItems, frequency)

    ui.setSheetTitle(drugName, dosage.nullIfBlank(), drugFrequencyChoiceItems[index].labelResId)
  }

  private fun getIndexOfDrugFrequencyChoiceItem(
      drugFrequencyChoiceItems: List<DrugFrequencyChoiceItem>,
      frequency: DrugFrequency?
  ) = drugFrequencyChoiceItems.map { it.drugFrequency }.indexOf(frequency)

  private fun showDefaultDosagePlaceholder(
      dosage: String?,
      dosageHasFocus: Boolean?
  ) {
    if (dosageHasFocus == null) return

    when {
      dosage != null && dosage == dosagePlaceholder && dosageHasFocus.not() -> ui.setDrugDosageText("")
      dosage.isNullOrBlank() && dosageHasFocus -> {
        ui.setDrugDosageText(dosagePlaceholder)
        ui.moveDrugDosageCursorToBeginning()
      }
    }
  }

  private fun initialSetup(openAs: OpenAs) {
    when (openAs) {
      is OpenAs.New -> setUpUIForCreatingDrugEntry()
      is OpenAs.Update -> setUpUIForUpdatingDrugEntry()
    }
  }

  private fun setUpUIForCreatingDrugEntry() {
    ui.hideRemoveButton()
    ui.setButtonTextAsAdd()
  }

  private fun setUpUIForUpdatingDrugEntry() {
    ui.showRemoveButton()
    ui.setButtonTextAsSave()
  }
}
