package com.duobao.yidianyou.TCP;

/**
 * Created by Administrator on 2017/8/4.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 用TCP进行文件传输
 * 此文件为客户端文件
 * 连接上服务器之后，直接接受文件
 *
 * */

public class SendFileClient {


    private static final String SERVERIP = "192.168.1.190";
    private static final int SERVERPORT = 8888;
    private static final int CLIENTPORT = 54321;


    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            //用来接受传输过来的字符
            byte[] buf = new byte[1024];
            Socket s = new Socket();
            //建立连接
            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            byte info_type=1;
            byte[] user_id=ByteUtil.getBytes(10);
            String file_name_="readme.txt";
            byte[] file_name=ByteUtil.getBytes(file_name_);
            file_name=addLen(file_name, 20);
            byte[] requestary=new byte[user_id.length+file_name.length+1];
            requestary[0]=info_type;
            System.arraycopy(user_id, 0, requestary, 1, user_id.length);
            System.arraycopy(file_name, 0, requestary, 1+user_id.length, file_name.length);
            os.write(requestary);
            int len=-1;
            String stutus = "";
            while ((len = is.read(buf))!=-1) {
                stutus = new String(buf,0,len);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("ok".equals(stutus)) {
                    break;
                }
            }
            if ("ok".equals(stutus)) {
                FileInputStream fIn=new FileInputStream("F:\\MyTest"+file_name_);
                int len_=0;
                while(-1!=fIn.read(buf))
                {
                    os.write(buf,0,len_);
                }
                fIn.close();
            }
            s.shutdownOutput();
            len = is.read(buf);
            if (len!=-1) {
                stutus = new String(buf,0,len);
                if ("fok".equals(stutus)) {
                    int len_=0;
                    FileOutputStream fouts = new FileOutputStream("F:\\MyTest\\shoudao");
                    // 通过fins读取文件，并通过os将文件传输
                    while ((len_=is.read(buf)) !=-1) {
                        fouts.write(buf,0,len_);// 写入文件
                    }
                    fouts.flush();
                    fouts.close();
                }else {
                    //得不到fok返回表示失败 自己处理
                }
            }
            os.close();
            is.close();
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  byte[] addLen(byte[] b,int len){
        if (b.length<len) {
            byte[] retAry=new byte[len];
            for (int i = 0; i < len-b.length; i++) {
                retAry[i]=0;
            }
            System.arraycopy(b, 0, retAry, len-b.length, b.length);
         		/*System.arraycopy(b, 0, retAry, 0, b.length);
         		for (int i = b.length; i<len ; i++) {
         			retAry[i]=0;
         		}*/
            return retAry;
        }
        return b;
    }
}
