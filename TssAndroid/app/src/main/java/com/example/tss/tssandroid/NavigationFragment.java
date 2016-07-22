package com.example.tss.tssandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tss.course.fragment.FragmentDisplyCourse;
import com.example.tss.file.fragement.FragmentFileShow;
import com.example.tss.login.activity.LoginActivity;
import com.example.tss.message.activity.ConverListActivity;

import com.example.tss.file.helper.stub;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.ui.EaseConversationListFragment;


/**
 * Created by dyp on 2016/7/8.
 */
public class NavigationFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    Fragment contentFra;
    Fragment messageFra;
    public Fragment courseFra;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_main, container, false);


        NavigationView navi = (NavigationView) view.findViewById(R.id.nav_view);
        navi.setNavigationItemSelectedListener(this);


        setDefaultFragment();
        return view;
    }


    private void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        contentFra = new ContentFragment();
        transaction.replace(R.id.content,contentFra);
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_message) {
            // Handle the camera action
//            if(messageFra == null){
//                messageFra = new MsgFragment();
//            }
//            transaction.addToBackStack(null);
//            transaction.replace(R.id.content,messageFra);




            startActivity(new Intent(getActivity(), ConverListActivity.class));

        } else if (id == R.id.nav_courses) {
            if(courseFra == null){
                courseFra = new FragmentDisplyCourse();
            }
            transaction.addToBackStack(null);
            transaction.replace(R.id.content,courseFra,"disply");

        } else if (id == R.id.nav_files) {
            stub s = new stub();
            FragmentFileShow fileShow = new FragmentFileShow(s.getMainFile());
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.content,fileShow);
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            logout();
        }
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }


    void logout() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        EMClient.getInstance().logout(true,new EMCallBack() {

            @Override
            public void onSuccess() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        // show login screen
                        ((MainActivity) getActivity()).finish();
                        startActivity(new Intent(getActivity(), LoginActivity.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                        Toast.makeText(getActivity(), "unbind devicetokens failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
