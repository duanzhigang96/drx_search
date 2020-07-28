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
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    private java.lang.Object Object;

    @RequestMapping("/getByIdFromSolr/{id}")
    @ResponseBody
    public SearchBean getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {
        SearchBean searchBean = searchService.searchById(id);
        return searchBean;
    }

    @RequestMapping("/findWithAll/{item}")
    @ResponseBody
    public List<SearchBean> findWithAll(@PathVariable("item") String item) throws IOException, SolrServerException {
        List<SearchBean> searchBeanList = searchService.searchWithHighlight(1, 3, item);
        return searchBeanList;
    }
}