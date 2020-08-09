package structure.tree.huffmantree.demo;

import java.io.*;
import java.util.*;

/**
 * @author Xuxx3309
 * @Description 使用Huffman编码进行字符串压缩和解压缩
 * @create 2020-07-28 16:38
 */
public class HuffmanCodeDemo {
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) {
        /*String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("压缩前的字符串:" + str);
        System.out.println("压缩前的byte[]:" + Arrays.toString(bytes) + " 长度:" + bytes.length);

        byte[] huffmanCodeZipBytes = huffmanZip(bytes);
        System.out.println("赫夫曼编码压缩后的byte[]:" + Arrays.toString(huffmanCodeZipBytes) + " 长度:" + huffmanCodeZipBytes.length);

        byte[] decodeBytes = decode(huffmanCodes, huffmanCodeZipBytes);
        System.out.println("解压缩后的byte[]:" + Arrays.toString(decodeBytes) + " 长度:" + decodeBytes.length);
        System.out.println("解压缩后的字符串:" + new String(decodeBytes));*/

        /*
        List<Node> nodes = getNodes(bytes);
        //System.out.println(nodes);

        Node rootNode = createHuffmanTree(nodes);
        System.out.println("赫夫曼树：");
        preOrder(rootNode);

//        getHuffmanCodes(rootNode, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getHuffmanCodes(rootNode);
        System.out.println("赫夫曼编码对照表：" + huffmanCodes);

        byte[] huffmanCodeZipBytes = zip(bytes, huffmanCodes);
        System.out.println("赫夫曼编码压缩后的byte[]：" + Arrays.toString(huffmanCodeZipBytes));
        */

        //测试压缩文件
        /*String srcFile = "D:/UserData/Desktop/test/1.jpg";
        String dstFile = "D:/UserData/Desktop/1.jpg";
        zipFile(srcFile, dstFile);*/

        //测试解压缩文件
        String zipFile = "D:/UserData/Desktop/1.jpg";
        String dstFile = "D:/UserData/Desktop/test/2.jpg";
        unZipFile(zipFile, dstFile);
        System.out.println("解压缩完成");
    }

    /**
     * 将一个文件进行压缩
     *
     * @param srcFile 想压缩的文件的全路径
     * @param dstFile 压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[fileInputStream.available()];
            //读取文件
            fileInputStream.read(bytes);
            //直接对源文件压缩    huffmanZip方法处理了huffmanCodes
            byte[] huffmanZipBytes = huffmanZip(bytes);
            //创建文件的输出流，存放压缩文件
            fileOutputStream = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            //把赫夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanZipBytes);
            //以对象流的方式写入赫夫曼编码，是为了以后恢复源文件时使用
            objectOutputStream.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 完成对压缩文件的解压
     *
     * @param zipFile 要解压的文件
     * @param dstFile 存放位置
     */
    public static void unZipFile(String zipFile, String dstFile) {
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(zipFile);
            objectInputStream = new ObjectInputStream(inputStream);
            //读取byte数组  huffmanZipBytes
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            //解码
            byte[] decodes = decode(huffmanCodes, huffmanBytes);
            outputStream = new FileOutputStream(dstFile);
            //写出数据到文件中
            outputStream.write(decodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /*完成数据的解压
    1.将压缩后的byte[]：[-88, -65, -56,...]转换为赫夫曼编码对应的二进制的字符串“10101000101111111100...”
    2.将赫夫曼编码对应的二进制的字符串对照赫夫曼编码对照表转换回原来的字符串 “i like like like java do you like a java” */

    /**
     * 将一个byte转换为一个二进制字符串
     *
     * @param flag
     * @param b    标识是否需要补高位，ture:需要; false:不需要    如果是最后一个byte，无需补高位
     * @return 是该b对应的二进制的字符串，( 注意是按补码返回,原来的二进制字符串也是补码)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b (将b转成int)
        int temp = b;

        //如果temp是正数我们还需要补高位,如果是最后一个字节，无需补高位
        if (flag) {
            //按位与256  例:1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }

        //返回的是temp对应的二进制补码
        String s = Integer.toBinaryString(temp);
        //System.out.println(s);
        if (flag) {
            //截取最后8位
            return s.substring(s.length() - 8);
        } else {
            return s;

        }
    }

    /**
     * 完成对压缩数据的解码
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.将压缩后的byte[]：[-88, -65, -56,...]转换为赫夫曼编码对应的二进制的字符串“10101000101111111100...”
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个byte
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串按照指定的赫夫曼编码进行解码
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //i先不动，让count移动，直到匹配到一个字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                //没有匹配到
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            //匹配到后，i直接移动到cout
            i += count;
        }
        //当for循环结束后，list中就存放了所有的字符  "i like like like java do you like a java"
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将使用赫夫曼编码压缩字符串方法封装起来
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return 使用赫夫曼编码压缩后的字符串对应的字节数组
     */
    public static byte[] huffmanZip(byte[] bytes) {

        List<Node> nodes = getNodes(bytes);
        //根据nodes创建的赫夫曼树
        Node rootNode = createHuffmanTree(nodes);
        //创建对应的赫夫曼编码(根据赫夫曼树)
        Map<Byte, String> huffmanCodes = getHuffmanCodes(rootNode);
        //根据对应的赫夫曼编码进行压缩
        return zip(bytes, huffmanCodes);
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表,返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        要处理的字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes[]
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        //System.out.println("赫夫曼编码压缩后的字符串: " + stringBuilder.toString() + "  长度：" + stringBuilder.length());

        //将 stringBuilder.toString() 转成 byte[]

        //统计返回byte[] huffmanCodeBytes 长度
        //一句话就是 int len = (stringBuilder.1ength() + 7) / 8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //用来存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        //用来记录 huffmanCodeBytes 下标
        int index = 0;
        //因为1个byte占8位,所以步长为8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            //不够8位
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte转成一个byte ,放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    /**
     * 生成赫夫曼树对应的赫夫曼编码
     * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node          赫夫曼树根节点
     * @param code          路径（左:0 ; 右:1）
     * @param stringBuilder 用于拼接路径
     */
    private static void getHuffmanCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            //判断当前node是叶子结点还是非叶子结点
            if (node.data == null) {
                //非叶子结点
                //向左递归（左:0）
                getHuffmanCodes(node.left, "0", stringBuilder2);
                //向右递归（右:1）
                getHuffmanCodes(node.right, "1", stringBuilder2);
            } else {
                //找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //重载 getHuffmanCodes
    private static Map<Byte, String> getHuffmanCodes(Node rootNode) {
        if (rootNode == null) {
            return null;
        }
        //处理左子树
        getHuffmanCodes(rootNode.left, "0", stringBuilder);
        //处理右子树
        getHuffmanCodes(rootNode.right, "1", stringBuilder);
        return huffmanCodes;

    }

    /**
     * 将byte[]的元素转换为Node并存入List
     *
     * @param bytes 接收字节数组
     * @return 返回的就是字节数组的List形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数- >map[key, value]
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.get(b);
            //Map还没有这个字符数据,第一次
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //根据nodes创建的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            //排序(从小到大)
            Collections.sort(nodes);

            //(1)取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的结点(二叉树)
            Node rightNode = nodes.get(1);
            //(3)构建一颗新的二叉树(没有data,只有权值)
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4)从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5)将parent加入到ArrayList
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    public static void preOrder(Node node) {
        if (node != null) {
            node.perOrder();
        } else {
            System.out.println("空树，无法遍历");
        }
    }
}

//创建Node , 有数据和权值
class Node implements Comparable<Node> {
    //存放数据(字符)本身，比如'a' => 97
    Byte data;
    //权值，表示字符出现的次数
    int weight;
    Node left;
    Node right;

    //前序遍历
    public void perOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.perOrder();
        }
        if (this.right != null) {
            this.right.perOrder();
        }
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}