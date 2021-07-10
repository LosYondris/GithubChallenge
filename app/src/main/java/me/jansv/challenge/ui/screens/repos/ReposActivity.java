package me.jansv.challenge.ui.screens.repos;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
//import import androidx.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import me.jansv.challenge.R;
import me.jansv.challenge.api.ServiceRepoX;
import me.jansv.challenge.model.RepoX;

import me.jansv.challenge.ui.screens.users.UsersContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposActivity extends AppCompatActivity {


    List<RepoX> xList;


    @BindView(R.id.list)
    ListView list;


    ArrayAdapter arrayAdapter;
    ReposAdapter madapter;
    ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        ButterKnife.bind(this);
        AndroidInjection.inject(this);



        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        list.setAdapter(arrayAdapter);


        String userRp = (String) getIntent().getSerializableExtra("listDB");
        getServiceRep(userRp);


    }

    public void getServiceRep(String us) {
        String url_base = "https://api.github.com/users/"+us+"/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServiceRepoX serviceRepoX = retrofit.create(ServiceRepoX.class);
        Call<List<RepoX> > call = serviceRepoX.getServiceRep();
        call.enqueue(new Callback<List<RepoX>>() {
            @Override
            public void onResponse(Call<List<RepoX>> call, Response<List<RepoX>> response) {
                for (RepoX rp : response.body()){
                    titles.add(rp.getName());

                }

                arrayAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<List<RepoX>> call, Throwable t) {

            }
        });



    }




}
