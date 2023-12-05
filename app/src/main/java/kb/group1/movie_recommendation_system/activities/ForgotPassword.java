package kb.group1.movie_recommendation_system.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import kb.group1.movie_recommendation_system.R;

public class ForgotPassword extends AppCompatActivity {
    EditText foemail;
    Button back, reset;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        foemail=findViewById(R.id.femail);
        reset=findViewById(R.id.reset);
        back=findViewById(R.id.back);
        mAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String email = foemail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgotPassword.this, "Failed to send reset password email!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ForgotPassword.this, Login.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),Login.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}