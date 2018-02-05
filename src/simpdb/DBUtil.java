/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author alexa
 */
public class DBUtil {
    
    String file = "stuff.db";
    long pos = 0;    
    HashMap<String, String> dbMap = new HashMap();
    
    public DBUtil(){
    //dbMap = extractDB();
}
    
    public void insertDB(String key, String value) throws IOException{
        String rocketShipWithLasers = key +"~" +value;
        byte[] stuff = rocketShipWithLasers.getBytes();
        dbMap.put(key, pos+"|"+stuff.length);
        pos += stuff.length;
        
    try{
        FileOutputStream out = new FileOutputStream(file, true);       
        ObjectOutputStream os = new ObjectOutputStream(out);
            
            os.write(stuff);
                    
       
        out.close();
    }
    catch(FileNotFoundException e){
        e.printStackTrace();           
    }
    }
    
    public HashMap extractDB(){
        HashMap<String, String> mp = new HashMap();
        
        byte[] b = new byte[0];
                
        try {
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(in);
            
            mp = (HashMap<String, String>) oi.readObject();
            for (int i = 0; i < mp.size()+1; i++) {
                System.out.println(mp.get(i));
            }
            System.out.println(mp);
            return mp;
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return mp;
    }
    
    public String searchDB(String key){
        return dbMap.get(key);
    }
}
