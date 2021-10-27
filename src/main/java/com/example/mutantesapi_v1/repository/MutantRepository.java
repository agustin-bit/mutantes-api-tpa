package com.example.mutantesapi_v1.repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class MutantRepository {

    public MutantRepository() {
        pool = getPool();
    }

    private static JedisPool pool;

    public String searchHistory(String key) {
        try (Jedis jedis = pool.getResource()){
            String result = jedis.get(key);
            return result;
        }
    }

    public void saveResult (String key, String value){
        try (Jedis jedis = pool.getResource()){
            jedis.set(key, value);
            if (value == "1") {
                jedis.incr("count_human_dna");
            } else {
                jedis.incr("count_mutant_dna");
            }
        }
    }

    public String getValue (String key) {
        try (Jedis jedis = pool.getResource()){
            return jedis.get(key);
        }
    }

    private static JedisPool getPool() {
        try {
            TrustManager bogusTrustManager = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{bogusTrustManager}, new java.security.SecureRandom());

            HostnameVerifier bogusHostnameVerifier = (hostname, session) -> true;

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(20);
            poolConfig.setMaxIdle(5);
            poolConfig.setMinIdle(1);
            poolConfig.setTestOnBorrow(true);
            poolConfig.setTestOnReturn(true);
            poolConfig.setTestWhileIdle(true);

            return new JedisPool(poolConfig,
                    URI.create("rediss://:paea4fe3be1def1518808dddbe450d25a2807e12ebae716c237f9a39bc65eb1ec@ec2-3-217-107-7.compute-1.amazonaws.com:20710"),
                    sslContext.getSocketFactory(),
                    sslContext.getDefaultSSLParameters(),
                    bogusHostnameVerifier);

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Cannot obtain Redis connection!", e);
        }
    }
}
