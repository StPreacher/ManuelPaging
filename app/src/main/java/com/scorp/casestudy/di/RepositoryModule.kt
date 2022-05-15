package com.scorp.casestudy.di

import com.scorp.casestudy.repo.PersonRepository
import com.scorp.casestudy.source.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * @author Mucahid Dogan
 * @since 15.05.22
 *
 * mucahidd3@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePersonRepository(source: DataSource) : PersonRepository = PersonRepository(source)

}