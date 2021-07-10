package me.jansv.challenge.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ApiModule {

    @Provides
    @Singleton
    public static GithubService provideApiService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(
            HttpUrl apiHost,
            Converter.Factory converter,
            CallAdapter.Factory callAdapter,
            OkHttpClient client
    ) {
        return new Retrofit.Builder()
                .baseUrl(apiHost)
                .addConverterFactory(converter)
                .addCallAdapterFactory(callAdapter)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    public static Converter.Factory provideConverter(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public static CallAdapter.Factory provideAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }
}
