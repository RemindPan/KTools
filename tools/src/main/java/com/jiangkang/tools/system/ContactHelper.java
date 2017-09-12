package com.jiangkang.tools.system;

import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;

/**
 * Created by jiangkang on 2017/9/8.
 */

public class ContactHelper {

    private final static String[] FROM_CLOUMNS = new String[]{
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY:
                    ContactsContract.Contacts.DISPLAY_NAME
    };

    private final static String[] PROJECTION = new String[]{
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB?
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY:
                    ContactsContract.Contacts.DISPLAY_NAME

    };

    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the LOOKUP_KEY column
    private static final int LOOKUP_KEY_INDEX = 1;


    private long mContactId;

    private String mContactKey;

    private Uri mContactUri;




}
