package uz.agesoft.universalkredit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class TahrirlashOyna extends AppCompatActivity {
    private int position;
    EditText editIzoh,id_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahrirlash_oyna);
        id_edit = findViewById(R.id.id);
        editIzoh = findViewById(R.id.izohh);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        id_edit.setText(MainActivity.mijozlarArrayList.get(position).getId());
        editIzoh.setText(MainActivity.mijozlarArrayList.get(position).getIzoh());
    }
    public void btn_updateData(View view) {

//        final String name = edName.getText().toString();
//        final String email = edEmail.getText().toString();
//        final String contact = edContact.getText().toString();
//        final String address = edAddress.getText().toString();
//        final String id = edId.getText().toString();
        final String izoh = editIzoh.getText().toString();
        final String id = id_edit.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Yangilanmoqda....");
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, "https://javadev.uz/asdfghjklopiutrewqa/izohqushish.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(TahrirlashOyna.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TahrirlashOyna.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("id",id);
                params.put("izoh",izoh);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(TahrirlashOyna.this);
        requestQueue.add(request);
    }
}