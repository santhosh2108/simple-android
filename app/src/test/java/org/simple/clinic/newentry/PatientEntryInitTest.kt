package org.simple.clinic.newentry

import com.spotify.mobius.test.FirstMatchers.hasEffects
import com.spotify.mobius.test.FirstMatchers.hasModel
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import org.junit.Test
import org.simple.clinic.editpatient.EditPatientInit
import org.simple.clinic.editpatient.EditPatientModel
import org.simple.clinic.editpatient.EditPatientState
import org.simple.clinic.editpatient.FetchBpPassportsEffect
import org.simple.clinic.editpatient.PrefillFormEffect
import org.simple.clinic.feature.Feature
import org.simple.clinic.feature.Features
import org.simple.clinic.remoteconfig.DefaultValueConfigReader
import org.simple.clinic.remoteconfig.NoOpRemoteConfigService
import java.util.UUID

class PatientEntryInitTest {
  private val initSpec = InitSpec(PatientEntryInit(isVillageTypeAheadEnabled = true))
  private val defaultModel = PatientEntryModel.DEFAULT

  @Test
  fun `when screen is created, then load initial data`() {
    initSpec.whenInit(defaultModel).then(assertThatFirst(
        hasModel(defaultModel),
        hasEffects(
            FetchPatientEntry, LoadInputFields, FetchColonyOrVillagesEffect
        )
    ))
  }

  @Test
  fun `when screen is created and village type ahead is not enabled, then do not fetch colony or villages`() {
    val initSpec = InitSpec(PatientEntryInit(isVillageTypeAheadEnabled = false))

    initSpec.whenInit(defaultModel).then(assertThatFirst(
        hasModel(defaultModel),
        hasEffects(
            FetchPatientEntry, LoadInputFields
        )
    ))
  }

  @Test
  fun `when screen is restored, then don't fetch colony or villages`() {
    val colonyOrVillages = listOf("Colony1", "Colony2", "Colony3", "Colony4")

    val updatedVillageOrColonyNamesModel = defaultModel.colonyOrVillageListUpdated(colonyOrVillages)

    initSpec.whenInit(updatedVillageOrColonyNamesModel).then(assertThatFirst(
        hasModel(updatedVillageOrColonyNamesModel),
        hasEffects(
            FetchPatientEntry, LoadInputFields
        )
    ))
  }
}