package simply.homework.inventory.presentation.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import simply.homework.inventory.domain.usecase.DeleteItemUseCase

class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for ItemDetailsScreen
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails()
)
