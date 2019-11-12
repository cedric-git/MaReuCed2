//package com.crea2dev.mareuced.ui.ui.main;
//
//
//import android.os.Bundle;
//import androidx.fragment.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Spinner;
//import android.widget.Toast;
//
//import com.crea2dev.mareuced.R;
//import com.google.android.material.textfield.TextInputLayout;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * A simple {@link Fragment} subclass.
// */
//
//
//public class FilterByPlaceFragment extends Fragment {
//
////    private Spinner mRoomSpinner;
//
//    public FilterByPlaceFragment() {
//        // Required empty public constructor
//    }
//
//    // 3 - build <<<<<<<<<<<<<<<<<<<<<<
//    public static FilterByPlaceFragment newInstance() {
//        return new FilterByPlaceFragment();
//    }
//
//    @BindView(R.id.spinner_room) Spinner  mRoomSpinner;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_filter_by_place, container, false);
//        ButterKnife.bind(this, view);
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_filter_by_place, container, false);
//
//
//    }
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//
//            String mRoom = String.valueOf(mRoomSpinner.getSelectedItem());
//
//            if (!mRoom.equals("")){
//
//            } else {
//                Toast.makeText(getContext(), "Please, choose a place", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };
//
//}
