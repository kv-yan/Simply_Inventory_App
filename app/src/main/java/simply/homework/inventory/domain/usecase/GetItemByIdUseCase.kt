package simply.homework.inventory.domain.usecase

import kotlinx.coroutines.flow.Flow
import simply.homework.inventory.data.entity.Item
import simply.homework.inventory.domain.repo.ItemsRepository

interface GetItemByIdUseCase {
    fun invoke(id: Int): Flow<Item?>
}

class GetItemByIdUseCaseImpl(private val itemsRepository: ItemsRepository) : GetItemByIdUseCase {
    override fun invoke(id: Int): Flow<Item?> = itemsRepository.getItemStream(id)
}
