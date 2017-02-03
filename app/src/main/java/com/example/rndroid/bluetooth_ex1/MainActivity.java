package com.example.rndroid.bluetooth_ex1;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4;
    BluetoothAdapter bluetoothAdapter;
    ListView listView;
    Set<BluetoothDevice> deviceSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        listView = (ListView) findViewById(R.id.listView);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void on(View view){

        if (!bluetoothAdapter.isEnabled()){
            Intent intentOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentOn,0);
            Toast.makeText(this, "Turned On", Toast.LENGTH_SHORT).show();
        }            Toast.makeText(this, "Already On", Toast.LENGTH_SHORT).show();

    }

    public void off(View view){
        bluetoothAdapter.disable();
        Toast.makeText(this, "Turned Off", Toast.LENGTH_SHORT).show();
    }

    public void visible(View view){
    Intent intentVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(intentVisible,0);
    }

    public void list(View view){
        deviceSet = bluetoothAdapter.getBondedDevices();
        ArrayList<String> deviceArrayList = new ArrayList<>();
        for (BluetoothDevice bluetoothDevice : deviceSet){
            String s = bluetoothDevice.getName();
            deviceArrayList.add(s);
        }

        ArrayAdapter listArrayAdapter = new ArrayAdapter (this,android.R.layout.simple_list_item_1,deviceArrayList);
        listView.setAdapter(listArrayAdapter);
    }
}
