package youth.service;

import youth.model.Student;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.Entity;

/**
 * 网吧web services 接口
 * @author xiaojf 2017/7/24 21:35
 */
@WebService(targetNamespace = "http://service.netbar.temple.xiaojf.cn")// 命名空间,一般是接口的包名倒序

public interface NetbarServices {
    @WebMethod
    String sayHello(@WebParam(name = "userName") String name);
    @WebMethod
    public String getAllBSubject(@WebParam(name = "s_id")String s_id);
    @WebMethod
    public String getShareBSubject(@WebParam(name = "s_id")String s_id);
    @WebMethod
    public String getStudents();
    @WebMethod
    public String getChoices();
    @WebMethod
    public String getAllChooseDetail();
    @WebMethod
    public Student getStuInfo(@WebParam(name = "Sno")String Sno);
    @WebMethod
    public boolean chooseSubject(@WebParam(name = "s_id")String s_id, @WebParam(name = "c_id")String c_id);
    @WebMethod
    public boolean deleteSubject(@WebParam(name = "s_id")String s_id, @WebParam(name = "c_id")String c_id);






}