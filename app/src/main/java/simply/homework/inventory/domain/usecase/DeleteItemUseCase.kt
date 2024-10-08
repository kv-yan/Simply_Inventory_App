package simply.homework.inventory.domain.usecase

import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface DeleteItemUseCase {
    suspend fun invoke(item: Item)
}

class DeleteItemUseCaseImpl(private val itemsRepository: ItemsRepository) : DeleteItemUseCase {
    override suspend fun invoke(item: Item) {
        itemsRepository.deleteItem(item)
    }
}