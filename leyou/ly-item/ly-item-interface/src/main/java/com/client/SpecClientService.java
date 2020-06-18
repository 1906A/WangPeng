package com.client;

import com.pojo.SpecGroup;
import com.pojo.SpecParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("specParam")
public interface SpecClientService {

    @RequestMapping("paramsByCid")
    public List<SpecParam> findSpecParamByCidAndSearching(@RequestParam("cid") Long cid);

    @RequestMapping("paramsByCidAndGeneric")
    public List<SpecParam> findParamsByCidAndGeneric(@RequestParam("cid") Long cid,@RequestParam("generic") boolean generic);



}
