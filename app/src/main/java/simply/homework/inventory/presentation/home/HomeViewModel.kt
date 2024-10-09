package simply.homework.inventory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.usecase.GetAllItemsUseCase

class HomeViewModel(getAllItemsUseCase: GetAllItemsUseCase) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = getAllItemsUseCase.invoke()
        .map { items ->
            HomeUiState(itemList = items)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class HomeUiState(val itemList: List<Item> = listOf())
