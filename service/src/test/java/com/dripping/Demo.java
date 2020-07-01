package com.dripping;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;
import com.dripping.service.elastic.ElasticSearchSqlService;
import com.dripping.service.elastic.EsResult;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 描述：
 *
 * @Author: mabiao
 * @Date: 2020/7/1 13:50
 * @Version 1.0
 */
public class Demo {

//    @Test
//    public void testJDBC() throws Exception {
//        Properties properties = new Properties();
//        properties.put("url", "jdbc:elasticsearch://10.108.3.115:9300/_sql");
//        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
//        Connection connection = dds.getConnection();
//        PreparedStatement ps = connection.prepareStatement("SELECT  * from  'metricbeat-zabbix-2020.06.10'");
//        ResultSet resultSet = ps.executeQuery();
//        List<String> result = new ArrayList<String>();
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("@timestamp"));
//        }
//        ps.close();
//        connection.close();
//        dds.close();
//    }

    @Test
    public void query(){
        ElasticSearchSqlService sqlService = new ElasticSearchSqlService();
        String[] hosts = new String[]{"10.108.3.115:9300"};
        EsResult result = sqlService.search("select * from metricbeat-zabbix-2020.06.10",hosts);
        System.out.println(result.array);
    }
}
