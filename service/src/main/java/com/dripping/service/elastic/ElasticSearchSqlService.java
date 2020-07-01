package com.dripping.service.elastic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dripping.service.utils.ESUtil;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.plugin.nlpcn.QueryActionElasticExecutor;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.nlpcn.es4sql.jdbc.ObjectResult;
import org.nlpcn.es4sql.jdbc.ObjectResultsExtractor;
import org.nlpcn.es4sql.query.QueryAction;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;

/**
 * 描述：
 *
 * @Author: mabiao
 * @Date: 2020/7/1 14:24
 * @Version 1.0
 */
@Service
public class ElasticSearchSqlService {

    public EsResult search(String sql,String[] hostNames){
        EsResult esResult = new EsResult();
        try {
            //1.解释SQL
            SearchDao searchDao = new SearchDao(ESUtil.getClient("elasticsearch",hostNames));
            QueryAction queryAction = searchDao.explain(sql);
            //2.执行
            Object execution = QueryActionElasticExecutor.executeAnyAction(searchDao.getClient(), queryAction);
            //3.格式化查询结果
            ObjectResult result = (new ObjectResultsExtractor(true, false, false)).extractResults(execution, true);
            List<String> colmns = result.getHeaders();
            List<List<Object>> contents = result.getLines();
            JSONArray array = new JSONArray();
            for (List<Object> content : contents){
                JSONObject object = new JSONObject();
                for (int i=0;i < content.size(); i++){
                    object.put(colmns.get(i),content.get(i));
                }
                array.add(object);
            }
            esResult.setArray(array);
        }catch (Exception e){
            e.printStackTrace();
        }

        return esResult;
    }
}
