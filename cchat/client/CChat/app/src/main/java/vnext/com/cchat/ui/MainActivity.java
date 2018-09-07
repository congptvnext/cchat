package vnext.com.cchat.ui;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import vnext.com.cchat.R;
import vnext.com.cchat.handler.PostOffice;
import vnext.com.cchat.model.Message;
import vnext.com.cchat.model.User;
public class MainActivity extends AppCompatActivity {
    EditText edtMessage,edtCreateGroup,edtJoinGroup;
    Button btnSend,btnConnect,btnDisconnect,btnCreateGroup,btnJoinGroup;
    PostOffice postOffice;
    User user;
    Message message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
        initSocket();
        initControls();
    }
    private void initData(){
        user = new User("Nguyen Van A");
        message = new Message();
    }
    private void initSocket() {
        postOffice = new PostOffice(this);
    }
    private void initViews() {
        edtMessage = findViewById(R.id.edt_message);
        btnSend = findViewById(R.id.btn_send);
        btnConnect = findViewById(R.id.btn_connect);
        btnDisconnect = findViewById(R.id.btn_disconnect);
        btnCreateGroup = findViewById(R.id.btn_create_group);
        btnJoinGroup = findViewById(R.id.btn_join_group);
        edtCreateGroup = findViewById(R.id.edt_create_group);
        edtJoinGroup = findViewById(R.id.edt_join_group);
    }
    private void initControls() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mess = edtMessage.getText().toString();
                message.setMessageText(mess);
                message.setUser(user);
                postOffice.sendMessage(message);
            }
        });
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postOffice.connect(user);
                postOffice.receiveMessage();
            }
        });
        btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                postOffice.disConnect();
            }
        });
    }

    @Override
    protected void onDestroy() {
//        postOffice.disConnect();
        super.onDestroy();
    }

}

