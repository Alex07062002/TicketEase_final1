package com.example.ticketease.MVVM

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.ticketease.MVVM.Event.Catalog.CatalogRepository
import com.example.ticketease.MVVM.Event.Catalog.CatalogRepositoryImpl
import com.example.ticketease.MVVM.Event.Preferences.PreferencesRepository
import com.example.ticketease.MVVM.Event.Preferences.PreferencesRepositoryImpl
import com.example.ticketease.MVVM.Event.SelectTime.CreateEvent
import com.example.ticketease.MVVM.Event.SelectTime.CreateEventImpl
import com.example.ticketease.MVVM.Event.SuccessfulEvent.SuccessfulEventRepository
import com.example.ticketease.MVVM.Event.SuccessfulEvent.SuccessfulEventRepositoryImpl
import com.example.ticketease.MVVM.Event.getEvents.getEventsRepository
import com.example.ticketease.MVVM.Event.getEvents.getEventsRepositoryImpl
import com.example.ticketease.MVVM.Event.getTime.getTimeRepository
import com.example.ticketease.MVVM.Event.getTime.getTimeRepositoryImpl


import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtRepository
import com.example.ticketease.MVVM.Person.Buyer.Avtorize.AvtRepositoryImpl
import com.example.ticketease.MVVM.Person.Buyer.CartPersonal.CartPersonalRepository
import com.example.ticketease.MVVM.Person.Buyer.CartPersonal.CartPersonalRepositoryImpl
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
import com.example.ticketease.MVVM.RetrofitAPI

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
    fun postDataRegisterUser() : RetrofitAPI {
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
    fun provideRegisterRepository(api : RetrofitAPI, prefs : SharedPreferences) : RegisterRepository{
        return RegisterRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideAvtRepository(api : RetrofitAPI, prefs : SharedPreferences) : AvtRepository {
        return AvtRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun providePersonalRepository(api : RetrofitAPI, prefs : SharedPreferences) : PersonalRepository {
        return PersonalRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideRegisterRepositoryOrg(api : RetrofitAPI, prefs : SharedPreferences) : RegisterRepositoryOrg {
        return RegisterRepositoryOrgImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideAvtRepositoryOrg(api : RetrofitAPI, prefs : SharedPreferences) : AvtRepositoryOrg {
        return AvtRepositoryOrgImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalRepositoryOrg(api : RetrofitAPI, prefs : SharedPreferences) : PersonalRepositoryOrg {
        return PersonalRepositoryOrgImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalUpdateBuyer(api : RetrofitAPI, prefs : SharedPreferences) : UpdateBuyerRepository {
        return UpdateBuyerRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideCatalog(api : RetrofitAPI, prefs : SharedPreferences) : CatalogRepository {
        return CatalogRepositoryImpl(api, prefs)
    }


    @Provides
    @Singleton
    fun provideCitySelector(api : RetrofitAPI, prefs : SharedPreferences) : CitySelectorRepository {
        return CitySelectorRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideCitySelectorOrg(api : RetrofitAPI, prefs : SharedPreferences) : CitySelectorOrgRepository {
        return CitySelectorOrgRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideCartPersonalBuyer(api : RetrofitAPI, prefs : SharedPreferences) : CartPersonalRepository {
        return CartPersonalRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePersonalUpdateOrg(api : RetrofitAPI, prefs : SharedPreferences) : UpdateOrgRepository {
        return UpdateOrgRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideGetPlace(api : RetrofitAPI, prefs : SharedPreferences) : getEventsRepository {
        return getEventsRepositoryImpl(api, prefs)
    }
    @Provides
    @Singleton
    fun provideGetTime(api : RetrofitAPI, prefs : SharedPreferences) : getTimeRepository {
        return getTimeRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(
        api: RetrofitAPI,
        prefs: SharedPreferences
    ): PreferencesRepository {
        return PreferencesRepositoryImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideCreateEventRepository(
        api: RetrofitAPI,
        prefs: SharedPreferences
    ): CreateEvent {
        return CreateEventImpl(api, prefs)
    }

    @Provides
    @Singleton
    fun provideSuccessfulEventRepository(
        api: RetrofitAPI,
        prefs: SharedPreferences
    ): SuccessfulEventRepository {
        return SuccessfulEventRepositoryImpl(api, prefs)
    }
}

