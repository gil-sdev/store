package com.example.store.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FromPrendaViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FromPrendaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("fragment register");
    }

    public LiveData<String> getText() {
        return mText;
    }
}