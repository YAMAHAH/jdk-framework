package com.example.demo.service.rbac;

import java.util.*;
import java.util.stream.Collectors;

public class AuthorizationService {

    //上下级关系处理
    //岗位代理处理
    //自定义规则扩展权限管理包含功能权限和数据权限
    // 预设过滤规则当作一种功能权限来控制
    //可以针对岗位 员工 部门定制规则控制模板的功能权限和数据权限
    //权限分字段 功能 过滤器 组四种类型
    //在计算功能权限时考虑全局过滤器

    // 部门1:M岗位 部门1:M角色 部门1:M权限 <子级继承父级部门权限>
    // 岗位1:1部门 岗位1:M员工 岗位1:M角色  岗位1:M权限
    // 员工1:1用户 员工1:M岗位 员工1:M角色 员工1:M权限
    // 角色1:M权限 角色1:M岗位 角色1:M员工
    // 权限1:M角色 权限1:M岗位 权限1:M员工
    // 用户1:1员工
    // 规则1:

   String[] getPermissionsByRole(String roleName){
     return null;
    }

    String[] getPermissionsByUser(String userName){
        List<Map<String,Object>> lists = new ArrayList<>();
        Map<String, Object> merged = lists.stream()
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map<String, Object> hashMap = new HashMap<>();
        lists.forEach(map -> hashMap.putAll(map));
        return getDirectPermissionsByUser(userName);
    }

    String[] getDirectPermissionsByUser(String userName){
       return null;
    }

    String[] getInheritedPermissionsByUser(String userName){
        return null;
    }

    String[] getChildrenList(){
       return null;
    }

    void getChildrenRecursive(String name, String[] childrenList, String[] result) {

    }

//    protected function detectLoop($parent, $child)
////    {
////        if ($child->name === $parent->name) {
////            return true;
////        }
////        foreach ($this->getChildren($child->name) as $grandchild) {
////        if ($this->detectLoop($parent, $grandchild)) {
////            return true;
////        }
////    }
////
////        return false;
////    }


//    public function getChildren($name)
//    {
//        $query = (new Query())
//            ->select(['name', 'type', 'description', 'rule_name', 'data', 'created_at', 'updated_at'])
//            ->from([$this->itemTable, $this->itemChildTable])
//            ->where(['parent' => $name, 'name' => new Expression('[[child]]')]);
//
//        $children = [];
//        foreach ($query->all($this->db) as $row) {
//        $children[$row['name']] = $this->populateItem($row);
//    }
//
//        return $children;
//    }

}
