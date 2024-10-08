package simply.homework.inventory.domain.repo

import kotlinx.coroutines.flow.Flow
import simply.homework.inventory.data.entity.Item

interface ItemsRepository {
    fun getAllItemsStream(): Flow<List<Item>>

    fun getItemStream(id: Int): Flow<Item?>

    suspend fun insertItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateItem(item: Item)
}
