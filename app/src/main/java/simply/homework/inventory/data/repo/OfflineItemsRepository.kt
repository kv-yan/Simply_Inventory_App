package simply.homework.inventory.data.repo

import kotlinx.coroutines.flow.Flow
import simply.homework.inventory.domain.repo.ItemsRepository
import simply.homework.inventory.data.dao.InventoryDao
import simply.homework.inventory.data.entity.Item

class OfflineItemsRepository(private val inventoryDao: InventoryDao) : ItemsRepository {

    override fun getAllItemsStream(): Flow<List<Item>> = inventoryDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Item?> = inventoryDao.getItem(id)

    override suspend fun insertItem(item: Item) = inventoryDao.insert(item)

    override suspend fun deleteItem(item: Item) = inventoryDao.delete(item)

    override suspend fun updateItem(item: Item) = inventoryDao.update(item)
}
