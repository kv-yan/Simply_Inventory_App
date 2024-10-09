package simply.homework.inventory.presentation.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import simply.homework.inventory.domain.usecase.DeleteItemUseCase
import simply.homework.inventory.domain.usecase.GetItemByIdUseCase
import simply.homework.inventory.domain.usecase.UpdateItemUseCase

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase,
    private val updateItemUseCase: UpdateItemUseCase
) : ViewModel() {
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    // MutableStateFlow to hold the UI state
    private val _uiState = MutableStateFlow(ItemDetailsUiState())

    val uiState: StateFlow<ItemDetailsUiState> = _uiState.asStateFlow()

    init {
        // Launch a coroutine in the ViewModel scope to fetch the item details
        viewModelScope.launch {
            getItemByIdUseCase.invoke(itemId) // Fetch the item by its ID
                .filterNotNull() // Filter out any null values
                .collect { item ->
                    // Update the UI state with the fetched item
                    println(item.toItemDetails())
                    _uiState.value = ItemDetailsUiState(
                        outOfStock = item.quantity <= 0, itemDetails = item.toItemDetails()
                    )
                }
        }
    }


    // Function to delete an item
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
