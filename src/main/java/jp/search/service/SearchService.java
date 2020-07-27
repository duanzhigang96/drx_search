package jp.search.service;

import jp.search.reponsiory.SearchRepository;
import jp.search.pojo.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public SearchBean searchByName(String name) {
        return searchRepository.findByName(name);
    }

    public List<SearchBean> searchWithPageable(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return searchRepository.findAllWithPageable(pageRequest).getContent();
    }

    public List<SearchBean> searchWithHighlight(Integer pageNum, Integer pageSize) {
        List<SearchBean> result = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        HighlightPage<SearchBean> highlightPage = searchRepository.findWithHighlight(pageRequest);
        for (HighlightEntry<SearchBean> highlightEntry : highlightPage.getHighlighted()) {
            for (HighlightEntry.Highlight highlight : highlightEntry.getHighlights()) {
                if (highlight.getField().equals("name")) {
                    highlightEntry.getEntity().setName("");
                    String temp = "";
                    for (String sl : highlight.getSnipplets()) {
                        temp += sl;
                    }
                    highlightEntry.getEntity().setName(temp);
                }
            }
            result.add(highlightEntry.getEntity());
        }
        return result;
    }

}
