package simply.homework.inventory.presentation.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import simply.homework.inventory.presentation.home.HomeViewModel
import simply.homework.inventory.presentation.item.ItemDetailsViewModel
import simply.homework.inventory.presentation.item.ItemEditViewModel
import simply.homework.inventory.presentation.item.ItemEntryViewModel

val presentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::ItemDetailsViewModel)
    viewModelOf(::ItemEditViewModel)
    viewModelOf(::ItemEntryViewModel)

    factory { SavedStateHandle() }

}