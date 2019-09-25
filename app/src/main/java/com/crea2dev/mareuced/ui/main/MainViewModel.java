package com.crea2dev.mareuced.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.crea2dev.mareuced.Model.MeetingModel;
import com.crea2dev.mareuced.repositories.MeetingRepository;
import com.crea2dev.mareuced.utils.SortMeetings;

import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<MeetingModel>> mMeetings = new MutableLiveData<>();
    private MeetingRepository mMeetingRepository;

//    public MeetingViewModel(){
//        mMeetingRepository = MeetingRepository.getInstance();
//        mMeetings.setValue(mMeetingRepository.getMeetings());
//    }

    public void deleteMeeting(MeetingModel meeting){
        mMeetingRepository.deleteMeeting(meeting);
        mMeetings.setValue(mMeetingRepository.getMeetings());
    }
    public void setSortingOrder(SortMeetings.SortMethods sortingOrder) {

        switch (sortingOrder){
            case DATE_ORDER:
                mMeetings.setValue(SortMeetings.dateOrder(mMeetingRepository.getMeetings()));
                break;
            case NAME_ORDER:
                mMeetings.setValue(SortMeetings.nameOrder(mMeetingRepository.getMeetings()));
                break;

        }
    }
}
