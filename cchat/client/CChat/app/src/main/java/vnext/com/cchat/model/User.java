package vnext.com.cchat.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String passWord;
    private String avatar;
    private String email;
    public static final long serialVersionUID = 2L;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String userName, String passWord, String email) {
        super();
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public User(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (this.userName.equals(user.getUserName()) && this.passWord.equals(user.getPassWord()))
                return true;
        }
        return false;
    }
    public boolean isExists(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            if (this.userName.equals(user.getUserName())) return true;
        }
        return false;
    }
}
