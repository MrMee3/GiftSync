package uk.co.portalcomputing.giftsync;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts(this.getContentResolver());
    }

    public void getContacts(ContentResolver cr){
        Cursor contacts = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);

        ListView list = findViewById(R.id.contactList);
        ArrayList<String> displayContactListViewContents = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, displayContactListViewContents);

        list.setAdapter(adapter);
        if (contacts.getCount()>0) {
            ArrayList<String> contactsNumberList = new ArrayList<>();
            while (contacts.moveToNext()) {
                String name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
                if(!contactsNumberList.contains(phoneNumber)){
                    contactsNumberList.add(phoneNumber);
                    displayContactListViewContents.add(name + " :" + phoneNumber);
                }
            }
        }else{
            Toast.makeText(this,"Error no Contacts found",Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();
        contacts.close();
    }
}
