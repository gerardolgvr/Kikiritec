package mx.com.collegedays.collegedays.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Adapters.PagerAdapter;
import mx.com.collegedays.collegedays.Fragments.NotasFragment;
import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;

public class MainActivity extends AppCompatActivity {

    private Realm realm;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private FloatingActionButton fab;
    private String diaSeleccionado = "LUNES";
    private RealmResults<Nota> notas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        notas = realm.where(Nota.class).findAll();

        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
    }


    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void setTabLayout(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("LUNES"));
        tabLayout.addTab(tabLayout.newTab().setText("MARTES"));
        tabLayout.addTab(tabLayout.newTab().setText("MIERCOLES"));
        tabLayout.addTab(tabLayout.newTab().setText("JUEVES"));
        tabLayout.addTab(tabLayout.newTab().setText("VIERNES"));
        tabLayout.addTab(tabLayout.newTab().setText("SABADO"));
        tabLayout.addTab(tabLayout.newTab().setText("DOMINGO"));
        tabLayout.addTab(tabLayout.newTab().setText("NOTAS"));
    }

    public void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTabLayout(final ViewPager viewPager){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                diaSeleccionado = tab.getText().toString();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.deletaAllNotas:
                realm.beginTransaction();
                realm.deleteAll();
                NotasFragment.updateFragmentNotas();
                realm.commitTransaction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
