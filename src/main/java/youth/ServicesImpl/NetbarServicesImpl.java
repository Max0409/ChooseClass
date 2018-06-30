package youth.ServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import youth.model.Student;
import youth.service.NetbarServices;
import youth.util.ServiceTool;

import javax.jws.WebService;

/**
 *  网吧web services 接口实现
 * @author xiaojf 2017/7/24 21:38
 */
@WebService(serviceName = "NetbarServices"//服务名
        ,targetNamespace = "http://service.netbar.temple.xiaojf.cn"//报名倒叙，并且和接口定义保持一致
        ,endpointInterface = "youth.service.NetbarServices")//包名
@Component

public class NetbarServicesImpl implements NetbarServices {


@Autowired(required = false)
    private ServiceTool serviceTool;







    private final String basicUrl="http://172.19.184.117:8080";




    @Override
    public String sayHello(String name) {
        return "hello , "+ name;
    }

    @Override
    public String getAllBSubject(String s_id) {return  serviceTool.getAllBSubject(s_id);}

    @Override
    public String getShareBSubject(String s_id) {
        return serviceTool.getShareBSubject(s_id);

    }

    @Override
    public String getStudents() {
        return serviceTool.getStudents();
    }

    @Override
    public String getChoices() {
        return serviceTool.getChoices();
    }

    @Override
    public String getAllChooseDetail() {
        return serviceTool.getAllChooseDetail();

    }

    @Override
    public Student getStuInfo(String Sno) {
        return serviceTool.getStuInfo(Sno);
    }

    @Override
    public boolean chooseSubject(String s_id, String c_id) {
        return serviceTool.chooseSubject(s_id,c_id);
    }

    @Override
    public boolean deleteSubject(String s_id, String c_id) {
        return serviceTool.deleteSubject(s_id,c_id);
    }


}