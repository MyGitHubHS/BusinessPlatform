package utils;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @description: 基于JPA持久框架的简单工具
 * @company: yaorange
 * @author: 土匪
 * @version: 1.0
 * @create: 22-10-20 22:43:03
 */
@Repository
public class JPADaoUtils {

    @PersistenceContext
    private EntityManager entityManager;

   /**
   * @description:根据id获取对象
   * @param tClass 类型class文件
   * @param id 需要查询你的id
   * @return T
   * @author 土匪
   * @date 2019/10/20 22:55
   * @exception
   */
    public <T>T getOne(Class<T> tClass ,String id){
        return (T) entityManager.find(tClass, id);
    }

    /**
    * @description: 添加对象基本操作
    * @param values
    * @return void
    * @author 土匪
    * @date 2019/10/20 23:45
    * @exception
    */
    public void add(Object... values) {
        for(int i=0;i<values.length;i++){
            entityManager.persist(values[i]);
        }
    }



    /**
    * @description:删除单一对象
    * @param t
    * @return void
    * @author 土匪
    * @date 2019/10/20 22:59
    * @exception
    */
    public <T>void delete(T t) {
        entityManager.remove(t);
    }

    /**
    * @description:根据id数组进行删除
    * @param t  删除对象的class文件
    * @param id 字符串数组
    * @return void
    * @author 土匪
    * @date 2019/10/20 23:32
    * @exception
    */
    public <T>void delete(Class<T> t,String[] id) {
        for(String string: id){
            deleteById(t,string);
        }
    }

    /**
    * @description:根据id进行删除
    * @param t 删除类型的class文件
    * @param id 删除对象对应的id
    * @return void
    * @author 土匪
    * @date 2019/10/20 23:37
    * @exception
    */
    public <T>void deleteById(Class<T> t,String id) {
        T t1=getOne(t, id);
        if ( t1!= null) {
            delete(t1);
        }
    }


    /**
    * @description:更新操作
    * @param t 对象
    * @param id 给对象id
    * @return void
    * @author 土匪
    * @date 2019/10/20 23:06
    * @exception
    */
    public <T>void updata(T t,String id){
        T t1= (T) getOne(t.getClass(),id);
        //反射分别获取两个对象的所有字段
        Field[] fields = t.getClass().getDeclaredFields();
        Field[] fields1 = t1.getClass().getDeclaredFields();
        //遍历将t对应字段的值赋给t1
       for(int i=0;i<fields.length;i++){
           fields[i].setAccessible(true);
           fields1[i].setAccessible(true);
           try {
               //当我们单个属性时，其类型为字符串且属性为空时，其他属性不进行更新（也就是说字符串属性不能置为空）
               if(fields[i].get(t) instanceof String|| fields[i].get(t)==null) {

               }else{

                   fields1[i].set(t1, fields[i].get(t));
               }
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
       }
        entityManager.merge(t1);
    }


    /**
    * @description:基于Mysql数据库的分页查询
    * @param pageNo 当前查询页码
    * @param pageSize 每页显示多少个
    * @param tClass 查询的类型
    * @param values 第一个数据传入模糊查询的字符串，第二个参数传入查询依据(例名)
    * @return utils.Pagination
    * @author 土匪
    * @date 2019/10/21 0:14
    * @exception
    */
    public <T>Pagination getPageByHql(Integer pageNo, Integer pageSize,Class<T> tClass,Object... values) {
        String hqlCount=null;
        String hql=null;
        if (values.length==0||values[0]==null||values[0].equals("")){
            //获取总条数
            hqlCount ="select count(*) from "+tClass.getSimpleName()+" d";
            System.out.println(hqlCount);
        }else{
            //根据名字查询的条数
            hqlCount ="select count(*) from "+tClass.getSimpleName()+" d where d."+values[1]+" like '%"+values[0]+"%'";
        }
        Long count= (Long) this.entityManager.createQuery(hqlCount).getSingleResult();
        //创建分页对象,指定当前的页码，每页显示条数，数据总条数（查询）
        Pagination pagination = new Pagination(pageNo, pageSize, count.intValue());
        if (values.length==0||values[0]==null||values[0].equals("")){
            //获取当前分页的内容
            hql = "select d from "+tClass.getSimpleName()+" d";
        }else {
            hql="select d from "+tClass.getSimpleName()+" d where d."+values[1]+" like '%"+values[0]+"%'";
        }

        Query query = this.entityManager.createQuery(hql);
        //设置开始位置:(pageNo - 1) * pageSize;
        query.setFirstResult(pagination.getFirstResult());
        //设置每次查询的条数
        query.setMaxResults(pageSize);
        List<T> list = query.getResultList();
        //将分页内容设置给分页对象
        pagination.setList(list);
        return pagination;
    }

}
