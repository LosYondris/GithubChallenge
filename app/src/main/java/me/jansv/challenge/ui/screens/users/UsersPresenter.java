package me.jansv.challenge.ui.screens.users;

import android.util.Log;

import me.jansv.challenge.api.GithubService;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersPresenter implements UsersContract.Presenter {

    private UsersContract.View mView;

    private GithubService api;

    public UsersPresenter(GithubService api) {
        this.api = api;
    }

    @Override
    public void bind(UsersContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    @Override
    public void fetchUserList() {
        final String filter = "language:java location:lagos";
        api.getUserList(filter).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.i("TAG","Valor Usuario: "+response);
                if(!mView.isActive())
                    return;
                if (response.isSuccessful()) {
                    mView.showUserList(response.body().getItems());
                } else {
                    mView.showNetworkErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                if (mView.isActive()) {
                    mView.showNetworkErrorMessage();
                }
            }
        });
    }
}
