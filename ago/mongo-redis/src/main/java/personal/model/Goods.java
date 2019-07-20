package personal.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods implements Serializable {
    @Id
    private Long id;
    private String name;
    private String price;
    private Date createTime;
    private Date updateTime;
}
