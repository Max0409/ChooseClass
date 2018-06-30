package youth.util;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jdk.internal.org.xml.sax.InputSource;
import org.dom4j.*;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import youth.bean.SubjectCount;
import youth.bean.SubjectVO;
import youth.dao.ChoiceRepository;
import youth.dao.ManagerRepository;
import youth.dao.StudentRepository;
import youth.dao.SubjectRepository;
import youth.model.Choice;
import youth.model.Student;
import youth.model.Subject;
import youth.util.CallInterface;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//访问：localhost:8080/user/hello，路径中不用加cloud

public class ServiceTool {



    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ChoiceRepository choiceRepository;
    @Autowired
    private final ManagerRepository managerRepository;






    private final String basicUrl="http://172.19.184.117:8080";




    public ServiceTool(SubjectRepository subjectRepository, StudentRepository studentRepository, ChoiceRepository choiceRepository, ManagerRepository managerRepository) {
        this.subjectRepository = subjectRepository;

        this.studentRepository = studentRepository;
        this.choiceRepository = choiceRepository;
        this.managerRepository = managerRepository;


    }



    public String say() {

        return "Helloxixiix";
    }

    /*
    得到院系所有学科
     */

    public String getAllBSubject(String s_id) {



        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("nju.edu.cn/schema/b");
        qmaps.setDefaultPrefix("b");

        XStream xStream = new XStream(new StaxDriver(qmaps));
        //调用toXML 将对象转成字符串
        xStream.alias("课程列表", List.class);

        xStream.alias("课程", youth.model.Subject.class);
        xStream.aliasField("编号", Subject.class,"id");//为类的字段节点重命名
        xStream.aliasField("名称", Subject.class,"name");//为类的字段节点重命名
        xStream.aliasField("课时", Subject.class,"time");//为类的字段节点重命名
        xStream.aliasField("学分", Subject.class,"score");//为类的字段节点重命名
        xStream.aliasField("老师", Subject.class,"teacher");//为类的字段节点重命名
        xStream.aliasField("地点", Subject.class,"location");//为类的字段节点重命名
        xStream.aliasField("共享", Subject.class,"share");//为类的字段节点重命名
        xStream.aliasField("IsChosen", Subject.class,"isChosen");//为类的字段节点重命名


        String s = xStream.toXML(getIsChoosen(subjectRepository.findAll(),s_id));
        return  s;

    }


    /*
得到院系所有共享学科
 */

    public String getShareBSubject(String s_id) {



        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("nju.edu.cn/schema/b");
        qmaps.setDefaultPrefix("b");

        XStream xStream = new XStream(new StaxDriver(qmaps));
        //调用toXML 将对象转成字符串
        xStream.alias("课程列表", List.class);

        xStream.alias("课程", youth.model.Subject.class);
        xStream.aliasField("编号", Subject.class,"id");//为类的字段节点重命名
        xStream.aliasField("名称", Subject.class,"name");//为类的字段节点重命名
        xStream.aliasField("课时", Subject.class,"time");//为类的字段节点重命名
        xStream.aliasField("学分", Subject.class,"score");//为类的字段节点重命名
        xStream.aliasField("老师", Subject.class,"teacher");//为类的字段节点重命名
        xStream.aliasField("地点", Subject.class,"location");//为类的字段节点重命名
        xStream.aliasField("共享", Subject.class,"share");//为类的字段节点重命名
        xStream.alias("课程", youth.bean.SubjectVO.class);
        xStream.aliasField("编号", SubjectVO.class,"id");//为类的字段节点重命名
        xStream.aliasField("名称", SubjectVO.class,"name");//为类的字段节点重命名
        xStream.aliasField("课时", SubjectVO.class,"time");//为类的字段节点重命名
        xStream.aliasField("学分", SubjectVO.class,"score");//为类的字段节点重命名
        xStream.aliasField("老师", SubjectVO.class,"teacher");//为类的字段节点重命名
        xStream.aliasField("地点", SubjectVO.class,"location");//为类的字段节点重命名
        xStream.aliasField("共享", SubjectVO.class,"share");//为类的字段节点重命名
        xStream.aliasField("IsChosen", SubjectVO.class,"isChosen");//为类的字段节点重命名


        String s = xStream.toXML(toSubjectVO(getIsChoosen(subjectRepository.findByShare("Y"),s_id)));
        return  s;

    }
    /*
  返回所有学生
   */

    public String getStudents() {

        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("nju.edu.cn/schema/b");
        qmaps.setDefaultPrefix("b");

        XStream xStream = new XStream(new StaxDriver(qmaps));
        //调用toXML 将对象转成字符串
        xStream.alias("学生列表", List.class);

        xStream.alias("学生信息", youth.model.Student.class);
        xStream.aliasField("账户名", Student.class,"uName");//为类的字段节点重命名
        xStream.aliasField("密码", Student.class,"password");//为类的字段节点重命名
        xStream.aliasField("级别", Student.class,"sLevel");//为类的字段节点重命名
        xStream.aliasField("学号", Student.class,"sId");//为类的字段节点重命名
        xStream.aliasField("专业", Student.class,"major");//为类的字段节点重命名
        xStream.aliasField("姓名", Student.class,"sName");//为类的字段节点重命名
        xStream.aliasField("性别", Student.class,"gender");//为类的字段节点重命名

        String s = xStream.toXML(studentRepository.findAll());
        return  s;


    }



    /*
    返回所有选课信息
     */


    public String getChoices() {
        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("nju.edu.cn/schema/b");
        qmaps.setDefaultPrefix("b");

        XStream xStream = new XStream(new StaxDriver(qmaps));
        //调用toXML 将对象转成字符串
        xStream.alias("选课列表", List.class);

        xStream.alias("选课", youth.model.Choice.class);
        xStream.aliasField("开课院系", Choice.class,"school");//为类的字段节点重命名
        xStream.aliasField("学号", Choice.class,"sId");//为类的字段节点重命名
        xStream.aliasField("编号", Choice.class,"cId");//为类的字段节点重命名
        xStream.aliasField("得分", Choice.class,"score");//为类的字段节点重命名

        String s = xStream.toXML(choiceRepository.findAll());
        return  s;
    }
    /*

    返回所有选课统计信息
     */


    public String getAllChooseDetail() {

        QNameMap qmaps = new QNameMap();
        qmaps.setDefaultNamespace("nju.edu.cn/schema/b");
        qmaps.setDefaultPrefix("b");

        XStream xStream = new XStream(new StaxDriver(qmaps));


        //调用toXML 将对象转成字符串
        xStream.alias("课程列表", List.class);



        xStream.alias("课程", youth.model.Subject.class);
        xStream.aliasField("编号", Subject.class,"id");//为类的字段节点重命名
        xStream.aliasField("名称", Subject.class,"name");//为类的字段节点重命名
        xStream.aliasField("课时", Subject.class,"time");//为类的字段节点重命名
        xStream.aliasField("学分", Subject.class,"score");//为类的字段节点重命名
        xStream.aliasField("老师", Subject.class,"teacher");//为类的字段节点重命名
        xStream.aliasField("地点", Subject.class,"location");//为类的字段节点重命名
        xStream.aliasField("共享", Subject.class,"share");//为类的字段节点重命名




        List<SubjectCount> subjectCounts=getSubjectCount();

        String s = xStream.toXML(subjectCounts);
        return  s;

    }


    /*
manager登录
 */

    public boolean ManagerLogin(HttpServletResponse response, String name, String password) {

        try {
            if (managerRepository.findByName(name).getPassword().equals(password)){
                Cookie userCookie=new Cookie("managerName",name);


                userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
                userCookie.setPath("/");
                response.addCookie(userCookie);
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }

    }



    /*
管理员增加课程信息
 */

    public boolean managerAddSubject(String name,String time,String score,String teacher,String location,String share ) {

        try {

            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,3);
            String id="b"+uuid;


            Subject subject=new Subject( id, name, time, score,  teacher, location, share);
            subjectRepository.save(subject);


            return true;

        }catch (Exception e){
            return false;
        }


    }

    /*
管理员删除课程信息
*/

    public boolean managerDeleteSubject(String id) {

        try {




            subjectRepository.deleteById(id);



            return true;

        }catch (Exception e){
            return false;
        }


    }



    /*
用户登录
 */

    public boolean login(HttpServletResponse response, String id, String password) {

        try {
            if (studentRepository.findBySId(id).getPassword().equals(password)){
                Cookie userCookie=new Cookie(id,id);


                userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
                userCookie.setPath("/");
                response.addCookie(userCookie);
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }

    }


    public Student getStuInfo( String Sno) {

        return  studentRepository.findBySId(Sno);


    }

    //得到当前用户id

    public String getLoginId(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
        if (null==cookies) {
            System.out.println("没有cookie=========");
            return null;
        } else {
            for(Cookie cookie : cookies){
                System.out.println("name:"+cookie.getName()+",value:"+ cookie.getValue());
            }
        }
        return cookies[3].getName();

    }



    /*
    新的选课
     */


    public String chooseCourse(String sId, String cId) {

        return CallInterface.interfaceUtil(basicUrl + "/api/chooseCourse?sId=" + sId + "&cId=" + cId);
    }

    /*
    新的退课
     */


    public String quitCourse(String sId, String cId) {

        return CallInterface.interfaceUtil(basicUrl + "/api/quitCourse?sId=" + sId + "&cId=" + cId);
    }


     /*
    新的拿到学校全部的课程
     */


    public String getUserShareCourses(String sId,String dep) {

        return CallInterface.interfaceUtil(basicUrl + "/api/getUserShareCourses?sId=" + sId+"&system=b&dep="+dep );
    }



    /*
选课
 */

    public boolean chooseSubject(String s_id, String c_id) {

        try{


            choiceRepository.save(new Choice(s_id,c_id,"0","计算机科学"));
            return true;
        }catch (Exception e){
            return  false;
        }
    }





    /*
退课
 */



    public boolean deleteSubject(String s_id, String c_id) {

        try{


            choiceRepository.deleteBySIdAndCId(s_id,c_id);


            return true;
        }catch (Exception e){
            return  false;
        }
    }




    /*
增加学生
  */

    public boolean addStudent(HttpServletResponse response,HttpServletRequest request) {

        try {


            BufferedReader br = request.getReader();

            String str, wholeStr = "";
            while((str = br.readLine()) != null){
                wholeStr += str;
            }
            System.out.println(wholeStr);


            Document doc = null;
            doc = DocumentHelper.parseText(wholeStr); // 将字符串转为XML
            Element root = doc.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                System.out.println(element.element("账户名").getText());
            }



            return true;

        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }


    /*
    判断学生是否选课
     */


    public boolean isChoose(String s_id, String c_id) {

        try{
            if (choiceRepository.findBySIdAndCId(s_id,c_id)!=null)
                return true;
            else
                return false;
        }catch (Exception e){
            return  false;
        }
    }


    public List<SubjectCount> getSubjectCount(){

        ArrayList arrayList=new ArrayList();
        ArrayList<Subject> subjects= (ArrayList<Subject>) subjectRepository.findAll();
        for(Subject subject:subjects){
            SubjectCount subjectCount=new SubjectCount(choiceRepository,subject);
            subjectCount.getCount();
            arrayList.add(subjectCount);
            System.out.println(subject.getName()+" "+subjectCount.count);

        }

        return arrayList;
    }


    public List<Subject> getIsChoosen(List<Subject> subjects,String s_id){

        for (Subject subject:subjects){
            System.out.println(s_id+"  "+subject.getId());
            if (choiceRepository.findBySIdAndCId(s_id,subject.getId())!=null){
                subject.setChosen("true");
            }
        }

        return subjects;

    }



    public List<SubjectVO> toSubjectVO(List<Subject>  subjects){
        List<SubjectVO> subjectVOS=new ArrayList<SubjectVO>();
        for(Subject subject:subjects){
            SubjectVO subjectVO=new SubjectVO();
            subjectVO.toSubjectVO(subject);

            subjectVOS.add(subjectVO);

        }

        return subjectVOS;
    }

}
