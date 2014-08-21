package nl.jketelaar.chatter;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;

class Utils {
    public static String parametersToWWWFormURLEncoded(Map<String, String> parameters)
            throws Exception {
        StringBuilder s = new StringBuilder();
        for (Map.Entry parameter : parameters.entrySet()) {
            if (s.length() > 0) {
                s.append("&");
            }
            s.append(URLEncoder.encode((String) parameter.getKey(), "UTF-8"));
            s.append("=");
            s.append(URLEncoder.encode((String) parameter.getValue(), "UTF-8"));
        }
        return s.toString();
    }

    public static String md5(String input) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(input.getBytes("UTF-8"));
        BigInteger hash = new BigInteger(1, md5.digest());
        return String.format("%1$032X", hash);
    }

    public static String post(String url, Map<String, String> parameters) throws Exception {
        URLConnection connection = new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.95 Safari/537.36");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
        osw.write(parametersToWWWFormURLEncoded(parameters));
        osw.flush();
        osw.close();
        Reader r = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringWriter w = new StringWriter();
        char[] buffer = new char[1024];
        int n = 0;
        while ((n = r.read(buffer)) != -1) {
            w.write(buffer, 0, n);
        }
        r.close();
        return w.toString();
    }

    public static String stringAtIndex(String[] strings, int index) {
        if (index >= strings.length) return "";
        return strings[index];
    }
}
