package simply.homework.inventory.domain.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import simply.homework.inventory.domain.usecase.DeleteItemUseCase
import simply.homework.inventory.domain.usecase.DeleteItemUseCaseImpl
import simply.homework.inventory.domain.usecase.GetAllItemsUseCase
import simply.homework.inventory.domain.usecase.GetAllItemsUseCaseImpl
import simply.homework.inventory.domain.usecase.GetItemByIdUseCase
import simply.homework.inventory.domain.usecase.GetItemByIdUseCaseImpl
import simply.homework.inventory.domain.usecase.InsertItemUseCase
import simply.homework.inventory.domain.usecase.InsertItemUseCaseImpl
import simply.homework.inventory.domain.usecase.UpdateItemUseCase
import simply.homework.inventory.domain.usecase.UpdateItemUseCaseImpl


val domainModule = module {
    factoryOf(::GetAllItemsUseCaseImpl) { bind<GetAllItemsUseCase>() }
    factoryOf(::InsertItemUseCaseImpl) { bind<InsertItemUseCase>() }
    factoryOf(::DeleteItemUseCaseImpl) { bind<DeleteItemUseCase>() }
    factoryOf(::GetItemByIdUseCaseImpl) { bind<GetItemByIdUseCase>() }
    factoryOf(::UpdateItemUseCaseImpl) { bind<UpdateItemUseCase>() }


//    factory<InsertItemUseCase> { InsertItemUseCaseImpl(get()) }  }

}
