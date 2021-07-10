package me.jansv.challenge.network;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import me.jansv.challenge.BuildConfig;
import me.jansv.challenge.util.Constants;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient(
            HttpLoggingInterceptor loggingInterceptor,
            @Named("networkConnectionTimeoutSeconds") int networkConnectTimeoutSeconds,
            @Named("networkReadTimeoutSeconds") int networkReadTimeoutSeconds,
            @Named("isDebug") boolean isDebug
    ) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(networkConnectTimeoutSeconds, TimeUnit.SECONDS)
                .readTimeout(networkReadTimeoutSeconds, TimeUnit.SECONDS)
                .cache(null);
        if (isDebug)
            builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Binds
    @Singleton
    public abstract NetworkManager provideNetworkManager(NetworkManagerImp imp);


    @Provides
    @Singleton
    public static HttpUrl provideApiUrl(@Named("githubBaseURL") String apiUrl) {
        return HttpUrl.parse(apiUrl);
    }

    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }


    @Provides
    @Named("githubBaseURL")
    public static String provideApiHost() {
        return BuildConfig.GITHUB_HOST;
    }


    @Provides
    @Named("networkConnectionTimeoutSeconds")
    public static int provideNetworkTimeoutInSeconds() {
        return Constants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Named("networkReadTimeoutSeconds")
    public static int provideNetworkReadTimeoutInSeconds() {
        return Constants.NETWORK_READ_TIMEOUT;
    }
}
