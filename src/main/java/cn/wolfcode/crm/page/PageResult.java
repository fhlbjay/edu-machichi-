package cn.wolfcode.crm.page;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResult {
    private  Integer total;
    private List<?> rows;
}
