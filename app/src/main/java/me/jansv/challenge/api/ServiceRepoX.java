package me.jansv.challenge.api;

import java.util.List;

import me.jansv.challenge.model.RepoX;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceRepoX {

    //@GET("users/{LosYondris}/repos")
    //Call<List<RepoX> > getServiceRep();

    @GET("repos")
    Call<List<RepoX> > getServiceRep();
}
