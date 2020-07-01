package com.dripping.service.elastic;

import org.elasticsearch.client.Client;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.ESActionFactory;
import org.nlpcn.es4sql.query.QueryAction;

import java.sql.SQLFeatureNotSupportedException;

/**
 * 描述：es dao
 */
public class SearchDao {

    private Client client = null;


    public SearchDao(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    /**
     * Prepare action And transform sql
     * into ES ActionRequest
     * @param sql SQL query to execute.
     * @return ES request
     * @throws SqlParseException
     */
    public QueryAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException {
        return ESActionFactory.create(client, sql);
    }
}
