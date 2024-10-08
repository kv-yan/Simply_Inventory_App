package simply.homework.inventory.data.di

import org.koin.dsl.module
import simply.homework.inventory.domain.repo.ItemsRepository
import simply.homework.inventory.data.repo.OfflineItemsRepository
import simply.homework.inventory.data.dao.InventoryDao
import simply.homework.inventory.data.database.InventoryDatabase

val dataModule = module {
    single<ItemsRepository> { OfflineItemsRepository(inventoryDao = get()) }
    single<InventoryDao> { InventoryDatabase.getDatabase(context = get()).inventoryDao() }
}