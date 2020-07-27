package jp.search.controller;
import jp.search.pojo.User;
import jp.search.service.LoginService;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @Autowired
    private SolrClient solrClient;

    /**
     * 返回登录页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {



        return "login";
    }

//    @RequestMapping("/getByIdFromSolr/{id}")
//    public  void getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {
//
//        SolrDocument solrDocument = solrClient.getById(id);
//        System.out.println("byId=================="+solrDocument);
//        Collection<String> fieldNames = solrDocument.getFieldNames();
//        Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();
//
//        List<SolrDocument> childDocuments = solrDocument.getChildDocuments();
//
//
//        System.out.println("fieldNames=================="+fieldNames);
//        System.out.println("fieldValueMap=================="+fieldValueMap);
//        System.out.println("childDocuments=================="+childDocuments);
//    }

    /**
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public User login(String userName, String passWord) {
        String name = userName;
        String passwrod = passWord;
        User user = loginService.login();
        return user;
    }

    @PostMapping(value = "/home")
    @ResponseBody
    public List<User> home(){
        List<User> userList =loginService.getUser();
        return userList;
    }

    @RequestMapping(value = "/datalist")
    @ResponseBody
    public ModelAndView datalist() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home2");
        List<User> userList =loginService.getUser();
        modelAndView.addObject("user", userList);
        return modelAndView;
    }
}
