package me.jansv.challenge.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import me.jansv.challenge.util.Utils;

@Singleton
final class NetworkManagerImp implements NetworkManager {

    private Subject<Boolean> mNetworkChanges;

    private final BroadcastReceiver mNetworkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mNetworkChanges.onNext(isNetworkAvailableValue());
        }
    };

    Context mContext;

    @Inject
    public NetworkManagerImp(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean isNetworkAvailableValue() {
        return Utils.isNetworkAvailable(mContext);
    }

    @Override
    public Single<Boolean> isNetworkAvailable() {
        return Single.just(isNetworkAvailableValue());
    }

    @Override
    public Observable<Boolean> connectivityOn(Context context) {
        if (null == mNetworkChanges)
            mNetworkChanges = PublishSubject.create();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(mNetworkChangeReceiver, intentFilter);

        return Observable.create(observer -> {
            Disposable d = mNetworkChanges.subscribe(observer::onNext, throwable -> {
            }, () -> {
            });
            observer.setDisposable(Disposables.fromAction(() -> {
                stopConnectivityOn(context);
                d.dispose();
            }));
        });
    }

    private void stopConnectivityOn(Context context) {
        try {
            context.unregisterReceiver(mNetworkChangeReceiver);
        } catch (Exception ex) {
            // ignore
        }
    }
}