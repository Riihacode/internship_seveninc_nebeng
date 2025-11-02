package com.example.nebeng.core.database

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "nebeng.db"
//        ).fallbackToDestructiveMigration().build()
//    }
//
//    @Provides
//    fun provideAuthDao(database: AppDatabase): AuthDao {
//        return database.authDao()
//    }
//}
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideAuthRepository(api: AuthApi): AuthRepository =
//        AuthRepositoryImpl(api)
//}
