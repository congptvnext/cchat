package vnext.com.cchat.handler;

import android.app.Activity;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import vnext.com.cchat.model.Message;
import vnext.com.cchat.model.User;
import vnext.com.cchat.utils.DialogUtils;
public class PostOffice {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Activity activity;
    /* private Message message; */
    private User user;
    private DialogUtils dialogUtils;
    private Gson gson;
    private Thread connect, disConnect, send, receive;
    Lock lock;
    public PostOffice(Activity activity) {
        this.activity = activity;
        dialogUtils = new DialogUtils(activity);
        lock = new ReentrantLock();
        gson = new Gson();
    }
    public void connect(final User user) {
        dialogUtils.showDialog("connecting...");
        this.user = user;
        connect = new Thread(new Runnable() {
            @Override
            public void run() {
                String messageResponse;
                try {
                    socket = new Socket(NetworkProfile.IP, NetworkProfile.PORT);
                    String userData = gson.toJson(user);
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());
                    dos.writeUTF(userData);
                    messageResponse = "success";
                } catch (IOException e) {
                    dialogUtils.dismissDialog();
                    messageResponse = e.getMessage();
                    e.printStackTrace();
                } catch (Exception e) {
                    messageResponse = e.getMessage();
                    e.printStackTrace();
                }
                dialogUtils.dismissDialog();
                DialogUtils.showToastRunOnThread(activity, messageResponse);
            }
        });
        connect.start();
    }
    public void disConnect() {
        disConnect = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        disConnect.start();
    }
    public void sendMessage(final Message message) {
        dialogUtils.showDialog("sending...");
        System.out.println("send message ... ");
        send = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String messageJson = gson.toJson(message);
                    dos.writeUTF(messageJson);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialogUtils.dismissDialog();
            }
        });
        send.start();
    }
    public void receiveMessage() {
        receive = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    int count = 0;
                    while (true) {
                        count++;
                        System.out.println("Message count : " + count);
                        String messageData = dis.readUTF();
                        Message message = gson.fromJson(messageData, Message.class);
                        DialogUtils.showToastRunOnThread(activity, message.getUser().getUserName() + " : " + message.getMessageText());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        receive.start();
    }


}
