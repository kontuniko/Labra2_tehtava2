package fi.mobtietoliikenne.labra2_tehtava2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class AsynkTask extends AsyncTask<String, String, ArrayList<String>> {

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> result = null;

        try {
            URL url = new URL("https://financialmodelingprep.com/api/company/price/AAPL,GOOG,FB,NOK?datatype=json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            result = jsonParse(fromStream(stream));
            Log.d("DEBUG",""+result.get(0));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<String> jsonParse(String dataString) {
        try {

            JSONObject jsonObject = new JSONObject(dataString);

            Iterator iterator = jsonObject.keys();
            ArrayList<String> arrayList = new ArrayList<>();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                JSONObject stockKeys = jsonObject.getJSONObject(key);
                Log.d("JSONPARSE", "JsonKeys: " + key + " " + stockKeys.getDouble("price"));
                arrayList.add(key + " " +stockKeys.getDouble("price"));
            }
            return arrayList;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //From: https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
    public static String fromStream(InputStream in) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }

    @Override
    protected void onPostExecute(ArrayList<String> keys) {
        super.onPostExecute(keys);
        String lista = "";
        for (String stock:keys){
            lista = lista + stock + "\n" + "\n";
        }
        MainActivity.tvOutput2.setText(lista);
    }
}