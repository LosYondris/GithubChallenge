package me.jansv.challenge.network;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface NetworkManager {
    boolean isNetworkAvailableValue();

    Single<Boolean> isNetworkAvailable();
    // this method may use broadcast receiver to monitor network changes
    // check the implementation for more detail
    Observable<Boolean> connectivityOn(Context context);
}