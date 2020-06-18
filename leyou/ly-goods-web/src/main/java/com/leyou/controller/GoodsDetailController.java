package com.leyou.controller;

import com.leyou.client.*;
import com.leyou.service.GoodService;
import com.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class GoodsDetailController {

    @Autowired
    SpuClient spuClient;
    @Autowired
    SkuClient skuClient;
    @Autowired
    SpecGroupClient specGroupClient;
    @Autowired
    CategoryClient categoryClient;
    @Autowired
    SpecClient specClient;
    @Autowired
    BrandClient brandClient;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    GoodService goodService;

    @RequestMapping("hello")
    public String hello(Model model){
        System.out.println("1111111111111");
        String name="张三";
        model.addAttribute("name",name);
        return "hello";
    }

    @RequestMapping("item/{spuId}.html")
    public String item(@PathVariable("spuId") Long spuId,Model model){

       /* //1:spu
        Spu spu=spuClient.findSpuById(spuId);

        //spuDetail
        SpuDetail spuDetail = spuClient.findSpuDetailBySpuId(spuId);


        //sku
        List<Sku> skuList = skuClient.findSkuBySpuId(spuId);

        //查询规格参数组
        List<SpecGroup> groups = specGroupClient.findSpecGroupList(spu.getCid3());
        System.out.println(groups.toString());

        //三级分类 category id name
        List<Category> categoryList = categoryClient.findCategoryByCids(
                Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));

        //规格参数详情 参数中的特殊熟悉
        List<SpecParam> specParamList = specClient.findParamsByCidAndGeneric(spu.getCid3(), false);
        Map<Long,String> paramMap=new HashMap<>();
        specParamList.forEach(param->{
            paramMap.put(param.getId(),param.getName());
        });


        //查询品牌
        Brand brand = brandClient.findBrandById(spu.getBrandId());
        model.addAttribute("spu",spu);
        model.addAttribute("spuDetail",spuDetail);
        model.addAttribute("skuList",skuList);
        model.addAttribute("groups",groups);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("paramMap",paramMap);
        model.addAttribute("brand",brand);*/

        Map<String, Object> map = goodService.item(spuId);
        model.addAllAttributes(map);
        //写入静态文件
        goodService.creaHtml(spuId);
        return "item";
    }

    /**
     * 通过thyemleaf 实现页面的静态化
     * @param spu
     * @param spuDetail
     * @param skuList
     * @param groups
     * @param categoryList
     * @param paramMap
     * @param paramMap1
     * @param brand
     */
  /*  private void creaHtml(Spu spu, SpuDetail spuDetail, List<Sku> skuList, List<SpecGroup> groups,
                          List<Category> categoryList, Map<Long, String> paramMap, Map<Long, String> paramMap1, Brand brand) {

        PrintWriter writer=null;
        try {
            //创建上下文 thyemleaf
            Context context=new Context();

            //把数据放入到上下文
            context.setVariable("spu",spu);
            context.setVariable("spuDetail",spuDetail);
            context.setVariable("skuList",skuList);
            context.setVariable("groups",groups);
            context.setVariable("categoryList",categoryList);
            context.setVariable("paramMap",paramMap);
            context.setVariable("brand",brand);

            //写入文件，写入流
            File file=new File("D:\\Java\\nginx-1.16.1\\nginx-1.16.1\\html\\"+spu.getId()+".html");
            writer=new PrintWriter(file);

            //执行静态化
            templateEngine.process("item",context,writer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(writer!=null){
                //关闭写入流
                writer.close();
            }
        }
    }*/


}
