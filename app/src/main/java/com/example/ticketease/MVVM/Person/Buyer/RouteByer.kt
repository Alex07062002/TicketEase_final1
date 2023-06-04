package com.example.ticketease.MVVM.Person.Buyer

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.ticketease.MVVM.Event.Catalog.CatalogRepository
import com.example.ticketease.MVVM.Event.Catalog.CatalogRepositoryImpl
import com.example.ticketease.MVVM.Event.Preferences.PreferencesRepository
import com.example.ticketease.MVVM.Event.Preferences.PreferencesRepositoryImpl
import com.example.ticketease.MVVM.Event.getEvents.getEventsRepository
import com.example.ticketease.MVVM.Event.getEvents.getEventsRepositoryImpl
import com.example.ticketease.MVVM.Event.getTime.getTimeRepository
import com.example.ticketease.MVVM.Event.getTime.getTimeRepositoryImpl


import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtRepository
import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtRepositoryImpl
import com.example.ticketease.MVVM.Person.Buyer.CitySelector.CitySelectorRepository
import com.example.ticketease.MVVM.Person.Buyer.CitySelector.CitySelectorRepositoryImpl
import com.example.ticketease.MVVM.Person.Buyer.Personal.PersonalRepository
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterRepository
import com.example.ticketease.MVVM.Person.Buyer.Register.RegisterRepositoryImpl
import com.example.ticketease.MVVM.Person.Buyer.Personal.PersonalRepositoryImpl
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerRepository
import com.example.ticketease.MVVM.Person.Buyer.UpdateBuyer.UpdateBuyerRepositoryImpl

import com.example.ticketease.MVVM.Person.Organizer.Avtorize.AvtRepositoryOrg
import com.example.ticketease.MVVM.Person.Organizer.Avtorize.AvtRepositoryOrgImpl
import com.example.ticketease.MVVM.Person.Organizer.CitySelectorOrg.CitySelectorOrgRepository
import com.example.ticketease.MVVM.Person.Organizer.CitySelectorOrg.CitySelectorOrgRepositoryImpl
import com.example.ticketease.MVVM.Person.Organizer.Personal.PersonalRepositoryOrg
import com.example.ticketease.MVVM.Person.Organizer.Personal.PersonalRepositoryOrgImpl
import com.example.ticketease.MVVM.Person.Organizer.Register.RegisterRepositoryOrg
import com.example.ticketease.MVVM.Person.Organizer.Register.RegisterRepositoryOrgImpl
import com.example.ticketease.MVVM.Person.Organizer.UpdateOrganizer.UpdateOrgRepository
import com.example.ticketease.MVVM.Person.Organizer.UpdateOrganizer.UpdateOrgRepositoryImpl

import com.example.ticketease.MVVM.url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.*
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun postDataRegisterUser() : BuyerRetrofitAPI {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create()
    }

@Provides
@Singleton
fun provideSharedPref(app : Application) : SharedPreferences{
    return app.getSharedPreferences("prefs",MODE_PRIVATE)
}

    @Provides
    @Singleton
    fun provideRegisterRepository(api : BuyerRetrofitAPI,prefs : SharedPreferences) : RegisterRepository{
        return RegisterRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideAvtRepository(api : BuyerRetrofitAPI,prefs : SharedPreferences) : AvtRepository {
        return AvtRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun providePersonalRepository(api : BuyerRetrofitAPI,prefs : SharedPreferences) : PersonalRepository {
        return PersonalRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideRegisterRepositoryOrg(api : BuyerRetrofitAPI,prefs : SharedPreferences) : RegisterRepositoryOrg {
        return RegisterRepositoryOrgImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideAvtRepositoryOrg(api : BuyerRetrofitAPI,prefs : SharedPreferences) : AvtRepositoryOrg {
        return AvtRepositoryOrgImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalRepositoryOrg(api : BuyerRetrofitAPI,prefs : SharedPreferences) : PersonalRepositoryOrg {
        return PersonalRepositoryOrgImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalUpdateBuyer(api : BuyerRetrofitAPI,prefs : SharedPreferences) : UpdateBuyerRepository {
        return UpdateBuyerRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideCatalog(api : BuyerRetrofitAPI,prefs : SharedPreferences) : CatalogRepository {
        return CatalogRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideCitySelector(api : BuyerRetrofitAPI,prefs : SharedPreferences) : CitySelectorRepository {
        return CitySelectorRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideCitySelectorOrg(api : BuyerRetrofitAPI,prefs : SharedPreferences) : CitySelectorOrgRepository {
        return CitySelectorOrgRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalUpdateOrg(api : BuyerRetrofitAPI,prefs : SharedPreferences) : UpdateOrgRepository {
        return UpdateOrgRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideGetPlace(api : BuyerRetrofitAPI,prefs : SharedPreferences) : getEventsRepository {
        return getEventsRepositoryImpl(api, prefs)
    }
    @Provides
    @Singleton
    fun provideGetTime(api : BuyerRetrofitAPI,prefs : SharedPreferences) : getTimeRepository {
        return getTimeRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(
        api: BuyerRetrofitAPI,
        prefs: SharedPreferences
    ): PreferencesRepository {
        return PreferencesRepositoryImpl(api, prefs)
    }
}

