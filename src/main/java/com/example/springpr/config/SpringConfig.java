package com.example.springpr.config;


import co.elastic.clients.elasticsearch.transform.Settings;
import com.example.springpr.repository.elasticsearch.ProductElasticsearchRepository;
import com.example.springpr.utils.DomainUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@ComponentScan(basePackages = {"com.example.springpr"})
@EnableElasticsearchRepositories(basePackages = {"com.example.springpr.repository.elasticsearch"})
public class SpringConfig {

}