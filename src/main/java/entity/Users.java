package entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Todo: Created by Grady on 2017/10/24.
 */
public class Users implements Serializable{

    private String id;
    private String name;
    private String password;
    @JSONField(format =  "yyyy-MM-DD HH:MM")
    private Date date;

    public static void main(String[] args) {
        Users users = new Users("hhh","ddd","dddasa",new Date());
        System.out.println(users.toString());

    }
    public Users(){}

    public Users(String id, String name, String password, Date date) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
