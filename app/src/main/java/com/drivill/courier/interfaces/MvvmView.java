package com.drivill.courier.interfaces;

public interface MvvmView {
    void showLoading();

    void hideLoading();

    void openActivityOnTokenExpire();

    void openFullImagectivity(String url, String name);

    void onError(String message);

    void showMessage(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

    void callBackPressed();

    void logout();
    void logoutMerchant();
    void login();
}