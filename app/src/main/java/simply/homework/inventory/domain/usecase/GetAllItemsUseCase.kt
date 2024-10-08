package simply.homework.inventory.domain.usecase

import kotlinx.coroutines.flow.Flow
import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface GetAllItemsUseCase {
    suspend fun invoke(): Flow<List<Item>>
}

class GetAllItemsUseCaseImpl(private val itemsRepository: ItemsRepository) : GetAllItemsUseCase {
    override suspend fun invoke(): Flow<List<Item>> = itemsRepository.getAllItemsStream()
}
