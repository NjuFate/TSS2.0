package com.example.tss.tssandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tss.file.fragement.FragmentFileShow;
import com.example.tss.message.fragment.MsgFragment;

import com.example.tss.file.helper.stub;


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
            if(messageFra == null){
                messageFra = new MsgFragment();
            }
            transaction.addToBackStack(null);
            transaction.replace(R.id.content,messageFra);

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

        }
        transaction.addToBackStack(null);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }
}
