package com.yyx.aidemo1.controller;

import com.yyx.aidemo1.utils.AiUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class AiController {

    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public String PicToWord(@RequestParam("file") MultipartFile file) throws IOException {
        String res = AiUtils.picToWords(file);
        System.out.println(res);
        return res;
    }

    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public String ImgToWord(@RequestParam("file") MultipartFile file) throws IOException {
        String res = AiUtils.imgRecognition(file);
        System.out.println(res);
        return res;
    }

    @RequestMapping(value = "/correction", method = RequestMethod.GET)
    public String NLP_Correction(@RequestParam("text") String text) throws IOException {
        String res = AiUtils.nlp_correction(text);
        System.out.println(res);
        return res;
    }
}
