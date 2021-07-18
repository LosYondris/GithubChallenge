package me.jansv.challenge.ui.screens.repos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import import androidx.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.jansv.challenge.R;
import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.RepoX;

import me.jansv.challenge.network.NetworkManager;
import me.jansv.challenge.ui.screens.users.UsersAdapter;
import me.jansv.challenge.util.schedulers.SchedulerProvider;

public class ReposActivity extends AppCompatActivity implements RepoxContract.View{

    // injected components

    @Inject
    GithubService api_ReposX;

    @Inject
    NetworkManager connectivity; // optional component

    @Inject
    SchedulerProvider schedulers; // optional component

    List<RepoX> xList;


    @BindView(R.id.rv_user_repositorie)
    RecyclerView rv_repoX;

    @BindView(R.id.view_rep)
    View ved;

    private final CompositeDisposable subscriptions = new CompositeDisposable();
    private RepoxContract.Presentex mpresentex;
    private  boolean mIsActive;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);


        ButterKnife.bind(this);
        AndroidInjection.inject(this);


        mpresentex = new RepoxPresenter(api_ReposX);

        setupViews();
        scheduleForTitleChange();


//        String userRp = (String) getIntent().getSerializableExtra("listDB");



    }

    private void setupViews() {

        /*
        ArtistArrayAdapter adapter = new ArtistArrayAdapter(this, artists);
recyclerView = (RecyclerView) findViewById(R.id.cardList);
recyclerView.setHasFixedSize(true);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */
        rv_repoX.setHasFixedSize(true);
        rv_repoX.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActive = true;
        mpresentex.bind(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIsActive = false;
        mpresentex.unbind();
        subscriptions.clear();
    }

    @Override
    public void showNoNetworkMessage() {
        Snackbar.make(ved, R.string.network_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, v ->
                   mpresentex.fetchRepoX()
                )
                .show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Snackbar.make(ved, R.string.no_network, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showReposList(List<RepoX> xList) {
        rv_repoX.setAdapter(new ReposAdapter(xList) );
        Log.i("TAG","linea 135 RActivity" + xList);

    }



    private void fetchUsersWhenReady() {
        subscriptions.clear();
        Observable<Boolean> isConnected = connectivity
                .connectivityOn(this)
                .subscribeOn(schedulers.io())
                .share();

        // handle initial disconnection
        Disposable d1 = isConnected
                .take(1)
                .filter(connected -> !connected) // if disconnected show "no connection message"
                .observeOn(schedulers.ui())
                .subscribe(connected -> showNoNetworkMessage() , error -> Log.e("***", "", error), () -> {});

        // handle initial connection
        Disposable d2 = isConnected
                .startWith(connectivity.isNetworkAvailableValue())
                .skipWhile(connected -> !connected)
                .take(1)
                .observeOn(schedulers.ui())
                .subscribe(connected -> mpresentex.fetchRepoX(), error -> Log.e("***", "", error), () -> {});

        subscriptions.add(d1);
        subscriptions.add(d2);
    }


    private void scheduleForTitleChange() {
        final boolean delayTitleChange = getResources().getBoolean(R.bool.allowsDelayedTitleChange);

        if (delayTitleChange) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                setTitle(R.string.users_title);
            }).start();
        }
    }


    @Override
    public boolean isActive() {
        return false;
    }
}
