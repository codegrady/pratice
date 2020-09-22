package entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Todo: Created by Grady on 2017/10/24.
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
}
