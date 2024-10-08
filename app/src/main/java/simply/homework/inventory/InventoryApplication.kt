package simply.homework.inventory

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import simply.homework.inventory.data.di.dataModule
import simply.homework.inventory.domain.di.domainModule
import simply.homework.inventory.presentation.di.presentationModule

class InventoryApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@InventoryApplication)
            modules(presentationModule, dataModule, domainModule)
        }
    }
}
