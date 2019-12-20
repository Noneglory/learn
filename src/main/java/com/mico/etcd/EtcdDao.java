package com.mico.etcd;

import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.ClientBuilder;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.PutResponse;
import io.etcd.jetcd.options.GetOption;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
public class EtcdDao {
    private Client client;
    private Charset charSet = Charset.forName("UTF-8");
    public EtcdDao(){
        ClientBuilder builder = Client.builder().executorService(Executors.newCachedThreadPool());
        client = builder.endpoints("http://127.0.0.1:2379").build();
    }
    private ByteSequence getByteSequence(String key){
        return ByteSequence.from(key, charSet);
    }

    /**
     * 设置key，value
     * @param key
     * @param value
     */
    public void setKeyValue(String key,String value) throws ExecutionException, InterruptedException {
        PutResponse putResponse= client.getKVClient().put(getByteSequence(key),getByteSequence(value)).get();
        System.out.println(putResponse.toString());
    }

    /**
     * 根据指定的配置名称获取对应的value
     * @param key 配置项
     */
    public String getEtcdValueByKey(String key) throws Exception {
        List<KeyValue> kvs = client.getKVClient().get(getByteSequence(key)).get().getKvs();
        if(kvs.size()>0){
            String value=kvs.get(0).getValue().toString(charSet);
            return value;
        }else{
            return null;
        }

    }

    /**
     * 新增或者修改指定的配置
     * @param key
     * @param value
     */
    public void putEtcdValueByKey(String key,String value){
        client.getKVClient().put(getByteSequence(key),getByteSequence(value));
    }

    /**
     * 删除某个配置
     * @param key
     */
    public void delEtcdValueByKey(String key){
        client.getKVClient().delete(getByteSequence(key));
    }

    /**
     * 获取某个前缀的所有Key value
     * @param keyPrefix
     * @return
     */
    public Map<String,String> getEtcdValuesByKey(String keyPrefix) throws ExecutionException, InterruptedException {
        GetOption option = GetOption.newBuilder().withPrefix(getByteSequence(keyPrefix)).withLimit(1000L).build();
        List<KeyValue> kvs = client.getKVClient().get(getByteSequence(keyPrefix),option).get().getKvs();
        if(kvs.isEmpty()){
            return Collections.emptyMap();
        }
        return kvs.stream().collect(Collectors.toMap(kv -> kv.getKey().toString(charSet),kv -> kv.getValue().toString(charSet),(oldVal,currVal)->currVal));

    }

    public long countEtcdValuesByKey(String keyPrefix) throws ExecutionException, InterruptedException {
        GetOption option = GetOption.newBuilder().withPrefix(getByteSequence(keyPrefix)).withCountOnly(true).build();
        long count = client.getKVClient().get(getByteSequence(keyPrefix),option).get().getCount();
        return count;
    }

    public Client getClient(){return client;}
    public void setClient(Client client){this.client=client;}

    public static void main(String[] args) {

        EtcdDao etcdDao = new EtcdDao();
        System.out.println(etcdDao);
    }
}
