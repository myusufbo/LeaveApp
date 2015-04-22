package com.example.iit.dhakathon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


public class Approve extends ActionBarActivity {
    private Drawer.Result result;
    private AccountHeader.Result headerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve);

        initDrawer(savedInstanceState);
    }
    private void initDrawer(Bundle savedInstanceState){

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            headerResult = new AccountHeader()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.header)
                    .addProfiles(
                            new ProfileDrawerItem().withName("Test").withEmail("test@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                    )
                    .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                        @Override
                        public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                            return false;
                        }
                    })
                    .build();

            // Handle Toolbar
            result = new Drawer()
                    .withActivity(this)
                    .withHeader(R.layout.header)
                    .withAccountHeader(headerResult)
                    .withToolbar(toolbar)
                    .addDrawerItems(
                            new PrimaryDrawerItem().withName(R.string.drawer_item_my_leave).withIcon(FontAwesome.Icon.faw_calendar).withIdentifier(0),
                            new PrimaryDrawerItem().withName(R.string.drawer_item_leave_apply).withIcon(FontAwesome.Icon.faw_calendar).withIdentifier(1),
                            new PrimaryDrawerItem().withName(R.string.drawer_item_approve).withIcon(FontAwesome.Icon.faw_angellist).withIdentifier(2),
                            new PrimaryDrawerItem().withName(R.string.drawer_item_relieve).withIcon(FontAwesome.Icon.faw_ambulance).withIdentifier(3)
                    )
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                            if (drawerItem instanceof Nameable) {
                                getSupportActionBar().setTitle(((Nameable) drawerItem).getNameRes());
                                Fragment fragment = null;
                                if (drawerItem.getIdentifier() == 0) {
                                    //Toast.makeText(getBaseContext(), "On Drawer Created", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Approve.this,MainActivity.class));
                                } else if (drawerItem.getIdentifier() == 1) {
                                    //Toast.makeText(getBaseContext(),"On Drawer Created" , Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Approve.this, Apply.class));
                                }/* else if (drawerItem.getIdentifier() == 2) {
                                    Toast.makeText(getBaseContext(),"On Drawer Created" , Toast.LENGTH_LONG).show();
                                    //startActivity(new Intent(MainActivity.this, Map.class));
                                } */ else if (drawerItem.getIdentifier() == 3) {
                                    //Toast.makeText(getBaseContext(), "On Drawer Created", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Approve.this, Relieve.class));
                                }
                                if (fragment != null) {
                                    FragmentManager fragmentManager = getSupportFragmentManager();
                                    fragmentManager.beginTransaction()
                                            .replace(R.id.fragment_container, fragment)
                                            .commit();
                                }
                            }
                        }
                    })
                    .withFireOnInitialOnClick(true)

                    .withSelectedItem(2)
                    .build();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(false);
            result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_approve, menu);
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
}
