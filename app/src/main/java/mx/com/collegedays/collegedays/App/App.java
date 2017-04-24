package mx.com.collegedays.collegedays.App;

import android.app.Application;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;
import mx.com.collegedays.collegedays.Models.Clase;
import mx.com.collegedays.collegedays.Models.Nota;

/**
 * Created by gerardo on 22/04/17.
 */

public class App extends Application {
    public static AtomicInteger NotaID = new AtomicInteger();
    public static AtomicInteger ClaseID = new AtomicInteger();

    @Override
    public void onCreate(){
        super.onCreate();
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        NotaID = getIdByTable(realm, Nota.class);
        ClaseID = getIdByTable(realm, Clase.class);
        realm.close();
    }

    private void setUpRealmConfig(){
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0)? new AtomicInteger(results.max("id").intValue()): new AtomicInteger();
    }
}
