package com.thedomain.koindemonstration.koin

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

    //base declaration
    //single<UserRepoImpl> {UserRepoImpl()}

    //compacted version
    //Implementated version should go first, then original Interface
    singleOf(::UserRepoImpl) {bind<UserRepo>()}
}