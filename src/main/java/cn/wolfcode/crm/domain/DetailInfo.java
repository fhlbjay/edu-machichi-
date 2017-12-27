package cn.wolfcode.crm.domain;

import lombok.*;

/**
 * @author khalil
 */
@Getter
@Setter
@ToString
public class DetailInfo extends BaseDomain{
    private Long id;

    private Long official_id;

    private String idNo;

    private String emergencyContact;

    private String emergencyTel;

    private String workExperience;

    private Long workIntention;


}