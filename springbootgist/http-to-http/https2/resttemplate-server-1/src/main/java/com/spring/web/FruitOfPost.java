package com.spring.web;

import com.spring.model.Fruit;
import com.spring.service.FruitService;
import com.spring.web.postUse.PostBuild;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/fruit")
public class FruitOfPost {
    @Autowired
    private FruitService fruitService;
    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Fruit postFruit( @Valid @RequestBody PostBuild coffer){
        return fruitService.postFruit(coffer.getName(),coffer.getPrice());
    }

    @PostMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Fruit> postFruitByTxt(@RequestParam("file")MultipartFile file){
        List<Fruit> fruitList =new ArrayList<>();
        if(!file.isEmpty()){
            BufferedReader bufferedReader =null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                String str;
                while((str = bufferedReader.readLine())!=null){
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null && arr.length == 2) {
                        fruitList.add(fruitService.postFruit(arr[0],
                                Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(arr[1]))));
                    }
                }
            }catch (IOException e){
                log.error("error:",e);
            }finally {
                IOUtils.closeQuietly(bufferedReader);
            }
        }
        return fruitList;
    }

}
