package me.jansv.challenge.api;


import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;
import me.jansv.challenge.model.ListRepoX;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") @Nullable String filter);

    @GET("/users/{usu}/repos")
    Call<ListRepoX> getxList(@Path("usu") @Nullable String usu);

///https://api.github.com/users/"+us+"/" "/users/{usu}/repos"
}

