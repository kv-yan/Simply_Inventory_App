package simply.homework.inventory.domain.usecase

import kotlinx.coroutines.flow.Flow
import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface GetAllItemsUseCase {
    fun invoke(): Flow<List<Item>>
}

class GetAllItemsUseCaseImpl(private val itemsRepository: ItemsRepository) : GetAllItemsUseCase {
    override fun invoke(): Flow<List<Item>> = itemsRepository.getAllItemsStream()
}
