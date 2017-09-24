package com.louanimashaun.fattyzgrill.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.louanimashaun.fattyzgrill.R;
import com.louanimashaun.fattyzgrill.contract.BasePresenter;
import com.louanimashaun.fattyzgrill.contract.NotificationContract;
import com.louanimashaun.fattyzgrill.model.Notification;

import java.util.List;

import static com.louanimashaun.fattyzgrill.util.PreconditonUtil.checkNotNull;

/**
 * Created by louanimashaun on 10/09/2017.
 */

public class NotificationFragment extends Fragment implements NotificationContract.View{

    NotificationAdapter mNotificationAdapter;
    BasePresenter mNotificationPresenter;

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        //        Bundle args = new Bundle();
        //        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notification_frag,container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.notifications_rv);

        LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mNotificationAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mNotificationPresenter.start();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mNotificationPresenter = checkNotNull(presenter);
    }

    @Override
    public void showNotifications(List<Notification> notifications) {
        checkNotNull(notifications);
        mNotificationAdapter.replaceData(notifications);
    }

    @Override
    public void showNewNotification(Notification notification) {
        checkNotNull(notification);
        mNotificationAdapter.addData(notification);
    }

    @Override
    public void showNoNotifcations() {

    }
}