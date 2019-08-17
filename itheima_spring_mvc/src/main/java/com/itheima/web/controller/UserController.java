package com.itheima.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.domain.User;
import com.itheima.domain.VO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value="/quick23")
    @ResponseBody
    public void save23(String username, MultipartFile[] uploadFile) throws IOException {
        System.out.println(username);

        for (MultipartFile multipartFile : uploadFile) {
            System.out.println(multipartFile.getName());// 表单中的name 的属性值
            // 我们可以根据文件的扩展名做限制
            System.out.println(multipartFile.getContentType());// 文件的MIME类型
            // 我们可以判断文件的大小 做限制
            System.out.println(multipartFile.getSize());// 文件的大小
            // 一般会把文件名称给加一个随机字符串 避免重名
            System.out.println(multipartFile.getOriginalFilename());// 文件的真实名称
            // 文件上传的路径一般有  文件服务器 web服务器(建议存放在WEB-INF目标下)
            // 建议对文件夹使用hash打散算法创建多级目录
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("C:\\upload\\"+UUID.randomUUID().toString()+originalFilename));
            //FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),new File("C:\\upload\\"+UUID.randomUUID().toString()+originalFilename));
        }
    }

    @RequestMapping(value="/quick220")
    @ResponseBody
    public void save22(String username, MultipartFile uploadFile,MultipartFile uploadFile2) throws IOException {
        System.out.println(username);

        //获得上传文件的名称
        String originalFilename = uploadFile.getOriginalFilename();
        uploadFile.transferTo(new File("C:\\upload\\"+originalFilename));
    }

    @RequestMapping(value="/quick22")
    @ResponseBody
    public void save22(String username,MultipartFile uploadFile,HttpServletRequest request) throws IOException {
        String filename = uploadFile.getOriginalFilename();
        filename = UUID.randomUUID().toString().replace("-","") + "_" +  filename;
        LocalDate localDate = LocalDate.now();
        String dateStr = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String realPath = request.getServletContext().getRealPath("/uploads/") + dateStr + "/";
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(realPath + filename);

        uploadFile.transferTo(file);
    }

    @RequestMapping(value="/quick21")
    @ResponseBody
    public void save21(@CookieValue(value = "JSESSIONID") String jsessionId)  {
        System.out.println(jsessionId);
    }

    @RequestMapping(value="/quick20")
    @ResponseBody
    public void save20(@RequestHeader(value = "User-Agent",required = false) String user_agent) {
        System.out.println(user_agent);
    }


    @RequestMapping(value="/quick19")
    @ResponseBody
    public void save19(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
    }


    @RequestMapping(value="/quick18")
    @ResponseBody
    public void save18(Date date){
        System.out.println(date);
    }

    @RequestMapping(value="/quick17/{name}")
    @ResponseBody
    public void save17(@PathVariable(value="name") String username)  {
        System.out.println(username);
    }

    @RequestMapping(value="/quick16")
    @ResponseBody                          //没有设置@RequestParam注解,前端传参时可以不用传这个参数
    public void save16(/*@RequestParam(value="name",required = false,defaultValue = "itcast"*/ String username) {
        System.out.println(username);
    }

    @RequestMapping(value="/quick15")  //json格式请求不需要包装类,直接用集合对象接受
    @ResponseBody
    public void save15(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }

    @RequestMapping(value="/quick14") //GET请求或POST请求x-wwww-form-urlencoded编码格式 封装集合类型需要使用包装类
    @ResponseBody
    public void save14(VO vo) throws IOException {
        System.out.println(vo);
    }

    @RequestMapping(value="/quick13",method = RequestMethod.GET) //需要开启annotation-driven， 改成post请求或json 都无法进行封装 ,
    @ResponseBody
    public void save13(String[] strs){  //封装非json数据的时候，可以不用开启<mvc:annotation-driven/>
        System.out.println(Arrays.toString(strs));
    }

    @RequestMapping(value="/quick12")
    @ResponseBody
    public User save12(User user) throws IOException {  //GET请求或POST请求x-wwww-form-urlencoded编码格式(表单提交)都能封装上
        System.out.println(user);
        return user;
    }

    @RequestMapping(value="/quick122")
    @ResponseBody                     //配置局部的转换用这个注解,转换器是全局的转换
    public Date save122(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        System.out.println(date);
        return date;
    }

    @RequestMapping(value="/quick120")
    public String save120(){

        return "redirect: quick12";  //重定向到另一个controller
    }

    @RequestMapping(value="/quick11")
    @ResponseBody
    public void save11(String username,int age) throws IOException {
        System.out.println(username);
        System.out.println(age);
    }

    @RequestMapping(value="/quick10")
    @ResponseBody
    //期望SpringMVC自动将User转换成json格式的字符串
    public User save10() throws IOException {
        User user = new User();
        user.setUsername("lisi2");
        user.setAge(32);
        return user;
    }

    @RequestMapping(value="/quick9")
    @ResponseBody
    public String save9() throws IOException {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json的转换工具将对象转换成json格式字符串在返回
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        return json;
    }

    @RequestMapping(value="/quick8")
    @ResponseBody
    public String save8() throws IOException {
        return "{\"username\":\"zhangsan\",\"age\":18}";
    }

    @RequestMapping(value="/quick7")
    @ResponseBody //告知SpringMVC框架 不进行视图跳转 直接进行数据响应
    public String save7() throws IOException {
        return "hello itheima";
    }

    @RequestMapping(value="/quick6")  //没有限制method的时候所有请求方式都匹配
    public void save6(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello itcast");
    }


    @RequestMapping(value="/quick5",method = RequestMethod.GET /*method = RequestMethod.POST*/)
    public String save5(@RequestParam Map<String,String> map){  //加@RequestParam注解就可以直接用map封装表单请求的或get请求的参数了
        System.out.println(map);
        return "success";
    }



    //前端传入json数据，request Headers: Content-Type:application/json
    /**
     * body:
     * {
     * 	"username":"zz",
     * 	"pwd":1232
     * }
     * @param map
     * @return
     */
    @RequestMapping(value="/quick4",method = RequestMethod.POST)
    public String save4(@RequestBody Map<String,String> map){  //要使json和实体类自动转换要开启 <mvc:annotation-driven/>
        System.out.println(map);//{username=zz, pwd=1232}
        return "success";
    }


    @RequestMapping(value="/quick3",method = RequestMethod.GET)
        public String save3(Model model){ //ModelMap modelMap
       model.addAttribute("username","haha");  //request.setAttribute("username","酷丁鱼");
        return "success";
    }

    @RequestMapping(value="/quick2",method = RequestMethod.GET)
    public ModelAndView save2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username","zz");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    // 请求地址  http://localhost:8080/user/quick
    @RequestMapping(value="/quick",method = RequestMethod.GET,params = {"username"})
    public String save(HttpServletRequest request){
        System.out.println("UserController save running....");
        return "redirect: "+ request.getContextPath()+ "/jsp/success.jsp"; //跳转必须加虚拟目录
    }

    // 请求地址  http://localhost:8080/user/quick
    @RequestMapping(value="/quick0",method = RequestMethod.GET,params = {"username"})
    public String save(){
        System.out.println("UserController save running....");
        return "success";
    }


}
