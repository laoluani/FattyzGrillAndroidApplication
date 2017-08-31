package com.louanimashaun.fattyzgrill.data.source.local;

import com.louanimashaun.fattyzgrill.data.DataSource;
import com.louanimashaun.fattyzgrill.model.Meal;
import com.louanimashaun.fattyzgrill.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by louanimashaun on 19/06/2017.
 */

public class MealsLocalDataSoure implements DataSource<Meal>{

    private static MealsLocalDataSoure INSTANCE = null;
    private static Realm realm;


    public static MealsLocalDataSoure getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MealsLocalDataSoure();
        }
        return INSTANCE;
    }

    private MealsLocalDataSoure(){
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void loadData(LoadCallback<Meal> callback) {
        RealmResults<Meal> result = realm.where(Meal.class).findAllAsync();

        if(result.size() == 0){
            callback.onDataNotAvailable();
        }else{
            callback.onDataLoaded(realm.copyFromRealm(result));
        }
    }

    @Override
    public void getData(String id, GetCallback callback) {
        RealmResults<Meal> result = realm.where(Meal.class).equalTo("id", id).findAll();

        if(result.size() == 0){
            callback.onDataNotAvailable();
        }else{
            callback.onDataLoaded(result.first());
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void saveData(final Meal data, final CompletionCallback callback) {
        realm.executeTransactionAsync(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                if(callback != null) callback.onComplete();

            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                if(callback != null) callback.onCancel();
            }
        });
    }

    @Override
    public void saveData(final List<Meal> data, final CompletionCallback callback) {
        realm.executeTransactionAsync(new Realm.Transaction(){

            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
            }
        }, new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                if(callback != null) callback.onComplete();

            }
        }, new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                if(callback != null) callback.onCancel();
            }
        });
    }
}
