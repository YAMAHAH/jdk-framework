package com.example.demo.utils;

import com.example.demo.queryBuilder.TreeNode;
import org.springframework.stereotype.Component;

@SuppressWarnings("unchecked")
@Component
public class TreeUtil {

    private Boolean getChildNode = false;
    private Integer nodeOrd = 0;

    private void visitRootNode(TreeNode exprNode, TreeNode parentNode) {
        nodeLogicHandler(exprNode, parentNode);
        if (exprNode.getIsVirtualNode()) {
            this.visitVirtualNode(exprNode, parentNode);
        } else if (exprNode.getChilds().size() == 0) {
            this.visitLeafNode(exprNode, parentNode);
        } else {
            this.visitNormalNode(exprNode, parentNode);
        }
    }

    private void nodeLogicHandler(TreeNode exprNode, TreeNode parentNode) {
        exprNode.setParent(parentNode);
        exprNode.setOrd(++this.nodeOrd);
        if (null == parentNode) {
            exprNode.setLevel(0);
            exprNode.setSingleTotal(1.0);
            exprNode.setTotal(1.0);
            exprNode.setMrpTotal(exprNode.getTotal() * exprNode.getNeedTotal());
            exprNode.setCost(exprNode.getNeedTotal() * exprNode.getPrice());
        } else {
            exprNode.setLevel(parentNode.getLevel() + 1);
            exprNode.setTotal(exprNode.getSingleTotal() * parentNode.getTotal());
            exprNode.setMrpTotal(parentNode.getMrpTotal() * exprNode.getTotal());
            exprNode.setCost(exprNode.getMrpTotal() * exprNode.getPrice());
        }
    }

    private void visitLeafNode(TreeNode exprNode, TreeNode parentNode) {
       // result.add(exprNode);

        TreeNode nd = exprNode.getParent();
        while (nd != null) {
            nd.setCost(nd.getCost() + exprNode.getCost());
            nd = nd.getParent();
        }
    }

    private void visitVirtualNode(TreeNode exprNode, TreeNode parentNode) {
//        if (!getChildNode) {
//            result.add(exprNode);
//        }
        this.visitChildNode(exprNode, parentNode);
    }

    private void visitNormalNode(TreeNode exprNode, TreeNode parentNode) {
       // result.add(exprNode);
        if (!getChildNode) {
            this.visitChildNode(exprNode, parentNode);
        }
    }

    private void visitChildNode(TreeNode exprNode, TreeNode parentNode) {
        for (TreeNode node : exprNode.getChilds()) {
            visitRootNode(node, exprNode);
        }
    }

    public TreeNode visitTree(TreeNode root, boolean getChildNode) {
        if (null == root) return null;
        this.getChildNode = getChildNode;
        this.nodeOrd = 0;
        visitRootNode(root, null);
        return root;
    }
}
