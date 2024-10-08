package simply.homework.inventory.domain.usecase

import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface UpdateItemUseCase {
    suspend fun invoke(item: Item)
}

class UpdateItemUseCaseImpl(private val itemsRepository: ItemsRepository) : UpdateItemUseCase {
    override suspend fun invoke(item: Item) {
        itemsRepository.updateItem(item)
    }
}