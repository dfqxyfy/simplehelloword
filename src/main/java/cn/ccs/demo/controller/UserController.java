package cn.ccs.demo.controller;

import cn.ccs.demo.util.DeleteFileUtil;
import cn.ccs.demo.util.ZipUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {


    @RequestMapping("/upload")
    public String login(@RequestParam("fileName") MultipartFile file){
        Map map = new HashMap();
        String name = file.getOriginalFilename();
        String dirName = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf("."));
        map.put("msg","success");
        if(!name.endsWith("zip")){
            map.put("msg","请输入zip文件");
            return JSONObject.toJSONString(map);
        }
        try {
            InputStream inputStream = file.getInputStream();
            String absFilePath = System.getProperty("user.home")+"/temp/"+file.getOriginalFilename();
            String unzipFilePath = System.getProperty("user.home")+"/temp/"+dirName;
            File fileTemp = new File(absFilePath);
            if(fileTemp.exists()){
                DeleteFileUtil.delete(fileTemp.getAbsolutePath());
            }
            fileTemp.createNewFile();
            OutputStream outputStream = new FileOutputStream(fileTemp);
            int ch;
            while((ch=inputStream.read())!= -1){
                outputStream.write(ch);
            }
            inputStream.close();
            outputStream.close();

            File unzipFile = new File(unzipFilePath);
            if(unzipFile.exists()) {
                DeleteFileUtil.delete(unzipFile.getAbsolutePath());
            }
            ZipUtil.unZip(fileTemp.getAbsolutePath());

            String s = moveFile(unzipFilePath, dirName);

            map.put("copyMsg",s);

        } catch (IOException e) {
            map.put("msg","出现异常:"+e.getMessage());
            e.printStackTrace();
        }

        return JSONObject.toJSONString(map);
    }

    private String moveFile(String unzipFilePath,String dirName){
        try {
            //String basDir = "/sunlands/temp/";
            String basDir = "/sunlands/temp/";
            //String basDir = "/usr/share/nginx/ad/";
            File cpFile = new File(basDir+dirName);
            if(cpFile.exists()) {
                DeleteFileUtil.delete(cpFile.getAbsolutePath());
            }

            String command = "mv -f " + unzipFilePath+"/"+dirName + " " +basDir;
            //String command = "mv -f " + unzipFilePath + " /usr/share/nginx/ad/" + dirName;
            System.out.println("exe command: "+command);
            Runtime.getRuntime().exec(command);
            return "执行成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "执行失败";
    }
}
