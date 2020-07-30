package jp.search;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;


public class SolrTest {
    public static void main(String[] args) throws IOException, SolrServerException {
//        String urlString = args[0];

    	 File fileFolder = new File("D:/common/");

         // 検索フォルダ存在しない場合
         if (fileFolder == null || !fileFolder.exists()) {
             //Constants.LOG.fatal("CSVファイルを直接変換対象パスが存在しません。");
             System.out.println("対象パスが存在しません。");
             System.exit(1);
         }

        File flist[] = fileFolder.listFiles();
        System.out.println("START");
        for (File f : flist) {
        	insertToCore(f.toString());
        }
//        deleteFromCore("111");
        System.out.println("END");
    }

    public static void insertToCore(String strPath) throws SolrServerException, IOException{
//        HttpSolrClient client = new HttpSolrClient("http://192.168.1.86:8983/solr/common", null, null, false);

        SolrClient solrClient=new HttpSolrClient.Builder("http://192.168.1.98:8983/solr/common").withConnectionTimeout(10000)
				.withSocketTimeout(6000).build();

        SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.1.98:8983/solr/common").build();

        // EXCEL取得
        strPath = "D:/common/android_hmi.xlsx";
        XSSFWorkbook xwb = new XSSFWorkbook(strPath);
        // 读取第一章表格内容
        XSSFSheet sheet = xwb.getSheetAt(0);

        XSSFRow row;
        String cell;

        String[] insertInfo = {"create", "create_user", "project_name","lan_type", "version","fun_head", "fun_name", "fun_describe", "fun_request_parm",
        		"fun_request_detail", "fun_response_parm", "fun_response_detail", "fun_exceptions", "core"};

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

        TimeZone UTC = TimeZone.getTimeZone("UTC");
        SimpleDateFormat tmp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA);

        String time = format.format(new Date());
        Collection<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
        	if (i > 0) {
        		row = sheet.getRow(i);

        		SolrInputDocument input = new SolrInputDocument();
        		boolean dateFlg = false;

    			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
    				dateFlg = true;
    				cell = row.getCell(j).toString();
    				if (j > insertInfo.length - 1) {
    					break;
    				}
    				input.addField(insertInfo[j], cell);
    			}

        		if (dateFlg) {
        			System.out.println("-----------"+"添加完成"+"-----------");
        			documents.add(input);
        			solrServer.add(documents);
        			solrServer.commit();
        		} else {
        			break;
        		}

        	}
        }
    }

    public static void deleteFromCore(String strPath) throws SolrServerException, IOException{
//      HttpSolrClient client = new HttpSolrClient("http://192.168.1.86:8983/solr/common", null, null, false);

      SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.1.98:8983/solr/commonApi").build();


      solrServer.deleteById("2");

      //提交修改

      solrServer.commit();

    }

    public void deleteDocumentByQuery() throws Exception {

        //创建连接

        SolrClient solrServer = new HttpSolrClient.Builder("http://192.168.1.98:8983/solr/commonApi").build();

        //根据查询条件删除文档

        solrServer.deleteByQuery("*:*");

        //提交修改

        solrServer.commit();

   }


  //查询索引



       public void queryIndex() throws Exception {

            //创建连接

            SolrClient solrServer = new HttpSolrClient.Builder("http://localhost:8983/solr/my_db").build();

            //创建一个query对象

            SolrQuery query = new SolrQuery();

            //设置查询条件

            query.setQuery("*:*");

            //执行查询

            QueryResponse queryResponse = solrServer.query(query);

            //取查询结果

            SolrDocumentList solrDocumentList = queryResponse.getResults();

            //共查询到商品数量

            System.out.println("共查询到商品数量:" + solrDocumentList.getNumFound());

            //遍历查询的结果

            for (SolrDocument solrDocument : solrDocumentList) {

                 System.out.println(solrDocument.get("id"));

                 System.out.println(solrDocument.get("product_name"));

                 System.out.println(solrDocument.get("product_price"));

                 System.out.println(solrDocument.get("product_catalog_name"));

                 System.out.println(solrDocument.get("product_picture"));



            }

       }

     //复杂查询索引


       public void queryIndex2() throws Exception {

            //创建连接

            SolrClient solrServer = new HttpSolrClient.Builder("http://localhost:8983/solr/my_db").build();

            //创建一个query对象

            SolrQuery query = new SolrQuery();

            //设置查询条件

            query.setQuery("钻石");

            //过滤条件

            query.setFilterQueries("product_catalog_name:幽默杂货");

            //排序条件

            query.setSort("product_price", ORDER.asc);

            //分页处理

            query.setStart(0);

            query.setRows(10);

            //结果中域的列表

            query.setFields("id","product_name","product_price","product_catalog_name","product_picture");

            //设置默认搜索域

            query.set("df", "product_keywords");

            //高亮显示

            query.setHighlight(true);

            //高亮显示的域

            query.addHighlightField("product_name");

            //高亮显示的前缀

            query.setHighlightSimplePre("<em>");

            //高亮显示的后缀

            query.setHighlightSimplePost("</em>");

            //执行查询

            QueryResponse queryResponse = solrServer.query(query);

            //取查询结果

            SolrDocumentList solrDocumentList = queryResponse.getResults();

            //共查询到商品数量

            System.out.println("共查询到商品数量:" + solrDocumentList.getNumFound());

            //遍历查询的结果

            for (SolrDocument solrDocument : solrDocumentList) {

                 System.out.println(solrDocument.get("id"));

                 //取高亮显示

                 String productName = "";

                 Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

                 List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");

                 //判断是否有高亮内容

                 if (null != list) {

                      productName = list.get(0);

                 } else {

                      productName = (String) solrDocument.get("product_name");

                 }



                 System.out.println(productName);

                 System.out.println(solrDocument.get("product_price"));

                 System.out.println(solrDocument.get("product_catalog_name"));

                 System.out.println(solrDocument.get("product_picture"));



            }

       }
}
