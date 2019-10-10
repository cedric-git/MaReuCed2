package com.crea2dev.mareuced;

import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

public class ItemCount implements ViewAssertion {
    private final int expectedCount;

    public ItemCount(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {

    }
}
