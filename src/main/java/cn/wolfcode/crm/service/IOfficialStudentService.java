package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IOfficialStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(OfficialStudent record);

    OfficialStudent selectByPrimaryKey(Long id);

    List<OfficialStudent> selectAll();

    int updateByPrimaryKey(OfficialStudent record);

    PageResult query(QueryObject qo);

    void changeClass(Long id);


    void export(HttpServletResponse response) throws Exception;


    void quitSchool(Long id,Long statusId);

    void runOff(Long id);

    List<OfficialStudent> selectUnpaid();

    OfficialStudent selectByPsId(Long id);

}
