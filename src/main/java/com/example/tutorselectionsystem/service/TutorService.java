package com.example.tutorselectionsystem.service;


import com.example.tutorselectionsystem.entity.*;
import com.example.tutorselectionsystem.repository.*;
import com.example.tutorselectionsystem.utils.excute;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Encoder;
import java.util.*;

@Service
@Slf4j
@Transactional
public class TutorService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private PasswordEncoder encoder;

    //添加老师
    public void addTutor(Tutor tutor){
        tutorRepository.save(tutor);
    }

    //获取所有老师
    public List<Tutor> getTutors(){
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors;
    }
    /**
     * 对user声明了persist/remove级联操作
     * 缺少判断该生已经被其他老师选择，可以抛个异常
     * @param s
     * @param tid
     * @return
     */
    public Student addStudent(Student s,int tid){
        Student student = Optional.ofNullable(studentRepository.find(s.getUser().getName(),s.getUser().getNumber()))
                .orElseGet(()->{
                    User u = s.getUser();
                    u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                    u.setRole(User.Role.STUDENT);
                    return s;
                });
        Tutor t = tutorRepository.findById(tid).orElseThrow();
        int number = t.getNowStuNum();
        t.setNowStuNum(number+1);
        student.setTutor(new Tutor(tid));
        studentRepository.save(student);
        return student;
    }
    //更改方向
    public void updateDirections(List<Direction> newDirections){
        directionRepository.deleteAll();
        for(int i=0;i<newDirections.size();i++){
            directionRepository.save(newDirections.get(i));
        }
    }
    public void deleteCourse(int id){
        courseRepository.delete(new Course(id));
    }
    public Course updateCourse (int id,String name,double weight){
        Course c = courseRepository.findById(id).orElseThrow();
        c.setName(name);
        c.setWeight(weight);
        return c;
    }

    //建立学生 学生-课程-成绩关联
    public void buildStudents(List<StudentCourse> scs){
        StudentCourse sc0 = studentCourseRepository.getSC(scs.get(0).getCourse().getId());
        if (sc0==null){
            studentCourseRepository.deleteAllByCourse_Id(scs.get(0).getCourse().getId());
        }
//        studentCourseRepository.deleteAllByCourse_Id(scs.get(1).getCourse().getId());
        for (StudentCourse sc:scs) {
            Student student = Optional.ofNullable(studentRepository.find(sc.getStudent().getUser().getName(),sc.getStudent().getUser().getNumber()))
                    .orElseGet(()->{
                        Student s = sc.getStudent();
                        User u = s.getUser();
                        u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                        u.setRole(User.Role.STUDENT);
                        return s;
                    });
            sc.setStudent(student);
            log.debug("{}", sc);
            studentCourseRepository.save(sc);
        }
//        for(int i=0;i<scs.size();i++){
//
//            studentCourseRepository.save(scs.get(i));
//        }
    }

    //计算学生
    public List<Integer> excuteStudent(Tutor tutor){
        //制造当前老师选择的课的当前课程id数组
        int n = tutor.getCourses().size();
        Integer tcoursesIds[] = new Integer[n];
        for (int i=0;i<n;i++){
             tcoursesIds[i] =  tutor.getCourses().get(i).getId();
        }
        //制造键为学号，值为分数的map
        //只操作sc表中课程id为课程id数组内部id的sc，计算最终分数
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        List<StudentCourse> scs = studentCourseRepository.findAll();
        for(StudentCourse sc:scs){
            for(int cid:tcoursesIds){
                if(sc.getCourse().getId()==cid){
                    double t =0;
                    int id = sc.getStudent().getUser().getNumber();
                    if(map.get(id)!=null){
                        t = map.get(id);
                    }
                    map.put(id, (t+(sc.getCourse().getWeight())*(sc.getScore())*0.01));
                }
            }

        }
        //map基于value值的降序
        map =  excute.sortByValueDescending(map);//引入函数文件
        System.out.println("基于value值的降序，排序输出结果为：");
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //返回排完序后，只有键(学号)的数组
        List<Integer> mapKeyList = new ArrayList<Integer>(map.keySet());
        int maxStuScope = tutor.getScopeStuNum();
        mapKeyList = mapKeyList.subList(0, maxStuScope);
        System.out.println("mapKeyList:"+mapKeyList);
        return mapKeyList;



    }
}











