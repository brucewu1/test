package cn.vfc.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class Client {
    private String address = "";
    private int port = 0;


    private Socket socket = null;

    private Client() {
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private Client(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address, port);
    }

    public static byte[] sendCommand(String address, int port, byte[] data) {
        Client client = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        byte[] recevie = new byte[40];
        try {
            client = new Client(address, port);
            outputStream = client.getSocket().getOutputStream();
            outputStream.write(data);
            System.out.print("Send Data Success");
            inputStream = client.getSocket().getInputStream();
            inputStream.read(recevie);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                client.getSocket().close();
            } catch (IOException ioe) {
                System.out.print("IOE when closing resources");
            }
        }
        return recevie;
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        int i = 1;
        while (true){
            //服务器地址和IP
            String address = "127.0.0.1";
            int port = 8033;
            //心跳包
            byte[] data = "#$*beat0011".getBytes();
            String receive = new String(Client.sendCommand(address, port, data));
            System.out.println(receive.length());
            System.err.println("第" + i + "次发送心跳包" + receive);
            i++;
            if(receive.equals("success")){
                break;
            }
            try {
                //每隔2分钟发送一个心跳包
                Thread.sleep(3 * 1000);
                Thread thread = Thread.currentThread();
                System.out.println(thread.getName());
                thread.sleep(3*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //省略Getter和Setter方法
}

