package com.louanimashaun.fattyzgrill.data.source.remote;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.louanimashaun.fattyzgrill.model.Meal;
import com.louanimashaun.fattyzgrill.data.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louanimashaun on 18/06/2017.
 */

public class MealsRemoteDataSource implements DataSource<Meal> {

    private static MealsRemoteDataSource INSTANCE = null;
    private final FirebaseDatabase mFirebaseDatabase ;
    private DatabaseReference mMealsReference;
    private static final String MEALS_PATH= "meals";

    public static MealsRemoteDataSource getInstance(){
        if (INSTANCE == null){
            INSTANCE = new MealsRemoteDataSource();
        }
        return INSTANCE;
    }

    private MealsRemoteDataSource(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mMealsReference = mFirebaseDatabase.getReference(MEALS_PATH);
    }

    @Override
    public void loadData(final LoadCallback<Meal> callBack) {
       final List<Meal> meals = new ArrayList<>();

        mMealsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String key = snapshot.getKey();
                    Meal meal = snapshot.getValue(Meal.class);
                    meal.setLuid(key);
                    meals.add(meal);
                }
                callBack.onDataLoaded(meals);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callBack.onDataNotAvailable();
            }
        });

        return;
    }

    @Override
    public void getData(GetCallback getCallback) {

    }

    @Override
    public void deleteAll() {
        //not in use
    }

    @Override
    public void saveData(Meal data,CompletionCallback callback) {
        mMealsReference.push().setValue(data);
    }

    @Override
    public void saveData(List<Meal> data, CompletionCallback callback) {
        //not in use§
    }


}