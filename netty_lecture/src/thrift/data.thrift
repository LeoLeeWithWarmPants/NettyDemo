//java的包名
namespace java com.leolee.thrift.person

//定义数据类型的别名
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {

    1: optional String name,
    2: optional int age,
    3: optional boolean married

}


//自定义异常
exception DataException {

    1: optional String msg,
    2: optional String callStack,
    3: optional String data

}

//接口
service PersonService {

    Person getPersonByName(1: required String name) throws (1: DataException dataException),

    void savePerson(1: required Person person) throws (1: DataException dataException),

}
//编译命令：thrift --gen java src/thrift/data.thrift
