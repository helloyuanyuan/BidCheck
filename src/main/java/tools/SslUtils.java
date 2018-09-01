package tools;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;

public class SslUtils {

    @SuppressWarnings("deprecation")
    public static String getRequest(String urlAddress, int timeOut) throws Exception {
        URL url = new URL(urlAddress);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            SslUtils.ignoreSsl();
        }
        URLConnection urlConnection = url.openConnection();
        urlConnection.setConnectTimeout(timeOut);
        urlConnection.setReadTimeout(timeOut);
        return IOUtils.toString(urlConnection.getInputStream());
    }

    @SuppressWarnings("deprecation")
    public static String postRequest(String urlAddress, String args, int timeOut) throws Exception {
        URL url = new URL(urlAddress);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            SslUtils.ignoreSsl();
        }
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(timeOut);
        urlConnection.setReadTimeout(timeOut);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
        outputStreamWriter.write(args);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        urlConnection.getOutputStream();
        return IOUtils.toString(urlConnection.getInputStream());
    }

    public static void ignoreSsl() throws Exception {
        HostnameVerifier hVerifier = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hVerifier);
    }

    static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tManager = new miTM();
        trustAllCerts[0] = tManager;
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
    }

    static class miTM implements TrustManager, X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
            return;
        }
    }

}