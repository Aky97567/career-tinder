package com.softwaregiants.careertinder.utilities;

import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilityMethods {

    private static String TAG = "UtilityMethods";

    public static Point getDisplaySize(Context mContext){
        try {
            if(Build.VERSION.SDK_INT > 16) {
                WindowManager wm = ((WindowManager) (mContext.getSystemService(Context.WINDOW_SERVICE)));
                Point size = new Point();
                wm.getDefaultDisplay().getRealSize(size);
                return size;
            }else{
                return new Point(0, 0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Point(0, 0);
        }
    }
    public static int dpToPx(int dp, Context mContext) {
        return (int) (dp * ((float) mContext.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean isConnected(Context mContext)
    {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        boolean isConnected = (info != null && info.isConnected());
        if ( !isConnected ) {
            Toast.makeText(mContext,Constants.MSG_CONNECTION_ERROR,Toast.LENGTH_SHORT).show();
        }
        return isConnected;
    }

    public static String sha224Hash(String toHash )
    {
        String hash = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance( "SHA-224" );
            byte[] bytes = toHash.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            bytes = digest.digest();

            // This is ~55x faster than looping and String.formating()
            hash = bytesToHex( bytes );
        }
        catch( NoSuchAlgorithmException e )
        {
            e.printStackTrace();
        }
        catch( UnsupportedEncodingException e )
        {
            Log.e(TAG, "sha224Hash: ", e );
        }
        return hash;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex( byte[] bytes )
    {
        char[] hexChars = new char[ bytes.length * 2 ];
        for( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[ j ] & 0xFF;
            hexChars[ j * 2 ] = hexArray[ v >>> 4 ];
            hexChars[ j * 2 + 1 ] = hexArray[ v & 0x0F ];
        }
        return new String( hexChars );
    }
}
