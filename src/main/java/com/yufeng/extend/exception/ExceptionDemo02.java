package com.yufeng.extend.exception;

/**
 * @description
 *      异常代码演示(二)
 * @author yufeng
 * @create 2020-02-18
 */
public class ExceptionDemo02 {

    public static void main(String[] args) {
        try {
            int[] data = new int[]{100, 99, 98};
            System.out.println(getByIndex(3, data));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static int getByIndex(int index, int[] data) {
        if (index < 0 || index >= data.length){
            throw new ArrayIndexOutOfBoundsException("数组下标越界");
        }
        return data[index];
    }

}
