package cn.jianbin.alogthrim.datastructure;

/**
 * @author aaron.zou
 * @date 2022/2/8 7:01 下午
 *
 * 前缀树，字典数 实现
 */
public class PreTree {

    public static class Node {
        /**
         *  这个节点被通过了几次
         */
        private int pass;

        /**
         *  这个节点作为终点 几次
         */
        private int end;

        /**
         *  这个节点通往其它节点的路径
         *
         *  = null，说明没有路
         *  ！= null，说明有路
         */
        private Node[] paths;

        public Node() {
            pass = 0;
            end = 0;
            // 最多可以存储 26 条路径（26个字母）
            paths = new Node[26];
        }
    }

    /**
     *  树只保存根节点
     */
    private Node root;

    public PreTree() {
        root = new Node();
    }


    public void addWord(String word) {
        if (word == null) {
            return;
        }

        char[] str = word.toCharArray();
        Node cur = root;
        cur.pass++;

        for (int i = 0; i < str.length; i++) {
            // 对每个字符遍历； 检查是否当前节点有路径
            int path = str[i] - 'a';
            if (cur.paths[path] == null) {
                cur.paths[path] = new Node();
            }

            cur.paths[path].pass++;
            cur = cur.paths[path];
        }

        cur.end++;
    }

    public int searchWord(String word) {
        if (word == null) {
            return 0;
        }

        char[] str = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < str.length; i++) {
            int path = str[i] - 'a';
            if (cur.paths[path] == null) {
                return 0;
            }
            cur = cur.paths[path];
        }

        return cur.end;
    }

    public void remove(String word) {
        if (searchWord(word) == 0) {
            return;
        }

        char[] str = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < str.length; i++) {
            int path = str[i] - 'a';
            if (--cur.paths[path].pass == 0) {
                // 加入 就加入了 abc，并且删除了 abc
                cur.paths[path] = null;
                return;
            }

            cur = cur.paths[path];
        }
        cur.end--;
    }

    public void print() {
        Node cur = root;

    }
}
