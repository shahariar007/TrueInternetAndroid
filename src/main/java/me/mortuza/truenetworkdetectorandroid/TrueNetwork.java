package me.mortuza.truenetworkdetectorandroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import java.io.IOException;

/**
 * Created by mortuza on 3/4/2018.
 */

public class TrueNetwork {
    private Context mContext;
    NetworkResult networkResult;

    public TrueNetwork(Context mContext) {
        this.mContext = mContext;
    }

    public void detect(String ip, NetworkResult networkResult) {
        new MyTask(ip, networkResult).execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class MyTask extends AsyncTask<Void, Boolean, Boolean> {
        String ip;
        NetworkResult networkResult;

        public MyTask(String ip, NetworkResult networkResult) {
            this.ip = ip;
            this.networkResult = networkResult;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Boolean status = false;
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                Runtime runtime = Runtime.getRuntime();
                Process proc = null; // other servers, for example
                try {
                    proc = runtime.exec("/system/bin/ping -c 1 " + ip);
                    proc.waitFor();

                    int exit = proc.exitValue();
                    if (exit == 0) {
                        status = true;
                    } else {
                        status = false;
                    }

                } catch (IOException e) {
                    status = false;
                    e.printStackTrace();
                    proc.destroy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    status = false;
                    proc.destroy();

                }

            } else {
                status = false;
            }
            return status;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            networkResult.result(aBoolean);
        }
    }
}
