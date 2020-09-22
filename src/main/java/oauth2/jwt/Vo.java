package oauth2.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class Vo implements Serializable {
    private String mobile;
    private Integer meetingRoomId;
    private String startTime;
    private String endTime;
}
