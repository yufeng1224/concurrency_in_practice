package com.yufeng.extend.lambda.impl;

import com.yufeng.extend.lambda.MyPredicate;

/**
 * @author yufeng
 * @create 2020-02-15
 */
public class FilterEmployeeForSalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
