package mx.com.collegedays.collegedays.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import mx.com.collegedays.collegedays.Adapters.PagerAdapter;
import mx.com.collegedays.collegedays.Models.Nota;
import mx.com.collegedays.collegedays.R;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private FloatingActionButton fab;
    private String diaSeleccionado = "LUNES";
    public static ArrayList<Nota> notas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
        setFloatingButton();

        notas = new ArrayList<Nota>();
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

    public void setFloatingButton(){
        fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diaSeleccionado == "NOTAS"){
                    Intent intent = new Intent(MainActivity.this, NotasActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, RegistroClase.class );
                    intent.putExtra("esNuevo", true);
                    intent.putExtra("dia", diaSeleccionado);
                    startActivity( intent );
                }

            }
        });
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
}
