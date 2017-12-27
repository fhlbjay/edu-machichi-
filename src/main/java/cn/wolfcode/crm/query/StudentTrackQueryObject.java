package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by joy on 2017/12/24.
 */
@Getter
@Setter
@ToString
public class StudentTrackQueryObject extends QueryObject {
    private String name;
    private Long prevId;
    private Long nowId;
}
