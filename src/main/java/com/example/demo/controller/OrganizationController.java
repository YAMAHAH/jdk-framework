package com.example.demo.controller;

import com.example.demo.input.FilterInput;
import com.example.demo.mapper.FilterCycleMapper;
import com.example.demo.mapper.utils.CycleMappingContext;
import com.example.demo.models.FilterModel;
import com.example.demo.models.Organization;
import com.example.demo.models.SaleOrderItem;
import com.example.demo.output.AjaxResponse;
import com.example.demo.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@RestController
@RequestMapping("/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private FilterCycleMapper filterCycleMapper;

    @ResponseBody
    @PostMapping("/addOrg")
    public AjaxResponse AddOrg() {
        organizationService.addParentOrg();
        return AjaxResponse.ok("Add org sucessful",null);
    }

    @ResponseBody
    @PostMapping("/updateOrg")
    @Transactional
    public AjaxResponse updateOrg(@RequestBody Organization org) {
        return AjaxResponse.ok("update org sucessful",organizationService.updateOrg(org));
    }

    @ResponseBody
    @GetMapping("/getOrg")
    public AjaxResponse getOrg(@RequestParam Long id) {
        return AjaxResponse.ok("get org sucessful",organizationService.getOrg(id));
    }

    @ResponseBody
    @GetMapping("/getOrgs")
    public AjaxResponse getOrgs(@RequestParam Long[] ids) {
        return AjaxResponse.ok("get orgs sucessful",organizationService.getOrg(Arrays.asList (ids)));
    }

    @ResponseBody
    @PostMapping("/getOrgsByFilter")
    public AjaxResponse getOrgsByFilter(@RequestBody List<FilterInput> filters) {
        Map<String, Object> propertyMap = new HashMap<String, Object>() {
            {
                put("pId", "parent.id");
                put("pCode", "parent.code");
                put("pName", "parent.name");
            }
        };
        List<FilterModel> filterModels = filterCycleMapper.toFilterModel(filters, new CycleMappingContext(propertyMap));
        List<Organization> orgs = organizationService.getOrgs(filterModels.get(0));
        return AjaxResponse.ok("get orgs from Filter sucessful",orgs);
    }
    @ResponseBody
    @GetMapping("/getOrgParents")
    public AjaxResponse getOrgParents(@RequestParam Long id) {
        return AjaxResponse.ok("get orgs sucessful",organizationService.getOrgParents(id));
    }
}
