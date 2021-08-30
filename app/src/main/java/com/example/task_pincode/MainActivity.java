package com.example.task_pincode;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView demo =  (TextView) findViewById(R.id.demo);
        demo.setText("Enter PIN (Ex: 452001)");
        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("");

        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        //final String[] url = {"https://api.zippopotam.us/in/"};

        EditText pin = (EditText) findViewById(R.id.pin);

        pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {



            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                String url = "https://api.zippopotam.us/in/" + s;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {



                                try {
                                    JSONArray jsonArray = response.getJSONArray("places");

                                    textView.setText("");

                                    for (int i=0; i<jsonArray.length();i++){

                                        JSONObject obj = jsonArray.getJSONObject(i);
//                                        Log.d("Shubham", "onResponse: Title is: " +
//                                                obj.getString("place name") + " and Id is " + obj.getString("latitude"));

                                        textView.append("Place Name: " + obj.getString("place name") + "\n" + "State: " + obj.getString("state") + "\n" + "State Abbreviation: " + obj.getString("state abbreviation") + "\n" + "Longitude: " + obj.getString("longitude") + "\n" + "Latitude: " + obj.getString("latitude") + "\n" + "Country: " + response.getString("country") +
                                                "\n\n"  );
                                    }



                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }




                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", "ERROR");
                              //  textView.setText("Data Not Found");
                              //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                queue.add(jsonObjectRequest);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}