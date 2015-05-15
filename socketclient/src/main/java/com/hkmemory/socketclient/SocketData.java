package com.hkmemory.socketclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by Jacky Li on 29/1/2015.
 */
class SocketData implements Serializable {
    private static Socket socket;
    private static int ServerPort;
    private static String ServerIP;
    private static OutputStream OutStream;
    private static DataOutputStream DataOut;
    private static int SocketTimeout;
    private static BufferedReader br;
    private static InputStream InStream;
    private static DataInputStream DataIn;
    private static InputStreamReader InStreamReader;

    public static InputStreamReader getInStreamReader() {
        return InStreamReader;
    }

    public static void setInStreamReader(InputStreamReader inStreamReader) {
        InStreamReader = inStreamReader;
    }


    public static InputStream getInStream() {
        return InStream;
    }

    public static void setInStream(InputStream inStream) {
        InStream = inStream;
    }


    public static DataInputStream getDataIn() {
        return DataIn;
    }

    public static void setDataIn(DataInputStream dataIn) {
        DataIn = dataIn;
    }



    public static BufferedReader getBr() {
        return br;
    }

    public static void setBr(BufferedReader br) {
        SocketData.br = br;
    }

    public static int getSocketTimeout() {
        return SocketTimeout;
    }

    public static void setSocketTimeout() {
        SocketTimeout = 30000;
    } //The Socket Server connection timeout threshold

    public static Socket getSocketObj() {
        return socket;
    }

    public static void setSocketObj(Socket _Socket) {
        socket = _Socket;
    }

    public static int getServerPort() {
        return ServerPort;
    }

    public static void setServerPort() {
        ServerPort = 8990;
    } //The Socket Server Port number

    public static String getServerIP() {
        return ServerIP;
    }

    public static void setServerIP() {
        ServerIP = "192.168.0.2";
    } //The Socket Server IP Address

    public static OutputStream getOutStream() {
        return OutStream;
    }

    public static void setOutStream(OutputStream _OutStream) {
        OutStream = _OutStream;
    }

    public static DataOutputStream getDataOut() {
        return DataOut;
    }

    public static void setDataOut(DataOutputStream _DataOut) {
        DataOut = _DataOut;
    }

}
