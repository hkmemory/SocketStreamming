package com.hkmemory.socketclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * Created by Jacky Li on 14/5/2015.
 */
public class SocketUtility {
    private final Runnable SocketRunnable; //Create Runnable for create thread
    private SocketData socketData = new SocketData(); //New Socket Data Object

    {
        SocketRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    getSocketConnection();
                } catch (Exception e) {
                    Log.d(CommonData.logCatMobile, e.toString());  //LogCat Exception
                    getSocketConnection();
                }
            }
        };
    }

    //Text file writer
    private static void writeFile(String str, File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(file, true));
            buf.append(str);
            buf.newLine();
            buf.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Connect() {
        new Thread(SocketRunnable).start();
    }

    //Socket connection method
    private void getSocketConnection() {
        try {
            SocketData.setServerIP();
            SocketData.setServerPort();
            SocketData.setSocketObj(new java.net.Socket());
            SocketData.setSocketTimeout();
            InetAddress serverAddr = InetAddress.getByName(SocketData.getServerIP());
            SocketAddress sc_add = new InetSocketAddress(serverAddr, SocketData.getServerPort());
            SocketData.getSocketObj().connect(sc_add, SocketData.getSocketTimeout());
            SocketData.setOutStream(SocketData.getSocketObj().getOutputStream());
            SocketData.setInStream(SocketData.getSocketObj().getInputStream());
            SocketData.setDataIn(new DataInputStream(SocketData.getSocketObj().getInputStream()));
            SocketData.setInStreamReader(new InputStreamReader(SocketData.getDataIn()));
            SocketData.setBr(new BufferedReader(SocketData.getInStreamReader()));
            SocketData.setDataOut(new DataOutputStream(SocketData.getSocketObj().getOutputStream()));

            Log.d(CommonData.logCatMobile, "Socket Running");
        } catch (IOException e1) {
            e1.printStackTrace();
            Log.d(CommonData.logCatMobile, e1.toString());
        }
    }

    public void Disconnect() {
        try {
            Log.d(CommonData.logCatMobile, "Connection Close.....");
            if (SocketData.getDataOut() != null) {
                SocketData.getDataOut().close();
            }
            if (SocketData.getOutStream() != null) {
                SocketData.getOutStream().close();
            }
            if (SocketData.getSocketObj() != null) {
                SocketData.getSocketObj().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(CommonData.logCatMobile, e.toString());
        }

    }

    public void sentData(String WriteScoketOBJ) throws IOException {
        SocketData.getDataOut().write((WriteScoketOBJ + "\r").getBytes("UTF-8"));
    }

    public String receivedData() throws IOException {
        String recvData = SocketData.getBr().readLine() + "\n";
        Log.d(CommonData.logCatMobile, "Data: " + recvData);
        return recvData;
    }

}
