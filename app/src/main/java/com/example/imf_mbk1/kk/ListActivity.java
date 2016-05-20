package com.example.imf_mbk1.kk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import com.google.android.gms.maps.model.LatLng;

public class ListActivity extends AppCompatActivity {

    public List<String> name = new ArrayList<>();
    public List<LatLng> loc=new ArrayList<>();
    public List<String> addr = new ArrayList<>();
    public List<String> coti = new ArrayList<>();
    public List<String> cname = new ArrayList<>();
    public List<String> tcma = new ArrayList<>();
    public List<String> tctl = new ArrayList<>();
    public List<String> tcma3 = new ArrayList<>();
    public List<String> tctl3 = new ArrayList<>();
    public List<String> npurp = new ArrayList<>();
    ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();

    ListView worklist;
    TextView workname;
    TextView disname;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ListActivity.this, MainActivity.class);
                startActivity(intent);
                ListActivity.this.finish();
            }
        });
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

        workname = (TextView)findViewById(R.id.workname);
        disname = (TextView)findViewById(R.id.disname);


        for (int i=0;i<name.size();i++)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name",name.get(i));
            map.put("cname", cname.get(i)+"\n");
            oslist.add(map);
        }
        worklist=(ListView)findViewById(R.id.worklist);
        ListAdapter adapter = new SimpleAdapter(ListActivity.this, oslist,
                R.layout.list_v,
                new String[] {"name", "cname" }, new int[] {
                R.id.workname, R.id.disname});

        worklist.setAdapter(adapter);
        worklist.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
                //Do your tasks here
                Builder MyAlertDialog = new AlertDialog.Builder(ListActivity.this);
                MyAlertDialog.setTitle("施工單位:"+name.get(position));
                MyAlertDialog.setMessage("施工位置:" + addr.get(position) + "\n" + "施工時段:" + coti.get(position) + "\n" + "監工:" + tcma.get(position) + "\n" + "監工電話:"
                        + tctl.get(position) + "\n" + "廠商現場施工人員:" + tcma3.get(position) + "\n" + "現場施工人員手機" + tctl3.get(position) + "\n" + "挖掘目的:"+npurp.get(position)+"\n");
                MyAlertDialog.show();

                return true;
            }
        });






    }

}
