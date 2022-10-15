/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.cloudinary.service;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class CloudinaryService {
    //datos necesarios para conectarse con la api de cloudinary
    Cloudinary cloudinary;
    
    private Map<String, String> valueMap = new HashMap<>();
    
    public CloudinaryService(){
        valueMap.put("cloud_name", "dkxzdyjpv");
        valueMap.put("api_key", "311118111537943");
        valueMap.put("api_secret", "R9Ff-UMAkccabgXNJi8JtyFWKOU");
        
        cloudinary = new Cloudinary(valueMap);
    }
    
}
