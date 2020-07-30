package jp.search.controller.search;

import jp.search.Constants;
import jp.search.pojo.SearchBean;
import jp.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/getByIdFromSolr/{id}")
    public ModelAndView getByIdFromSolr(@PathVariable("id") String id, ModelMap map) throws IOException, SolrServerException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search_detail/search_detail");
        SearchBean searchBean = searchService.searchById(id);
        modelAndView.addObject(Constants.SEARCH_BEAN, searchBean);
        return modelAndView;
    }

    @RequestMapping("/findWithAll/{item}")
    @ResponseBody
    public List<SearchBean> findWithAll(@PathVariable("item") String item) throws IOException, SolrServerException {
        List<SearchBean> searchBeanList = searchService.searchWithHighlight(1, 3, item);
        return searchBeanList;
    }
}