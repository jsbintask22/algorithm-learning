package cn.jianbin.algorithm.binarytree;

import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import lombok.experimental.UtilityClass;
import org.apache.commons.jexl3.*;

@UtilityClass
public class MergerTree {
    public static void main(String[] args) {
        JexlEngine jexlEngine = new JexlBuilder().strict(true).debug(true).antish(true)
                .safe(true).create();

        int ret = -1;
        boolean c = true;
        JexlExpression expression = jexlEngine.createExpression("a = 1 + (true && false) || (false || c)");
        MapContext cxt = new MapContext();
        cxt.set("c", c);


        Object evaluate = expression.evaluate(cxt);
        Object e2 = jexlEngine.createExpression("b = [(true || true) && (false && c)]")
                .evaluate(cxt);
        System.out.println(e2);

        System.out.println(evaluate.getClass() + ", = " + evaluate + ", ret = " + ret);
    }

    public BinTreeNode mergeTrees(BinTreeNode root1, BinTreeNode root2) {
        // 合并二叉树
        // 准备三个队列，


        return null;
    }
}
