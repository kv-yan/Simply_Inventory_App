package simply.homework.inventory.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import simply.homework.inventory.data.dao.InventoryDao
import simply.homework.inventory.data.entity.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase = Instance ?: synchronized(this) {
            Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                .fallbackToDestructiveMigration().build().also {
                    Instance = it
                }
        }
    }
}

