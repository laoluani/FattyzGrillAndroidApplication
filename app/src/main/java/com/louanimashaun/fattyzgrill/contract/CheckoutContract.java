package com.louanimashaun.fattyzgrill.contract;

import com.louanimashaun.fattyzgrill.model.Meal;
import com.louanimashaun.fattyzgrill.model.Order;

import java.util.List;

/**
 * Created by louanimashaun on 27/08/2017.
 */

public interface CheckoutContract {

    interface View extends BaseView{
        void showCheckout(List<Meal> meals);

        void showNoCheckout();

        void notifyOrderSent();

        void notifyOrderError();

    }

    interface Presenter extends BasePresenter{
        void loadCheckout();

        void checkoutOrder();

        void addSelectedMeals(List<String> selectedMeals);

    }

}
