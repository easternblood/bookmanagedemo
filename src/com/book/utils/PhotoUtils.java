package com.book.utils;

import org.apache.commons.fileupload.FileItem;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

public class PhotoUtils {
    //base64字符串转化成图片
    public static String GenerateImage(String imgStr,String wenjian)
    {
        System.out.print("已经收到了把字节码转化为图片的方法");
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return "error";

        //解析base64码，获取图片格式
        String str [] = imgStr.split(",");
        imgStr = str[1];
        String imgInfo = str[0];
        String imgExt = imgInfo.split("/")[1].split(";")[0];
//        String imgExt="gif";

        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            String imgFileReturn= "http://localhost:8080/image/"+getPhotoNewName(imgExt,wenjian);
            String imgFilePath = "E:\\JavaProject\\bookManage\\web\\image\\"+"\\"+getPhotoNewName(imgExt,wenjian);//新生成的图片
//            String imgFilePath = "E:\\image\\"+"\\"+getPhotoNewName(imgExt,wenjian);//新生成的图片
            System.out.println(imgFilePath);
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFileReturn;
        }
        catch (Exception e)
        {
            return "";
        }
    }
    /**
     *这个函数的功能是获取当前时间点与1970年的间隔秒数
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        System.out.println(timestamp);
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     *
     *2018年10月24日下午8:42:05
     *这个函数的功能是得到新的照片名称
     */
    public static String getPhotoNewName(String imgExt,String wenjian) {
        Date date=new Date();
        int second=getSecondTimestamp(date);
        String fileName=wenjian+String.valueOf(second)+"."+imgExt;
        return fileName;
    }
}
