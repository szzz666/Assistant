package top.szzz666.Assistant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebPost {
    private String PlayerName;
    private String Processing;
    private String Parameter;
    private String Username;
    private String Password;
    private Integer Time;
}
