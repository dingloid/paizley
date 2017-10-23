package grayteam.paizley_customerapp.fragments;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MenuItemFragment extends MenuItemListFragment {

    public MenuItemFragment(){}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return null;
    }
}
