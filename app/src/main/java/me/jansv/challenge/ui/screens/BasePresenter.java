package me.jansv.challenge.ui.screens;


public interface BasePresenter<V extends BaseView> {
    void bind(V view);
    void unbind();
}
