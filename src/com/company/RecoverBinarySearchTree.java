package com.company;

public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        RecoverBinarySearchTree s = new RecoverBinarySearchTree();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        one.left = two;
        one.right = three;
        InOrder inOrderPrinter = new InOrder();
        System.out.println(inOrderPrinter.inOrder(one));
        /*
              1
            /   \
            2    3
        * **/
        TreeNode res = s.recover(one);
        System.out.println(inOrderPrinter.inOrder(res));
        /*
              2
            /   \
            1    3
        * **/

        TreeNode four = new TreeNode(4);
        TreeNode nine = new TreeNode(9);
        TreeNode eight = new TreeNode(8);
        four.left = nine;
        four.right = eight;
        TreeNode six = new TreeNode(6);
        TreeNode two2 = new TreeNode(2);
        eight.left = six;
        eight.right = two2;
        System.out.println(inOrderPrinter.inOrder(four));
        /*
              4
            /   \
            9    8          < = >        (9   4)   8   (6   2)
                /  \                    first             second
                6   2
        **/
        RecoverBinarySearchTree s1 = new RecoverBinarySearchTree();
        res = s1.recover(four);
        System.out.println(inOrderPrinter.inOrder(res));
        /*
              4
            /   \
            2    8
                /  \
                6   9
        **/
    }

    // The inorder traversal of a BST produces a sorted array. So a simple method is to store inorder traversal of the input tree in an auxiliary array.
    // Sort the auxiliary array. Finally, insert the auxiliary array elements back to the BST, keeping the structure of the BST same.
    // The time complexity of this method is O(nLogn) and the auxiliary space needed is O(n).
    // We can solve this in O(n) time and with a single traversal of the given BST. Since inorder traversal of BST is always a sorted array,
    // the problem can be reduced to a problem where two elements of a sorted array are swapped. There are two cases that we need to handle:
    // 1. The swapped nodes are not adjacent in the inorder traversal of the BST.
    // 2. The swapped nodes are adjacent in the inorder traversal of BST.
    // Using inorder traversal to find two nodes that have val < prev.val
    // Time: O(n)
    // Space: O(h)
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;
    public TreeNode recover(TreeNode root) {
        inOrder(root);
        int tmp = first.key;
        first.key = second.key;
        second.key = tmp;
        return root;
    }

    private void inOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        //search left tree
        inOrder(root.left);
        //in inorder traversal of BST, prev should always have smaller value than current value
        if(prev != null && prev.key >= root.key){
            //incorrect first node is always found as prev node
            if (first == null) {
                first = prev;
            }
            //incorrect second node is always found as curr(root) node
            second = root;
        }
        //update prev node
        prev = root;
        //search right tree
        inOrder(root.right);
    }
}
