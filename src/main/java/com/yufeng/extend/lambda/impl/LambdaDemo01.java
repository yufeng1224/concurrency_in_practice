package com.yufeng.extend.lambda.impl;


import com.yufeng.extend.lambda.MyPredicate;
import org.junit.Test;

import java.util.*;


/**
 * @description
 *      lambda代码演示(一)
 * @author yufeng
 * @create 2020-02-15
 */
public class LambdaDemo01 {

    List<Employee> employees = Arrays.asList(
            new Employee(101, "张三", 8, 3333),
            new Employee(102, "李四", 18, 6666),
            new Employee(103, "王五", 28, 7777),
            new Employee(104, "赵六", 38, 8888),
            new Employee(105, "田七", 48, 9999)
    );


    /** 常规的匿名内部类, 写法比容冗余 */
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(comparator);
    }


    /** 使用lambda表达式精简代码 */
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }


    /**
     * 需求1: 获取公司中员工年龄大于35的员工信息
     */
    public List<Employee> filterEmployeeAge(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getAge() >= 35) {
                list.add(emp);
            }
        }
        return list;
    }


    /** 需求1单元测试案例 */
    @Test
    public void test3() {
        List<Employee> list = filterEmployeeAge(employees);

        for (Employee e : list) {
            System.out.println(e);
        }
    }


    /**
     * 需求2: 获取公司中员工工资大于5000的员工信息
     */
    public List<Employee> filterEmployeeSalary(List<Employee> emps) {
        List<Employee> list = new ArrayList<>();
        for (Employee emp : emps) {
            if (emp.getSalary() >= 5000) {
                list.add(emp);
            }
        }
        return list;
    }


    /** 需求2单元测试案例 */
    @Test
    public void test4() {
        List<Employee> list = filterEmployeeSalary(employees);

        for (Employee e : list) {
            System.out.println(e);
        }
    }


    /** 优化方式一: 策略设计模式 */
    public List<Employee> filterEmployee(List<Employee> emps, MyPredicate<Employee> mp) {
        List<Employee> list = new ArrayList<>();

        for (Employee e : emps) {
            if (mp.test(e)) {
                list.add(e);
            }
        }
        return list;
    }


    /** 策略设计模式单元测试案例 */
    @Test
    public void test5() {
        List<Employee> list = filterEmployee(employees, new FilterEmployeeForAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("--------------------");

        List<Employee> list1 = filterEmployee(employees, new FilterEmployeeForSalary());
        for (Employee employee : list1) {
            System.out.println(employee);
        }
    }


    /** 优化方式二: 使用匿名内部类(在方式一上面进一步做优化)*/
    @Test
    public void test6() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getId() <= 103;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }


    /** 优化方式三: 匿名内部类进一步转化为Lambda表达式 */
    @Test
    public void test7() {
        List<Employee> list = filterEmployee(employees, employee -> employee.getSalary() > 6000);
        list.forEach(System.out::println);

        System.out.println("--------------------");

        List<Employee> list1 = filterEmployee(employees, employee -> employee.getAge() <= 35);
        list1.forEach(System.out::println);
    }


    /** 优化方式四: Lambda表达式进一步转换为Stream API **/
    @Test
    public void test8() {
        employees.stream().filter(employee -> employee.getAge() >= 40).forEach(System.out::println);

        System.out.println("--------------------");

        employees.stream().map(Employee::getName).limit(3).sorted().forEach(System.out::println);
    }
}
