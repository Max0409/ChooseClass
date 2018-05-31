package youth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import youth.dao.ChoiceRepository;
import youth.dao.StudentRepository;
import youth.dao.SubjectRepository;
import youth.model.Choice;
import youth.model.Subject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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


    public UserController(SubjectRepository subjectRepository, StudentRepository studentRepository, ChoiceRepository choiceRepository) {
        this.subjectRepository = subjectRepository;

        this.studentRepository = studentRepository;
        this.choiceRepository = choiceRepository;
    }


    @RequestMapping("/hello")
    public String say() {

        return "Helloxixiix";
    }

/*
得到院系所有学科
 */
    @RequestMapping("/B_Subject")
    public List<Subject> getAllBSubject() {
        return  subjectRepository.findAll();
    }


/*
登录
 */
    @PostMapping("/login")
    public boolean login(HttpServletResponse response, String id, String password) {

        try {
            if (studentRepository.findBySId(id).getPassword().equals(password)){
                Cookie userCookie=new Cookie("id",id);

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
选课
 */
    @PostMapping("/chooseSubject")
    public boolean chooseSubject(String s_id, String c_id) {

       try{


           choiceRepository.save(new Choice(s_id,c_id));


           return true;
       }catch (Exception e){
           return  false;
       }
    }




    /*
退课
 */
    @PostMapping("/deleteSubject")


    public boolean deleteSubject(String s_id, String c_id) {

        try{


            choiceRepository.deleteBySIdAndCId(s_id,c_id);


            return true;
        }catch (Exception e){
            return  false;
        }
    }



}
