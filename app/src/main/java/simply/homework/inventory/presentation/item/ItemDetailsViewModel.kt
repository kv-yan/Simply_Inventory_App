package simply.homework.inventory.presentation.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import simply.homework.inventory.domain.usecase.DeleteItemUseCase
import simply.homework.inventory.domain.usecase.GetItemByIdUseCase
import simply.homework.inventory.domain.usecase.UpdateItemUseCase

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val deleteItemUseCase: DeleteItemUseCase,
    getItemByIdUseCase: GetItemByIdUseCase,
    private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.ITEM_ID_ARG])

    val uiState: StateFlow<ItemDetailsUiState> = getItemByIdUseCase.invoke(itemId)
        .filterNotNull()
        .map { item ->
            ItemDetailsUiState(
                outOfStock = item.quantity <= 0,
                itemDetails = item.toItemDetails()
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailsUiState()
        )

    fun deleteItem() {
        viewModelScope.launch {
            uiState.value.itemDetails.let { itemDetails ->
                deleteItemUseCase.invoke(itemDetails.toItem())
            }
        }
    }

    fun sellItem() {
        viewModelScope.launch {
            uiState.value.itemDetails.let { itemDetails ->
                val item = itemDetails.toItem()
                val quantity = if (item.quantity > 0) item.quantity - 1 else 0
                val newItem = item.copy(quantity = quantity)
                updateItemUseCase.invoke(newItem)
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true, val itemDetails: ItemDetails = ItemDetails()
)
