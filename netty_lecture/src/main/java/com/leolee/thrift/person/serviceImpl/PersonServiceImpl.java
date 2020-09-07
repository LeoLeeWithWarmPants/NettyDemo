package com.leolee.thrift.person.serviceImpl;

import com.leolee.thrift.person.DataException;
import com.leolee.thrift.person.Person;
import com.leolee.thrift.person.PersonService;
import org.apache.thrift.TException;

/**
 * @ClassName PersonServiceImpl
 * @Description: 实现在data.thrift中定义的接口
 * @Author LeoLee
 * @Date 2020/9/6
 * @Version V1.0
 **/
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByName(String name) throws DataException, TException {//此处

        System.out.println("Got client param:" + name);

        Person person = new Person();
        person.setAge(25);
        person.setName(name);
        person.setMarried(false);

        System.out.println("getPersonByName调用成功");
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {

        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
        System.out.println("savePerson调用成功");
    }
}
