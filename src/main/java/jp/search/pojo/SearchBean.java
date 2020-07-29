package jp.search.pojo;


import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;

@SolrDocument(solrCoreName="common")
public class SearchBean implements Serializable {

    //创建时间
    @Field("creat")
    String create;

    //创建人
    @Field("create_user")
    String create_user;

    //项目名
    @Field("project_name")
    String name;

    //语言类型
    @Field("lan_type")
    String age;

    //版本号
    @Field("version")
    String version;

    //方法标题，关键字，分类
    @Field("title")
    String title;

    //方法名
    @Field("fun_name")
    String fun_name;

    //方法描述
    @Field("fun_describe")
    String fun_describe;

    @Field("fun_request_parm")
    String fun_request_parm;

    @Field("fun_request_detail")
    String fun_request_detail;

    @Field("fun_response_parm")
    String fun_response_parm;

    @Field("fun_response_detail")
    String fun_response_detail;

    @Field("fun_exceptions")
    String fun_exceptions;

    @Field("core")
    String core;

    @Field("id")
    String id;

    @Field("_version_")
    String _version_;

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFun_name() {
        return fun_name;
    }

    public void setFun_name(String fun_name) {
        this.fun_name = fun_name;
    }

    public String getFun_describe() {
        return fun_describe;
    }

    public void setFun_describe(String fun_describe) {
        this.fun_describe = fun_describe;
    }

    public String getFun_request_parm() {
        return fun_request_parm;
    }

    public void setFun_request_parm(String fun_request_parm) {
        this.fun_request_parm = fun_request_parm;
    }

    public String getFun_request_detail() {
        return fun_request_detail;
    }

    public void setFun_request_detail(String fun_request_detail) {
        this.fun_request_detail = fun_request_detail;
    }

    public String getFun_response_parm() {
        return fun_response_parm;
    }

    public void setFun_response_parm(String fun_response_parm) {
        this.fun_response_parm = fun_response_parm;
    }

    public String getFun_response_detail() {
        return fun_response_detail;
    }

    public void setFun_response_detail(String fun_response_detail) {
        this.fun_response_detail = fun_response_detail;
    }

    public String getFun_exceptions() {
        return fun_exceptions;
    }

    public void setFun_exceptions(String fun_exceptions) {
        this.fun_exceptions = fun_exceptions;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_version_() {
        return _version_;
    }

    public void set_version_(String _version_) {
        this._version_ = _version_;
    }
}
