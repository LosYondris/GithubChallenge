package me.jansv.challenge.ui.screens.users;

import java.util.List;

import me.jansv.challenge.model.User;
import me.jansv.challenge.ui.screens.BasePresenter;
import me.jansv.challenge.ui.screens.BaseView;

public interface UsersContract {

    interface View extends BaseView {
        void showNoNetworkMessage();

        void showNetworkErrorMessage();

        void showUserList(List<User> users);
    }

    interface Presenter extends BasePresenter<View> {
        void fetchUserList();
    }
}
