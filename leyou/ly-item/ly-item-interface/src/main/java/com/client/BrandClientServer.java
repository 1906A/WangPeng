package com.client;

import com.pojo.Brand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@RequestMapping("brand")
public interface BrandClientServer {
    @RequestMapping("findBrandById")
    public Brand findBrandById(@RequestParam("id") Long id);
}
