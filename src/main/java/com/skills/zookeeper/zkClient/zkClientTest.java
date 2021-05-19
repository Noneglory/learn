package com.skills.zookeeper.zkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * describe:
 *
 * @author leijiang
 * @date 2021/05/19
 */
public class zkClientTest {
    private static final String  CLUSTER="192.168.45.130:2181";
    private static final String PATH = "/leijiang";
    public static void main(String args[]){
        // 创建 zkClient
        ZkClient zkClient = new ZkClient(CLUSTER);
        //创建持久节点
        //zkClient.create(PATH,"hello world", CreateMode.PERSISTENT);
        //此节点要没有子节点，否则会报错
        //zkClient.delete(PATH);
        zkClient.subscribeDataChanges(PATH, new IZkDataListener() {
            /**
             * 当节点内容被修改时触发
             * @param path 监听的节点
             * @param o 修改后的值。
             * @throws Exception
             */
            @Override
            public void handleDataChange(String path, Object o) throws Exception {
                System.out.println("路径"+path+"修改后的值"+o);
            }

            /**
             * 当节点内容被删除，也就是被设置成null的时候触发
             * @param path
             * @throws Exception
             */
            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("路径"+path+"值被删除");
            }
        });

        zkClient.writeData(PATH,"favorite leijiang");
        Object readData = zkClient.readData(PATH);
        System.out.println("修改后："+readData);

    }
}
