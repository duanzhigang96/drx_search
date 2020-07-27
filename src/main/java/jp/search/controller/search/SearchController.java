package jp.search.controller.search;

import jp.search.pojo.SearchBean;
import jp.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    private java.lang.Object Object;

    @RequestMapping("/getByIdFromSolr/{id}")
    @ResponseBody
    public SearchBean getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {
        SearchBean searchBean = searchService.searchByName("6abbbe57-5e93-41f1-8224-2d248c36daf0");
        return searchBean;
    }
}
