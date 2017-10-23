package grayteam.paizley_customerapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import grayteam.paizley_customerapp.models.User;

public class SignupActivity extends BaseActivity {

    private TextView login;
    private EditText emailField;
    private EditText passwordField;
    private EditText name;
    private Button signUpButton;

    private FirebaseAuth auth;
    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login = (TextView)findViewById(R.id.link_login);
        emailField = (EditText) findViewById(R.id.input_email);
        passwordField = (EditText) findViewById(R.id.input_password);
        signUpButton = (Button) findViewById(R.id.btn_signup);
        name = (EditText) findViewById(R.id.input_name);
        signUpButton = (Button) findViewById(R.id.btn_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivityForResult(intent,0);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }

    //Private Method
    private void signUp(){
        if(!validateForm(emailField, passwordField)){
            return;
        }

        showProgressDialog();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser(), name.getText().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user, String username) {
        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());
        //Push user name to next activity
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("username", username);
        // Go to MainActivity
        startActivity(intent);
        finish();
    }

}
