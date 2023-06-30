package com.example.laptop_market.presenter.activities;

import android.content.Context;

import com.example.laptop_market.contracts.IAccountContract;
import com.example.laptop_market.contracts.ILaptopContract;
import com.example.laptop_market.contracts.IPostContract;
import com.example.laptop_market.model.account.AccountModel;
import com.example.laptop_market.model.laptop.LaptopModel;
import com.example.laptop_market.model.post.PostModel;

public class PostDetailActivityPresenter implements IPostContract.Presenter.PostDetailActivityPresenter
        , ILaptopContract.Presenter.PostDetailActivityPresenter
        , IAccountContract.Presenter.PostDetailActivityPresenter {

    private ILaptopContract.View.PostDetailActivityView laptopView;
    private IPostContract.View.PostDetailActivityView postView;
    private IAccountContract.View.PostDetailActivityView accountView;
    private IPostContract.Model postModel;
    private ILaptopContract.Model laptopModel;
    private IAccountContract.Model accountModel;

    public PostDetailActivityPresenter(Context context, ILaptopContract.View.PostDetailActivityView laptopView, IPostContract.View.PostDetailActivityView postView
            , IAccountContract.View.PostDetailActivityView accountView) {
        this.accountView = accountView;
        this.laptopView = laptopView;
        this.postView = postView;
        laptopModel = new LaptopModel(context);
        postModel = new PostModel(context);
        accountModel = new AccountModel(context);
    }

    @Override
    public void OnLoadingLaptopInPostDetail(String laptopId) {
        laptopModel.LoadingLaptopInPostDetail(laptopId,((laptop, error) -> {
            if(error!=null)
                laptopView.FailedLoadingPostDetail(error);
            else
                laptopView.LoadingLapTopInPostDetail(laptop);
        }));
    }

    @Override
    public void OnLoadingPostInPostDetail(String postID) {
        postModel.LoadingPostInPostDetail(postID,(post, error) -> {
            if(error!=null)
                postView.FailedLoadingPostDetail(error);
            else
                postView.LoadingPostInPostDetail(post);
        });
    }

    @Override
    public void OnLoadingAccountInPostDetail(String accountId) {
        accountModel.LoadAccountWithId(accountId,(account, error) -> {
            if(error!=null)
                accountView.FailedLoadingPostDetail(error);
            else
                accountView.LoadingAccountInPostDetail(account);
        });
    }
}
