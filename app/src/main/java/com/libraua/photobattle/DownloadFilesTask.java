package com.libraua.photobattle.photobattle;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {
    private static final String TAG = "DownloadFilesTask";

    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        for (int i = 0; i < count; i++) {
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) urls[i].openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                log(readFully(in, "UTF-8"));
            } catch (MalformedURLException e) {
                log(e.toString());
            } catch (IOException e) {
                log(e.toString());
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }

            publishProgress((int) ((i / (float) count) * 100));
            // Escape early if cancel() is called
            if (isCancelled()) break;
        }
        return totalSize;
    }

    protected void onProgressUpdate(Integer... progress) {
        //setProgressPercent(progress[0]);
    }

    protected void onPostExecute(Long result) {
    }


    public String readFully(InputStream inputStream, String encoding)
            throws IOException {
        return new String(readFully(inputStream), encoding);
    }

    private byte[] readFully(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toByteArray();
    }

    private void log(String msg) {
        Log.d(TAG, msg);
    }

}