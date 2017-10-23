package grayteam.paizley_customerapp;

import android.os.Bundle;

public class MenuItemDetailActivity extends BaseActivity {
    public static final String EXTRA_POST_KEY = "post_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_detail);
    }
}
