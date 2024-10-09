package simply.homework.inventory.presentation.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import simply.homework.inventory.domain.usecase.GetItemByIdUseCase
import simply.homework.inventory.domain.usecase.UpdateItemUseCase

class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {

    var itemUiState by mutableStateOf(ItemUiState())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.ITEM_ID_ARG])

    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    init {
        viewModelScope.launch {
            println(itemId)
            getItemByIdUseCase.invoke(itemId)
                .filterNotNull()
                .collect { item ->
                    println(item.toItemDetails())
                    itemUiState = ItemUiState(
                        isEntryValid = validateInput(item.toItemDetails()),
                        itemDetails = item.toItemDetails()
                    )
                }
        }
    }

    fun saveChanges() {
        if (validateInput()) {
            updateItem()
        }
    }

    private fun updateItem() {
        viewModelScope.launch {
            itemUiState.itemDetails.toItem().let { item ->
                updateItemUseCase.invoke(item)
            }
        }
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
