package com.fdj.nicemallbackend.common.utils;

import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.GenericRequest;
import com.fdj.nicemallbackend.system.dto.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Classname OssuploadUtil
 * @Description TODO
 * @Date 19-8-31 下午1:59
 * @Created by xns
 */
public class OssuploadUtil {

    public String updateHead(MultipartFile file) throws Exception {
      /*  if (file == null || file.getSize() <= 0) {
            throw new Exception("file不能为空");
        }
        OSSClientUtil ossClient = new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];*/
      return  null;
    }

    /**
     * 上传图片到指定的文件夹
     *
     * @param file
     * @param filedir
     * @return
     * @throws Exception
     */
    public String updateHead(MultipartFile file, String filedir) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("file不能为空");
        }
       /* OSSClientUtil ossClient = new OSSClientUtil();
        ossClient.changeFiledir(filedir);
        String name = ossClient.uploadImg2Oss(file);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        return split[0];*/

       return null;
    }

    /**
     * 上传多张图片，然后拼接所有的url
     *
     * @param image
     * @return
     */
    public Result uploadReturnUrl(List<String> image) {
        StringBuilder imageStr = new StringBuilder();
        for (int i = 0; i < image.size(); i++) {
            MultipartFile file = Base64tTransformUtil.base64ToMultipart(image.get(i));
            try {
                String url = this.updateHead(file);
                System.out.println("第" + (i + 1) + "张图片路径: " + url);
                if (i + 1 == image.size()) {
                    imageStr.append(url);
                } else {
                    imageStr.append(url + ",");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new Result().fail("图片上传失败!!!");
            }
        }
        return new Result().success(imageStr.toString(), "图片上传成功");
    }


    /**
     * 上传一张图片
     *
     * @param image
     * @return
     */
    public Result oneuploadReturnUrl(String image) {
        String url;
        MultipartFile file = Base64tTransformUtil.base64ToMultipart(image);
        try {
            url = this.updateHead(file);
            System.out.println("图片路径: " + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().fail("图片上传失败!!!");
        }
        return new Result().success(url, "图片上传成功");
    }

    /**
     * 上传一张图片到是定文件将夹
     *
     * @param image
     * @return
     */
    public Result oneuploadReturnUrlToPoint(String image, String filedir) {
        String url;
        MultipartFile file = Base64tTransformUtil.base64ToMultipart(image);
        try {
            url = this.updateHead(file, filedir);
            System.out.println("图片路径: " + url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().fail("图片上传失败!!!");
        }
        return new Result().success(url, "图片上传成功");
    }

    /**
     *
     * @MethodName: deleteFile
     * @Description: 单文件删除
     * @param fileUrl 需要删除的文件url
     * @return boolean 是否删除成功
     */
    public boolean deleteFile(String fileUrl){
       /* OSSClientUtil ossClient = new OSSClientUtil();

        *//**
         * 根据url获取bucketName
         *//*
        String bucketName = OssuploadUtil.getBucketName(fileUrl);
        *//**
         * 根据url获取fileName
         *//*
        String fileName = OssuploadUtil.getFileName(fileUrl);
        if(bucketName==null||fileName==null) {
            return false;
        }
        try {
            GenericRequest request = new DeleteObjectsRequest(bucketName).withKey(fileName);
            ossClient.deleteFile(request);
        } catch (Exception oe) {
            oe.printStackTrace();
            return false;
        }*/
        return true;
    }

    /**
     *
     * @MethodName: getBucketName
     * @Description: 根据url获取bucketName
     * @param fileUrl 文件url
     * @return String bucketName
     */
    public static String getBucketName(String fileUrl){
        String http = "http://";
        String https = "https://";
        int httpIndex = fileUrl.indexOf(http);
        int httpsIndex = fileUrl.indexOf(https);
        int startIndex  = 0;
        if(httpIndex==-1){
            if(httpsIndex==-1){
                return null;
            }else{
                startIndex = httpsIndex+https.length();
            }
        }else{
            startIndex = httpIndex+http.length();
        }
        int endIndex = fileUrl.indexOf(".oss-");
        return fileUrl.substring(startIndex, endIndex);
    }

    /**
     *
     * @MethodName: getFileName
     * @Description: 根据url获取fileName
     * @param fileUrl 文件url
     * @return String fileName
     */
    public static String getFileName(String fileUrl){
        String str = "aliyuncs.com/";
        int beginIndex = fileUrl.indexOf(str);
        if(beginIndex==-1) {
            return null;
        }
        return fileUrl.substring(beginIndex+str.length());
    }
}
