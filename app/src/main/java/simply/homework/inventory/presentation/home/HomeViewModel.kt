package simply.homework.inventory.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import   simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.usecase.GetAllItemsUseCase

class HomeViewModel(getAllItemsUseCase: GetAllItemsUseCase) : ViewModel() {
    init {
        viewModelScope.launch {
            getAllItemsUseCase.invoke().collect {
                _homeUiState.value = HomeUiState(it)
            }
        }
    }
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}


data class HomeUiState(val itemList: List<Item> = listOf())
