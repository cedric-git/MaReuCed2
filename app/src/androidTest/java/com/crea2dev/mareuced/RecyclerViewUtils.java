///*
// * Copyright 2011 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package org.gradle.api.artifacts;
//
///**
// * A {@code DependencyResolutionListener} is notified as dependencies are resolved.
// */
//public interface DependencyResolutionListener {
//    /**
//     * This method is called immediately before a set of dependencies are resolved.
//     *
//     * @param dependencies The set of dependencies to be resolved.
//     */
//    void beforeResolve(ResolvableDependencies dependencies);
//
//    /**
//     * This method is called immediately after a set of dependencies are resolved.
//     *
//     * @param dependencies The set of dependencies resolved.
//     */
//    void afterResolve(ResolvableDependencies dependencies);
//}
//                                                                                                                                                                                                                                                                                                                                                                                                                                               
package com.crea2dev.mareuced;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecyclerViewUtils {


    public static class ItemCount implements ViewAssertion {
        private final int expectedCount;

        public ItemCount(int expectedCount) {
            this.expectedCount = expectedCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            assertThat(adapter.getItemCount(), is(expectedCount));
        }
    }

    public static ViewAction clickChildView(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(androidx.test.espresso.UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}
