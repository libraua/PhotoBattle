package com.libraua.photobattle;

/**
 * Created by 23060607 on 2015-05-20.
 */
public interface PlusBaseInterface {
    void onPlusClientRevokeAccess();

    void onPlusClientSignIn();

    void onPlusClientSignOut();

    void onPlusClientBlockingUI(boolean show);

    void updateConnectButtonState();
}
