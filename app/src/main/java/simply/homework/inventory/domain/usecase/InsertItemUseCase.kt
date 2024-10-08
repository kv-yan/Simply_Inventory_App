package simply.homework.inventory.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface InsertItemUseCase {
    suspend fun invoke(item: Item)
}

class InsertItemUseCaseImpl(private val itemsRepository: ItemsRepository) : InsertItemUseCase {
    override suspend fun invoke(item: Item) = withContext(Dispatchers.IO) {
        itemsRepository.insertItem(item)
    }
}