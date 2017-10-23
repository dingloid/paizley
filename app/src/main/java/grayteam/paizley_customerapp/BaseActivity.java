package grayteam.paizley_customerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import grayteam.paizley_customerapp.models.User;

public class BaseActivity extends AppCompatActivity{
    private ProgressDialog mProgressDialog;

    public FirebaseAuth auth;
    public DatabaseReference database;


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    //Firebase Sign in/ Log on
    public boolean validateForm(EditText email, EditText password){
        boolean result = true;
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Required");
            result = false;
        } else{
            email.setError(null);
        }

        if(TextUtils.isEmpty(password.getText().toString())){
            password.setError("Required");
            result = false;
        } else{
            password.setError(null);
        }

        return result;
    }

    public void onAuthSuccess(FirebaseUser user) {
        String username = user.getDisplayName();
        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());
        //Push user name to next activity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("username", username);
        // Go to MainActivity
        startActivity(intent);
        finish();
    }


    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        database.child("users").child(userId).setValue(user);
    }


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
