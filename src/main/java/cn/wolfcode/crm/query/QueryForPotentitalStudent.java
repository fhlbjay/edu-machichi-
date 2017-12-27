package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by joy on 2017/12/23.
 */
@Getter
@Setter
public class QueryForPotentitalStudent extends QueryObject{
    private String name;
    private Integer minAge;
    private Integer maxAge;
    private Boolean gender;
    private Integer educationId;
    private Integer intentionId;
    private Integer  stateId;
    private Integer classGradeId;
    private Integer campusId;
    private Integer intentionSubjectId;
}
