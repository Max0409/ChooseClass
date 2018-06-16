package youth.controller;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import youth.bean.SubjectCount;
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
import java.util.ArrayList;
import java.util.List;

//访问：localhost:8080/user/hello，路径中不用加cloud
@Api(value = "用户模块", description = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {



    @Autowired
    private final SubjectRepository subjectRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ChoiceRepository choiceRepository;
    @Autowired
    private final ManagerRepository managerRepository;






    private final String basicUrl="http://172.19.184.117:8080";



   public UserController(SubjectRepository subjectRepository, StudentRepository studentRepository, ChoiceRepository choiceRepository, ManagerRepository managerRepository) {
        this.subjectRepository = subjectRepository;

        this.studentRepository = studentRepository;
        this.choiceRepository = choiceRepository;
        this.managerRepository = managerRepository;


   }


    @RequestMapping("/hello")
    public String say() {

        return "Helloxixiix";
    }

/*
得到院系所有学科
 */
    @RequestMapping("/B_Subject")
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

        String s = xStream.toXML(getIsChoosen(subjectRepository.findAll(),s_id));
        return  s;

    }
    /*
  返回所有学生
   */
    @RequestMapping("/student")
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

    @RequestMapping("/choice")
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

    @RequestMapping("/getAllChooseDetail")
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
    @RequestMapping("/manager_login")
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
    @RequestMapping("/managerAddSubject")
    public boolean managerAddSubject(String id,String name,String time,String score,String teacher,String location,String share ) {

        try {



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
    @RequestMapping("/managerDeleteSubject")
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
    @RequestMapping("/login")
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

    @RequestMapping("/getStuInfo")
    public Student getStuInfo( String Sno) {

        return  studentRepository.findBySId(Sno);


    }

    //得到当前用户id
    @PostMapping("/getLoginId")
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

    @RequestMapping(value = "/chooseCourse")
    public String chooseCourse(String sId, String cId) {

        return CallInterface.interfaceUtil(basicUrl + "/api/chooseCourse?sId=" + sId + "&cId=" + cId);
    }

    /*
    新的退课
     */

    @RequestMapping(value = "/quitCourse")
    public String quitCourse(String sId, String cId) {

        return CallInterface.interfaceUtil(basicUrl + "/api/quitCourse?sId=" + sId + "&cId=" + cId);
    }


     /*
    新的拿到学校全部的课程
     */

    @RequestMapping(value = "/getUserShareCourses")
    public String getUserShareCourses(String sId) {

        return CallInterface.interfaceUtil(basicUrl + "/api/getUserShareCourses?sId=" + sId );
    }



    /*
选课
 */
    @RequestMapping(value = "/chooseSubject")
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
    @RequestMapping("/deleteSubject")


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
    @PostMapping(value = "/addStudent")
    public boolean addStudent(String sId, String sName, String gender, String major, String password) {

        try{
            Student student=new Student(sId,sName,gender,major,password);
            studentRepository.save(student);

            return true;
        }catch (Exception e){
            return  false;
        }
    }


    /*
    判断学生是否选课
     */

    @PostMapping(value = "/isChoose")
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


}
