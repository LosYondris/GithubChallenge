package me.jansv.challenge.api;


import org.jetbrains.annotations.Nullable;

import java.util.List;

import me.jansv.challenge.model.RepoX;
import me.jansv.challenge.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubService {
    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") @Nullable String filter);
}

