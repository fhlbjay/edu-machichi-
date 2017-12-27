package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.domain.Payroll;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPayrollService {
    int deleteByPrimaryKey(Long id);

    int insert(Payroll record);

    Payroll selectByPrimaryKey(Long id);

    List<Payroll> selectAll();

    int updateByPrimaryKey(Payroll record);

    PageResult query(QueryObject qo);

    void downPayrollModal() throws Exception;

    Defray checkAccount(Payroll payroll);

    boolean reviseEmpSalaryInformation(String managerName, String managerPassword);

    void export() throws Exception;

    void input(MultipartFile file)throws Exception;
}
