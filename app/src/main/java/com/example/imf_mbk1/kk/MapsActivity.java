package com.example.imf_mbk1.kk;

import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;



public class MapsActivity extends AppCompatActivity implements OnMarkerClickListener { //需引用OnMarkerClickListener
    private GoogleMap map;
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
        setContentView(R.layout.activity_maps);
        map = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MapsActivity.this, MainActivity.class);
                startActivity(intent);
                MapsActivity.this.finish();
            }
        });
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            System.out.print("定位失敗");

        }

        Bundle bundle = getIntent().getExtras();
        ArrayList list = bundle.getParcelableArrayList("list");
        name= (List<String>)list.get(0);
        loc= (List<LatLng>)list.get(1);
        addr= (List<String>)list.get(2);
        coti= (List<String>)list.get(3);
        cname= (List<String>)list.get(4);
        tcma= (List<String>)list.get(5);
        tctl= (List<String>)list.get(6);
        tcma3= (List<String>)list.get(7);
        tctl3= (List<String>)list.get(8);
        npurp= (List<String>)list.get(9);


        if (map != null) {
            map.setOnMarkerClickListener(this); //點取Marker動作
            // google mark
            for (int i=0;i<name.size();i++)
            {
                map.addMarker(new MarkerOptions().position(loc.get(i)).title(name.get(i)).snippet(npurp.get(i)));
            }
            LatLng taipei = new LatLng(25.04427,121.504617);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(taipei, 13));



    }


    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        String id=marker.getId();
        int intValue = Integer.valueOf(id.substring(1));
        Builder MyAlertDialog = new AlertDialog.Builder(this);
        MyAlertDialog.setTitle("施工單位:"+name.get(intValue));
        MyAlertDialog.setMessage("施工位置:" + addr.get(intValue) + "\n" + "施工時段:" + coti.get(intValue) + "\n" + "監工:" + tcma.get(intValue) + "\n" + "監工電話:"
                + tctl.get(intValue) + "\n" + "廠商現場施工人員:" + tcma3.get(intValue) + "\n" + "現場施工人員手機" + tctl3.get(intValue) + "\n" + "行政區:"+cname.get(intValue)+"\n");
        MyAlertDialog.show();


        return false;
    }



}