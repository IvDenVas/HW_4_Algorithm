
public class ColorTree {
    private Node root;
    public static class Node {
        private int value;
        private Node left;
        private Node right;
        private Color color;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = Color.RED;
        }
    }
    private enum Color {
        RED, BLACK
    }
    private boolean addNode(Node node, int value) {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.left != null) {
                    boolean result = addNode(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node(value);
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = addNode(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new Node(value);
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }
    private void colorSwap(Node node) {
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }
    private Node leftSwap(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }
    private Node rightSwap(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }
    private Node rebalance (Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if(result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)) {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    (result.left.left != null && result.left.left.color == Color.RED)) {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.RED &&
                    (result.right != null && result.right.color == Color.RED)) {
                needRebalance = true;
                colorSwap(result);
            }
        }
        while (needRebalance);
        return result;
    }
    private boolean add(int value) {
        if(root != null) {
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        } else {
            root = new Node(value);
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }
    public void printTree() { // для проверки )))
        Node node = root;
        System.out.printf("%50d",node.value);
        System.out.println(" " + node.color);
        if (node.left != null) {
            System.out.printf("%25d",node.left.value);
            System.out.println(node.left.color);
        } else {
            System.out.println("null");
        }
        if (node.right != null) {
            System.out.printf("%75d",node.right.value);
            System.out.println(node.right.color);
        } else {
            System.out.println("null");
        }
        if (node.left.left != null) {
            System.out.println(node.left.left.value + " " + node.left.left.color);
        } else {
            System.out.println("null");
        }
        if (node.left.right != null) {
            System.out.printf("%35d",node.left.right.value);
            System.out.println(" " + node.left.right.color);
        } else {
            System.out.println("null");
        }
        if (node.right.left != null) {
            System.out.printf("%55d", node.right.left.value);
            System.out.println(" " + node.right.left.color);
        } else {
            System.out.println("null");
        }
        if (node.right.right != null) {
            System.out.printf("%80d", node.right.right.value);
            System.out.println(" " + node.right.right.color);
        } else {
            System.out.println("null");
        }
        if (node.left.left.left != null) {
            System.out.println(node.left.left.left.value);
            System.out.println(node.left.left.left.color);
        } else {
            System.out.println("Null");
        }
    }

    public static void main(String[] args) {
        ColorTree colorTree = new ColorTree();
        colorTree.add(24);
        colorTree.add(5);
        colorTree.add(1);
        colorTree.add(15);
        colorTree.add(3);
        colorTree.add(8);
        colorTree.printTree();

    }
}
