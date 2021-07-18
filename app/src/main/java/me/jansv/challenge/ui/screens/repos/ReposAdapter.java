package me.jansv.challenge.ui.screens.repos;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import me.jansv.challenge.R;
import me.jansv.challenge.model.RepoX;
import me.jansv.challenge.model.User;

import java.util.List;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.Holder>{

    private List<RepoX> xList;

    public ReposAdapter(List<RepoX> xList) {
        this.xList = xList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_repositories_item, viewGroup, false));
       //Log.i("TAG","valorXs: "+viewGroup +"- :"+ i);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bind(xList.get(i));
        Log.i("TAG","valorX: "+xList.get(i));
    }


    @Override
    public int getItemCount() {
        if(xList != null){
            return xList.size();
        }else {
            return 0;
        }



    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_perfil_photo)
        ImageView userImage;

        @BindView(R.id.tv_username)
        TextView userName;

        @BindView(R.id.tv_user_repositorie)
        TextView userRepositories;

        public Holder(@NonNull View itemView) {
            super(itemView);
        }

        private void bind(RepoX xLi) {
            userName.setText(xLi.getName());
            userRepositories.setText(xLi.getFull_name());
            ///Glide.with(itemView.getContext()).load(rpX.getAvatarUrl()).into(userImage);
         //   Log.i("TAG","Valor: "+xList.size());
        }
    }
}
