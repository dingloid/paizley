package grayteam.paizley_customerapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import DynamoDBManager.AmazonClientManager;
import DynamoDBManager.DynamoDBManager;

public class MainActivity extends AppCompatActivity {
    public static AmazonClientManager clientManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientManager = new AmazonClientManager(this);

        Button btn = (Button) findViewById(R.id.btn_data);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView result = (TextView) findViewById(R.id.view_result);
                String dbresult = DynamoDBManager.getTestTableStatus();
                result.setText(dbresult);
                // Code here executes on main thread after user presses button
            }
        });
    }
}
