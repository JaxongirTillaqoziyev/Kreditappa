package uz.agesoft.universalkredit;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
public class RegistrActivity extends AppCompatActivity {
    boolean isremember = false;
    EditText loginedt;
    CheckBox mRemember;
    EditText paroledt;
    SharedPreferences sharedPreferences;
    Button tizimgakirishtugmasi;
    String url = "https://javadev.uz/asdfghjklopiutrewqa/login.php";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        setTitle("Universal Kredit prri");
        loginedt = (EditText) findViewById(R.id.login);
        paroledt = (EditText) findViewById(R.id.parol);
        mRemember = (CheckBox) findViewById(R.id.checkboxx);
         sharedPreferences = getSharedPreferences("SHARED_PREF", MODE_PRIVATE);
             isremember = sharedPreferences.getBoolean("CHECKBOX",false);

            if (isremember) {
            startActivity(new Intent(RegistrActivity.this, MainActivity.class));
            finish();
        }
        tizimgakirishtugmasi = (Button) findViewById(R.id.tizimgakirish);

        tizimgakirishtugmasi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (loginedt.getText().toString().equals("")) {
                    Toast.makeText(RegistrActivity.this, "Loginni kiriting!!!", Toast.LENGTH_SHORT).show();
                } else if (paroledt.getText().toString().equals("")) {
                    Toast.makeText(RegistrActivity.this, "Parol kiriting!!!", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(RegistrActivity.this);
                    progressDialog.show();
                    progressDialog.setTitle("Iltimos kuting!!!");
                    String login = loginedt.getText().toString().trim();
                    String parol = paroledt.getText().toString().trim();
                    boolean checked = mRemember.isChecked();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("LOGIN", login);
                    editor.putString("PAROL", parol);
                    editor.putBoolean("CHECKBOX", checked);
                    editor.apply();
                    final String str = login;
                    final String str2 = parol;
                    /////
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            if(response.equalsIgnoreCase("logged in successfully")){

                                loginedt.setText("");
                                paroledt.setText("");
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Toast.makeText(RegistrActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegistrActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrActivity.this, "Internetingiz yaxshi ishlamayapti!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("login",str);
                            params.put("parol",str2);
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(RegistrActivity.this);
                    requestQueue.add(request);
                    //////

                }
            }
        });
    }
}

