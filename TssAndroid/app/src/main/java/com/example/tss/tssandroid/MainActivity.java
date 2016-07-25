package com.example.tss.tssandroid;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tss.course.fragment.FragmentDisplyCourse;
import com.example.tss.file.fragement.FragmentFileShow;
import com.example.tss.login.activity.LoginActivity;
import com.example.tss.message.activity.ConverListActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialize.util.UIUtils;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;
    private static final int INFORM_MESSAGE = 1;
    private static final int COURSE = 2;
    private static final int FILE = 3;
    private static final int SETTING = 4;
    private static final int HELP = 5;
    private static final int LOGOUT = 6;


    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private String user;
    Fragment contentFra;
    Fragment messageFra;
    Fragment fileFra;
    public Fragment courseFra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setDefaultFragment();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        fm = getFragmentManager();
        user = EMClient.getInstance().getCurrentUser();
        final IProfile profile = new ProfileDrawerItem().withName(user).withEmail("707969656@qq.com").withIcon(R.drawable.profile);
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIdentifier(PROFILE_SETTING),
                        new ProfileSettingDrawerItem().withName("Manage Account")
                )
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_message).withIcon(R.drawable.ic_menu_camera).withIdentifier(INFORM_MESSAGE),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_course).withIcon(R.drawable.ic_menu_gallery).withIdentifier(COURSE),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_file).withIcon(R.drawable.ic_menu_camera).withIdentifier(FILE),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),

                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(R.drawable.ic_menu_camera).withIdentifier(SETTING),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(R.drawable.ic_menu_camera).withIdentifier(HELP),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_logout).withIcon(R.drawable.ic_menu_camera).withIdentifier(LOGOUT)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        if (drawerItem != null) {
//                            startSupportActionMode(new ActionBarCallBack());
//                            findViewById(R.id.action_mode_bar).setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(CompactHeaderDrawerActivity.this, R.attr.colorPrimary, R.color.material_drawer_primary));
                            long identifier = drawerItem.getIdentifier();
                            if(identifier == INFORM_MESSAGE){
                                startActivity(new Intent(MainActivity.this, ConverListActivity.class));
                            }else if(identifier == COURSE){
                                if(courseFra == null){
                                    courseFra = new FragmentDisplyCourse();
                                }
                                transaction.replace(R.id.content,courseFra,"disply");
                            }else if(identifier == FILE){
//                                if(fileFra == null){
//                                    stub s = new stub();
//                                    fileFra = new FragmentFileShow(s.getMainFile());
//                                }
//                                transaction.replace(R.id.content,fileFra);
                            }else if(identifier == SETTING){
                                if(contentFra == null){
                                    contentFra = new ContentFragment();
                                }
                                transaction.replace(R.id.content,contentFra);
                            }else if(identifier == LOGOUT){
                                logout();
                            }
                        }

                        if (drawerItem instanceof Nameable && drawerItem.getIdentifier()!=1) {
                            toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                        }

                        transaction.addToBackStack(null);
                        transaction.commit();

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        // set the selection to the item with the identifier 5
        if (savedInstanceState == null) {
            result.setSelection(5, false);
        }
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    private void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        contentFra = new ContentFragment();
        transaction.replace(R.id.content,contentFra);
        transaction.commit();
    }

    void logout() {
        final ProgressDialog pd = new ProgressDialog(this);
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        EMClient.getInstance().logout(true,new EMCallBack() {

            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        // show login screen
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, "unbind devicetokens failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
