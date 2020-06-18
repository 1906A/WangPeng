package com.leyou.service;

import com.leyou.client.*;
import com.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService {
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

    /**
     * 查询商品详情数据
     * @param spuId
     * @return
     */
    public Map<String,Object> item(Long spuId){

        //1:spu
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
       //参数中的特殊熟悉
        Map<Long,String> paramMap=new HashMap<>();
        specParamList.forEach(param->{
            paramMap.put(param.getId(),param.getName());
        });
        //查询品牌
        Brand brand = brandClient.findBrandById(spu.getBrandId());

        Map<String,Object> map=new HashMap<>();
        map.put("spu",spu);
        map.put("spuDetail",spuDetail);
        map.put("skuList",skuList);
        map.put("groups",groups);
        map.put("categoryList",categoryList);
        map.put("paramMap",paramMap);
        map.put("brand",brand);

        return map;
    }

    /**
     * 通过thyemleaf 实现页面的静态化
     * @param spuId
     *
     */
    public void creaHtml(Long spuId) {

        PrintWriter writer=null;
        try {
            //创建上下文 thyemleaf
            Context context=new Context();

            //把数据放入到上下文
            /*context.setVariable("spu",spu);
            context.setVariable("spuDetail",spuDetail);
            context.setVariable("skuList",skuList);
            context.setVariable("groups",groups);
            context.setVariable("categoryList",categoryList);
            context.setVariable("paramMap",paramMap);
            context.setVariable("brand",brand);*/
            context.setVariables(this.item(spuId));

            //写入文件，写入流
            File file=new File("D:\\Java\\nginx-1.16.1\\nginx-1.16.1\\html\\"+spuId+".html");
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
    }

    /**
     * 删除静态页面
     * @param spuId
     */
    public void deleteHtml(Long spuId) {
        File file=new File("D:\\Java\\nginx-1.16.1\\nginx-1.16.1\\html\\"+spuId+".html");
        if(file!=null&&file.exists()){
            file.delete();
        }
    }
}
