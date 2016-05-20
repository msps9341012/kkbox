package com.example.imf_mbk1.kk;

import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.gms.maps.model.LatLng;
import android.app.ProgressDialog;



public class MainActivity extends AppCompatActivity {

    public List<String> name = new ArrayList<>();
    public List<LatLng> loc = new ArrayList<>();
    public List<String> addr = new ArrayList<>();
    public List<String> coti = new ArrayList<>();
    public List<String> cname = new ArrayList<>();
    public List<String> tcma = new ArrayList<>();
    public List<String> tctl = new ArrayList<>();
    public List<String> tcma3 = new ArrayList<>();
    public List<String> tctl3 = new ArrayList<>();
    public List<String> npurp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTaskParseJson().execute();

    }




    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        final String TAG = "AsyncTaskParseJson.java";

        // set your json string url here
        String yourJsonStringUrl ="http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=201d8ae8-dffc-4d17-ae1f-e58d8a95b162";
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                // instantiate our json parser
                JSONParser jParser = new JSONParser();

                // get json string from url
                JSONObject json = jParser. getJSONFromUrl(yourJsonStringUrl);

                JSONObject jsonOb = json.getJSONObject("result");

                dataJsonArr = jsonOb.getJSONArray("results");



                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);
                    // String[] name=new String[dataJsonArr.length()];



                    // Storing each json item in variable

                    name.add(c.getString("APP_NAME"));

                    Double x=(Double.parseDouble(c.getString("X")));
                    Double y=(Double.parseDouble(c.getString("Y")));
                    TWD97 parameter = new TWD97();
                    double latlon[] = TMToLatLon.convert(parameter,x, y);
                    //LatLng a= new LatLng(latlon[0],latlon[1]);
                    loc.add(new LatLng(latlon[0],latlon[1]));
                    addr.add(c.getString("ADDR"));
                    coti.add(c.getString("CO_TI"));
                    cname.add(c.getString("C_NAME"));
                    tcma.add(c.getString("TC_MA"));
                    tctl.add(c.getString("TC_TL"));
                    tcma3.add(c.getString("TC_MA3"));
                    tctl3.add(c.getString("TC_TL3"));
                    npurp.add(c.getString("NPURP"));
                    //System.out.println(name.get(i));

                    // show the values in our logcat


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {

            pDialog.dismiss();
            Button button01 = (Button)findViewById(R.id.Button01);
            button01.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("name", (Serializable)name);
                    ArrayList list = new ArrayList();
                    list.add(name);
                    list.add(loc);
                    list.add(addr);
                    list.add(coti);
                    list.add(cname);
                    list.add(tcma);
                    list.add(tctl);
                    list.add(tcma3);
                    list.add(tctl3);
                    list.add(npurp);
                    bundle.putParcelableArrayList("list", list);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, MapsActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });
            Button button02 = (Button)findViewById(R.id.Button02);
            button02.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("name", (Serializable)name);
                    ArrayList list = new ArrayList();
                    list.add(name);
                    list.add(loc);
                    list.add(addr);
                    list.add(coti);
                    list.add(cname);
                    list.add(tcma);
                    list.add(tctl);
                    list.add(tcma3);
                    list.add(tctl3);
                    list.add(npurp);
                    bundle.putParcelableArrayList("list",list);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
            });

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
