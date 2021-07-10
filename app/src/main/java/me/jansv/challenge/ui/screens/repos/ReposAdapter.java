package me.jansv.challenge.ui.screens.repos;

import androidx.annotation.NonNull;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import me.jansv.challenge.R;
import me.jansv.challenge.model.RepoX;
import me.jansv.challenge.model.User;
import me.jansv.challenge.ui.screens.users.UsersContract;

import java.security.acl.LastOwnerException;
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>{

    private List<RepoX> mlist;
    //private  LayoutInflater minflater;
    private Context context;

    public ReposAdapter(List<RepoX> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_repositories_item, parent, false));
        //        View v = minflater.inflate(R.layout.user_repositories_item,null);
//        return new ReposAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder holder, int position) {
        ///repostHolder.bind(xList.get(i));
        holder.bind(mlist.get(position));
    }

    @Override
    public int getItemCount() { return mlist.size(); }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView useName, useRepos;

        ViewHolder(View i){
            super(i);
            img = i.findViewById(R.id.img_perfil_photo);
            useName = i.findViewById(R.id.tv_username);
            useRepos = i.findViewById(R.id.tv_user_repositorie);

            //userName.setText(i.getName());
            //userName.setText(xList.getLogin());
            //userRepositories.setText(xList.getHtmlUrl());
            //Glide.with(itemView.getContext()).load(xList.getAvatarUrl()).into(userImage);
        }

        void bind(final RepoX r){
            useName.setText(r.getName());
            useRepos.setText(r.getFull_name());
        }

    }

}



/*
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepostHolder>{
    private List<RepoX> xList;

    public ReposAdapter(List<RepoX> xList) {
        this.xList = xList;
    }

    @NonNull
    @Override
    public RepostHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RepostHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_repositories_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepostHolder repostHolder, int i) {
        repostHolder.bind(xList.get(i));
        Log.i("TAG","pturba linea 41 report:" +xList.get(i));

    }

    @Override
    public int getItemCount() {
        return xList.size();
    }

    public class RepostHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_perfil_photo)
        ImageView userImage;

        @BindView(R.id.tv_username)
        TextView userName;

        @BindView(R.id.tv_user_repositorie)
        TextView userRepositories;

        public RepostHolder(@NonNull View itemView) {
            super(itemView);
        }


        private void bind(RepoX xList) {
            userName.setText(xList.getName());
            //userName.setText(xList.getLogin());
            //userRepositories.setText(xList.getHtmlUrl());
            //Glide.with(itemView.getContext()).load(xList.getAvatarUrl()).into(userImage);


        }
    }
}
*/