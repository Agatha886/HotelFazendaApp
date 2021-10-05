package br.com.unip.pimIV.hotelFazenda.dao;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    private Connection con;
    private String user, pass, ip, dataBase;

    public Connection connectionClass(){
        ip = "192.168.1.38";
        dataBase = "HotelFazendaDb";
        user = "sa";
        pass ="";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionUrl = null;
         try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + ";" + "databasename"
                    + dataBase + ";user=" + user+"; password=" + pass;
            connection = DriverManager.getConnection(ConnectionUrl);

         }catch (Exception e){
             Log.e("ERRO ", "connectionClass:",e);
         }
        return connection;

    }
}
