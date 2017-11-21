package lucene.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Grady on 2017/9/7.
 */
public class SolrPratice {
    public static void main(String[] args) throws Exception{
//        queryIndexByKey("中国");
        importIndex();
    }



    static void importIndex()throws Exception{
//        String url = "http://10.1.1.186:8080/solr/testCore";
        String url = "http://10.1.1.186:8983/solr/testCore";
        SolrClient solrClient = new HttpSolrClient(url);
//        SolrInputDocument document = new SolrInputDocument();
//        document.addField("id","test");
//        document.addField("name","中国人");

//        solrClient.add(document);
        Collection<SolrInputDocument> list = new ArrayList<>();
        for(int i = 1; i<=30;i++){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id","test"+i);
            document.addField("name","我是中国人"+i);
            list.add(document);
        }
        solrClient.add(list);
        solrClient.commit();
    }

    static void deleteIndex()throws Exception{

    }

    static void updateIndex()throws Exception{
        String url = "http://10.1.1.186:8983/testCore";
        SolrClient solrClient = new HttpSolrClient(url);
        solrClient.deleteById("");
        solrClient.deleteByQuery("");
        solrClient.commit();
    }

    static void queryIndexByKey(String key)throws Exception{
        String url = "http://10.1.1.186:8983/testCore";
        SolrClient solrClient = new HttpSolrClient(url);
//        SolrParams params = SolrParams
//        solrClient.query("ddd");
        SolrQuery query = new SolrQuery();

        query.setQuery(key);
        //分页
        query.setStart(5);
        query.setRows(20);

        //默认 域
        query.set("df","name");

        //设置高亮
        query.setHighlight(true);
        query.addHighlightField("name");//高亮域
        query.setHighlightSimplePre("<");//前缀
        query.setHighlightSimplePost(">");//后缀

        QueryResponse response = solrClient.query(query);

        SolrDocumentList list = response.getResults();
        System.out.println("start = " + list.getStart());
        System.out.println("count: "+list.getNumFound());
        list.forEach(v->{
            System.out.println(v.get("id"));
            System.out.println(v.get("name"));
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            System.out.println("highlighting: "+highlighting.get(v.get("id")).get("name").get(0));
            System.out.println("-----------------------------");
        });

    }
}
