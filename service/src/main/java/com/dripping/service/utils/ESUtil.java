package com.dripping.service.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 描述：ES工具类
 *
 * @Author: mabiao
 * @Date: 2020/7/1 15:22
 */
public class ESUtil {

    private static String esHosts;

    private static String clusterName;

    private static TransportClient client = null;

    public ESUtil(String clusterName, String esHost) throws Exception{
        this.clusterName = clusterName;
        this.esHosts = esHost;
        getClient();
    }

    /**
     * 获取默认客户端
     * @return
     */
    public TransportClient getClient() throws Exception{
        if(null == client){
            String hostNames[] = this.esHosts.split(",");
            client = getClient(clusterName,hostNames);
        }

        return client;
    }

    /**
     * 创建客户端
     * @param clusterName
     * @param hostNames
     * @return
     */
    public static TransportClient getClient(String clusterName, String... hostNames) throws Exception{
        try {
            //1.设置集群名称
            Settings settings = Settings.builder()
                    .put("cluster.name",clusterName) //设置集群的名字,默认是elasticsearch
                    .put("client.transport.sniff", true)//自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                    .build();

            //2.创建client
            client = new PreBuiltTransportClient(settings);
            if (null != hostNames) {
                for (String hostName : hostNames) {
                    String[] hostPort = hostName.trim().split(":");
                    String host = hostPort[0].trim();
                    int port = hostPort.length == 2 ? Integer.parseInt(hostPort[1].trim()) : 9300;
                    client.addTransportAddress(new TransportAddress(InetAddress.getByName(host),port));
                }
            }
        } catch (UnknownHostException e) {
            throw e;
        }
        return client;
    }
}
